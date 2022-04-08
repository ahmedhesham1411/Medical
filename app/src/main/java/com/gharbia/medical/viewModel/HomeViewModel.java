package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.AnalysisActivity;
import com.gharbia.medical.Activities.ClinicResultActivity;
import com.gharbia.medical.Activities.HospitalsActivity;
import com.gharbia.medical.Activities.MedicalCentersActivity;
import com.gharbia.medical.Activities.PharmaciesActivity;
import com.gharbia.medical.Activities.XRayActivity;
import com.gharbia.medical.Adapter.SliderAdapter;
import com.gharbia.medical.DataModel.CitiesResponse;
import com.gharbia.medical.DataModel.ContactUsResponse;
import com.gharbia.medical.DataModel.SliderResponse;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.Utilities.LocaleManeger;
import com.gharbia.medical.Utilities.Preferences;
import com.gharbia.medical.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.gharbia.medical.Utilities.LocaleManeger.ARABIC;
import static com.gharbia.medical.Utilities.LocaleManeger.setNewLocale;

public class HomeViewModel extends ViewModel {

    HomeFragment fragment;
    @SuppressLint("StaticFieldLeak")
    Activity activity;
    SliderAdapter adapter;
    List<Integer> images = new ArrayList<>();
    public List<String> cityList = new ArrayList<>();
    public List<String> cityIdList = new ArrayList<>();
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    AlertDialog alertDialog;
    public String city,whatsApp;

    public HomeViewModel(HomeFragment fragment) {
        this.fragment = fragment;
        activity = fragment.getActivity();

        images.add(R.drawable.slider_test);
        images.add(R.drawable.slider_test);
        images.add(R.drawable.slider_test);
        images.add(R.drawable.slider_test);
        images.add(R.drawable.slider_test);
        if (Locale.getDefault().getLanguage().equals("en"))
            fragment.binding.lang2.setText(activity.getString(R.string.lang));
        fragment.binding.imageSliderHome.setEnabled(true);
        GetCities();
        GetSlider();
        GetDetails();
        //Utils.runAnimation2(fragment.binding.itemsLay, 2);
    }

    public void whatsapp() {
        goTowhatsApp(activity, whatsApp);
    }

    public static void goTowhatsApp(Activity activity, String smsNumber) {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://api.whatsapp.com/send?phone=+2"+smsNumber+"&text=")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GoToClinics(int id){
        Intent intent = new Intent(activity, ClinicResultActivity.class);
        intent.putExtra("id",id);
        activity.startActivity(intent);
    }

    public void GoToHospitals(int id){
        Intent intent = new Intent(activity, HospitalsActivity.class);
        intent.putExtra("id",id);
        activity.startActivity(intent);
    }

    public void GoToMedicalCenters(int id){
        Intent intent = new Intent(activity, MedicalCentersActivity.class);
        intent.putExtra("id",id);
        activity.startActivity(intent);
    }

    public void GoToAnalysis(int id){
        Intent intent = new Intent(activity, AnalysisActivity.class);
        intent.putExtra("id",id);
        activity.startActivity(intent);
    }

    public void GoToXRay(int id){
        Intent intent = new Intent(activity, XRayActivity.class);
        intent.putExtra("id",id);
        activity.startActivity(intent);
    }

    public void GoToPharmacies(int id){
        Intent intent = new Intent(activity, PharmaciesActivity.class);
        intent.putExtra("id",id);
        activity.startActivity(intent);
    }

    public void initSlider(SliderResponse sliderResponse){
        List<String> images = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for (int i=0;i<sliderResponse.getData().size();i++){
            images.add(sliderResponse.getData().get(i).getImageUrl());
            names.add(sliderResponse.getData().get(i).getName());
        }
        fragment.binding.imageSliderHome.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        adapter = new SliderAdapter(activity,images,names);
        fragment.binding.imageSliderHome.setSliderAdapter(adapter);
        fragment.binding.imageSliderHome.setCircularHandlerEnabled(true);
    }

    private void GetCities() {
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .GetCities()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));

    }

    private void handleResponse(CitiesResponse citiesResponse) {
        cityList.clear();
        cityIdList.clear();
        cityList.add(activity.getString(R.string.city));
        cityIdList.add("0");
/*        if (Preferences.GetCityName(activity).equals("noName")){
            cityList.add(activity.getString(R.string.your_loc));
            cityIdList.add("0");
        }else {
            cityList.add(Preferences.GetCityName(activity));
            cityIdList.add(Preferences.GetCityId(activity));
        }*/

        try {
            for (int i=0;i<citiesResponse.getData().size();i++) {
                cityList.add(citiesResponse.getData().get(i).getName());
                cityIdList.add(String.valueOf(citiesResponse.getData().get(i).getId()));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        fragment.adapterCity.notifyDataSetChanged();
        //Preferences.saveCityId(activity,cityIdList.get(0));
    }

    private void handleError(Throwable throwable) {
        Constant.handleErrors(throwable,activity);
    }

    private void GetSlider() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .GetSlider()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSlider, this::handleErrorSlider));
    }
    private void handleResponseSlider(SliderResponse sliderResponse) {
        alertDialog.dismiss();
        initSlider(sliderResponse);
    }

    private void handleErrorSlider(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,activity);
    }

    public void GetDetails() {
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .GetContactUs()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSlider2, this::handleErrorSlider2));
    }

    private void handleResponseSlider2(ContactUsResponse contactUsResponse) {
        whatsApp = contactUsResponse.getData().getPhoneNumber();
        Preferences.saveWhatsApp(activity,whatsApp);
    }

    private void handleErrorSlider2(Throwable throwable) {
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void changeLanguage(){
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.lang_alert, null, false);
        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog = builder.create();
        AppCompatTextView arabic,english;
        arabic = view.findViewById(R.id.arabic);
        english = view.findViewById(R.id.english);
        if (Locale.getDefault().getLanguage().equals("en")){
            english.setBackgroundDrawable(activity.getDrawable(R.drawable.rounded_blue));
            arabic.setBackgroundDrawable(activity.getDrawable(R.drawable.rounded_grey));
            english.setTextColor(activity.getColor(R.color.white));
            arabic.setTextColor(activity.getColor(R.color.blue));
        }else {
            english.setBackgroundDrawable(activity.getDrawable(R.drawable.rounded_grey));
            arabic.setBackgroundDrawable(activity.getDrawable(R.drawable.rounded_blue));
            english.setTextColor(activity.getColor(R.color.blue));
            arabic.setTextColor(activity.getColor(R.color.white));
        }
        arabic.setOnClickListener(view12 -> {
            alertDialog.dismiss();
            if (Locale.getDefault().getLanguage().equals("ar")){}else {
                setNewLocale(activity,ARABIC);
                Intent i = activity.getBaseContext().getPackageManager().getLaunchIntentForPackage(activity.getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(i);
            }
        });
        english.setOnClickListener(view12 -> {
            alertDialog.dismiss();
            if (Locale.getDefault().getLanguage().equals("en")){}else {
                setNewLocale(activity, LocaleManeger.ENGLISH);
                Intent i = activity.getBaseContext().getPackageManager().getLaunchIntentForPackage(activity.getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(i);
            }
        });
        alertDialog.setCancelable(true);
        alertDialog.show();

    }

}