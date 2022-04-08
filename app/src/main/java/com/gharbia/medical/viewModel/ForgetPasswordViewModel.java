package com.gharbia.medical.viewModel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.ForgetPasswordActivity;
import com.gharbia.medical.Activities.ForgetPasswordVerificationActivity;
import com.gharbia.medical.DataModel.LoginResponse;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ForgetPasswordViewModel extends ViewModel {

    ForgetPasswordActivity activity;
    AlertDialog alertDialog, alertDialog1;
    String code2,phone;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    public ForgetPasswordViewModel(ForgetPasswordActivity activity) {
        this.activity = activity;
    }

    public void back(){
        activity.finish();
    }

    public void code(){
        Intent intent = new Intent(activity, ForgetPasswordVerificationActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public void Validate(){
        Constant.hideKeyboard(activity);
        if (activity.binding.phoneTxt.getText().toString().equals("")){
            Constant.showErrorDialog(activity.getString(R.string.allrequired),activity);
        }else {
            phone = activity.binding.phoneTxt.getText().toString();
            if (Constant.CheckInternet(activity))
                Forget(phone);
        }
    }

    public void Forget(String phone) {
        //showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .ForgetPassword(phone)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(LoginResponse loginResponse) {
        //alertDialog.dismiss();
        Intent intent = new Intent(activity, ForgetPasswordVerificationActivity.class);
        intent.putExtra("phone",phone);
        activity.startActivity(intent);
        activity.finish();
    }

    private void handleError(Throwable throwable) {
        //alertDialog.dismiss();
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