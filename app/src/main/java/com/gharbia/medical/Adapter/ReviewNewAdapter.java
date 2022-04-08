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
import com.gharbia.medical.databinding.ReviewsItemNewBinding;

import java.util.List;

public class ReviewNewAdapter extends RecyclerView.Adapter<ReviewNewAdapter.ViewHolder> {

    Activity activity;
    List<ReviewResponse.ReviewResponse2> reviewResponse2s;

    public ReviewNewAdapter(Activity activity, List<ReviewResponse.ReviewResponse2> reviewResponse2s) {
        this.activity = activity;
        this.reviewResponse2s = reviewResponse2s;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ReviewsItemNewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.reviews_item_new, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.rating.setRating(reviewResponse2s.get(position).getRate());
        holder.binding.reviewTitle.setText(reviewResponse2s.get(position).getFullName());
        holder.binding.reviewText.setText(reviewResponse2s.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return reviewResponse2s.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ReviewsItemNewBinding binding;

        private ViewHolder(ReviewsItemNewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
