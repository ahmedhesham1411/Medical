package com.gharbia.medical.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gharbia.medical.DataModel.DepartmentsDataModel;
import com.gharbia.medical.Interfaces.AClick;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.databinding.DepartmentsItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Departments2Adapter extends RecyclerView.Adapter<Departments2Adapter.ViewHolder> {

    Activity activity;
    List<DepartmentsDataModel> departmentsDataModels;
    int id;
    AClick aClick;

    public Departments2Adapter(Activity activity, List<DepartmentsDataModel> departmentsDataModels, int id,AClick aClick) {
        this.activity = activity;
        this.departmentsDataModels = departmentsDataModels;
        this.id = id;
        this.aClick = aClick;
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
        //holder.binding.name.setSelected(true);
        holder.binding.name.setText(departmentsDataModels.get(position).getName());
        holder.binding.doctorsnum.setText(String.valueOf(departmentsDataModels.get(position).getDoctorsNumber()));
        Picasso.get().load(Constant.BASEURL+departmentsDataModels.get(position).getImageUrl()).resize(400,400).into(holder.binding.image);
        holder.itemView.setOnClickListener(view -> {
            aClick.aaa(id,departmentsDataModels.get(position).getName(),departmentsDataModels.get(position).getId());
        });
    }

    public static void goTowhatsApp(Activity activity, String smsNumber) {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://api.whatsapp.com/send?phone=+2"+smsNumber+"&text=")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return departmentsDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private DepartmentsItemBinding binding;

        private ViewHolder(DepartmentsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
