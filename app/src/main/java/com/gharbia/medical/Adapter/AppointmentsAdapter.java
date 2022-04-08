package com.gharbia.medical.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gharbia.medical.Activities.BookDetailsActivity;
import com.gharbia.medical.DataModel.AppointResponse;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.databinding.AppointmentsItemBinding;
import com.gharbia.medical.databinding.QuestionsItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.ViewHolder> {

    Activity activity;
    List<AppointResponse> appointResponses;
    String whatsApp;
    int status;

    public AppointmentsAdapter(Activity activity, List<AppointResponse> appointResponses,int status) {
        this.activity = activity;
        this.appointResponses = appointResponses;
        this.status = status;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AppointmentsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.appointments_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        whatsApp = appointResponses.get(position).getWhatsAppNumber();
        holder.binding.whatsApp2.setOnClickListener(view -> whatsapp());
        Picasso.get().load(Constant.BASEURL+appointResponses.get(position).getDrImage()).placeholder(activity.getDrawable(R.mipmap.ic_launcher11)).into(holder.binding.circleColored);
        holder.binding.notiTitle.setText(appointResponses.get(position).getDrName());
        holder.binding.reviews.setText(String.valueOf(appointResponses.get(position).getReviwes()));
        holder.binding.rating.setRating(appointResponses.get(position).getRate());
        holder.binding.notiDesc.setText(appointResponses.get(position).getBranchName());
        holder.binding.price.setText(appointResponses.get(position).getCost());
        holder.binding.day.setText(appointResponses.get(position).getDay());
        holder.binding.hour.setText(appointResponses.get(position).getHour());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, BookDetailsActivity.class);
            intent.putExtra("bookId",String.valueOf(appointResponses.get(position).getBookId()));
            intent.putExtra("status",String.valueOf(status));
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return appointResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private AppointmentsItemBinding binding;

        private ViewHolder(AppointmentsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

    public void whatsapp() {
        goTowhatsApp(activity, whatsApp);
    }

    public static void goTowhatsApp(Activity activity, String smsNumber) {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://api.whatsapp.com/send?phone=+2"+smsNumber+"&text=")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
