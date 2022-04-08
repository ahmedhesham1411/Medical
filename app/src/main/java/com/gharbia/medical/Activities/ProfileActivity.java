package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.Utilities.Utils;
import com.gharbia.medical.databinding.ActivityProfileBinding;
import com.gharbia.medical.viewModel.ProfileViewModel;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends BaseActivity {

    ActivityProfileBinding binding;
    ProfileViewModel viewModel;
    public String name,email,image,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile);

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        image = getIntent().getStringExtra("image");
        phone = getIntent().getStringExtra("phone");

        binding.name.setText(name);
        binding.phone.setText(phone);
        binding.email.setText(email);
        Picasso.get().load(Constant.BASEURL+image).placeholder(getDrawable(R.mipmap.ic_launcher11)).into(binding.image);

        Utils.runAnimation2(binding.fullLay,1);
        Utils.runAnimation3(binding.frameLay,1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel = new ProfileViewModel(this);
        binding.setVerificationVM(viewModel);
    }
}