package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.gharbia.medical.Adapter.AddressesAdapter;
import com.gharbia.medical.Adapter.DepartmentsAdapter;
import com.gharbia.medical.Adapter.ImageItemResultAdapter;
import com.gharbia.medical.Adapter.ReviewsAdapter;
import com.gharbia.medical.DataModel.AddressModel;
import com.gharbia.medical.DataModel.DepartmentsDataModel;
import com.gharbia.medical.DataModel.ReviewResponse;
import com.gharbia.medical.Interfaces.AddressClick;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.databinding.ActivityItemResultAnalysisBinding;
import com.gharbia.medical.viewModel.ItemResultAnalysisViewModel;

import java.util.ArrayList;
import java.util.List;

public class ItemResultAnalysisActivity extends BaseActivity implements AddressClick {

    public ActivityItemResultAnalysisBinding binding;
    ItemResultAnalysisViewModel viewModel;
    public int clinicId;
    AddressesAdapter addressesAdapter;
    Bundle savedInstanceState2;
    List<AddressModel> addressModelsMaster;
    public int addressId = 0;
    ImageItemResultAdapter adapter;
    ReviewsAdapter reviewsAdapter;
    DepartmentsAdapter departmentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_result_analysis);
        addressModelsMaster = new ArrayList<>();
        clinicId = getIntent().getIntExtra("id",0);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_item_result_analysis);
        viewModel = new ItemResultAnalysisViewModel(this);
        binding.setVerificationVM(viewModel);

    }

    public void initRecImages(List<String> images){
        adapter = new ImageItemResultAdapter(this,images);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.imagesRecycler.setLayoutManager(linearLayoutManager);
        binding.imagesRecycler.setHasFixedSize(true);
        binding.imagesRecycler.setAdapter(adapter);
    }

    public void initRecAddresses(List<AddressModel> addressModels){
        addressModelsMaster.addAll(addressModels);
        for (int i=0;i<addressModelsMaster.size();i++){
            addressModelsMaster.get(i).setSelected(false);
        }
        addressesAdapter = new AddressesAdapter(this,addressModels,savedInstanceState2,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.adressesRec.setLayoutManager(linearLayoutManager);
        binding.adressesRec.setHasFixedSize(true);
        binding.adressesRec.setAdapter(addressesAdapter);
    }

    public void initRecReviews(ReviewResponse reviewResponse){
        reviewsAdapter = new ReviewsAdapter(this,reviewResponse.getData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.reviewsRecycler.setLayoutManager(linearLayoutManager);
        binding.reviewsRecycler.setHasFixedSize(true);
        binding.reviewsRecycler.setAdapter(reviewsAdapter);
    }

    public void initRecycler(List<DepartmentsDataModel> departmentsResponse){
        departmentsAdapter = new DepartmentsAdapter(ItemResultAnalysisActivity.this,departmentsResponse,0);
        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        binding.departmentsRec.setLayoutManager(manager);
        binding.departmentsRec.setHasFixedSize(true);
        binding.departmentsRec.setAdapter(departmentsAdapter);
    }

    @Override
    public void click(int position) {
        for (int i=0;i<addressModelsMaster.size();i++){
            addressModelsMaster.get(i).setSelected(false);
        }
        addressModelsMaster.get(position).setSelected(true);
        addressesAdapter.notifyDataSetChanged();
        addressId = addressModelsMaster.get(position).getAddressId();
    }
}