package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;

import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.ComplaintActivity;

public class ComplaintViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    ComplaintActivity activity;

    public ComplaintViewModel(ComplaintActivity activity) {
        this.activity = activity;
    }

    public void back(){
        activity.finish();
    }

}