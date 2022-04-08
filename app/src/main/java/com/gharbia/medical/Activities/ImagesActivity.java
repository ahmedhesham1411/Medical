package com.gharbia.medical.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.gharbia.medical.Adapter.ImagesAdapter;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.Utilities.GlobalVariables;
import com.gharbia.medical.databinding.ActivityImagesBinding;

public class ImagesActivity extends BaseActivity {

    ActivityImagesBinding binding;
    ImagesAdapter imagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_images);

        binding.backImg.setOnClickListener(view -> finish());
        try {
            imagesAdapter = new ImagesAdapter(this, GlobalVariables.images);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            binding.recycler.setLayoutManager(linearLayoutManager);
            binding.recycler.setAdapter(imagesAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}