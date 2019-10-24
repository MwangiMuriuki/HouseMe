package com.example.houseme.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.example.houseme.R;
import com.example.houseme.databinding.ActivityLoginBinding;


public class ActivityLogin extends Activity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg = new Intent(getApplication(), ActivityRegister.class);
                startActivity(reg);
            }
        });

        binding.resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reset = new Intent(getApplication(), ActivityResetPassword.class);
                startActivity(reset);
            }
        });
    }
}
