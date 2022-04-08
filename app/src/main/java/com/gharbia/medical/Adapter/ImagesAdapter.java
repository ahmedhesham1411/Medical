package com.gharbia.medical.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gharbia.medical.Activities.PhotoViewActivity;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.databinding.ImageItemBinding;
import com.gharbia.medical.databinding.SsssBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    Activity activity;
    List<String> images;

    public ImagesAdapter(Activity activity, List<String> images) {
        this.activity = activity;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SsssBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.ssss, parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(Constant.BASEURL+images.get(position)).placeholder(activity.getDrawable(R.mipmap.ic_launcher11)).into(holder.binding.img);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final SsssBinding binding;

        private ViewHolder(SsssBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
