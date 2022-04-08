package com.gharbia.medical.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gharbia.medical.DataModel.ReviewResponse;
import com.gharbia.medical.R;
import com.gharbia.medical.databinding.NotificationsItemBinding;
import com.gharbia.medical.databinding.ReviewsItemBinding;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    Activity activity;
    List<ReviewResponse.ReviewResponse2> reviewResponse2s;

    public ReviewsAdapter(Activity activity, List<ReviewResponse.ReviewResponse2> reviewResponse2s) {
        this.activity = activity;
        this.reviewResponse2s = reviewResponse2s;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ReviewsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.reviews_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.notiTitle.setText(reviewResponse2s.get(position).getFullName());
        holder.binding.notiDesc.setText(reviewResponse2s.get(position).getContent());
        holder.binding.notiDesc.setSelected(true);
        holder.binding.starBar.setRating((int) reviewResponse2s.get(position).getRate());
    }

    @Override
    public int getItemCount() {
        return reviewResponse2s.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ReviewsItemBinding binding;

        private ViewHolder(ReviewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
