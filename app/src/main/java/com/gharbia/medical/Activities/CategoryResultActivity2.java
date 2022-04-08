package com.gharbia.medical.Activities;

import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.gharbia.medical.Adapter.CategoryResultAdapter2;
import com.gharbia.medical.DataModel.ClinicsDataModel;
import com.gharbia.medical.Interfaces.SheetClick;
import com.gharbia.medical.Interfaces.SortClick;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.Utilities.Utils;
import com.gharbia.medical.databinding.ActivityCategoryResult2Binding;
import com.gharbia.medical.databinding.ActivityCategoryResultBinding;
import com.gharbia.medical.viewModel.CategoryResult2ViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryResultActivity2 extends BaseActivity implements SortClick, SheetClick {

    ActivityCategoryResult2Binding binding;
    CategoryResult2ViewModel viewModel;
    CategoryResultAdapter2 adapter;
    public int line=0;
    public int id = 0;
    public int departmentId = 0;
    public int pageIndex = 1;
    public String name;
    public String phone;
    List<ClinicsDataModel> clinicsDataModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_result);
        id = getIntent().getIntExtra("id",0);
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        departmentId = getIntent().getIntExtra("departmentId",0);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_category_result2);
        viewModel = new CategoryResult2ViewModel(this);
        binding.setVerificationVM(viewModel);

        binding.name.setText(name);
        clinicsDataModels = new ArrayList<>();
        initRecycler();

    }

    private void initRecycler() {
        adapter = new CategoryResultAdapter2(this,clinicsDataModels,this,phone);
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
                            viewModel.GetClinics();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }, 100);
                }
            }
        });
    }

    public void updateRecycler(List<ClinicsDataModel> clinicsDataModels2){
        clinicsDataModels.addAll(clinicsDataModels2);
        if (clinicsDataModels.size()==0){
            binding.aa.setVisibility(View.VISIBLE);
            binding.recycler.setVisibility(View.GONE);
        }else {
            binding.aa.setVisibility(View.GONE);
            binding.recycler.setVisibility(View.VISIBLE);

            adapter.notifyDataSetChanged();
            if (pageIndex==1)
                Utils.runAnimation(binding.recycler, 3);
        }
    }

    @Override
    public void sortClick() {

    }

    @Override
    public void sheetClick(int linee) {
        line = linee;
        viewModel.sortBottomSheet.dismiss();
    }
}