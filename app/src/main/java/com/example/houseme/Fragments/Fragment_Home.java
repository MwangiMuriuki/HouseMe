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

public class Fragment_Home extends Fragment {

    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    EditText search;

    List<ModelClassHouseInfo> list;
    AdapterHouseInfo adapterHouseInfo;

    Uri imageUri;

    public Fragment_Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.fragment_home, container, false);
        list = new ArrayList<>();

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = vw.findViewById(R.id.housesRecyclerView);
        GridLayoutManager grid = new GridLayoutManager(Objects.requireNonNull(getActivity()).getApplicationContext(), 2);
        recyclerView.setLayoutManager(grid);

        adapterHouseInfo = new AdapterHouseInfo(list, getActivity(), firebaseFirestore);
        recyclerView.scrollToPosition(list.size() -1);
        recyclerView.setAdapter(adapterHouseInfo);

        firebaseFirestore.collection("Houses").orderBy("price", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    for (DocumentSnapshot documentSnapshot: task.getResult()){

                        ModelClassHouseInfo modelClassHouseInfo = new ModelClassHouseInfo(documentSnapshot.getString("price"),
                                documentSnapshot.getString("location"),
                                documentSnapshot.getString("size"),
                                documentSnapshot.getString("bath"),
                                documentSnapshot.getString("description"),
                                documentSnapshot.getString("picture"));

                        imageUri = Uri.parse(documentSnapshot.getString("picture"));


                        Toast.makeText(getContext(), imageUri.toString(), Toast.LENGTH_LONG).show();

                        list.add(modelClassHouseInfo);
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
        getActivity().setTitle("Home");
    }

}
