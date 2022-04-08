package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.ItemResultAnalysisActivity;
import com.gharbia.medical.Activities.MainActivity;
import com.gharbia.medical.Activities.PhotoViewActivity;
import com.gharbia.medical.Adapter.SliderItemAdapter;
import com.gharbia.medical.DataModel.ClinicProfileResponse;
import com.gharbia.medical.DataModel.ReviewResponse;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ItemResultAnalysisViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    ItemResultAnalysisActivity activity;
    SliderItemAdapter adapter;
    public ObservableField<Boolean> isReviews = new ObservableField<>(false);
    AlertDialog alertDialog;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    String faceBook,youTube,whatsApp,instagram,webSite,whatsappNumber,phone,phone2,img;


    public ItemResultAnalysisViewModel(ItemResultAnalysisActivity activity) {
        this.activity = activity;

        GetDetails();

    }

    public void back(){
        activity.finish();
    }

    public void showReviews(int num){
        if (num==1)
            isReviews.set(false);
        else
            isReviews.set(true);
    }

    public void GetDetails() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .GetAnalysisDetails(activity.clinicId,Locale.getDefault().getLanguage())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSlider, this::handleErrorSlider));
    }

    private void handleResponseSlider(ClinicProfileResponse clinicProfileResponse) {
        alertDialog.dismiss();
        activity.initRecImages(clinicProfileResponse.getData().getMedicalImages());
        activity.binding.nameTop.setText(clinicProfileResponse.getData().getName());
        activity.binding.name.setText(clinicProfileResponse.getData().getDrName());
        activity.binding.disc.setText(clinicProfileResponse.getData().getDescription());
        activity.binding.doctorPhone.setText(clinicProfileResponse.getData().getDrPhone());
        activity.binding.clinicPhone.setText(clinicProfileResponse.getData().getMedicalProfilePhone());
        activity.binding.price.setText(String.valueOf(clinicProfileResponse.getData().getPrice()));
        activity.binding.rating.setRating(clinicProfileResponse.getData().getRate());
        faceBook = clinicProfileResponse.getData().getFacebookLink();
        youTube = clinicProfileResponse.getData().getYouTubeLink();
        whatsApp = clinicProfileResponse.getData().getWhatsappPhone();
        instagram = clinicProfileResponse.getData().getInstgramLink();
        webSite = clinicProfileResponse.getData().getWebSiteLink();
        whatsappNumber = clinicProfileResponse.getData().getWhatsappPhone();
        phone = clinicProfileResponse.getData().getDrPhone();
        phone2 = clinicProfileResponse.getData().getMedicalProfilePhone();
        initSlider(clinicProfileResponse.getData().getMedicalImages());
        Picasso.get().load(Constant.BASEURL+clinicProfileResponse.getData().getImageUrl()).placeholder(activity.getDrawable(R.mipmap.ic_launcher11)).into(activity.binding.img);
        img = clinicProfileResponse.getData().getImageUrl();
        activity.initRecAddresses(clinicProfileResponse.getData().getAddresses());
        //activity.initRecycler(clinicProfileResponse.getData().getMedicalDepartments());
        GetReviews();
    }

    private void handleErrorSlider(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,activity);
    }

    public void GetReviews() {
        //showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .GetReviews(activity.clinicId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSliders, this::handleErrorSliders));
    }

    private void handleResponseSliders(ReviewResponse reviewResponse) {
        //alertDialog.dismiss();
        activity.initRecReviews(reviewResponse);
        //activity.initRecReviews();
    }

    private void handleErrorSliders(Throwable throwable) {
        //alertDialog.dismiss();
        Constant.handleErrors(throwable,activity);
    }

    public void initSlider(List<String> images){
        activity.binding.imageSlider.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        adapter = new SliderItemAdapter(activity,images);
        activity.binding.imageSlider.setSliderAdapter(adapter);
        activity.binding.imageSlider.setCircularHandlerEnabled(true);
    }

    public void faceBook() {
        goToLink(activity, faceBook);
    }

    public void youTube() {
        goToLink(activity, faceBook);
    }

    public void instagram() {
        goToLink(activity, faceBook);
    }

    public void webSite() {
        goToLink(activity, faceBook);
    }

    public void whatsapp() {
        goTowhatsApp(activity, whatsappNumber);
    }

    public void call() {
        goTodialPhoneNumber(activity, phone);
    }

    public void call2() {
        goTodialPhoneNumber(activity, phone2);
    }

    public void bookSuccess(){
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.book_success_dialog, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog = builder.create();
        AppCompatTextView addToCalender;
        addToCalender = view.findViewById(R.id.book);
        addToCalender.setOnClickListener(view1 -> {
            Intent intent = new Intent(activity, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        });
        alertDialog.setCancelable(true);
        alertDialog.show();

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

    public static void goTowhatsApp(Activity activity, String smsNumber) {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://api.whatsapp.com/send?phone=+2"+smsNumber+"&text=")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void goTodialPhoneNumber(Activity currentActivity, String phoneNumber) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + mapNumber(phoneNumber)));
            if (intent.resolveActivity(currentActivity.getPackageManager()) != null) {
                currentActivity.startActivity(intent);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String mapNumber(String number) {
        String englishNumber;
        englishNumber = number.replace("٠", "0").replace("١", "1")
                .replace("٢", "2").replace("٣", "3")
                .replace("٤", "4").replace("٥", "5")
                .replace("٦", "6").replace("٧", "7")
                .replace("٨", "8").replace("٩", "9");
        return englishNumber;
    }

    public static void goToLink(Activity activity, String url) {
        try {
            if (url != null && !url.isEmpty()) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
            }else
                Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void aaa(){
        Bundle bundle = new Bundle();
        bundle.putString("img", img);
        Intent intent = new Intent(activity, PhotoViewActivity.class);
        intent.putExtra("data",bundle);
        activity.startActivity(intent);
    }
}