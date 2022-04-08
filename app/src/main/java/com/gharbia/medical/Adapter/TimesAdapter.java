package com.gharbia.medical.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gharbia.medical.DataModel.DayTimeResponse;
import com.gharbia.medical.Interfaces.TimesClick;
import com.gharbia.medical.R;
import com.gharbia.medical.databinding.DaysItemBinding;
import com.gharbia.medical.databinding.TimesItemBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TimesAdapter extends RecyclerView.Adapter<TimesAdapter.ViewHolder> {

    Activity activity;
    List<DayTimeResponse.DayTimeResponse3> dayTimeResponse2s;
    TimesClick timesClick;

    public TimesAdapter(Activity activity, List<DayTimeResponse.DayTimeResponse3> dayTimeResponse2s,TimesClick timesClick) {
        this.activity = activity;
        this.dayTimeResponse2s = dayTimeResponse2s;
        this.timesClick = timesClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TimesItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.times_item, parent, false);
        return new ViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdfs = new SimpleDateFormat("hh:mm");
        Date dt = null;
        try {
            dt = sdf.parse(dayTimeResponse2s.get(position).getHour());
            System.out.println("Time Display: " + sdfs.format(dt)); // <-- I got result here
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.binding.notiTitle.setText(sdfs.format(dt));

        if (!dayTimeResponse2s.get(position).isBooked()){
            holder.binding.notiTitle.setBackgroundDrawable(activity.getDrawable(R.drawable.rounded_white));
            holder.binding.notiTitle.setTextColor(activity.getColor(R.color.black));
        }else if (dayTimeResponse2s.get(position).isBooked()){
            holder.binding.notiTitle.setBackgroundDrawable(activity.getDrawable(R.drawable.rounded_grey));
            holder.binding.notiTitle.setTextColor(activity.getColor(R.color.black));
        }
        if (dayTimeResponse2s.get(position).isSelected()){
            holder.binding.notiTitle.setBackgroundDrawable(activity.getDrawable(R.drawable.rounded_blue_2));
            holder.binding.notiTitle.setTextColor(activity.getColor(R.color.white));
        }

        holder.itemView.setOnClickListener(view -> timesClick.timeClick(position));
    }

    @Override
    public int getItemCount() {
        return dayTimeResponse2s.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TimesItemBinding binding;

        private ViewHolder(TimesItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
