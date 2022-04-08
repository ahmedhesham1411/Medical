package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.gharbia.medical.Activities.EditProfileActivity;
import com.gharbia.medical.Activities.ProfileActivity;

public class ProfileViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    ProfileActivity activity;

    public ProfileViewModel(ProfileActivity activity) {
        this.activity = activity;
    }

    public void back(){
        activity.finish();
    }

    public void editProfile(){
        Intent intent = new Intent(activity, EditProfileActivity.class);
        intent.putExtra("name",activity.name);
        intent.putExtra("email",activity.email);
        intent.putExtra("image",activity.image);
        intent.putExtra("phone",activity.phone);
        activity.startActivity(intent);
        activity.finish();
    }
}