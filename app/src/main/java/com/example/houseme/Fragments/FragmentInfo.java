package com.example.houseme.Fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContentResolverCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.houseme.Activities.ActivityAddProperty;
import com.example.houseme.Models.TestModelClass;
import com.example.houseme.R;
import com.example.houseme.databinding.FragmentInfoBinding;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

import dmax.dialog.SpotsDialog;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInfo extends Fragment {

    FragmentInfoBinding binding;

    String region, location, bedrooms, bath, price, desc;
    Boolean hasParking, hasCctv, hasWifi, hasAccessibility, hasLaundry, hasPlayground, hasSolar,
            hasSwimming, forSale, isResidential;

    AlertDialog addPropertyDialog;
    StorageReference mStorageRef;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    Uri featuredImageUrl;
    String featuredImageUrlString;
    String featuredImageDownloadUrl;
    String featuredImageFileName;

    public FragmentInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false);

        addPropertyDialog = new SpotsDialog(getContext(), R.style.savePropertyInfoAlert);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        int white = ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorWhite);
        int purple = ContextCompat.getColor(getContext(), R.color.tab_background_selected);

        /*ROW 1*/
        binding.amParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorDrawable colorDrawable = (ColorDrawable) binding.amParking.getBackground();
                int colorParking = colorDrawable.getColor();

                if (colorParking == white){
                    binding.amParking.setBackgroundColor(purple);
                    hasParking = true;
                    Toast.makeText(getContext(), "Has Parking", Toast.LENGTH_SHORT).show();

                }else{

                    binding.amParking.setBackgroundColor(white);
                    hasParking = false;
                    Toast.makeText(getContext(), "Has NO Parking", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.amCctv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable colorDrawableCctv = (ColorDrawable) binding.amCctv.getBackground();
                int colorCctv = colorDrawableCctv.getColor();

                if (colorCctv == white){
                    binding.amCctv.setBackgroundColor(purple);
                    hasCctv = true;
                }else{
                    binding.amCctv.setBackgroundColor(white);
                    hasCctv = false;
                }

            }
        });

        binding.amWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable colorDrawableWifi = (ColorDrawable) binding.amWifi.getBackground();
                int colorWifi = colorDrawableWifi.getColor();

                if (colorWifi == white){
                    binding.amWifi.setBackgroundColor(purple);
                    hasWifi = true;

                }else{
                    binding.amWifi.setBackgroundColor(white);
                    hasWifi = false;
                }

            }
        });

        binding.amAccesibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable colorDrawableAccessibility = (ColorDrawable) binding.amAccesibility.getBackground();
                int colorWifi = colorDrawableAccessibility.getColor();

                if (colorWifi == white){
                    binding.amAccesibility.setBackgroundColor(purple);
                    hasAccessibility = true;

                }else{
                    binding.amAccesibility.setBackgroundColor(white);
                    hasAccessibility = false;
                }
            }
        });

        /*ROW 2*/
        binding.amLaundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable colorDrawableLaundry = (ColorDrawable) binding.amLaundry.getBackground();
                int colorWifi = colorDrawableLaundry.getColor();

                if (colorWifi == white){
                    binding.amLaundry.setBackgroundColor(purple);
                    hasLaundry = true;

                }else{
                    binding.amLaundry.setBackgroundColor(white);
                    hasLaundry = false;
                }

            }
        });

        binding.amPlayground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable colorDrawablePlayground = (ColorDrawable) binding.amPlayground.getBackground();
                int colorWifi = colorDrawablePlayground.getColor();

                if (colorWifi == white){
                    binding.amPlayground.setBackgroundColor(purple);
                    hasPlayground = true;

                }else{
                    binding.amPlayground.setBackgroundColor(white);
                    hasPlayground = false;
                }

            }
        });

        binding.amSolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable colorDrawableSolar = (ColorDrawable) binding.amSolar.getBackground();
                int colorWifi = colorDrawableSolar.getColor();

                if (colorWifi == white){
                    binding.amSolar.setBackgroundColor(purple);
                    hasSolar = true;

                }else{
                    binding.amSolar.setBackgroundColor(white);
                    hasSolar = false;
                }

            }
        });

        binding.amSwimming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable colorDrawableSwimming = (ColorDrawable) binding.amSwimming.getBackground();
                int colorWifi = colorDrawableSwimming.getColor();

                if (colorWifi == white){
                    binding.amSwimming.setBackgroundColor(purple);
                    hasSwimming = true;

                }else{
                    binding.amSwimming.setBackgroundColor(white);
                    hasSwimming = false;
                }

            }
        });

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioButtonRental:
                        forSale = false;
                        break;
                    case R.id.radioButtonSale:
                        forSale = true;
                        break;
                }
            }
        });

        binding.radioGroupPurpose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioButtonResidential:
                        isResidential = true;
                        break;
                    case R.id.radioButtonCommercial:
                        isResidential = false;
                        break;
                }
            }
        });

        /*TO NEXT PAGE*/
        binding.infoNextLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                region = binding.townLayout.getEditText().getText().toString();
                location = binding.locationLayout.getEditText().getText().toString();
                bedrooms = binding.bedroomsLayout.getEditText().getText().toString();
                bath = binding.bathroomLayout.getEditText().getText().toString();
                price = binding.priceLayout.getEditText().getText().toString();
                desc = binding.descriptionLayout.getEditText().getText().toString();

                validateFields(region,location, bedrooms, bath, price, desc, hasParking, hasCctv, hasWifi, hasAccessibility, hasLaundry, hasPlayground, hasSolar,
                        hasSwimming, forSale, isResidential);
            }
        });

        return binding.getRoot();
    }

    private void validateFields(String region, String location, String bedrooms, String bath, String price,
                                String desc, Boolean hasParking, Boolean hasCctv, Boolean hasWifi,
                                Boolean hasAccessibility, Boolean hasLaundry, Boolean hasPlayground,
                                Boolean hasSolar, Boolean hasSwimming, Boolean forSale, Boolean isResidential) {

        //CHECK IF DEVICE IS CONNECTED TO THE INTERNET
        ConnectivityManager cm = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(!isConnected){

            Toast.makeText(getContext(), "Please connect to the internet and try again.", Toast.LENGTH_LONG).show();

        }else {

            binding.town.setError(null);
            binding.location.setError(null);
            binding.bedrooms.setError(null);
            binding.bathrooms.setError(null);
            binding.price.setError(null);
            binding.description.setError(null);
            binding.radioButtonSale.setError(null);
            binding.radioButtonRental.setError(null);

            boolean cancel = false;
            View focusView = null;

            if (TextUtils.isEmpty(region)) {
                binding.town.setError("Please enter the Region.");
                focusView = binding.town;
                cancel = true;
            }else if (TextUtils.isEmpty(location)) {
                binding.location.setError("Please enter the Property Location.");
                focusView = binding.location;
                cancel = true;
            } else if (TextUtils.isEmpty(bedrooms)) {
                binding.bedrooms.setError("Please enter the number of bedrooms");
                focusView = binding.bedrooms;
                cancel = true;
            }else if (TextUtils.isEmpty(bath)) {
                binding.bathrooms.setError("Please enter the number of bathrooms");
                focusView = binding.bathrooms;
                cancel = true;
            }else if (TextUtils.isEmpty(price)) {
                binding.price.setError("Please enter the price");
                focusView = binding.price;
                cancel = true;
            }else if (TextUtils.isEmpty(desc)) {
                binding.description.setError("Please enter the property description");
                focusView = binding.description;
                cancel = true;
            }else if (forSale == null){
                binding.radioButtonRental.setTextColor(getResources().getColor(R.color.colorGoogle));
                binding.radioButtonSale.setTextColor(getResources().getColor(R.color.colorGoogle));
                binding.radioButtonSale.setError("Please select one option");
                focusView = binding.radioGroup;
                cancel = true;
            }else if (isResidential == null){
                binding.radioButtonCommercial.setTextColor(getResources().getColor(R.color.colorGoogle));
                binding.radioButtonResidential.setTextColor(getResources().getColor(R.color.colorGoogle));
                binding.radioButtonCommercial.setError("Please select one option");
                focusView = binding.radioGroupPurpose;
                cancel = true;
            }
            if (cancel) {
                focusView.requestFocus();
            }

            else {
              /*START HERE*/
                /*Add data to intent and carry it over to the next fragment*/

                addPropertyDialog.setCancelable(false);
                addPropertyDialog.show();

                uploadPropertyData();

            }

        }
    }

    private void uploadPropertyData() {

        TestModelClass testModelClass = new TestModelClass(null, null, price, region, location, bedrooms, bath, desc, hasParking, hasCctv, hasWifi, hasAccessibility, hasLaundry, hasPlayground, hasSolar,
                hasSwimming, forSale, isResidential );

        firestore.collection("Properties").add(testModelClass).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                addPropertyDialog.cancel();

//                binding.town.setText("");
//                binding.location.setText("");
//                binding.bedrooms.setText("");
//                binding.bathrooms.setText("");
//                binding.price.setText("");
//                binding.description.setText("");
//                binding.radioGroup.clearCheck();
//
//                Toast.makeText(getContext(), "Property has been added Successfully!", Toast.LENGTH_LONG).show();

                TabLayout tabLayout = getActivity().findViewById(R.id.tabLayout);
                tabLayout.getTabAt(1).select();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });


    }


}
