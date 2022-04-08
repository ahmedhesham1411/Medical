package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.databinding.ActivityChangePasswordBinding;
import com.gharbia.medical.viewModel.ChangePasswordViewModel;

public class ChangePasswordActivity extends BaseActivity {

    ActivityChangePasswordBinding binding;
    ChangePasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_change_password);
        viewModel = new ChangePasswordViewModel(this);
        binding.setVerificationVM(viewModel);
    }
}