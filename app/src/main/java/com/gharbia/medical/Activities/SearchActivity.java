package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.gharbia.medical.Adapter.CategoryResultAdapter;
import com.gharbia.medical.DataModel.ClinicsDataModel;
import com.gharbia.medical.Interfaces.SortClick;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.databinding.ActivitySearchBinding;
import com.gharbia.medical.viewModel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements SortClick {

    public ActivitySearchBinding binding;
    SearchViewModel viewModel;
    public ArrayAdapter<String> adapterCity,categoriesAdapter,sortAdapter,masterAdapter;
    CategoryResultAdapter adapter;
    List<ClinicsDataModel> clinicsDataModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_search);
        viewModel = new SearchViewModel(this);
        binding.setSearchVM(viewModel);

        masterAdapter = new ArrayAdapter<>(this, R.layout.xxx, viewModel.masterList);
        masterAdapter.setDropDownViewResource(R.layout.xxx2);

        binding.masterSpinner.setAdapter(masterAdapter);

        binding.masterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.master = viewModel.masterIdList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        adapterCity = new ArrayAdapter<>(this, R.layout.xxx, viewModel.cityList);
        adapterCity.setDropDownViewResource(R.layout.xxx2);

        binding.regionSpinner.setAdapter(adapterCity);

        binding.regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.city = viewModel.cityIdList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        categoriesAdapter = new ArrayAdapter<>(this, R.layout.xxx, viewModel.categoriesList);
        categoriesAdapter.setDropDownViewResource(R.layout.xxx2);

        binding.categorySpinner.setAdapter(categoriesAdapter);

        binding.categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.category = viewModel.categoryIdList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        sortAdapter = new ArrayAdapter<>(this, R.layout.xxx, viewModel.sortList);
        sortAdapter.setDropDownViewResource(R.layout.xxx2);

        binding.sortSpinner.setAdapter(sortAdapter);

        binding.sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.sort = viewModel.sortIdList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        initRecycler();
    }

    private void initRecycler() {
        clinicsDataModels = new ArrayList<>();
        adapter = new CategoryResultAdapter(this,clinicsDataModels,this);
        binding.recycler.setAdapter(adapter);

        final LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        binding.recycler.setLayoutManager(mLayoutManager);
    }

    public void updateRecycler(List<ClinicsDataModel> clinicsDataModels2){
        clinicsDataModels.clear();
        clinicsDataModels.addAll(clinicsDataModels2);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void sortClick() {

    }
}