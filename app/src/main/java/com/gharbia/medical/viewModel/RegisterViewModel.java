package com.gharbia.medical.viewModel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.LoginActivity;
import com.gharbia.medical.Activities.RegisterActivity;
import com.gharbia.medical.Activities.VerificationCodeActivity;
import com.gharbia.medical.DataModel.CitiesResponse;
import com.gharbia.medical.DataModel.RegisterRequest;
import com.gharbia.medical.DataModel.RegisterResponse;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.Utilities.Preferences;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class RegisterViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    RegisterActivity activity;
    AlertDialog alertDialog;
    public static final int IMAGE_REQUEST = 1;
    public String imageBase64 = "";
    public long imageBase642;
    private final CompositeSubscription mSubscriptions = new CompositeSubscription();
    String phone;
    int cityId = 0;

    public RegisterViewModel(RegisterActivity activity) {
        this.activity = activity;
        GetCities();
    }

    public void back(){
        activity.finish();
    }

    public void login(){
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    private void GetCities() {
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .GetCities()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse2, this::handleError2));

    }

    private void handleResponse2(CitiesResponse citiesResponse) {
        cityId = citiesResponse.getData().get(0).getId();
        Preferences.saveCityId(activity, String.valueOf(cityId));
    }

    private void handleError2(Throwable throwable) {
        Constant.handleErrors(throwable,activity);
    }

    public void openImage() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(activity,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(activity,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        }, 3);
            } else {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                activity.startActivityForResult(intent, IMAGE_REQUEST);
            }
        }
    }

    public void register(){

    }

    public void Validate(){
        Log.d("mybase",imageBase64);

        Constant.hideKeyboard(activity);
        if (Objects.requireNonNull(activity.binding.usernameText.getText()).toString().equals("") ||
                Objects.requireNonNull(activity.binding.phoneTxt.getText()).toString().equals("")||
                Objects.requireNonNull(activity.binding.passwordTxt.getText()).toString().equals("")){
            Constant.showErrorDialog(activity.getString(R.string.allrequired),activity);
        }
        else if (activity.binding.passwordTxt.getText().toString().length()<8){
            Constant.showErrorDialog(activity.getString(R.string.weakpass),activity);
        }
        else if (!activity.binding.passwordTxt.getText().toString().equals(activity.binding.confirmPasswordTxt.getText().toString())){
            Constant.showErrorDialog(activity.getString(R.string.password_match),activity);
        }
        else if (activity.binding.phoneTxt.getText().toString().length()!=11){
            Constant.showErrorDialog(activity.getString(R.string.incorrectphone),activity);
        }else {
            String name = activity.binding.usernameText.getText().toString();
            phone = activity.binding.phoneTxt.getText().toString();
            String email = Objects.requireNonNull(activity.binding.emailTxt.getText()).toString();
            String password = activity.binding.passwordTxt.getText().toString();

            if (Constant.CheckInternet(activity))
                CanRegister(name,phone,email,password);
        }
    }

    private void CanRegister(String name,String phone,String email,String password) {
        showLoadingDialog(activity);
        RegisterRequest registerRequest;
        if (email.equals(""))
            registerRequest = new RegisterRequest(name,password.trim(),phone.trim(),null,cityId,"");
        else
            registerRequest = new RegisterRequest(name,password.trim(),phone.trim(),email.trim(),cityId,"");

        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .Register(registerRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(RegisterResponse registerResponse) {
        alertDialog.dismiss();
        Intent intent = new Intent(activity, VerificationCodeActivity.class);
        intent.putExtra("phone",phone);
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

    public String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();

        return Base64.encodeToString(b, Base64.DEFAULT);
    }
}