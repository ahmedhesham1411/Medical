package com.gharbia.medical.Activities;

import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.gharbia.medical.Adapter.HospitalResultAdapter;
import com.gharbia.medical.DataModel.HospitalsResponse;
import com.gharbia.medical.Interfaces.SheetClick;
import com.gharbia.medical.Interfaces.SortClick;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.Utilities.Utils;
import com.gharbia.medical.databinding.ActivityHospitalsBinding;
import com.gharbia.medical.viewModel.HospitalsViewModel;

import java.util.ArrayList;
import java.util.List;

public class HospitalsActivity extends BaseActivity implements SortClick, SheetClick {

    ActivityHospitalsBinding binding;
    HospitalsViewModel viewModel;
    HospitalResultAdapter adapter;
    public int line=0;
    public int pageIndex = 1;
    List<HospitalsResponse.HospitalsData> hospitalsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hospitalsData = new ArrayList<>();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_hospitals);
        viewModel = new HospitalsViewModel(this);
        binding.setVerificationVM(viewModel);

        initRecycler();

    }

    private void initRecycler() {
        adapter = new HospitalResultAdapter(this,hospitalsData,this);
        binding.recycler.setAdapter(adapter);

        final LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        binding.recycler.setLayoutManager(mLayoutManager);

        binding.nestedScroll.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (v.getChildAt(v.getChildCount() - 1) != null) {
                if ((scrollY >= (v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight())) &&
                        scrollY > oldScrollY) {

                    final Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(() -> {
                        //code to fetch more data for endless scrolling
                        try {
                            pageIndex++;
                            viewModel.GetHospitals();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }, 100);
                }
            }
        });
    }

    public void updateRecycler(List<HospitalsResponse.HospitalsData> clinicsDataModels2){
        hospitalsData.addAll(clinicsDataModels2);
        adapter.notifyDataSetChanged();
        if (pageIndex==1)
            Utils.runAnimation(binding.recycler, 3);

    }

    @Override
    public void sheetClick(int linee) {
        line = linee;
        viewModel.sortBottomSheet.dismiss();
    }

    @Override
    public void sortClick() {

    }
}