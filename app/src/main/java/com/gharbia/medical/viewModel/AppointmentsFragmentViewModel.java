package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.ObservableBoolean;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.R;
import com.gharbia.medical.fragment.AppointmentsFragment;
import com.gharbia.medical.fragment.CurrentAppointmentsFragment;
import com.gharbia.medical.fragment.PreviousAppointmentsFragment;

import rx.subscriptions.CompositeSubscription;

import static com.gharbia.medical.Utilities.GlobalVariables.CURRENT_APPOINTMENTS_FRAGMENT_ID;
import static com.gharbia.medical.Utilities.GlobalVariables.CURRENT_APPOINTMENTS_FRAGMENT_TAG;
import static com.gharbia.medical.Utilities.GlobalVariables.PREVIOUS_APPOINTMENTS_FRAGMENT_ID;
import static com.gharbia.medical.Utilities.GlobalVariables.PREVIOUS_APPOINTMENTS_FRAGMENT_TAG;

public class AppointmentsFragmentViewModel extends ViewModel {

    AppointmentsFragment fragment;
    @SuppressLint("StaticFieldLeak")
    Activity activity;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    AlertDialog alertDialog;
    private String selectedFragmentTag;
    private FragmentManager fragmentManager;
    public ObservableBoolean bbbbbb = new ObservableBoolean(false);
    public ObservableBoolean aaaaa = new ObservableBoolean(true);

    public AppointmentsFragmentViewModel(AppointmentsFragment fragment,final FragmentManager fragmentManager) {
        this.fragment = fragment;
        activity = fragment.getActivity();

        this.fragmentManager = fragmentManager;
        fragmentManager.addOnBackStackChangedListener(() -> {
            selectedFragmentTag = fragmentManager.getFragments().get(fragmentManager.getFragments().size() - 1).getTag();
            Log.e("backstack fragmentTag", selectedFragmentTag);
            Bundle bundle = fragmentManager.getFragments().get(fragmentManager.getFragments().size() - 1).getArguments();
        });

        pushFragment(CURRENT_APPOINTMENTS_FRAGMENT_ID);
    }
    public void back(){
        activity.finish();
    }

    @SuppressLint("ResourceAsColor")
    public void orderTabClick(int type) {
        if (type == 1) {
            pushFragment(CURRENT_APPOINTMENTS_FRAGMENT_ID);
            aaaaa.set(true);
            bbbbbb.set(false);
        } else if (type == 2){
            bbbbbb.set(true);
            aaaaa.set(false);
            pushFragment(PREVIOUS_APPOINTMENTS_FRAGMENT_ID);
        }
    }

    public void pushFragment(int fragmentId) {
        String tag;
        FragmentTransaction fragmentTransaction = fragment.getChildFragmentManager().beginTransaction();
        Fragment fragment;

        switch (fragmentId) {
            case CURRENT_APPOINTMENTS_FRAGMENT_ID:
                tag = CURRENT_APPOINTMENTS_FRAGMENT_TAG;
                fragment = new CurrentAppointmentsFragment();

                int count = fragmentManager.getBackStackEntryCount();
                for (int i = 0; i < count; ++i)
                    fragmentManager.popBackStackImmediate();
                break;
            case PREVIOUS_APPOINTMENTS_FRAGMENT_ID:
                tag = PREVIOUS_APPOINTMENTS_FRAGMENT_TAG;
                fragment = new PreviousAppointmentsFragment();
                break;
            default:
                try {
                    throw new Exception("Invalid fragment id :: " + fragmentId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
        }

        selectedFragmentTag = tag;

        fragmentTransaction.replace(R.id.container1, fragment, tag).commit();
    }
}