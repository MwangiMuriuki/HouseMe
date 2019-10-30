package com.example.houseme.Fragments;


import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.houseme.Models.PropertyInfoModelClass;
import com.example.houseme.R;
import com.example.houseme.databinding.FragmentAddPropertyBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddProperty extends Fragment {

    FragmentAddPropertyBinding binding;
    String location, bedrooms, bath, price, desc;
    Boolean forSale;

    HorizontalScrollView galleryHorizontalScrollView;

    Uri resultUri;
    int PICK_FEATURED_IMAGE= 100;
    int PICK_EXTRA_IMAGE_1 = 101;
    int PICK_EXTRA_IMAGE_2 = 102;
    int PICK_EXTRA_IMAGE_3 = 103;
    int PICK_EXTRA_IMAGE_4 = 104;
    int PICK_EXTRA_IMAGE_5 = 105;
    List<String> imagesEncodedList;
    String imageEncoded;

    ViewPager myViewPager;
    ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public FragmentAddProperty() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_property, container, false);
        init();

//        location = binding.locationLayout.getEditText().getText().toString();
        location = binding.location.getText().toString();
        bedrooms = binding.bedroomsLayout.getEditText().getText().toString();
        bath = binding.bathroomLayout.getEditText().getText().toString();
        price = binding.priceLayout.getEditText().getText().toString();
        desc = binding.descriptionLayout.getEditText().getText().toString();

        binding.featuredImage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 selectFeaturedImage();
             }
        });

        binding.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectExtraImage1();
            }
        });

        binding.image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectExtraImage2();
            }
        });
        binding.image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectExtraImage3();
            }
        });
        binding.image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectExtraImage4();
            }
        });
        binding.image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectExtraImage5();
            }
        });

        binding.uploadProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mthdUploadProperty();
//                validateFields(location, bedrooms, bath, price, desc);
            }
        });

        return binding.getRoot();
    }

    private void init() {
    }

    private void selectFeaturedImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra("crop","true");
        intent.putExtra("aspectX", 4);
        intent.putExtra("aspectY", 3);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select featured image"), PICK_FEATURED_IMAGE);

    }
    private void selectExtraImage1() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra("crop","true");
        intent.putExtra("aspectX", 4);
        intent.putExtra("aspectY", 3);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select extra image 1"), PICK_EXTRA_IMAGE_1);
    }
    private void selectExtraImage2() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra("crop","true");
        intent.putExtra("aspectX", 4);
        intent.putExtra("aspectY", 3);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select extra image 2"), PICK_EXTRA_IMAGE_2);
    }
    private void selectExtraImage3() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra("crop","true");
        intent.putExtra("aspectX", 4);
        intent.putExtra("aspectY", 3);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select extra image 3"), PICK_EXTRA_IMAGE_3);
    }
    private void selectExtraImage4() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra("crop","true");
        intent.putExtra("aspectX", 4);
        intent.putExtra("aspectY", 3);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select extra image 4"), PICK_EXTRA_IMAGE_4);
    }
    private void selectExtraImage5() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra("crop","true");
        intent.putExtra("aspectX", 4);
        intent.putExtra("aspectY", 3);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select extra image 5"), PICK_EXTRA_IMAGE_5);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if (requestCode== PICK_FEATURED_IMAGE && resultCode == RESULT_OK){
                resultUri = data.getData();
                binding.featuredImage.setImageURI(resultUri);
            }
            if (requestCode== PICK_EXTRA_IMAGE_1 && resultCode == RESULT_OK){
                resultUri = data.getData();
                binding.image1.setImageURI(resultUri);
            }
            if (requestCode== PICK_EXTRA_IMAGE_2 && resultCode == RESULT_OK){
                resultUri = data.getData();
                binding.image2.setImageURI(resultUri);
            }
            if (requestCode== PICK_EXTRA_IMAGE_3 && resultCode == RESULT_OK){
                resultUri = data.getData();
                binding.image3.setImageURI(resultUri);
            }
            if (requestCode== PICK_EXTRA_IMAGE_4 && resultCode == RESULT_OK){
                resultUri = data.getData();
                binding.image4.setImageURI(resultUri);
            }
            if (requestCode== PICK_EXTRA_IMAGE_5 && resultCode == RESULT_OK){
                resultUri = data.getData();
                binding.image5.setImageURI(resultUri);
            }
    }

    private void validateFields(String location, String bedrooms, String bath, String price, String desc) {

        //CHECK IF DEVICE IS CONNECTED TO THE INTERNET
        ConnectivityManager cm = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(!isConnected){

            Toast.makeText(getContext(), "Please connect to the internet and try again.", Toast.LENGTH_LONG).show();

        }else {

            binding.location.setError(null);
            binding.bedrooms.setError(null);
            binding.bathrooms.setError(null);
            binding.price.setError(null);
            binding.description.setError(null);

            boolean cancel = false;
            View focusView = null;

            if (TextUtils.isEmpty(location)) {
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
            }
            if (cancel) {
                focusView.requestFocus();
            }else {

                PropertyInfoModelClass propertyInfoModelClass = new PropertyInfoModelClass(null, null, null, null, null, null, location, bedrooms, bath, price, desc, null);
                firestore.collection("Properties").document(location).set(propertyInfoModelClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        binding.location.setText("");
                        binding.bedrooms.setText("");
                        binding.bathrooms.setText("");
                        binding.price.setText("");
                        binding.description.setText("");


                        Toast.makeText(getContext(), "Property has been added Successfully!", Toast.LENGTH_LONG).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        }
    }

    private void mthdUploadProperty() {

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        PropertyInfoModelClass propertyInfoModelClass = new PropertyInfoModelClass(null, null, null, null, null, null, location, bedrooms, bath, price, desc, null);
        firestore.collection("Properties").document(location).set(propertyInfoModelClass).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                binding.location.setText("");
                binding.bedrooms.setText("");
                binding.bathrooms.setText("");
                binding.price.setText("");
                binding.description.setText("");


                Toast.makeText(getContext(), "Property has been added Successfully!", Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Add Property");
    }

}
