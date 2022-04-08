package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.Utilities.Utils;
import com.gharbia.medical.databinding.ActivityMainBinding;
import com.gharbia.medical.fragment.AccountFragment;
import com.gharbia.medical.fragment.AppointmentsFragment;
import com.gharbia.medical.fragment.DewanFragment;
import com.gharbia.medical.fragment.HomeFragment;
import com.gharbia.medical.fragment.QuestionsFragment;
import com.gharbia.medical.viewModel.MainViewModel;

import static com.gharbia.medical.Utilities.GlobalVariables.ACCOUNT_FRAGMENT_ID;
import static com.gharbia.medical.Utilities.GlobalVariables.ACCOUNT_FRAGMENT_TAG;
import static com.gharbia.medical.Utilities.GlobalVariables.APPOINTMENTS_FRAGMENT_ID;
import static com.gharbia.medical.Utilities.GlobalVariables.APPOINTMENTS_FRAGMENT_TAG;
import static com.gharbia.medical.Utilities.GlobalVariables.DIWAN_FRAGMENT_ID;
import static com.gharbia.medical.Utilities.GlobalVariables.DIWAN_FRAGMENT_TAG;
import static com.gharbia.medical.Utilities.GlobalVariables.MAIN_FRAGMENT_ID;
import static com.gharbia.medical.Utilities.GlobalVariables.MAIN_FRAGMENT_TAG;
import static com.gharbia.medical.Utilities.GlobalVariables.QUESTIONS_FRAGMENT_ID;
import static com.gharbia.medical.Utilities.GlobalVariables.QUESTIONS_FRAGMENT_TAG;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;
    MainViewModel viewModel;
    private FragmentManager fragmentManager;
    private Animation rotateAnimation;
    boolean doubleBackToExitPressedOnce = false;
    int fragment_num = 0;
    public String selectedFragment,FCMToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModel = new MainViewModel(this);
        binding.setVerificationVM(viewModel);
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate360);

        fragmentManager = getSupportFragmentManager();
        try {
            if (getIntent().getStringExtra("hh").equals("3"))
                editLayout(APPOINTMENTS_FRAGMENT_TAG);
            else
                editLayout(MAIN_FRAGMENT_TAG);
        }catch (Exception e){
            e.printStackTrace();
            editLayout(MAIN_FRAGMENT_TAG);
        }

    }

    @SuppressLint("NewApi")
    public void editLayout(String fragmentTag) {

        binding.main.clearAnimation();
        binding.questionsImg.clearAnimation();
        binding.accountImg.clearAnimation();
        binding.appointmentsImg.clearAnimation();

        binding.main.setColorFilter(getResources().getColor(R.color.hint), PorterDuff.Mode.SRC_ATOP);
        binding.questionsImg.setColorFilter(getResources().getColor(R.color.hint), PorterDuff.Mode.SRC_ATOP);
        binding.accountImg.setColorFilter(getResources().getColor(R.color.hint), PorterDuff.Mode.SRC_ATOP);
        binding.appointmentsImg.setColorFilter(getResources().getColor(R.color.hint), PorterDuff.Mode.SRC_ATOP);

        binding.mainTxt.setTextColor(getResources().getColor(R.color.hint));
        binding.questionsTxt.setTextColor(getResources().getColor(R.color.hint));
        binding.accountTxt.setTextColor(getResources().getColor(R.color.hint));
        binding.appointmentsTxt.setTextColor(getResources().getColor(R.color.hint));

        if (fragmentTag.equals(MAIN_FRAGMENT_TAG)) {
            binding.main.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            binding.mainTxt.setTextColor(getResources().getColor(R.color.white));
            binding.main.startAnimation(rotateAnimation);
            pushFragment(MAIN_FRAGMENT_ID,null);
        } else if (fragmentTag.equals(QUESTIONS_FRAGMENT_TAG)) {
            binding.questionsImg.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            binding.questionsTxt.setTextColor(getResources().getColor(R.color.white));
            binding.questionsImg.startAnimation(rotateAnimation);
            pushFragment(QUESTIONS_FRAGMENT_ID,null);
        } else if (fragmentTag.equals(ACCOUNT_FRAGMENT_TAG)) {
            binding.accountImg.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            binding.accountTxt.setTextColor(getResources().getColor(R.color.white));
            binding.accountImg.startAnimation(rotateAnimation);
            pushFragment(ACCOUNT_FRAGMENT_ID,null);
        } else if (fragmentTag.equals(APPOINTMENTS_FRAGMENT_TAG)) {
            binding.appointmentsImg.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            binding.appointmentsTxt.setTextColor(getResources().getColor(R.color.white));
            binding.appointmentsImg.startAnimation(rotateAnimation);
            pushFragment(APPOINTMENTS_FRAGMENT_ID,null);
        } else if (fragmentTag.equals(DIWAN_FRAGMENT_TAG)) {
            binding.dewanImg.startAnimation(rotateAnimation);
            pushFragment(DIWAN_FRAGMENT_ID,null);
        }
    }


    public void pushFragment(int fragmentId, Bundle bundle) {
        String tag;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment;

        switch (fragmentId) {
            case MAIN_FRAGMENT_ID:
                tag = MAIN_FRAGMENT_TAG;
                fragment = new HomeFragment();
                fragment_num = 1;

                int count = fragmentManager.getBackStackEntryCount();
                for (int i = 0; i < count; ++i)
                    fragmentManager.popBackStackImmediate();

                break;
            case QUESTIONS_FRAGMENT_ID:
                tag = QUESTIONS_FRAGMENT_TAG;
                fragment = new QuestionsFragment();
                fragment_num = 2;

                fragment.setArguments(bundle);
                break;
            case ACCOUNT_FRAGMENT_ID:
                tag = ACCOUNT_FRAGMENT_TAG;
                fragment = new AccountFragment();
                fragment_num = 3;
                fragment.setArguments(bundle);
                break;
            case APPOINTMENTS_FRAGMENT_ID:
                tag = APPOINTMENTS_FRAGMENT_TAG;
                fragment = new AppointmentsFragment();
                fragment_num = 4;
                fragment.setArguments(bundle);
                break;
            case DIWAN_FRAGMENT_ID:
                tag = DIWAN_FRAGMENT_TAG;
                fragment = new DewanFragment();
                fragment_num = 5;
                fragment.setArguments(bundle);
                break;
            default:
                try {
                    throw new Exception("Invalid fragment id :: " + fragmentId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
        }
        selectedFragment = tag;
        fragmentTransaction.replace(R.id.container, fragment, tag).commit();
    }

    @Override
    public void onBackPressed() {
        Utils.hideKeyboard(this);
        if (selectedFragment.equals(MAIN_FRAGMENT_TAG)){
            if (doubleBackToExitPressedOnce){
                super.onBackPressed();
            }else {
                doubleBackToExitPressedOnce = true;
                Toast.makeText(this, getString(R.string.press_again), Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(() -> doubleBackToExitPressedOnce=false, 2000);
            }
        }else {
            pushFragment(MAIN_FRAGMENT_ID, null);
        }
    }
}