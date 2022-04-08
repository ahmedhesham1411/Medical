package com.gharbia.medical.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gharbia.medical.DataModel.AddressModel;
import com.gharbia.medical.R;
import com.gharbia.medical.databinding.CategoryInsideItemBinding;
import com.gharbia.medical.databinding.ClinicItemBinding;

import java.util.List;

public class RecyclerInsideAdapter extends RecyclerView.Adapter<RecyclerInsideAdapter.ViewHolder> {

    Activity activity;
    List<AddressModel> addressModels;

    public RecyclerInsideAdapter(Activity activity, List<AddressModel> addressModels) {
        this.activity = activity;
        this.addressModels = addressModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryInsideItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.category_inside_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.text.setText(addressModels.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return addressModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CategoryInsideItemBinding binding;

        private ViewHolder(CategoryInsideItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
