package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.Utilities.Utils;
import com.gharbia.medical.databinding.ActivityTermsAndConditionBinding;
import com.gharbia.medical.viewModel.TermsAndConditionViewModel;

public class TermsAndConditionActivity extends BaseActivity {

    public ActivityTermsAndConditionBinding binding;
    TermsAndConditionViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_terms_and_condition);
        viewModel = new TermsAndConditionViewModel(this);
        binding.setVerificationVM(viewModel);
        //Utils.runAnimationRelativeLay(binding.fullLay,2);
    }
}