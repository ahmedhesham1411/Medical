package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.gharbia.medical.Adapter.DepartmentsAdapter;
import com.gharbia.medical.DataModel.DepartmentsDataModel;
import com.gharbia.medical.DataModel.DepartmentsResponse;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.Utilities.Utils;
import com.gharbia.medical.databinding.ActivityClinicResultBinding;
import com.gharbia.medical.viewModel.ClinicResultViewModel;

import java.util.ArrayList;
import java.util.List;

public class ClinicResultActivity extends BaseActivity {

    ActivityClinicResultBinding binding;
    ClinicResultViewModel viewModel;
    public ArrayAdapter<String> adapterCity;
    DepartmentsAdapter adapter;
    public int page = 1;
    public int id = 0;
    List<DepartmentsDataModel> departmentsDataModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_result);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_clinic_result);
        viewModel = new ClinicResultViewModel(this);
        binding.setVerificationVM(viewModel);

        id = getIntent().getIntExtra("id",0);
        //id = Integer.parseInt(getIntent().getStringExtra("id"));
        departmentsDataModelList = new ArrayList<>();
        adapterCity = new ArrayAdapter<>(this, R.layout.xxx, viewModel.cityList);
        adapterCity.setDropDownViewResource(R.layout.xxx2);

        binding.regionSpinner.setAdapter(adapterCity);

        binding.regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    view.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Utils.runAnimation2(binding.fullLay, 2);
        binding.recycler.setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    public void initRecycler(DepartmentsResponse departmentsResponse){
        adapter = new DepartmentsAdapter(ClinicResultActivity.this,departmentsResponse.getData(),id);
        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        binding.recycler.setLayoutManager(manager);
        //binding.recycler.setHasFixedSize(true);
        binding.recycler.setAdapter(adapter);
        Utils.runAnimation(binding.recycler, 2);
    }

}