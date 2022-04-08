package com.gharbia.medical.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gharbia.medical.Activities.CategoryResultActivity;
import com.gharbia.medical.DataModel.DepartmentsDataModel;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.databinding.AppointmentsItemBinding;
import com.gharbia.medical.databinding.DepartmentsItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DepartmentsAdapter extends RecyclerView.Adapter<DepartmentsAdapter.ViewHolder> {

    Activity activity;
    List<DepartmentsDataModel> departmentsDataModels;
    int id;

    public DepartmentsAdapter(Activity activity, List<DepartmentsDataModel> departmentsDataModels,int id) {
        this.activity = activity;
        this.departmentsDataModels = departmentsDataModels;
        this.id = id;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DepartmentsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.departments_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.name.setText(departmentsDataModels.get(position).getName());
        holder.binding.doctorsnum.setText(String.valueOf(departmentsDataModels.get(position).getDoctorsNumber()));
        Picasso.get().load(Constant.BASEURL+departmentsDataModels.get(position).getImageUrl()).resize(400,400).into(holder.binding.image);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, CategoryResultActivity.class);
            intent.putExtra("id",id);
            intent.putExtra("name",departmentsDataModels.get(position).getName());
            intent.putExtra("departmentId",departmentsDataModels.get(position).getId());
            activity.startActivity(intent);
        });
        holder.binding.name.setSelected(true);

    }

    @Override
    public int getItemCount() {
        return departmentsDataModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private DepartmentsItemBinding binding;

        private ViewHolder(DepartmentsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
