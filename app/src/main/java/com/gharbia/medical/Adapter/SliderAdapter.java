package com.gharbia.medical.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.databinding.BestOffersItem2Binding;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVHHHH> {

    Activity activity;
    List<String> images;
    List<String> names;

    public SliderAdapter(Activity activity, List<String> images,List<String> names) {
        this.activity = activity;
        this.images=images;
        this.names=names;
    }

    @Override
    public SliderAdapter.SliderAdapterVHHHH onCreateViewHolder(ViewGroup parent) {
        BestOffersItem2Binding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.best_offers_item2, parent, false);
        return new SliderAdapter.SliderAdapterVHHHH(binding);
    }

    @Override
    public void onBindViewHolder(SliderAdapter.SliderAdapterVHHHH viewHolder, int position) {
        Picasso.get().load(Constant.BASEURL+images.get(position)).into(viewHolder.binding.img);
        viewHolder.binding.text.setText(names.get(position));
        viewHolder.binding.img.setOnClickListener(view -> goToLink(activity,names.get(position)));
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

    public class SliderAdapterVHHHH extends SliderViewAdapter.ViewHolder {
        private BestOffersItem2Binding binding;

        private SliderAdapterVHHHH(BestOffersItem2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
