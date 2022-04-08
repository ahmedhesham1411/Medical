package com.gharbia.medical.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gharbia.medical.Activities.ItemResultPharmaciesActivity;
import com.gharbia.medical.DataModel.HospitalsResponse;
import com.gharbia.medical.Interfaces.SortClick;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.databinding.HospitalsItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhamaciesResultAdapter extends RecyclerView.Adapter<PhamaciesResultAdapter.ViewHolder> {

    Activity activity;
    SortClick sortClick;
    List<HospitalsResponse.HospitalsData> hospitalsData;

    public PhamaciesResultAdapter(Activity activity, List<HospitalsResponse.HospitalsData> hospitalsData, SortClick sortClick) {
        this.activity = activity;
        this.hospitalsData = hospitalsData;
        this.sortClick = sortClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HospitalsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.hospitals_item, parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.notiTitle.setText(hospitalsData.get(position).getName());
        holder.binding.notiDesc.setText(hospitalsData.get(position).getDescription());
        Picasso.get().load(Constant.BASEURL+hospitalsData.get(position).getImageUrl()).placeholder(activity.getDrawable(R.mipmap.ic_launcher11)).into(holder.binding.circleColored);
        if (hospitalsData.get(position).isSponserd())
            holder.binding.sponsorLay.setVisibility(View.VISIBLE);
        else
            holder.binding.sponsorLay.setVisibility(View.GONE);

/*        RecyclerInsideAdapter adapter = new RecyclerInsideAdapter(activity,hospitalsData.get(position).getAddresses());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        holder.binding.recyclerInside.setLayoutManager(linearLayoutManager);
        holder.binding.recyclerInside.setHasFixedSize(true);
        holder.binding.recyclerInside.setAdapter(adapter);*/

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, ItemResultPharmaciesActivity.class);
            intent.putExtra("id",hospitalsData.get(position).getId());
            intent.putExtra("name",hospitalsData.get(position).getName());
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return hospitalsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private HospitalsItemBinding binding;

        private ViewHolder(HospitalsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
