package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;
import android.webkit.WebSettings;

import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.TermsAndConditionActivity;

public class TermsAndConditionViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    TermsAndConditionActivity activity;

    @SuppressLint("SetJavaScriptEnabled")
    public TermsAndConditionViewModel(TermsAndConditionActivity activity) {
        this.activity = activity;

        activity.binding.web.getSettings().setJavaScriptEnabled(true);
        activity.binding.web.getSettings().setLoadWithOverviewMode(true);
        activity.binding.web.getSettings().setUseWideViewPort(true);
        activity.binding.web.getSettings().setBuiltInZoomControls(true);
        activity.binding.web.getSettings().setPluginState(WebSettings.PluginState.ON);
        activity.binding.web.loadUrl("http://creativecode-001-site2.itempurl.com/TermsAndCondition/webview");
    }

    public void back(){
        activity.finish();
    }

}