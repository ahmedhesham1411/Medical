package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.ContactUsActivity;
import com.gharbia.medical.Adapter.SliderAdapter;
import com.gharbia.medical.Adapter.SliderAdapterqqq;
import com.gharbia.medical.DataModel.ContactUsResponse;
import com.gharbia.medical.DataModel.SliderResponse;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.google.android.gms.maps.MapsInitializer;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ContactUsViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    ContactUsActivity activity;
    AlertDialog alertDialog;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    SliderAdapterqqq adapter;
    String faceBook,whatsApp,instagram,youtube,email,phone,web;

    public ContactUsViewModel(ContactUsActivity activity) {
        this.activity = activity;

        GetDetails();
        GetSlider();
    }

    public void back(){
        activity.finish();
    }

    public void GetDetails() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .GetContactUs()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSlider, this::handleErrorSlider));
    }

    private void handleResponseSlider(ContactUsResponse contactUsResponse) {
        alertDialog.dismiss();
        email=contactUsResponse.getData().getEmail();
        phone=contactUsResponse.getData().getPhoneNumber();
        web=contactUsResponse.getData().getWebsiteLink();
        activity.binding.address2.setText(contactUsResponse.getData().getAddress());
        activity.binding.address2.setSelected(true);
        activity.binding.phone.setText(contactUsResponse.getData().getPhoneNumber());
        activity.binding.email.setText(contactUsResponse.getData().getEmail());
        activity.binding.phone2.setText(contactUsResponse.getData().getPhoneNumber());
        activity.binding.address.setText(contactUsResponse.getData().getAddress());
        activity.binding.web.setText(contactUsResponse.getData().getWebsiteLink());

        faceBook = contactUsResponse.getData().getFacebook();
        whatsApp = contactUsResponse.getData().getPhoneNumber();
        instagram = contactUsResponse.getData().getInstgram();
        youtube = contactUsResponse.getData().getYoutube();
        activity.binding.whatsapp.setText(contactUsResponse.getData().getPhoneNumber());
        activity.binding.facebook.setText(contactUsResponse.getData().getFacebook());
        activity.binding.instagram.setText(contactUsResponse.getData().getInstgram());
        activity.binding.telegram.setText(contactUsResponse.getData().getPhoneNumber());
        activity.binding.twitter.setText(contactUsResponse.getData().getLinkedIn());
        activity.binding.location.setText(contactUsResponse.getData().getAddress());
        activity.lat = contactUsResponse.getData().getLatitude();
        activity.lng = contactUsResponse.getData().getLongitude();
        activity.binding.map.getMapAsync(activity);
        activity.binding.map.onResume();

        MapsInitializer.initialize(activity);
    }

    private void handleErrorSlider(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,activity);
    }

    public void GetSlider() {
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .GetContactUsSlider()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSlider2, this::handleErrorSlider2));
    }

    private void handleResponseSlider2(SliderResponse sliderResponse) {
        initSlider(sliderResponse);
    }

    private void handleErrorSlider2(Throwable throwable) {
        Constant.handleErrors(throwable,activity);
    }

    public void initSlider(SliderResponse sliderResponse){
        List<String> images = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for (int i=0;i<sliderResponse.getData().size();i++){
            images.add(sliderResponse.getData().get(i).getImageUrl());
            names.add(sliderResponse.getData().get(i).getName());
        }
        activity.binding.imageSlider.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        adapter = new SliderAdapterqqq(activity,images,names);
        activity.binding.imageSlider.setSliderAdapter(adapter);
        activity.binding.imageSlider.setCircularHandlerEnabled(true);
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
                if (!url.contains("http")){
                    Uri uri = Uri.parse("http://"+url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    activity.startActivity(intent);
                }else {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    activity.startActivity(intent);
                }

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

    public void website() {
        goToLink(activity, web);
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
        goToLink(activity, instagram);
    }

    public void youTube() {
        goToLink(activity, youtube);
    }

    public void openAddress() {
        goToLocationOnMap(activity, activity.lat,activity.lng);
    }

    public void openEmail() {
        sendEmail(activity, email);
    }

    public void call() {
        goTodialPhoneNumber(activity, phone);
    }

    public static void goToLocationOnMap(Activity activity, double lat, double lng) {
        if (lat != -99999 && lng != -99999) {
            String strUri = "http://maps.google.com/maps?q=loc:" + lat + "," + lng + " (" + "Location" + ")";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(strUri));
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
            activity.startActivity(intent);
        }
    }

    public static void sendEmail(Activity activity, String email) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        i.putExtra(Intent.EXTRA_SUBJECT, "");
        i.setPackage("com.google.android.gm");
        i.putExtra(Intent.EXTRA_TEXT, "");
        try {
            activity.startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(activity, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
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
}