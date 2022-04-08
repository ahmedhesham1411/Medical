package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.MainActivity;
import com.gharbia.medical.DataModel.FCMRequest;
import com.gharbia.medical.DataModel.SliderResponse;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.Utilities.Preferences;
import com.google.firebase.iid.FirebaseInstanceId;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.gharbia.medical.Utilities.GlobalVariables.ACCOUNT_FRAGMENT_TAG;
import static com.gharbia.medical.Utilities.GlobalVariables.APPOINTMENTS_FRAGMENT_TAG;
import static com.gharbia.medical.Utilities.GlobalVariables.DIWAN_FRAGMENT_TAG;
import static com.gharbia.medical.Utilities.GlobalVariables.MAIN_FRAGMENT_TAG;
import static com.gharbia.medical.Utilities.GlobalVariables.QUESTIONS_FRAGMENT_TAG;

public class MainViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    MainActivity activity;
    private final CompositeSubscription mSubscriptions = new CompositeSubscription();

    public MainViewModel(MainActivity activity) {
        this.activity = activity;

        Log.d("myToken   ", FirebaseInstanceId.getInstance().getToken());
        activity.FCMToken = FirebaseInstanceId.getInstance().getToken();
        UpdateFCM();
    }

    public void home(){
        if (!activity.selectedFragment.equals(MAIN_FRAGMENT_TAG))
        activity.editLayout(MAIN_FRAGMENT_TAG);
    }

    public void questions(){
        if (!activity.selectedFragment.equals(QUESTIONS_FRAGMENT_TAG))
            activity.editLayout(QUESTIONS_FRAGMENT_TAG);
    }

    public void account(){
        if (!activity.selectedFragment.equals(ACCOUNT_FRAGMENT_TAG))
            activity.editLayout(ACCOUNT_FRAGMENT_TAG);
    }

    public void Appointments(){
        if (!activity.selectedFragment.equals(APPOINTMENTS_FRAGMENT_TAG))
            activity.editLayout(APPOINTMENTS_FRAGMENT_TAG);
    }

    public void Dewan(){
        if (!activity.selectedFragment.equals(DIWAN_FRAGMENT_TAG))
            activity.editLayout(DIWAN_FRAGMENT_TAG);
    }

    private void UpdateFCM() {
        Log.d("FCM UPdate",activity.FCMToken);
        FCMRequest fcmRequest = new FCMRequest(activity.FCMToken,1);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Preferences.GetToken(activity))
                .UpdateFCM(fcmRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSlider, this::handleErrorSlider));
    }
    private void handleResponseSlider(SliderResponse sliderResponse) {
        Log.d("FCM UPdate","done");
    }

    private void handleErrorSlider(Throwable throwable) {
        Log.d("rrrr",throwable.getMessage());
    }
}