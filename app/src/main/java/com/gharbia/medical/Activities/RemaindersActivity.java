package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.gharbia.medical.Adapter.AppointmentsAdapter;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.databinding.ActivityRemaindersBinding;
import com.gharbia.medical.viewModel.RemindersViewModel;

public class RemaindersActivity extends BaseActivity {

    ActivityRemaindersBinding binding;
    RemindersViewModel viewModel;
    AppointmentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainders);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_remainders);
        viewModel = new RemindersViewModel(this);
        binding.setVerificationVM(viewModel);

        initRec();
    }
    public void initRec(){
/*        adapter = new AppointmentsAdapter(this,15);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recycler.setLayoutManager(linearLayoutManager);
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setAdapter(adapter);
        Utils.runAnimation(binding.recycler, 3);*/
    }
}