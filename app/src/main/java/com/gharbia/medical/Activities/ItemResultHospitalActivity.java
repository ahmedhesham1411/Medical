package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gharbia.medical.Adapter.AddressesAdapter;
import com.gharbia.medical.Adapter.Departments2Adapter;
import com.gharbia.medical.Adapter.ImageItemResultAdapter;
import com.gharbia.medical.Adapter.ReviewsAdapter;
import com.gharbia.medical.DataModel.AddressModel;
import com.gharbia.medical.DataModel.DepartmentsDataModel;
import com.gharbia.medical.DataModel.ReviewResponse;
import com.gharbia.medical.Interfaces.AClick;
import com.gharbia.medical.Interfaces.AddressClick;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.databinding.ActivityItemResultHospitalBinding;
import com.gharbia.medical.viewModel.ItemResultHospitalViewModel;

import java.util.ArrayList;
import java.util.List;

public class ItemResultHospitalActivity extends BaseActivity implements AddressClick, AClick {

    public ActivityItemResultHospitalBinding binding;
    ItemResultHospitalViewModel viewModel;
    public int clinicId;
    AddressesAdapter addressesAdapter;
    Bundle savedInstanceState2;
    List<AddressModel> addressModelsMaster;
    public int addressId = 0;
    ImageItemResultAdapter adapter;
    ReviewsAdapter reviewsAdapter;
    Departments2Adapter departmentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_result_hospital);
        addressModelsMaster = new ArrayList<>();
        clinicId = getIntent().getIntExtra("id",0);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_item_result_hospital);
        viewModel = new ItemResultHospitalViewModel(this);
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
        binding.departmentsRec.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        departmentsAdapter = new Departments2Adapter(ItemResultHospitalActivity.this,departmentsResponse,0,this);
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

    @Override
    public void aaa(int id, String name, int depId) {
        Intent intent = new Intent(this, CategoryResultActivity2.class);
        intent.putExtra("id",clinicId);
        intent.putExtra("name",name);
        intent.putExtra("phone",viewModel.phone);
        intent.putExtra("departmentId",depId);
        startActivity(intent);
    }
}