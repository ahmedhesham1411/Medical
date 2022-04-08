package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.CategoryResultActivity;
import com.gharbia.medical.Activities.ClinicResultActivity;
import com.gharbia.medical.Activities.SearchActivity;
import com.gharbia.medical.DataModel.DepartmentsResponse;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ClinicResultViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    ClinicResultActivity activity;
    AlertDialog alertDialog;
    public List<String> cityList = new ArrayList<>();
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    public ClinicResultViewModel(ClinicResultActivity activity) {
        this.activity = activity;

        cityList.add("city");
        cityList.add("city 1");
        cityList.add("city 2");
        cityList.add("city 3");
        cityList.add("city 4");
        cityList.add("city 5");

        GetDepartments();
    }

    public void back() {
        activity.finish();
    }

    public void goToResult() {
        Intent intent = new Intent(activity, CategoryResultActivity.class);
        activity.startActivity(intent);
    }

    public void GetDepartments() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .GetDepartments(Locale.getDefault().getLanguage(),0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(DepartmentsResponse departmentsResponse) {
        alertDialog.dismiss();
        activity.initRecycler(departmentsResponse);
    }

    private void handleError(Throwable throwable) {
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

    public void search(){
        Intent intent = new Intent(activity, SearchActivity.class);
        intent.putExtra("id","1");
        activity.startActivity(intent);
    }

}