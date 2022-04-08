package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.DataModel.ListOfApp;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.Utilities.Preferences;
import com.gharbia.medical.fragment.PreviousAppointmentsFragment;

import java.util.Locale;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class PreviousAppointmentsViewModel extends ViewModel {

    PreviousAppointmentsFragment fragment;
    @SuppressLint("StaticFieldLeak")
    Activity activity;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    AlertDialog alertDialog;

    public PreviousAppointmentsViewModel(PreviousAppointmentsFragment fragment) {
        this.fragment = fragment;
        activity = fragment.getActivity();

        GetDetails();
    }

    public void GetDetails() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Preferences.GetToken(activity))
                .GetPreviousAppointments(Locale.getDefault().getLanguage())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSlider2, this::handleErrorSlider2));
    }

    private void handleResponseSlider2(ListOfApp listOfApp) {
        alertDialog.dismiss();
        if (listOfApp.getData().size()==0){
            fragment.binding.noData.setVisibility(View.VISIBLE);
            fragment.binding.recycler.setVisibility(View.GONE);
        }else {
            fragment.binding.noData.setVisibility(View.GONE);
            fragment.binding.recycler.setVisibility(View.VISIBLE);
            fragment.initRec(listOfApp);
        }
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

    public void back(){
        activity.finish();
    }
}