package com.gharbia.medical.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gharbia.medical.R;
import com.gharbia.medical.databinding.FragmentAppointmentsBinding;
import com.gharbia.medical.viewModel.AppointmentsFragmentViewModel;

public class AppointmentsFragment extends Fragment {

    FragmentAppointmentsBinding binding;
    AppointmentsFragmentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_appointments, container, false);
        viewModel = new AppointmentsFragmentViewModel(AppointmentsFragment.this,getActivity().getSupportFragmentManager());
        binding.setVerificationVM(viewModel);
        return binding.getRoot();
    }
}