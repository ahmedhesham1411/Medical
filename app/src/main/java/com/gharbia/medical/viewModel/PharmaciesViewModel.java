package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.AppointmentsActivity;
import com.gharbia.medical.Activities.ComplaintActivity;
import com.gharbia.medical.Activities.ContactUsActivity;
import com.gharbia.medical.Activities.LoginActivity;
import com.gharbia.medical.Activities.NotificationsActivity;
import com.gharbia.medical.Activities.PharmaciesActivity;
import com.gharbia.medical.Activities.ProfileActivity;
import com.gharbia.medical.Activities.RemaindersActivity;
import com.gharbia.medical.Activities.SearchActivity;
import com.gharbia.medical.Activities.SettingActivity;
import com.gharbia.medical.Activities.TermsAndConditionActivity;
import com.gharbia.medical.DataModel.HospitalsResponse;
import com.gharbia.medical.DataModel.LoginResponse;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.Utilities.Preferences;
import com.gharbia.medical.Utilities.SortBottomSheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class PharmaciesViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    PharmaciesActivity activity;
    AlertDialog alertDialog;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    LoginResponse loginResponse2;
    public SortBottomSheet sortBottomSheet;
    List<HospitalsResponse.HospitalsData> clinicsDataModels2;

    public PharmaciesViewModel(PharmaciesActivity activity) {
        this.activity = activity;
        clinicsDataModels2 = new ArrayList<>();
        GetAnalysis();
    }

    public void GetAnalysis() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Preferences.GetToken(activity))
                .GetPharmacies(Locale.getDefault().getLanguage(),activity.pageIndex,Integer.parseInt(Preferences.GetCityId(activity)),true)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(HospitalsResponse hospitalsResponse) {
        //alertDialog.dismiss();
        clinicsDataModels2.addAll(hospitalsResponse.getData());
        GetHospitals2();
    }

    private void handleError(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,activity);
    }

    public void GetHospitals2() {
        //showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Preferences.GetToken(activity))
                .GetPharmacies(Locale.getDefault().getLanguage(),activity.pageIndex,Integer.parseInt(Preferences.GetCityId(activity)),false)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse2, this::handleError2));
    }

    private void handleResponse2(HospitalsResponse hospitalsResponse) {
        alertDialog.dismiss();
        clinicsDataModels2.addAll(hospitalsResponse.getData());
        activity.updateRecycler(clinicsDataModels2);
    }

    private void handleError2(Throwable throwable) {
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

    public void back(){
        activity.finish();
    }

    public void sort(){
        sortBottomSheet = new SortBottomSheet(activity, activity.line,activity);
        sortBottomSheet.show(activity.getSupportFragmentManager(), "tag");
    }

    public void notifications(){
        Intent intent = new Intent(activity, NotificationsActivity.class);
        activity.startActivity(intent);
    }

    public void search(){
        Intent intent = new Intent(activity, SearchActivity.class);
        intent.putExtra("id","4");
        activity.startActivity(intent);
    }

    public void setting(){
        Intent intent = new Intent(activity, SettingActivity.class);
        activity.startActivity(intent);
    }

    public void appointments(){
        Intent intent = new Intent(activity, AppointmentsActivity.class);
        activity.startActivity(intent);
    }

    public void profile(){
        Intent intent = new Intent(activity, ProfileActivity.class);
        intent.putExtra("name",loginResponse2.getData().getUserName());
        intent.putExtra("email",loginResponse2.getData().getEmail());
        intent.putExtra("image",loginResponse2.getData().getImageUrl());
        intent.putExtra("phone",loginResponse2.getData().getPhone());
        activity.startActivity(intent);
    }

    public void reminders(){
        Intent intent = new Intent(activity, RemaindersActivity.class);
        activity.startActivity(intent);
    }

    public void complaint(){
        Intent intent = new Intent(activity, ComplaintActivity.class);
        activity.startActivity(intent);
    }

    public void terms(){
        Intent intent = new Intent(activity, TermsAndConditionActivity.class);
        activity.startActivity(intent);
    }

    public void contactUs(){
        Intent intent = new Intent(activity, ContactUsActivity.class);
        activity.startActivity(intent);
    }

    public void logOut(){
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.log_out_dialog, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog = builder.create();
        AppCompatTextView proceed,cancel;
        proceed = view.findViewById(R.id.proceed);
        cancel = view.findViewById(R.id.cancel);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                //preferences.saveToken(activity,"default");
                Intent intent = new Intent(activity, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
            }
        });
        cancel.setOnClickListener(view1 -> alertDialog.dismiss());
        alertDialog.setCancelable(true);
        alertDialog.show();

    }
}