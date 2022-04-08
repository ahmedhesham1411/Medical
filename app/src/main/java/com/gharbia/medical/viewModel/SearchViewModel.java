package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.SearchActivity;
import com.gharbia.medical.DataModel.CitiesResponse;
import com.gharbia.medical.DataModel.ClinicsList;
import com.gharbia.medical.DataModel.DepartmentsResponse;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.Utilities.Preferences;
import com.gharbia.medical.Utilities.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SearchViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    SearchActivity activity;
    AlertDialog alertDialog;
    private final CompositeSubscription mSubscriptions = new CompositeSubscription();
    public ObservableField<Boolean> rate1 = new ObservableField<>(false);
    public ObservableField<Boolean> rate2 = new ObservableField<>(false);
    public ObservableField<Boolean> rate3 = new ObservableField<>(false);
    public ObservableField<Boolean> rate4 = new ObservableField<>(false);
    public ObservableField<Boolean> rate5 = new ObservableField<>(false);
    public ObservableField<String> rate = new ObservableField<>("-1");
    public List<String> cityList = new ArrayList<>();
    public List<String> masterList = new ArrayList<>();
    public List<String> masterIdList = new ArrayList<>();
    public List<String> categoriesList = new ArrayList<>();
    public List<String> cityIdList = new ArrayList<>();
    public List<String> categoryIdList = new ArrayList<>();
    public List<String> sortIdList = new ArrayList<>();
    public List<String> sortList = new ArrayList<>();
    public String city = "0";
    public String category = "-1";
    public String sort = "0";
    public String master = "-1";


    public SearchViewModel(SearchActivity activity) {
        this.activity = activity;

        sortIdList.add("0");
        sortIdList.add("1");

        sortList.add(activity.getString(R.string.low_to_high));
        sortList.add(activity.getString(R.string.high_to_low));

        masterIdList.add("0");
        masterIdList.add("1");
        masterIdList.add("2");
        masterIdList.add("3");
        masterIdList.add("4");
        masterIdList.add("5");
        masterIdList.add("6");
        masterIdList.add("7");

        masterList.add(activity.getString(R.string.advisor));
        masterList.add(activity.getString(R.string.specialist));
        masterList.add(activity.getString(R.string.phd));
        masterList.add(activity.getString(R.string.master));
        masterList.add(activity.getString(R.string.assistantprofessor));
        masterList.add(activity.getString(R.string.professor));
        masterList.add(activity.getString(R.string.teacherassistant));
        masterList.add(activity.getString(R.string.teacher));

        activity.binding.searchEdt.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Utils.hideKeyboard(activity);
                Search();
                return true;
            }
            return false;
        });


        activity.binding.expand.setOnClickListener(v -> {
            activity.binding.expand.setVisibility(View.GONE);
            activity.binding.expandableHotelFacilities.expand();
        });
        GetCities();
        GetDepartments();
    }

    public void back(){
        activity.finish();
    }

    public void GetCities() {
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .GetCities()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSliderr, this::handleErrorSliderr));
    }

    private void handleResponseSliderr(CitiesResponse citiesResponse) {
        try {
            for (int i=0;i<citiesResponse.getData().size();i++) {
                cityList.add(citiesResponse.getData().get(i).getName());
                cityIdList.add(String.valueOf(citiesResponse.getData().get(i).getId()));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        activity.adapterCity.notifyDataSetChanged();

    }

    private void handleErrorSliderr(Throwable throwable) {
        Constant.handleErrors(throwable,activity);
    }

    public void GetDepartments() {
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .GetDepartments(Locale.getDefault().getLanguage(),0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(DepartmentsResponse departmentsResponse) {
        try {
            for (int i=0;i<departmentsResponse.getData().size();i++) {
                categoriesList.add(departmentsResponse.getData().get(i).getName());
                categoryIdList.add(String.valueOf(departmentsResponse.getData().get(i).getId()));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        activity.categoriesAdapter.notifyDataSetChanged();
    }

    private void handleError(Throwable throwable) {
        Constant.handleErrors(throwable,activity);
    }

    public void Search() {
        Utils.hideKeyboard(activity);
        String name;
        if (Objects.requireNonNull(activity.binding.searchEdt.getText()).toString().isEmpty()) {
            showLoadingDialog(activity);
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(Preferences.GetToken(activity))
                    .Search1(Locale.getDefault().getLanguage(),1,
                            Integer.parseInt(category),Integer.parseInt(city),
                            10,Integer.parseInt(sort),
                            Integer.parseInt(rate.get()),Integer.parseInt(master))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseSlider1, this::handleErrorSlider1));
        }else{
            name = activity.binding.searchEdt.getText().toString();
            showLoadingDialog(activity);
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(Preferences.GetToken(activity))
                    .Search(Locale.getDefault().getLanguage(),1,
                            Integer.parseInt(category),Integer.parseInt(city),
                            name,10,Integer.parseInt(sort),
                            Integer.parseInt(rate.get()),Integer.parseInt(master))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseSlider1, this::handleErrorSlider1));
        }
    }

    private void handleResponseSlider1(ClinicsList clinicsList) {
        alertDialog.dismiss();
        if (clinicsList.getData().size()==0){
            Utils.MyToastError(activity,activity.getString(R.string.no_data));
        }else {
            if (!activity.binding.expandableHotelFacilities.isExpanded()){
                activity.binding.nestedScroll.setVisibility(View.VISIBLE);
            }else if (activity.binding.expandableHotelFacilities.isExpanded()){
                activity.binding.expand.setVisibility(View.VISIBLE);
                activity.binding.expandableHotelFacilities.collapse();
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(() -> activity.binding.nestedScroll.setVisibility(View.VISIBLE), 1000);
                activity.updateRecycler(clinicsList.getData());
            }
        }

    }

    private void handleErrorSlider1(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,activity);
    }

    public void showLoadingDialog(Context activity) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.loading, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.SheetDialog);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
    }

    public void RateClick(int number){
        switch (number){
            case 1:
                rate1.set(true);
                rate2.set(false);
                rate3.set(false);
                rate4.set(false);
                rate5.set(false);
                rate.set("1");
                break;

            case 2:
                rate1.set(false);
                rate2.set(true);
                rate3.set(false);
                rate4.set(false);
                rate5.set(false);
                rate.set("2");
                break;

            case 3:
                rate1.set(false);
                rate2.set(false);
                rate3.set(true);
                rate4.set(false);
                rate5.set(false);
                rate.set("3");
                break;

            case 4:
                rate1.set(false);
                rate2.set(false);
                rate3.set(false);
                rate4.set(true);
                rate5.set(false);
                rate.set("4");
                break;

            case 5:
                rate1.set(false);
                rate2.set(false);
                rate3.set(false);
                rate4.set(false);
                rate5.set(true);
                rate.set("5");
                break;
        }
    }

}