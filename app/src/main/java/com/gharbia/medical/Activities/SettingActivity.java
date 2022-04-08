package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.Utilities.LocaleManeger;
import com.gharbia.medical.databinding.ActivitySettingBinding;
import com.gharbia.medical.viewModel.SettingViewModel;

import java.util.Locale;

import static com.gharbia.medical.Utilities.LocaleManeger.ARABIC;
import static com.gharbia.medical.Utilities.LocaleManeger.setNewLocale;

public class SettingActivity extends BaseActivity {

    ActivitySettingBinding binding;
    SettingViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_setting);
        viewModel = new SettingViewModel(this);
        binding.setVerificationVM(viewModel);

        if (Locale.getDefault().getLanguage().equals("en")){
            binding.lineEnglish.setBackground(getDrawable(R.drawable.rounded_blue_3));
            binding.lineArabic.setBackground(getDrawable(R.drawable.rounded_dark_grey));
        }else {
            binding.lineEnglish.setBackground(getDrawable(R.drawable.rounded_dark_grey));
            binding.lineArabic.setBackground(getDrawable(R.drawable.rounded_blue_3));
        }

        binding.lineArabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Locale.getDefault().getLanguage().equals("ar")){}else {
                    setNewLocale(SettingActivity.this,ARABIC);
                    Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
            }
        });

        binding.lineEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Locale.getDefault().getLanguage().equals("en")){}else {
                    setNewLocale(SettingActivity.this, LocaleManeger.ENGLISH);
                    Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
            }
        });
    }
}