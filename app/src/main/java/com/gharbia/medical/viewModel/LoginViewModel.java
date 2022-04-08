package com.gharbia.medical.viewModel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.ForgetPasswordActivity;
import com.gharbia.medical.Activities.LoginActivity;
import com.gharbia.medical.Activities.MainActivity;
import com.gharbia.medical.Activities.RegisterActivity;
import com.gharbia.medical.DataModel.LoginRequest;
import com.gharbia.medical.DataModel.LoginResponse;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.Utilities.Preferences;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class LoginViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    LoginActivity activity;
    AlertDialog alertDialog;
    private final CompositeSubscription mSubscriptions = new CompositeSubscription();
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
/*    String[] PERMISSIONS = {
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };*/
    public LoginViewModel(LoginActivity activity) {
        this.activity = activity;


        activity.binding.backFrame.setOnClickListener(view -> activity.finish());

        activity.binding.signUp.setOnClickListener(view -> {
            Intent intent = new Intent(activity, RegisterActivity.class);
            activity.startActivity(intent);
        });

        activity.binding.forgetPassword.setOnClickListener(view -> {
            Intent intent = new Intent(activity, ForgetPasswordActivity.class);
            activity.startActivity(intent);
        });

        activity.binding.login.setOnClickListener(view -> {
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
        });
    }

    public void back(){
        activity.finish();
    }

    public void login(){
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    public void register(){

    }

    public void Validate(){

        Constant.hideKeyboard(activity);
        if (!hasPermissions(activity, PERMISSIONS)) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS, PERMISSION_ALL);
        }else {
            if (activity.binding.phoneTxt.getText().toString().equals("") ||
                    activity.binding.passwordTxt.getText().toString().equals("")){
                Constant.showErrorDialog(activity.getString(R.string.allrequired),activity);
            }else {
                String phone = activity.binding.phoneTxt.getText().toString();
                String password = activity.binding.passwordTxt.getText().toString();

                if (Constant.CheckInternet(activity))
                    CanLogin(phone,password);
            }
        }
    }

    public static boolean hasPermissions(Context context, String[] permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void CanLogin(String phone,String password) {
        showLoadingDialog(activity);
        LoginRequest loginRequest = new LoginRequest(phone.trim(),password.trim());
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .Login(loginRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));

    }

    private void handleResponse(LoginResponse loginResponse) {
        alertDialog.dismiss();
        Preferences.saveToken(activity,loginResponse.getData().getToken());
        Intent intent = new Intent(activity, MainActivity.class);
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