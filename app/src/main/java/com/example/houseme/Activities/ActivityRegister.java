package com.example.houseme.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.example.houseme.R;
import com.example.houseme.databinding.ActivityRegisterBinding;
import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.angads25.toggle.model.ToggleableView;
import com.github.angads25.toggle.widget.LabeledSwitch;

public class ActivityRegister extends AppCompatActivity {

    ActivityRegisterBinding binding;
    LabeledSwitch labeledSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.registerToolbar);
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        toolbar.setTitle("Register");
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
