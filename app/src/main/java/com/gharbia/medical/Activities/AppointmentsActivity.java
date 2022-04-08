package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.databinding.ActivityAppointmentsBinding;
import com.gharbia.medical.viewModel.AppointmentsViewModel;

public class AppointmentsActivity extends BaseActivity {

    ActivityAppointmentsBinding binding;
    AppointmentsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_appointments);
        viewModel = new AppointmentsViewModel(this,getSupportFragmentManager());
        binding.setVerificationVM(viewModel);
    }

}