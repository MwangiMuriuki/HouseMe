package com.example.houseme.Activities;

import android.app.Activity;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.example.houseme.R;
import com.example.houseme.databinding.ActivityResetPasswordBinding;

public class ActivityResetPassword extends Activity {

    ActivityResetPasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password);
    }
}
