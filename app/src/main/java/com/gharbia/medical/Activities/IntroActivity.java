package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gharbia.medical.Adapter.IntroAdapter;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.Utilities.Preferences;
import com.gharbia.medical.databinding.ActivityIntroBinding;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends BaseActivity {

    public ActivityIntroBinding binding;
    List<Integer> images = new ArrayList<>();
    public SliderView sliderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro);

        images.add(R.drawable.intro1);
        images.add(R.drawable.intro2);
        images.add(R.drawable.intro3);

        sliderView = findViewById(R.id.imageSliderHome);
        sliderView.setEnabled(false);
        sliderView.setClickable(false);
        IntroAdapter adapterr = new IntroAdapter(this,images,sliderView);
        sliderView.setSliderAdapter(adapterr);
        sliderView.setCircularHandlerEnabled(true);

        binding.line1.setVisibility(View.VISIBLE);
        binding.next1.setOnClickListener(view -> {
            binding.line1.setVisibility(View.GONE);
            binding.line2.setVisibility(View.VISIBLE);
            binding.line3.setVisibility(View.GONE);
            sliderView.setCurrentPagePosition(1);
        });

        binding.line2Next.setOnClickListener(view -> {
            binding.line1.setVisibility(View.GONE);
            binding.line2.setVisibility(View.GONE);
            binding.line3.setVisibility(View.VISIBLE);
            sliderView.setCurrentPagePosition(2);
        });

        binding.line2Previous.setOnClickListener(view -> {
            binding.line1.setVisibility(View.VISIBLE);
            binding.line2.setVisibility(View.GONE);
            binding.line3.setVisibility(View.GONE);
            sliderView.setCurrentPagePosition(0);
        });

        binding.line3Previous.setOnClickListener(view -> {
            binding.line1.setVisibility(View.GONE);
            binding.line2.setVisibility(View.VISIBLE);
            binding.line3.setVisibility(View.GONE);
            sliderView.setCurrentPagePosition(1);
        });

        binding.line3Next.setOnClickListener(view -> {
            Preferences.savIsFirst(this,"first");
            Intent intent = new Intent(IntroActivity.this,StartActivity.class);
            startActivity(intent);
        });
    }
}