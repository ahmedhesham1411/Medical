package com.gharbia.medical.Activities;

import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.gharbia.medical.Adapter.XRayResultAdapter;
import com.gharbia.medical.DataModel.HospitalsResponse;
import com.gharbia.medical.Interfaces.SheetClick;
import com.gharbia.medical.Interfaces.SortClick;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.Utilities.Utils;
import com.gharbia.medical.databinding.ActivityXrayBinding;
import com.gharbia.medical.viewModel.XRayViewModel;

import java.util.ArrayList;
import java.util.List;

public class XRayActivity extends BaseActivity implements SortClick, SheetClick {

    ActivityXrayBinding binding;
    XRayViewModel viewModel;
    XRayResultAdapter adapter;
    public int line=0;
    public int pageIndex = 1;
    List<HospitalsResponse.HospitalsData> hospitalsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xray);
        hospitalsData = new ArrayList<>();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_xray);
        viewModel = new XRayViewModel(this);
        binding.setVerificationVM(viewModel);

        initRecycler();

    }

    private void initRecycler() {
        adapter = new XRayResultAdapter(this,hospitalsData,this);
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
                            viewModel.GetXRay();
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