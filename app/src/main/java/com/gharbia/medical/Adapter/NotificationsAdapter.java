package com.gharbia.medical.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gharbia.medical.DataModel.NotiResponse;
import com.gharbia.medical.R;
import com.gharbia.medical.databinding.NotificationsItemBinding;
import com.gharbia.medical.databinding.QuestionsItemBinding;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    Activity activity;
    List<NotiResponse.NotiResponse2> notiResponse2s;

    public NotificationsAdapter(Activity activity, List<NotiResponse.NotiResponse2> notiResponse2s) {
        this.activity = activity;
        this.notiResponse2s = notiResponse2s;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NotificationsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.notifications_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.notiTitle.setText(notiResponse2s.get(position).getTitle());
        holder.binding.notiDesc.setText(notiResponse2s.get(position).getDescription());
        holder.binding.date.setText(notiResponse2s.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return notiResponse2s.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private NotificationsItemBinding binding;

        private ViewHolder(NotificationsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
