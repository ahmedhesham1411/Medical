package com.gharbia.medical.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gharbia.medical.Adapter.QuestionsAdapter;
import com.gharbia.medical.DataModel.QuestionsResponse;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Utils;
import com.gharbia.medical.databinding.FragmentQuestionsBinding;
import com.gharbia.medical.viewModel.QuestionViewModel;

public class QuestionsFragment extends Fragment {

    FragmentQuestionsBinding binding;
    QuestionViewModel viewModel;
    QuestionsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_questions, container, false);
        viewModel = new QuestionViewModel(QuestionsFragment.this);
        binding.setVerificationVM(viewModel);

        return binding.getRoot();
    }

    public void initRec(QuestionsResponse questionsResponse){
        adapter = new QuestionsAdapter(getActivity(),questionsResponse.getData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.questionsRecycler.setLayoutManager(linearLayoutManager);
        binding.questionsRecycler.setHasFixedSize(true);
        binding.questionsRecycler.setAdapter(adapter);
        Utils.runAnimation(binding.questionsRecycler, 3);
    }
}