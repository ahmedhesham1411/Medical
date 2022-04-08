package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.gharbia.medical.Adapter.CitiesAdapter;
import com.gharbia.medical.DataModel.CitiesResponse;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.Utilities.Preferences;
import com.gharbia.medical.Utilities.Utils;
import com.gharbia.medical.databinding.ActivityCitiesBinding;
import com.gharbia.medical.viewModel.CitiesViewModel;

public class CitiesActivity extends BaseActivity {

    public ActivityCitiesBinding binding;
    CitiesViewModel viewModel;
    CitiesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cities);
        viewModel = new CitiesViewModel(this);
        binding.setVerificationVM(viewModel);

    }

    public void initRec(CitiesResponse citiesResponse){
        for (int i=0;i<citiesResponse.getData().size();i++){
            if (citiesResponse.getData().get(i).getId()== Integer.parseInt(Preferences.GetCityId(this))){
                citiesResponse.getData().get(i).setSelected(true);
            }else {
                citiesResponse.getData().get(i).setSelected(false);
            }
        }
        adapter = new CitiesAdapter(this,citiesResponse.getData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.citiesRecycler.setLayoutManager(linearLayoutManager);
        binding.citiesRecycler.setHasFixedSize(true);
        binding.citiesRecycler.setAdapter(adapter);
        Utils.runAnimation(binding.citiesRecycler, 3);
    }
}