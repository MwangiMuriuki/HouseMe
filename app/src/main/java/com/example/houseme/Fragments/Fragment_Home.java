package com.example.houseme.Fragments;


import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.houseme.Adapters.AdapterHouseInfo;
import com.example.houseme.Adapters.Adapter_HomeFeatured;
import com.example.houseme.Adapters.Adapter_HomeOneBdr;
import com.example.houseme.Models.PropertyInfoModelClass;
import com.example.houseme.R;
import com.example.houseme.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Home extends Fragment {

    FragmentHomeBinding binding;

    FirebaseFirestore firebaseFirestoreFeatured;
    FirebaseFirestore firebaseFirestoreOneBdr;
    FirebaseFirestore firebaseFirestoreTwoBdr;
    FirebaseFirestore firebaseFirestoreThreeBdr;

    AdapterHouseInfo adapterHouseInfo;
    Adapter_HomeFeatured adapterHomeFeatured;
    Adapter_HomeOneBdr adapterHomeOneBdr;
    Adapter_HomeOneBdr adapterHomeTwoBdr;
    Adapter_HomeOneBdr adapterHomeThreeBdr;

    List<PropertyInfoModelClass> propertyInfoListFeatured;
    List<PropertyInfoModelClass> propertyInfoListOneBdr;
    List<PropertyInfoModelClass> propertyInfoListTwoBdr;
    List<PropertyInfoModelClass> propertyInfoListThreeBdr;


    Uri imageUri;

    public Fragment_Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

       populateFeaturedData();
       populateOneBdrData();
       populateTwoBdrData();
       populateThreeBdrData();

        return binding.getRoot();
    }

    private void populateFeaturedData() {

        propertyInfoListFeatured = new ArrayList<>();
        firebaseFirestoreFeatured = FirebaseFirestore.getInstance();

        binding.rvFeatured.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapterHomeFeatured = new Adapter_HomeFeatured(propertyInfoListFeatured, getActivity(), firebaseFirestoreFeatured);

        binding.rvFeatured.setAdapter(adapterHomeFeatured);

        firebaseFirestoreFeatured.collection("Properties")
                .whereGreaterThan("bedrooms", "3")
                .orderBy("bedrooms", Query.Direction.ASCENDING)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    for (DocumentSnapshot documentSnapshot: task.getResult()){

                        PropertyInfoModelClass propertyInfo = new PropertyInfoModelClass(
                                documentSnapshot.getString("featured_image"),
                                documentSnapshot.getString("extra_image_one"),
                                documentSnapshot.getString("extra_image_two"),
                                documentSnapshot.getString("extra_image_three"),
                                documentSnapshot.getString("extra_image_four"),
                                documentSnapshot.getString("extra_image_five"),
                                documentSnapshot.getString("price"),
                                documentSnapshot.getString("region"),
                                documentSnapshot.getString("location"),
                                documentSnapshot.getString("bedrooms"),
                                documentSnapshot.getString("bathrooms"),
                                documentSnapshot.getString("description"),
                                documentSnapshot.getBoolean("forSale"));

                        imageUri = Uri.parse(documentSnapshot.getString("featured_image"));

                        propertyInfoListFeatured.add(propertyInfo);
                    }

                    adapterHomeFeatured.notifyDataSetChanged();

                }else{
                    Toast.makeText(getContext(), "Error Getting info", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

    }

    private void populateOneBdrData() {

        propertyInfoListOneBdr = new ArrayList<>();
        firebaseFirestoreOneBdr = FirebaseFirestore.getInstance();

        binding.rvOneBdr.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapterHomeOneBdr = new Adapter_HomeOneBdr(propertyInfoListOneBdr, getActivity(), firebaseFirestoreOneBdr);

        binding.rvOneBdr.setAdapter(adapterHomeOneBdr);

        firebaseFirestoreOneBdr.collection("Properties")
                .whereEqualTo("bedrooms", "1")
                .orderBy("region", Query.Direction.ASCENDING)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    for (DocumentSnapshot documentSnapshot: task.getResult()){

                        PropertyInfoModelClass propertyInfo = new PropertyInfoModelClass(
                                documentSnapshot.getString("featured_image"),
                                documentSnapshot.getString("extra_image_one"),
                                documentSnapshot.getString("extra_image_two"),
                                documentSnapshot.getString("extra_image_three"),
                                documentSnapshot.getString("extra_image_four"),
                                documentSnapshot.getString("extra_image_five"),
                                documentSnapshot.getString("price"),
                                documentSnapshot.getString("region"),
                                documentSnapshot.getString("location"),
                                documentSnapshot.getString("bedrooms"),
                                documentSnapshot.getString("bathrooms"),
                                documentSnapshot.getString("description"),
                                documentSnapshot.getBoolean("forSale"));

                        imageUri = Uri.parse(documentSnapshot.getString("featured_image"));

                        propertyInfoListOneBdr.add(propertyInfo);
                    }

                    adapterHomeOneBdr.notifyDataSetChanged();

                }else{
                    Toast.makeText(getContext(), "Error Getting info", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

    }

    private void populateTwoBdrData() {

        propertyInfoListTwoBdr = new ArrayList<>();
        firebaseFirestoreTwoBdr = FirebaseFirestore.getInstance();

        binding.rvTwoBdr.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapterHomeTwoBdr = new Adapter_HomeOneBdr(propertyInfoListTwoBdr, getActivity(), firebaseFirestoreTwoBdr);

        binding.rvTwoBdr.setAdapter(adapterHomeTwoBdr);

        firebaseFirestoreOneBdr.collection("Properties")
                .whereEqualTo("bedrooms", "2")
                .orderBy("region", Query.Direction.ASCENDING)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    for (DocumentSnapshot documentSnapshot: task.getResult()){

                        PropertyInfoModelClass propertyInfo = new PropertyInfoModelClass(
                                documentSnapshot.getString("featured_image"),
                                documentSnapshot.getString("extra_image_one"),
                                documentSnapshot.getString("extra_image_two"),
                                documentSnapshot.getString("extra_image_three"),
                                documentSnapshot.getString("extra_image_four"),
                                documentSnapshot.getString("extra_image_five"),
                                documentSnapshot.getString("price"),
                                documentSnapshot.getString("region"),
                                documentSnapshot.getString("location"),
                                documentSnapshot.getString("bedrooms"),
                                documentSnapshot.getString("bathrooms"),
                                documentSnapshot.getString("description"),
                                documentSnapshot.getBoolean("forSale"));

                        imageUri = Uri.parse(documentSnapshot.getString("featured_image"));

                        propertyInfoListTwoBdr.add(propertyInfo);
                    }

                    adapterHomeTwoBdr.notifyDataSetChanged();

                }else{
                    Toast.makeText(getContext(), "Error Getting info", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

    }

    private void populateThreeBdrData() {

        propertyInfoListThreeBdr = new ArrayList<>();
        firebaseFirestoreThreeBdr = FirebaseFirestore.getInstance();

        binding.rvThreeBdr.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapterHomeThreeBdr = new Adapter_HomeOneBdr(propertyInfoListThreeBdr, getActivity(), firebaseFirestoreThreeBdr);

        binding.rvThreeBdr.setAdapter(adapterHomeThreeBdr);

        firebaseFirestoreOneBdr.collection("Properties")
                .whereEqualTo("bedrooms", "3")
                .orderBy("region", Query.Direction.ASCENDING)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    for (DocumentSnapshot documentSnapshot: task.getResult()){

                        PropertyInfoModelClass propertyInfo = new PropertyInfoModelClass(
                                documentSnapshot.getString("featured_image"),
                                documentSnapshot.getString("extra_image_one"),
                                documentSnapshot.getString("extra_image_two"),
                                documentSnapshot.getString("extra_image_three"),
                                documentSnapshot.getString("extra_image_four"),
                                documentSnapshot.getString("extra_image_five"),
                                documentSnapshot.getString("price"),
                                documentSnapshot.getString("region"),
                                documentSnapshot.getString("location"),
                                documentSnapshot.getString("bedrooms"),
                                documentSnapshot.getString("bathrooms"),
                                documentSnapshot.getString("description"),
                                documentSnapshot.getBoolean("forSale"));

                        imageUri = Uri.parse(documentSnapshot.getString("featured_image"));

                        propertyInfoListThreeBdr.add(propertyInfo);
                    }

                    adapterHomeThreeBdr.notifyDataSetChanged();

                }else{
                    Toast.makeText(getContext(), "Error Getting info", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Home");
    }

}
