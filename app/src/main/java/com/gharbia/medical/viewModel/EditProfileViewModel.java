package com.gharbia.medical.viewModel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.EditProfileActivity;
import com.gharbia.medical.DataModel.EditProfileRequest;
import com.gharbia.medical.DataModel.LoginResponse;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.Utilities.Preferences;
import com.gharbia.medical.Utilities.Utils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.gharbia.medical.viewModel.RegisterViewModel.IMAGE_REQUEST;

public class EditProfileViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    EditProfileActivity activity;
    public String imageBase64 = "";
    private final CompositeSubscription mSubscriptions = new CompositeSubscription();
    AlertDialog alertDialog;

    public EditProfileViewModel(EditProfileActivity activity) {
        this.activity = activity;
    }

    public void back(){
        activity.finish();
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

    public void Validate(){

        Constant.hideKeyboard(activity);
        if (activity.binding.usernameText.getText().toString().equals("") ||
                activity.binding.emailTxt.getText().toString().equals("")||
                activity.binding.phoneTxt.getText().toString().equals("")){
            Constant.showErrorDialog(activity.getString(R.string.allrequired),activity);
        }else if (activity.binding.phoneTxt.getText().toString().length()!=11){
            Constant.showErrorDialog(activity.getString(R.string.incorrectphone),activity);
        }/*else if (imageBase64.equals("")){
            Constant.showErrorDialog(activity.getString(R.string.required_photo),activity);
        }*/
        else {
            String name = activity.binding.usernameText.getText().toString();
            String phone = activity.binding.phoneTxt.getText().toString();
            String email = activity.binding.emailTxt.getText().toString();

            if (Constant.CheckInternet(activity))
                Update(name,phone,email);
        }
    }

    private void Update(String name,String phone,String email) {
        showLoadingDialog(activity);
        Log.d("ssssss",imageBase64);
        EditProfileRequest editProfileRequest = new EditProfileRequest(name,email,phone,imageBase64);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Preferences.GetToken(activity))
                .EditProfile(editProfileRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(LoginResponse loginResponse) {
        alertDialog.dismiss();
        Utils.MyToastSuccess(activity,activity.getString(R.string.done));
        activity.finish();
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