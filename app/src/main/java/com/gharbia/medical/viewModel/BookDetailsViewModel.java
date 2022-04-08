package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.BookDetailsActivity;
import com.gharbia.medical.Adapter.SliderAdapter;
import com.gharbia.medical.DataModel.BookDetailsResponse;
import com.gharbia.medical.DataModel.ContactUsResponse;
import com.gharbia.medical.DataModel.RateItemRequest;
import com.gharbia.medical.DataModel.ReviewResponse;
import com.gharbia.medical.DataModel.SliderResponse;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.Utilities.Preferences;
import com.gharbia.medical.Utilities.Utils;
import com.google.android.gms.maps.MapsInitializer;
import com.squareup.picasso.Picasso;

import java.util.Locale;
import java.util.Objects;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class BookDetailsViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    BookDetailsActivity activity;
    AlertDialog alertDialog;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    SliderAdapter adapter;
    String faceBook,whatsApp,instagram,youtube;
    int profileId = 0;
    BookDetailsResponse bookDetailsResponse;
    AlertDialog rateDialog;
    RatingBar ratingBar;
    AppCompatEditText rateTxt;

    public BookDetailsViewModel(BookDetailsActivity activity) {
        this.activity = activity;

        activity.bookId = activity.getIntent().getStringExtra("bookId");
/*        activity.status = activity.getIntent().getStringExtra("status");

        if (activity.status.equals("0")){
            activity.binding.review.setVisibility(View.VISIBLE);
            //activity.binding.cancel.setVisibility(View.VISIBLE);
        }else {
            activity.binding.review.setVisibility(View.GONE);
            //activity.binding.cancel.setVisibility(View.GONE);
        }*/
        GetDetails();
    }

    public void back(){
        activity.finish();
    }

    public void GetDetails() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Preferences.GetToken(activity))
                .GetBookDetails(Integer.parseInt(activity.bookId), Locale.getDefault().getLanguage())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSlider, this::handleErrorSlider));
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    private void handleResponseSlider(BookDetailsResponse bookDetailsResponse2) {
        bookDetailsResponse = bookDetailsResponse2;
        profileId = bookDetailsResponse2.getData().getMedicalId();
        if (bookDetailsResponse.getData().getStatus()==0){
            activity.binding.cancel.setVisibility(View.VISIBLE);
        }else {
            activity.binding.cancel.setVisibility(View.GONE);
        }
        if (bookDetailsResponse.getData().isIsreview()){
            activity.binding.review.setVisibility(View.GONE);
        }else {
            activity.binding.review.setVisibility(View.VISIBLE);
        }
        activity.binding.address2.setText(bookDetailsResponse2.getData().getAddress());
        activity.lat = bookDetailsResponse2.getData().getLatitude();
        activity.lng = bookDetailsResponse2.getData().getLongitude();
        activity.binding.map.getMapAsync(activity);
        activity.binding.map.onResume();

        MapsInitializer.initialize(activity);
        GetReviews();
    }

    private void handleErrorSlider(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,activity);
    }

    public void GetReviews() {
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .GetReviews(profileId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSliders, this::handleErrorSliders));
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    private void handleResponseSliders(ReviewResponse reviewResponse) {
        alertDialog.dismiss();
        Picasso.get().load(Constant.BASEURL+bookDetailsResponse.getData().getDrUrl()).placeholder(activity.getDrawable(R.mipmap.ic_launcher11)).into(activity.binding.userImage);
        activity.binding.name2.setText(String.valueOf(bookDetailsResponse.getData().getReviewsCount()));
        activity.binding.name.setText(bookDetailsResponse.getData().getDrName());
        activity.binding.degree.setText(bookDetailsResponse.getData().getMasterDegree());
        activity.binding.disc.setText(bookDetailsResponse.getData().getDescription());
        activity.initRecImages(bookDetailsResponse.getData().getSlider());
        activity.binding.price.setText(String.valueOf(bookDetailsResponse.getData().getCost()));
        activity.binding.status.setText(String.valueOf(bookDetailsResponse.getData().getStatues()));
        activity.binding.date.setText(bookDetailsResponse.getData().getDay() + " " + bookDetailsResponse.getData().getHour());
        activity.binding.name2.setText(String.valueOf(bookDetailsResponse.getData().getReviewsCount()));
        activity.initRec(reviewResponse);
        //activity.initRecReviews();
    }

    private void handleErrorSliders(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,activity);
    }

    public void showLoadingDialog(Context activity) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.loading, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.SheetDialog);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
    }

    public void showRateDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.rate_alert, null, false);
        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity,R.style.ErrorDialog);
        builder.setView(view);
        rateDialog = builder.create();
        LinearLayoutCompat rate = view.findViewById(R.id.rateee);
        rateTxt = view.findViewById(R.id.rate_txttt);
        ratingBar = view.findViewById(R.id.rating_bar);
        rateDialog.show();
        rateDialog.setCancelable(true);
        rate.setOnClickListener(v -> {
            if (!Objects.requireNonNull(rateTxt.getText()).toString().isEmpty()){
                RateItem();
            }else {
                Constant.showErrorDialog(activity.getString(R.string.allrequired),activity);
            }
        });

    }

    public void RateItem() {
        int aaa=(Math.round(ratingBar.getRating()));
        RateItemRequest rateItemRequest = new RateItemRequest(profileId, Objects.requireNonNull(rateTxt.getText()).toString(),aaa);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Preferences.GetToken(activity))
                .RateItem(rateItemRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponsedddd, this::handleErrordddd));
    }

    private void handleResponsedddd(ContactUsResponse responseModel) {
        rateDialog.dismiss();
        Utils.MyToastSuccess(activity,activity.getString(R.string.done));

    }

    private void handleErrordddd(Throwable throwable) {
        //rateDialog.dismiss();
        Constant.handleErrors(throwable,activity);
    }

    public void cancelOrder() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Preferences.GetToken(activity))
                .CancelOrder(Integer.parseInt(activity.bookId))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSlider2, this::handleErrorSlider2));
    }

    private void handleResponseSlider2(SliderResponse sliderResponse) {
        alertDialog.dismiss();
        Utils.MyToastSuccess(activity,activity.getString(R.string.done));
        activity.finish();
    }

    private void handleErrorSlider2(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,activity);
    }
}