package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.CitiesActivity;
import com.gharbia.medical.Adapter.SliderAdapter;
import com.gharbia.medical.DataModel.CitiesResponse;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class CitiesViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    CitiesActivity activity;
    AlertDialog alertDialog;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    SliderAdapter adapter;
    String faceBook,whatsApp,instagram,youtube;

    public CitiesViewModel(CitiesActivity activity) {
        this.activity = activity;

        GetDetails();
    }

    public void back(){
        activity.finish();
    }

    public void GetDetails() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .GetCities()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSlider, this::handleErrorSlider));
    }

    private void handleResponseSlider(CitiesResponse citiesResponse) {
        alertDialog.dismiss();
        activity.initRec(citiesResponse);

    }

    private void handleErrorSlider(Throwable throwable) {
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

    public void faceBook() {
        goToLink(activity, faceBook);
    }

    public static void goTowhatsApp(Activity activity, String smsNumber) {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://api.whatsapp.com/send?phone=+2"+smsNumber+"&text=")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void whatsapp() {
        goTowhatsApp(activity, whatsApp);
    }

    public void instagram() {
        goToLink(activity, faceBook);
    }

    public void youTube() {
        goToLink(activity, youtube);
    }
}