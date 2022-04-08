package com.gharbia.medical.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Utils;
import com.gharbia.medical.databinding.FragmentAccountBinding;
import com.gharbia.medical.viewModel.AccountViewModel;

public class AccountFragment extends Fragment {

    public FragmentAccountBinding binding;
    AccountViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false);

        return binding.getRoot();
    }

    public void initAnimation(){
        binding.itemsLay.setVisibility(View.VISIBLE);
        Utils.runAnimation2(binding.cardLay,1);
        Utils.runAnimation2(binding.itemsLay,1);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel = new AccountViewModel(AccountFragment.this);
        binding.setVerificationVM(viewModel);
    }
}