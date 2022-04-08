package com.gharbia.medical.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.gharbia.medical.Activities.ContactUsActivity;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Preferences;
import com.gharbia.medical.databinding.FragmentHomeBinding;
import com.gharbia.medical.viewModel.HomeViewModel;

public class HomeFragment extends Fragment {

    public FragmentHomeBinding binding;
    HomeViewModel viewModel;
    public ArrayAdapter<String> adapterCity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        viewModel = new HomeViewModel(HomeFragment.this);
        binding.setVerificationVM(viewModel);

        adapterCity = new ArrayAdapter<>(getActivity(), R.layout.xxx, viewModel.cityList);
        adapterCity.setDropDownViewResource(R.layout.xxx2);

        binding.regionSpinner.setAdapter(adapterCity);

        binding.regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                view.setEnabled(position != 0);
                viewModel.city = viewModel.cityList.get(position);
                Preferences.saveCityId(requireActivity(),viewModel.cityIdList.get(position));
                Preferences.saveCityName(requireActivity(),viewModel.cityList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        binding.search.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ContactUsActivity.class);
            startActivity(intent);
        });

        return binding.getRoot();
    }

}