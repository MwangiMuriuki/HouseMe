package com.example.houseme.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.houseme.R;
import com.example.houseme.databinding.ActivityViewPropertyBinding;

public class ActivityViewProperty extends AppCompatActivity {

    ActivityViewPropertyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_property);
    }
}
