package com.gharbia.medical.viewModel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.LoginActivity;
import com.gharbia.medical.Activities.ResetPasswordActivity;
import com.gharbia.medical.DataModel.LoginResponse;
import com.gharbia.medical.DataModel.ResetPasswordRequest;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ResetPasswordViewModel extends ViewModel {

    ResetPasswordActivity activity;
    AlertDialog alertDialog, alertDialog1;
    String code2,token;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    public ResetPasswordViewModel(ResetPasswordActivity activity) {
        this.activity = activity;
        token = activity.getIntent().getStringExtra("token");

    }

    public void back(){
        activity.finish();
    }

    public void confirm(){
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public void Validate(){
        Constant.hideKeyboard(activity);
        if (activity.binding.passwordTxt.equals("") || activity.binding.confirmPasswordTxt.equals("")){
            Constant.showErrorDialog(activity.getString(R.string.allrequired),activity);
        }else if (!activity.binding.passwordTxt.getText().toString().equals(activity.binding.confirmPasswordTxt.getText().toString())) {
                Constant.showErrorDialog(activity.getString(R.string.password_match),activity);
        }else {
            Change(activity.binding.passwordTxt.getText().toString());
        }
    }

    private void Change(String password) {
        showLoadingDialog(activity);
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest(password);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .ResetPassword(resetPasswordRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));

    }

    private void handleResponse(LoginResponse loginResponse) {
        alertDialog.dismiss();
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
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
}