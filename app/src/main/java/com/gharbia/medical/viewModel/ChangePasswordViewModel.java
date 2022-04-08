package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;

import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.ChangePasswordActivity;

public class ChangePasswordViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    ChangePasswordActivity activity;

    public ChangePasswordViewModel(ChangePasswordActivity activity) {
        this.activity = activity;
    }

    public void back(){
        activity.finish();
    }

}