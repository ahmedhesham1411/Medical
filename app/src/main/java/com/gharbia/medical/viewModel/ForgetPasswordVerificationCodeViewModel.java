package com.gharbia.medical.viewModel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.ForgetPasswordVerificationActivity;
import com.gharbia.medical.Activities.LoginActivity;
import com.gharbia.medical.Activities.ResetPasswordActivity;
import com.gharbia.medical.DataModel.LoginResponse;
import com.gharbia.medical.DataModel.VerificationRequest;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ForgetPasswordVerificationCodeViewModel extends ViewModel {

    ForgetPasswordVerificationActivity activity;
    public ObservableField<String> num1 = new ObservableField<>("");
    public ObservableField<String> num2 = new ObservableField<>("");
    public ObservableField<String> num3 = new ObservableField<>("");
    public ObservableField<String> num4 = new ObservableField<>("");
    public ObservableField<String> num5 = new ObservableField<>("");
    public ObservableField<String> codee = new ObservableField<>("");
    AlertDialog alertDialog, alertDialog1;
    String code2,phone;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();


    public ForgetPasswordVerificationCodeViewModel(ForgetPasswordVerificationActivity activity) {
        this.activity = activity;
        phone = activity.getIntent().getStringExtra("phone");

    }

    public void back(){
        activity.finish();
    }

    public void logIn(){
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public void Validate(){
        Constant.hideKeyboard(activity);
        if (num1.get().equals("") ||
                num2.get().equals("")||
                num3.get().equals("")||
                num4.get().equals("")){
            Constant.showErrorDialog(activity.getString(R.string.allrequired),activity);
        }else {
            code2 = num1.get()+num2.get()+num3.get()+num4.get();
            if (Constant.CheckInternet(activity))
                Verify(code2);
        }
    }

    private void Verify(String code) {
        //showLoadingDialog(activity);
        VerificationRequest verificationRequest = new VerificationRequest(phone,Integer.parseInt(code));
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .Verify(verificationRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));

    }

    private void handleResponse(LoginResponse loginResponse) {
        //alertDialog.dismiss();
        Intent intent = new Intent(activity, ResetPasswordActivity.class);
        intent.putExtra("token",loginResponse.getData().getToken());
        activity.startActivity(intent);
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