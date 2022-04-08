package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.gharbia.medical.Adapter.NotificationsAdapter;
import com.gharbia.medical.DataModel.NotiResponse;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.Utilities.Utils;
import com.gharbia.medical.databinding.ActivityNotificationsBinding;
import com.gharbia.medical.viewModel.NotificationsViewModel;

public class NotificationsActivity extends BaseActivity {

    ActivityNotificationsBinding binding;
    NotificationsAdapter adapter;
    NotificationsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_notifications);
        viewModel = new NotificationsViewModel(this);
        binding.setVerificationVM(viewModel);
    }

    public void initRec(NotiResponse notiResponse){
        adapter = new NotificationsAdapter(this,notiResponse.getData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.notificationsRecycler.setLayoutManager(linearLayoutManager);
        binding.notificationsRecycler.setHasFixedSize(true);
        binding.notificationsRecycler.setAdapter(adapter);
        Utils.runAnimation(binding.notificationsRecycler, 3);
    }
}