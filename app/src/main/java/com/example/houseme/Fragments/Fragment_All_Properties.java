package com.example.houseme.Fragments;


import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.houseme.Adapters.AdapterHouseInfo;
import com.example.houseme.Models.ModelClassHouseInfo;
import com.example.houseme.Models.PropertyInfoModelClass;
import com.example.houseme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fragment_All_Properties extends Fragment {

    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    EditText search;

    List<ModelClassHouseInfo> list;
    List<PropertyInfoModelClass> propertyInfoList;
    AdapterHouseInfo adapterHouseInfo;

    Uri imageUri;

    public Fragment_All_Properties() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.fragment_home, container, false);
        list = new ArrayList<>();
        propertyInfoList = new ArrayList<>();

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = vw.findViewById(R.id.housesRecyclerView);
        GridLayoutManager grid = new GridLayoutManager(Objects.requireNonNull(getActivity()).getApplicationContext(), 2);
        recyclerView.setLayoutManager(grid);

        adapterHouseInfo = new AdapterHouseInfo(propertyInfoList, getActivity(), firebaseFirestore);
        recyclerView.setAdapter(adapterHouseInfo);

        firebaseFirestore.collection("Properties").orderBy("region", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

                        propertyInfoList.add(propertyInfo);
                    }

                    adapterHouseInfo.notifyDataSetChanged();

                }else{
                    Toast.makeText(getContext(), "Error Getting info", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return vw;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("All Properties");
    }

}
