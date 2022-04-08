package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.databinding.ActivityForgetPasswordBinding;
import com.gharbia.medical.viewModel.ForgetPasswordViewModel;

public class ForgetPasswordActivity extends BaseActivity {

    public ActivityForgetPasswordBinding binding;
    ForgetPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_forget_password);
        viewModel = new ForgetPasswordViewModel(this);
        binding.setVerificationVM(viewModel);
    }
}