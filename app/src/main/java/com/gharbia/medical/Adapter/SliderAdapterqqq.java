package com.gharbia.medical.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.gharbia.medical.Activities.ImagesActivity;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.Utilities.GlobalVariables;
import com.gharbia.medical.databinding.BestOffersItem2Binding;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapterqqq extends SliderViewAdapter<SliderAdapterqqq.SliderAdapterVHHHH2> {

    Activity activity;
    List<String> images;
    List<String> names;

    public SliderAdapterqqq(Activity activity, List<String> images,List<String> names) {
        this.activity = activity;
        this.images=images;
        this.names=names;
    }

    @Override
    public SliderAdapterqqq.SliderAdapterVHHHH2 onCreateViewHolder(ViewGroup parent) {
        BestOffersItem2Binding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.best_offers_item2, parent, false);
        return new SliderAdapterqqq.SliderAdapterVHHHH2(binding);
    }

    @Override
    public void onBindViewHolder(SliderAdapterqqq.SliderAdapterVHHHH2 viewHolder, int position) {
        Picasso.get().load(Constant.BASEURL+images.get(position)).into(viewHolder.binding.img);
        viewHolder.binding.text.setText(names.get(position));
        //viewHolder.binding.img.setOnClickListener(view -> goToLink(activity,names.get(position)));
        viewHolder.binding.itemClick.setOnClickListener(view -> {
            GlobalVariables.images = images;
            Intent intent = new Intent(activity, ImagesActivity.class);
            activity.startActivity(intent);
        });
    }

    public static void goToLink(Activity activity, String url) {
        try {
            if (url != null && !url.isEmpty()) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
            }else
                Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return images.size();
    }

    public class SliderAdapterVHHHH2 extends SliderViewAdapter.ViewHolder {
        private BestOffersItem2Binding binding;

        private SliderAdapterVHHHH2(BestOffersItem2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
