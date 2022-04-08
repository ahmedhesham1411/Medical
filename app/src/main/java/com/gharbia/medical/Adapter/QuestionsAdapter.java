package com.gharbia.medical.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gharbia.medical.DataModel.QuestionsDataModel;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.LocaleManeger;
import com.gharbia.medical.databinding.QuestionsItemBinding;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {

    Activity activity;
    List<QuestionsDataModel> questionsDataModels;

    public QuestionsAdapter(Activity activity,List<QuestionsDataModel> questionsDataModels) {
        this.activity = activity;
        this.questionsDataModels = questionsDataModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuestionsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.questions_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (LocaleManeger.getLanguagePref(activity).equals("ar")){
            holder.binding.question.setBackground(activity.getDrawable(R.drawable.question_item_right));
            holder.binding.answerLine.setBackground(activity.getDrawable(R.drawable.question_item_left));
        }else {
            holder.binding.question.setBackground(activity.getDrawable(R.drawable.question_item_left));
            holder.binding.answerLine.setBackground(activity.getDrawable(R.drawable.question_item_right));
        }
        holder.binding.question.setText(questionsDataModels.get(position).getQuestion());
        holder.binding.answer.setText(questionsDataModels.get(position).getAnswer());
    }

    @Override
    public int getItemCount() {
        return questionsDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private QuestionsItemBinding binding;

        private ViewHolder(QuestionsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
