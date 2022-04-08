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
import com.squareup.picasso.Picasso;
import java.util.List;

public class ImageItemResultAdapter extends RecyclerView.Adapter<ImageItemResultAdapter.ViewHolder> {

    Activity activity;
    List<String> images;

    public ImageItemResultAdapter(Activity activity, List<String> images) {
        this.activity = activity;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.image_item, parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(Constant.BASEURL+images.get(position)).placeholder(activity.getDrawable(R.mipmap.ic_launcher11)).into(holder.binding.img);
        holder.itemView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("img", images.get(position));
            Intent intent = new Intent(activity, PhotoViewActivity.class);
            intent.putExtra("data",bundle);
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageItemBinding binding;

        private ViewHolder(ImageItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
