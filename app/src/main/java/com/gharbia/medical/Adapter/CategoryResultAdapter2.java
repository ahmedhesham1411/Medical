package com.gharbia.medical.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gharbia.medical.DataModel.ClinicsDataModel;
import com.gharbia.medical.Interfaces.SortClick;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.databinding.ClinicItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryResultAdapter2 extends RecyclerView.Adapter<CategoryResultAdapter2.ViewHolder> {

    Activity activity;
    SortClick sortClick;
    List<ClinicsDataModel> clinicsDataModels;
    String phone;

    public CategoryResultAdapter2(Activity activity, List<ClinicsDataModel> clinicsDataModels, SortClick sortClick,String phone) {
        this.activity = activity;
        this.clinicsDataModels = clinicsDataModels;
        this.phone = phone;
        this.sortClick = sortClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ClinicItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.clinic_item, parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.notiTitle.setText(clinicsDataModels.get(position).getName());
        holder.binding.notiDesc.setText(clinicsDataModels.get(position).getDescription());
        holder.binding.price.setText(String.valueOf(clinicsDataModels.get(position).getPrice()));
        holder.binding.reviews.setText(String.valueOf(clinicsDataModels.get(position).getReviwes()));
        holder.binding.rating.setRating((int) Math.ceil(clinicsDataModels.get(position).getRate()));
        Picasso.get().load(Constant.BASEURL+clinicsDataModels.get(position).getImageUrl()).placeholder(activity.getDrawable(R.mipmap.ic_launcher11)).into(holder.binding.circleColored);
        if (clinicsDataModels.get(position).isSponserd())
            holder.binding.sponsorLay.setVisibility(View.VISIBLE);
        else
            holder.binding.sponsorLay.setVisibility(View.GONE);

        RecyclerInsideAdapter adapter = new RecyclerInsideAdapter(activity,clinicsDataModels.get(position).getAddresses());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        holder.binding.recyclerInside.setLayoutManager(linearLayoutManager);
        holder.binding.recyclerInside.setHasFixedSize(true);
        holder.binding.recyclerInside.setAdapter(adapter);

        holder.itemView.setOnClickListener(view -> {
            goTowhatsApp(activity,phone);
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
        return clinicsDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ClinicItemBinding binding;

        private ViewHolder(ClinicItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
