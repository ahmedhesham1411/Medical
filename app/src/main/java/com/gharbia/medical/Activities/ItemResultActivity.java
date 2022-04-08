package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.gharbia.medical.Adapter.AddressesAdapter;
import com.gharbia.medical.Adapter.DaysAdapter;
import com.gharbia.medical.Adapter.ImageItemResultAdapter;
import com.gharbia.medical.Adapter.ReviewsAdapter;
import com.gharbia.medical.Adapter.TimesAdapter;
import com.gharbia.medical.DataModel.AddressModel;
import com.gharbia.medical.DataModel.DayTimeResponse;
import com.gharbia.medical.DataModel.ReviewResponse;
import com.gharbia.medical.Interfaces.AddressClick;
import com.gharbia.medical.Interfaces.TimesClick;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.Utilities.Utils;
import com.gharbia.medical.databinding.ActivityItemResultBinding;
import com.gharbia.medical.viewModel.ItemResultViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ItemResultActivity extends BaseActivity implements AddressClick, TimesClick {

    public ActivityItemResultBinding binding;
    ItemResultViewModel viewModel;
    ImageItemResultAdapter adapter;
    ReviewsAdapter reviewsAdapter;
    public int clinicId;
    AddressesAdapter addressesAdapter;
    Bundle savedInstanceState2;
    List<AddressModel> addressModelsMaster;
    public int addressId = 0;
    public int dayPosition = 0;
    DaysAdapter daysAdapter;
    LinearLayoutManager linearLayoutManager2;
    TimesAdapter timesAdapter;
    List<DayTimeResponse.DayTimeResponse2> dayTimeResponse2sFull;
    List<DayTimeResponse.DayTimeResponse3> dayTimeResponse3sFull;
    public ArrayAdapter<String> adapterGender;
    public List<String> genderList = new ArrayList<>();
    public List<String> genderListId = new ArrayList<>();
    public String genderId = "";
    public int hourId = 0;
    public ArrayAdapter<String> adapterDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        savedInstanceState2 = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_result);
        addressModelsMaster = new ArrayList<>();
        clinicId = getIntent().getIntExtra("id",0);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_item_result);
        viewModel = new ItemResultViewModel(this);
        binding.setVerificationVM(viewModel);

        dayTimeResponse2sFull = new ArrayList<>();
        dayTimeResponse3sFull = new ArrayList<>();

        if (Locale.getDefault().getLanguage().equals("ar")){
            binding.next.setRotation(180);
            binding.previous.setRotation(180);
        }
/*        binding.next.setOnClickListener(view -> {
            if (dayTimeResponse2sFull.size()-1 != dayPosition){
                try {
                    if (linearLayoutManager2.findLastCompletelyVisibleItemPosition() < (adapter.getItemCount() - 1)) {
                        linearLayoutManager2.scrollToPosition(linearLayoutManager2.findLastCompletelyVisibleItemPosition() + 1);
                        dayPosition = linearLayoutManager2.findLastCompletelyVisibleItemPosition()+1;
                        initRecTimes(dayTimeResponse2sFull.get(dayPosition).getHours());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        });

        binding.previous.setOnClickListener(view -> {
            try {
                if (linearLayoutManager2.findLastCompletelyVisibleItemPosition() < (adapter.getItemCount() - 1)) {
                    linearLayoutManager2.scrollToPosition(linearLayoutManager2.findLastCompletelyVisibleItemPosition() - 1);
                    dayPosition = linearLayoutManager2.findLastCompletelyVisibleItemPosition()-1;
                    initRecTimes(dayTimeResponse2sFull.get(dayPosition).getHours());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });*/

        //enableDisableView(binding.pp,false);

        genderList.add(getString(R.string.male));
        genderListId.add("0");
        genderList.add(getString(R.string.female));
        genderListId.add("1");

        adapterGender = new ArrayAdapter<>(this, R.layout.xxx, genderList);
        adapterGender.setDropDownViewResource(R.layout.xxx2);

        binding.regionSpinner.setAdapter(adapterGender);

        binding.regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genderId = genderListId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        adapterDays = new ArrayAdapter<>(this, R.layout.xxx3, viewModel.daysList);
        adapterDays.setDropDownViewResource(R.layout.xxx2);

        binding.regionSpinner2.setAdapter(adapterDays);

        binding.regionSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initRecTimes(dayTimeResponse2sFull.get(position).getHours());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
        addressModelsMaster.get(0).setSelected(true);
        addressesAdapter = new AddressesAdapter(this,addressModels,savedInstanceState2,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.adressesRec.setLayoutManager(linearLayoutManager);
        binding.adressesRec.setHasFixedSize(true);
        binding.adressesRec.setAdapter(addressesAdapter);
    }

    public void initRecReviews(ReviewResponse reviewResponse){
        if (reviewResponse.getData()==null){}else {
            reviewsAdapter = new ReviewsAdapter(this,reviewResponse.getData());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            binding.reviewsRecycler.setLayoutManager(linearLayoutManager);
            binding.reviewsRecycler.setHasFixedSize(true);
            binding.reviewsRecycler.setAdapter(reviewsAdapter);
        }
    }

    public void initRecTimes(List<DayTimeResponse.DayTimeResponse3> dayTimeResponse3s){
        dayTimeResponse3sFull = dayTimeResponse3s;
        timesAdapter = new TimesAdapter(this,dayTimeResponse3s,this);
        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        LinearLayoutManager linearLayoutManager22 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.timesRec.setLayoutManager(manager);
        binding.timesRec.setHasFixedSize(true);
        binding.timesRec.setAdapter(timesAdapter);
    }

    public void initRecDays(List<DayTimeResponse.DayTimeResponse2> dayTimeResponse2s){
        dayTimeResponse2sFull = dayTimeResponse2s;
/*        if (dayTimeResponse2s==null){}else {
            daysAdapter = new DaysAdapter(this,dayTimeResponse2s);
            linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            binding.daysRec.setLayoutManager(linearLayoutManager2);
            binding.daysRec.setHasFixedSize(true);
            binding.daysRec.setAdapter(daysAdapter);
            initRecTimes(dayTimeResponse2s.get(0).getHours());
        }*/
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
    public void timeClick(int position) {
        for (int j=0;j<dayTimeResponse2sFull.size();j++){
            for (int i=0;i< dayTimeResponse2sFull.get(j).getHours().size();i++){
                dayTimeResponse2sFull.get(j).getHours().get(i).setSelected(false);
            }
        }
        if (!dayTimeResponse3sFull.get(position).isBooked()){
            dayTimeResponse3sFull.get(position).setSelected(true);
            hourId = dayTimeResponse3sFull.get(position).getId();
        }else {
            Utils.MyToastError(this,getString(R.string.time_booked));
            //Constant.showErrorDialog(getString(R.string.time_booked),this);
        }
        timesAdapter.notifyDataSetChanged();
    }

    public static void enableDisableView(View view, boolean enabled) {
        view.setEnabled(enabled);
        if ( view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup)view;

            for ( int idx = 0 ; idx < group.getChildCount() ; idx++ ) {
                enableDisableView(group.getChildAt(idx), enabled);
            }
        }
    }
}