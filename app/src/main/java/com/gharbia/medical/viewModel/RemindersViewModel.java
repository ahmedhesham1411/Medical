package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;

import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.RemaindersActivity;

public class RemindersViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    RemaindersActivity activity;

    public RemindersViewModel(RemaindersActivity activity) {
        this.activity = activity;
    }

    public void back(){
        activity.finish();
    }

}