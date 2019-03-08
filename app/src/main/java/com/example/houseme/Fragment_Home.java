package com.example.houseme;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

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

    List<ModalClass> list;
    HouseInfo adapterHouseInfo;

    public Fragment_Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.houses_recycler_view_layout, container, false);
        list = new ArrayList<>();

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = vw.findViewById(R.id.housesRecyclerView);
        GridLayoutManager grid = new GridLayoutManager(Objects.requireNonNull(getActivity()).getApplicationContext(), 2);
        recyclerView.setLayoutManager(grid);

        adapterHouseInfo = new HouseInfo(list, getActivity(), firebaseFirestore);
        recyclerView.setAdapter(adapterHouseInfo);

        firebaseFirestore.collection("Houses").orderBy("price", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    for (DocumentSnapshot documentSnapshot: task.getResult()){

                        ModalClass modalClass = new ModalClass(documentSnapshot.getString("price"),
                                documentSnapshot.getString("location"),
                                documentSnapshot.getString("size"),
                                documentSnapshot.getString("bath"),
                                documentSnapshot.getString("description"),
                                documentSnapshot.getString("picture"));

                        list.add(modalClass);
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
