package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.databinding.ActivityResetPasswordBinding;
import com.gharbia.medical.viewModel.ResetPasswordViewModel;

public class ResetPasswordActivity extends BaseActivity {

    public ActivityResetPasswordBinding binding;
    ResetPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_reset_password);
        viewModel = new ResetPasswordViewModel(this);
        binding.setVerificationVM(viewModel);
    }
}