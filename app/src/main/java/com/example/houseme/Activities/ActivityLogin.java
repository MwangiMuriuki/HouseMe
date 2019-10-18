package com.example.houseme.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.houseme.R;


public class ActivityLogin extends Activity {
    Button login;
    TextView register, resetPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.buttonLogin);
        register = findViewById(R.id.textViewRegister);
        resetPwd = findViewById(R.id.resetPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg = new Intent(getApplication(), ActivityRegister.class);
                startActivity(reg);
            }
        });

        resetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reset = new Intent(getApplication(), ActivityResetPassword.class);
                startActivity(reset);
            }
        });
    }
}
