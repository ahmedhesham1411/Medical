package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.databinding.ActivityStartBinding;

public class StartActivity extends BaseActivity {

    ActivityStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_start);

        binding.login.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this,LoginActivity.class);
            startActivity(intent);
        });

        binding.signUp.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this,RegisterActivity.class);
            startActivity(intent);
        });
    }
}