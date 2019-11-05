package com.example.houseme.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.houseme.R;
import com.example.houseme.databinding.ActivityViewPropertyBinding;

public class ActivityViewProperty extends AppCompatActivity {

    ActivityViewPropertyBinding binding;

    Uri featuredImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_property);

        String prop_image = getIntent().getStringExtra("featuredImage");
        String prop_bedroom = getIntent().getStringExtra("bedrooms");
        String prop_price = getIntent().getStringExtra("price");
        String prop_bathroom = getIntent().getStringExtra("bathrooms");
        String prop_region = getIntent().getStringExtra("region");
        String prop_location = getIntent().getStringExtra("propertyLocation");
        String prop_desc = getIntent().getStringExtra("description");

        binding.location.setText(prop_location);
        binding.price.setText(prop_price);
        binding.houseBaths.setText(prop_bathroom);
        binding.houseSize.setText(prop_bedroom);
        binding.description.setText(prop_desc);

        featuredImage = Uri.parse(prop_image);
        Glide.with(this).load(featuredImage).into(binding.setFeaturedImage);

    }
}