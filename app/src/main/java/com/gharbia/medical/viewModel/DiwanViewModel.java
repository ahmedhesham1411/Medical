package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.DataModel.BlogResponse;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.fragment.DewanFragment;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class DiwanViewModel extends ViewModel {

    DewanFragment fragment;
    @SuppressLint("StaticFieldLeak")
    Activity activity;
    @SuppressLint("StaticFieldLeak")
    private final CompositeSubscription mSubscriptions = new CompositeSubscription();
    AlertDialog alertDialog;

    public DiwanViewModel(DewanFragment fragment) {
        this.fragment = fragment;
        activity = fragment.getActivity();

        GetBlog();
        fragment.videoIds = new ArrayList<>();

    }

    private void GetBlog() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .GetBlog(1,10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void handleResponse(BlogResponse blogResponse) {
        alertDialog.dismiss();
        List<BlogResponse.BlogResponse2> blogResponse2s = new ArrayList<>();
        blogResponse2s = blogResponse.getData();

        try {
            for (int i=0;i<blogResponse2s.size();i++){
                String currentString = blogResponse2s.get(i).getVideoUrl();
                String myId ;
                if (currentString.contains("=")){
                    String[] separated = currentString.split("=");
                    myId = separated[1];
                    fragment.videoIds.add(myId);
                }else {
                    fragment.videoIds.add("");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if (blogResponse.getData().size()>0){
            fragment.binding.recycler.setVisibility(View.VISIBLE);
            fragment.binding.dewanChannel.setVisibility(View.GONE);
            fragment.initRec(blogResponse2s);
        }
    }

    private void handleError(Throwable throwable) {
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
}