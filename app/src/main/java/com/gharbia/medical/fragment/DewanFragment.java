package com.gharbia.medical.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.gharbia.medical.Adapter.BlogAdapter;
import com.gharbia.medical.DataModel.BlogResponse;
import com.gharbia.medical.R;
import com.gharbia.medical.databinding.FragmentDewanBinding;
import com.gharbia.medical.viewModel.DiwanViewModel;

import java.util.List;

public class DewanFragment extends Fragment {

    public FragmentDewanBinding binding;
    DiwanViewModel viewModel;
    BlogAdapter blogAdapter;
    public List<String> videoIds;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dewan, container, false);
        viewModel = new DiwanViewModel(DewanFragment.this);
        binding.setVerificationVM(viewModel);
        return binding.getRoot();
    }

    public void initRec(List<BlogResponse.BlogResponse2> blogResponse2s){
        blogAdapter = new BlogAdapter(videoIds,getActivity().getLifecycle(),blogResponse2s,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recycler.setLayoutManager(linearLayoutManager);
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setAdapter(blogAdapter);
    }
}