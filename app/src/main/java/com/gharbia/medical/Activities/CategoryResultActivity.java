package com.gharbia.medical.Activities;

import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.gharbia.medical.Adapter.CategoryResultAdapter;
import com.gharbia.medical.DataModel.ClinicsDataModel;
import com.gharbia.medical.Interfaces.SheetClick;
import com.gharbia.medical.Interfaces.SortClick;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.Utilities.Utils;
import com.gharbia.medical.databinding.ActivityCategoryResultBinding;
import com.gharbia.medical.viewModel.CategoryResultViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryResultActivity extends BaseActivity implements SortClick, SheetClick {

    ActivityCategoryResultBinding binding;
    CategoryResultViewModel viewModel;
    CategoryResultAdapter adapter;
    public int line=0;
    public int id = 0;
    public int departmentId = 0;
    public int pageIndex = 1;
    public String name;
    List<ClinicsDataModel> clinicsDataModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_result);
        id = getIntent().getIntExtra("id",0);
        name = getIntent().getStringExtra("name");
        departmentId = getIntent().getIntExtra("departmentId",0);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_category_result);
        viewModel = new CategoryResultViewModel(this);
        binding.setVerificationVM(viewModel);

        binding.name.setText(name);
        clinicsDataModels = new ArrayList<>();
        initRecycler();

    }

    private void initRecycler() {
        adapter = new CategoryResultAdapter(this,clinicsDataModels,this);
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
        adapter.notifyDataSetChanged();
        if (pageIndex==1)
            Utils.runAnimation(binding.recycler, 3);

    }

/*    public void initRec(){
        adapter = new CategoryResultAdapter(this,15,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recycler.setLayoutManager(linearLayoutManager);
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setAdapter(adapter);
    }*/

    @Override
    public void sortClick() {

    }

    @Override
    public void sheetClick(int linee) {
        line = linee;
        viewModel.sortBottomSheet.dismiss();
    }
}