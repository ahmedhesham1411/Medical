package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.AppointmentsActivity;
import com.gharbia.medical.Activities.CitiesActivity;
import com.gharbia.medical.Activities.ComplaintActivity;
import com.gharbia.medical.Activities.ContactUsActivity;
import com.gharbia.medical.Activities.LoginActivity;
import com.gharbia.medical.Activities.NotificationsActivity;
import com.gharbia.medical.Activities.ProfileActivity;
import com.gharbia.medical.Activities.RemaindersActivity;
import com.gharbia.medical.Activities.SettingActivity;
import com.gharbia.medical.Activities.TermsAndConditionActivity;
import com.gharbia.medical.DataModel.LoginResponse;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.Utilities.Preferences;
import com.gharbia.medical.fragment.AccountFragment;
import com.squareup.picasso.Picasso;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class AccountViewModel extends ViewModel {

    AccountFragment fragment;
    @SuppressLint("StaticFieldLeak")
    Activity activity;
    AlertDialog alertDialog;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    LoginResponse loginResponse2;
    String whatsApp;

    public AccountViewModel(AccountFragment fragment) {
        this.fragment = fragment;
        activity = fragment.getActivity();

        GetProfile();
        whatsApp = Preferences.GetWhatsApp(activity);
    }

    private void GetProfile() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Preferences.GetToken(activity))
                .GetProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));

    }

    private void handleResponse(LoginResponse loginResponse) {
        alertDialog.dismiss();
        loginResponse2 = loginResponse;
        fragment.initAnimation();
        fragment.binding.userName.setText(loginResponse.getData().getUserName());
        fragment.binding.userPhone.setText(loginResponse.getData().getPhone());
        Picasso.get().load(Constant.BASEURL+loginResponse.getData().getImageUrl()).placeholder(activity.getDrawable(R.mipmap.ic_launcher11)).into(fragment.binding.userImage);

    }

    private void handleError(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,activity);
    }

    public void whatsapp() {
        goTowhatsApp(activity, whatsApp);
    }

    public static void goTowhatsApp(Activity activity, String smsNumber) {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://api.whatsapp.com/send?phone=+2"+smsNumber+"&text=")));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void notifications(){
        Intent intent = new Intent(activity, NotificationsActivity.class);
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

    public void cities(){
        Intent intent = new Intent(activity, CitiesActivity.class);
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
        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog = builder.create();
        AppCompatTextView proceed,cancel;
        proceed = view.findViewById(R.id.proceed);
        cancel = view.findViewById(R.id.cancel);
        proceed.setOnClickListener(view12 -> {
            alertDialog.dismiss();
            Preferences.saveToken(activity,"default");
            Intent intent = new Intent(activity, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        });
        cancel.setOnClickListener(view1 -> alertDialog.dismiss());
        alertDialog.setCancelable(true);
        alertDialog.show();

    }
}