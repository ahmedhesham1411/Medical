package com.gharbia.medical.Activities;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.Utilities.Utils;
import com.gharbia.medical.databinding.ActivityComplaintBinding;
import com.gharbia.medical.viewModel.ComplaintViewModel;

public class ComplaintActivity extends BaseActivity {

    ActivityComplaintBinding binding;
    ComplaintViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_complaint);
        viewModel = new ComplaintViewModel(this);
        binding.setVerificationVM(viewModel);
        Utils.runAnimation2(binding.fullLay,1);
    }
}