package com.gharbia.medical.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gharbia.medical.DataModel.DayTimeResponse;
import com.gharbia.medical.R;
import com.gharbia.medical.databinding.DaysItemBinding;
import com.gharbia.medical.databinding.NotificationsItemBinding;

import java.util.List;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.ViewHolder> {

    Activity activity;
    List<DayTimeResponse.DayTimeResponse2> dayTimeResponse2s;

    public DaysAdapter(Activity activity, List<DayTimeResponse.DayTimeResponse2> dayTimeResponse2s) {
        this.activity = activity;
        this.dayTimeResponse2s = dayTimeResponse2s;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DaysItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.days_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.notiTitle.setText(dayTimeResponse2s.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return dayTimeResponse2s.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private DaysItemBinding binding;

        private ViewHolder(DaysItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
