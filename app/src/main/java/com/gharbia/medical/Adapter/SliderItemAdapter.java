package com.gharbia.medical.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.gharbia.medical.Activities.ImagesActivity;
import com.gharbia.medical.Activities.PhotoViewActivity;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.Utilities.GlobalVariables;
import com.gharbia.medical.databinding.BestOffersItem2Binding;
import com.gharbia.medical.databinding.Test2ItemBinding;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderItemAdapter extends SliderViewAdapter<SliderItemAdapter.SliderAdapterVHHHH2> {

    Activity activity;
    List<String> images;

    public SliderItemAdapter(Activity activity, List<String> images) {
        this.activity = activity;
        this.images=images;
    }

    @Override
    public SliderItemAdapter.SliderAdapterVHHHH2 onCreateViewHolder(ViewGroup parent) {
        Test2ItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.test2_item, parent, false);
        return new SliderItemAdapter.SliderAdapterVHHHH2(binding);
    }

    @Override
    public void onBindViewHolder(SliderItemAdapter.SliderAdapterVHHHH2 viewHolder, int position) {
        Picasso.get().load(Constant.BASEURL+images.get(position)).placeholder(activity.getDrawable(R.mipmap.ic_launcher11)).into(viewHolder.binding.img);
        viewHolder.binding.img.setOnClickListener(view -> {
/*            Bundle bundle = new Bundle();
            bundle.putString("img", images.get(position));
            Intent intent = new Intent(activity, PhotoViewActivity.class);
            intent.putExtra("data",bundle);
            activity.startActivity(intent);*/
            GlobalVariables.images = images;
            Intent intent = new Intent(activity, ImagesActivity.class);
            activity.startActivity(intent);
        });
    }

    @Override
    public int getCount() {
        return images.size();
    }

    public class SliderAdapterVHHHH2 extends SliderItemAdapter.ViewHolder {
        private Test2ItemBinding binding;

        private SliderAdapterVHHHH2(Test2ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
