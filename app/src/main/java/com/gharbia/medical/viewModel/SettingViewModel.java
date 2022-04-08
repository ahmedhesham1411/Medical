package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.ChangePasswordActivity;
import com.gharbia.medical.Activities.SettingActivity;
import com.gharbia.medical.Utilities.LocaleManeger;

import java.util.Locale;

import static com.gharbia.medical.Utilities.LocaleManeger.ARABIC;
import static com.gharbia.medical.Utilities.LocaleManeger.setNewLocale;

public class SettingViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    SettingActivity activity;

    public SettingViewModel(SettingActivity activity) {
        this.activity = activity;
    }

    public void back(){
        activity.finish();
    }

    public void changePassword(){
        Intent intent = new Intent(activity, ChangePasswordActivity.class);
        activity.startActivity(intent);
    }

    public void arabic(){
        if (Locale.getDefault().getLanguage().equals("ar")){}else {
            setNewLocale(activity,ARABIC);
            Intent i = activity.getBaseContext().getPackageManager().getLaunchIntentForPackage(activity.getBaseContext().getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(i);
        }
    }

    public void english(){
        if (Locale.getDefault().getLanguage().equals("en")){}else {
            setNewLocale(activity, LocaleManeger.ENGLISH);
            Intent i = activity.getBaseContext().getPackageManager().getLaunchIntentForPackage(activity.getBaseContext().getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(i);
        }
    }
}