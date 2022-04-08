package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.databinding.ActivityPhotoViewBinding;
import com.squareup.picasso.Picasso;

public class PhotoViewActivity extends BaseActivity {

    ActivityPhotoViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_photo_view);
        binding.backImg.setOnClickListener(view -> finish());

        Bundle bundle = getIntent().getBundleExtra("data");

        Picasso.get().load(Constant.BASEURL+bundle.getString("img")).into(binding.photoView);
    }
}