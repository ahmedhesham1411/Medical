package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.CategoryResultActivity;
import com.gharbia.medical.Activities.SearchActivity;
import com.gharbia.medical.DataModel.ClinicsDataModel;
import com.gharbia.medical.DataModel.ClinicsList;
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

public class CategoryResultViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    CategoryResultActivity activity;
    public SortBottomSheet sortBottomSheet;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    AlertDialog alertDialog;
    public boolean isLoading = false;
    public int lastPage = 1;
    List<ClinicsDataModel> clinicsDataModels;

    public CategoryResultViewModel(CategoryResultActivity activity) {
        this.activity = activity;
        clinicsDataModels = new ArrayList<>();
        if (activity.id==1)
            GetClinics();

    }

    public void back(){
        activity.finish();
    }

    public void sort(){
        sortBottomSheet = new SortBottomSheet(activity, activity.line,activity);
        sortBottomSheet.show(activity.getSupportFragmentManager(), "tag");
    }

    public void search(){
        Intent intent = new Intent(activity, SearchActivity.class);
        intent.putExtra("id","1");
        activity.startActivity(intent);
    }

    public void GetClinics() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .GetClinics(Locale.getDefault().getLanguage(),activity.pageIndex,activity.departmentId, Integer.parseInt(Preferences.GetCityId(activity)),true,10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSlider, this::handleErrorSlider));
    }

    private void handleResponseSlider(ClinicsList clinicsList) {
        clinicsDataModels.clear();
        clinicsDataModels.addAll(clinicsList.getData());
        GetClinics2();
    }

    private void handleErrorSlider(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,activity);
    }

    public void GetClinics2() {
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .GetClinics(Locale.getDefault().getLanguage(),activity.pageIndex,activity.departmentId, Integer.parseInt(Preferences.GetCityId(activity)),false,10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSlider2, this::handleErrorSlider2));
    }

    private void handleResponseSlider2(ClinicsList clinicsList) {
        alertDialog.dismiss();
        clinicsDataModels.addAll(clinicsList.getData());
        activity.updateRecycler(clinicsDataModels);
    }

    private void handleErrorSlider2(Throwable throwable) {
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
}