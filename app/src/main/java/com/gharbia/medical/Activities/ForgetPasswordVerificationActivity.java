package com.gharbia.medical.Activities;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.databinding.ActivityForgetPasswordVerificationBinding;
import com.gharbia.medical.viewModel.ForgetPasswordVerificationCodeViewModel;

import java.util.Objects;

public class ForgetPasswordVerificationActivity extends BaseActivity {

    ActivityForgetPasswordVerificationBinding binding;
    ForgetPasswordVerificationCodeViewModel viewModel;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_verification);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_forget_password_verification);
        viewModel = new ForgetPasswordVerificationCodeViewModel(this);
        binding.setVerificationVM(viewModel);
        binding.num1Edt.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.num1Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ver_full));
                binding.num1Edt.setTextColor(getColor(R.color.black));
            }
            if (!hasFocus) {
                if (!Objects.equals(viewModel.num1.get(), "")){
                    binding.num1Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ver_empty));
                    binding.num1Edt.setTextColor(getColor(R.color.white));
                }else
                    binding.num1Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ver_empty));
            }
        });

        binding.num2Edt.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.num2Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ver_full));
                binding.num2Edt.setTextColor(getColor(R.color.black));
            }
            if (!hasFocus) {
                if (!Objects.equals(viewModel.num2.get(), "")){
                    binding.num2Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ver_empty));
                    binding.num2Edt.setTextColor(getColor(R.color.white));
                }else
                    binding.num2Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ver_empty));
            }
        });

        binding.num3Edt.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.num3Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ver_full));
                binding.num3Edt.setTextColor(getColor(R.color.black));
            }
            if (!hasFocus) {
                if (!Objects.equals(viewModel.num3.get(), "")){
                    binding.num3Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ver_empty));
                    binding.num3Edt.setTextColor(getColor(R.color.white));
                }else
                    binding.num3Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ver_empty));
            }
        });

        binding.num4Edt.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.num4Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ver_full));
                binding.num4Edt.setTextColor(getColor(R.color.black));
            }
            if (!hasFocus) {
                if (!Objects.equals(viewModel.num4.get(), "")){
                    binding.num4Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ver_empty));
                    binding.num4Edt.setTextColor(getColor(R.color.white));
                }else
                    binding.num4Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ver_empty));
            }
        });

        binding.num1Edt.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.num1.set(s.toString());
                requestFocus(1);
            }
        });
        binding.num2Edt.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.num2.set(s.toString());
                requestFocus(2);

            }
        });

        binding.num3Edt.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.num3.set(s.toString());
                requestFocus(3);

            }
        });

        binding.num4Edt.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.num4.set(s.toString());
                binding.num4Edt.clearFocus();
            }
        });
    }

    public void requestFocus(int input) {
        switch (input) {
            case 1:
                binding.num2Edt.requestFocus();
                break;

            case 2:
                binding.num3Edt.requestFocus();
                break;

            case 3:
                binding.num4Edt.requestFocus();
                break;
        }
    }
}