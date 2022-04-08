package com.gharbia.medical.Adapter;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gharbia.medical.DataModel.CitiesData;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Preferences;
import com.gharbia.medical.Utilities.Utils;
import com.gharbia.medical.databinding.CitiesItemBinding;
import com.gharbia.medical.databinding.QuestionsItemBinding;

import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {

    Activity activity;
    List<CitiesData> citiesData;

    public CitiesAdapter(Activity activity, List<CitiesData> citiesData) {
        this.activity = activity;
        this.citiesData = citiesData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CitiesItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.cities_item, parent, false);
        return new ViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.city.setText(citiesData.get(position).getName());
        if (citiesData.get(position).isSelected()){
            holder.binding.city.setTextColor(activity.getColor(R.color.blue));
            holder.binding.flag.setColorFilter(activity.getResources().getColor(R.color.blue), PorterDuff.Mode.SRC_ATOP);
        }
        else{
            holder.binding.city.setTextColor(activity.getColor(R.color.black));
            holder.binding.flag.setColorFilter(activity.getResources().getColor(R.color.hint), PorterDuff.Mode.SRC_ATOP);
        }

        holder.itemView.setOnClickListener(view -> {
            Preferences.saveCityId(activity, String.valueOf(citiesData.get(position).getId()));
            Utils.MyToastSuccess(activity, activity.getString(R.string.done));
            activity.finish();
        });
    }

    @Override
    public int getItemCount() {
        return citiesData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CitiesItemBinding binding;

        private ViewHolder(CitiesItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
