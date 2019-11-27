package com.example.houseme.Fragments;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.houseme.Activities.ActivityAddProperty;
import com.example.houseme.R;
import com.example.houseme.databinding.FragmentAddPhotosBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddPhotos extends Fragment {

    FragmentAddPhotosBinding binding;

    public FragmentAddPhotos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_photos, container, false);


        return binding.getRoot();
    }

}
