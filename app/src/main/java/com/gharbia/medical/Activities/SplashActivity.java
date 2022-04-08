package com.gharbia.medical.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.Utilities.LocaleManeger;
import com.gharbia.medical.Utilities.Preferences;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try {
            FirebaseApp.initializeApp(this);
            FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        }catch (Exception e){
            e.printStackTrace();
        }

        LocaleManeger.getLanguagePref(this);
        if (Preferences.GetIsFirst(this).equals("default")){
            new Handler().postDelayed(() -> {
                Intent mainIntent = new Intent(SplashActivity.this, IntroActivity.class);
                startActivity(mainIntent);
                finish();
            }, 2500);
        }else if (Preferences.GetToken(this).equals("default")){
            new Handler().postDelayed(() -> {
                Intent mainIntent = new Intent(SplashActivity.this, StartActivity.class);
                startActivity(mainIntent);
                finish();
            }, 2500);
        }else {
            new Handler().postDelayed(() -> {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }, 2500);
        }
    }
}