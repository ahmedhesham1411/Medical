package com.gharbia.medical.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gharbia.medical.Adapter.AppointmentsAdapter;
import com.gharbia.medical.DataModel.ListOfApp;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Utils;
import com.gharbia.medical.databinding.FragmentCurrentAppointmentsBinding;
import com.gharbia.medical.viewModel.CurrentAppointmentsViewModel;

public class CurrentAppointmentsFragment extends Fragment {

    public FragmentCurrentAppointmentsBinding binding;
    CurrentAppointmentsViewModel viewModel;
    AppointmentsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_current_appointments, container, false);
        viewModel = new CurrentAppointmentsViewModel(CurrentAppointmentsFragment.this);
        binding.setVerificationVM(viewModel);

        return binding.getRoot();

    }
    public void initRec(ListOfApp listOfApp){
        adapter = new AppointmentsAdapter(getActivity(),listOfApp.getData(),0);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recycler.setLayoutManager(linearLayoutManager);
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setAdapter(adapter);
        Utils.runAnimation(binding.recycler, 3);
    }
}