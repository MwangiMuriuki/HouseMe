package com.example.houseme.Fragments;


import android.app.AlertDialog;
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

import com.example.houseme.Adapters.MultipleImagesAdpter;
import com.example.houseme.Models.PropertyInfoModelClass;
import com.example.houseme.Models.TestModelClass;
import com.example.houseme.R;
import com.example.houseme.databinding.FragmentAddPropertyBinding;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dmax.dialog.SpotsDialog;

import static android.app.Activity.RESULT_OK;
import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddProperty extends Fragment {

    FragmentAddPropertyBinding binding;
    String region, location, bedrooms, bath, price, desc;
    Boolean hasParking, hasCctv, hasWifi, hasAccessibility, hasLaundry, hasPlayground, hasSolar,
            hasSwimming, forSale, isResidential;

    RecyclerView extraImagesRV;
    List<String> imageNameList;
    MultipleImagesAdpter multipleImagesAdpter;
    String FileName;
    List<String> downloadUrls;

    StorageReference mStorageRef;

    Uri featuredImageUrl;
    String featuredImageUrlString;
    String featuredImageDownloadUrl;
    String featuredImageFileName;

    Uri uri;
    String pathImageName;

    int PICK_MULTIPLE = 1000;
    int PICK_FEATURED_IMAGE= 100;

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    AlertDialog addPropertyDialog;

    public FragmentAddProperty() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_property, container, false);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        addPropertyDialog = new SpotsDialog(getContext(), R.style.addPropertyAlert);

        imageNameList = new ArrayList<>();

        multipleImagesAdpter = new MultipleImagesAdpter(imageNameList);
        binding.multipleImagesRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.multipleImagesRV.setAdapter(multipleImagesAdpter);

//        if (multipleImagesAdpter.getItemCount()==0){
//
//            binding.multipleImagesRV.setVisibility(View.GONE);
//        }else {
//
//            binding.multipleImagesRV.setVisibility(View.VISIBLE);
//        }

        binding.placeHolderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFeaturedImage();
            }
        });

        binding.featuredImage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 selectFeaturedImage();
             }
        });

        binding.multipleImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickMultiple();
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

        binding.uploadProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                region = binding.townLayout.getEditText().getText().toString();
                location = binding.locationLayout.getEditText().getText().toString();
                bedrooms = binding.bedroomsLayout.getEditText().getText().toString();
                bath = binding.bathroomLayout.getEditText().getText().toString();
                price = binding.priceLayout.getEditText().getText().toString();
                desc = binding.descriptionLayout.getEditText().getText().toString();

//                mthdUploadProperty();
//                getData(location, bedrooms, bath, price, desc);
                validateFields(region,location, bedrooms, bath, price, desc, forSale);
            }
        });

        return binding.getRoot();
    }

    private void pickMultiple() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.putExtra("crop","true");
        intent.putExtra("aspectX", 4);
        intent.putExtra("aspectY", 3);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select other images"), PICK_MULTIPLE);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == PICK_FEATURED_IMAGE && resultCode == RESULT_OK){
                if (data.getData() != null){

                    featuredImageUrl = data.getData();

                    featuredImageUrlString = featuredImageUrl.toString();

                    binding.featuredImage.setImageURI(featuredImageUrl);
                    binding.placeHolderLayout.setVisibility(View.GONE);
                    binding.featuredImage.setVisibility(View.VISIBLE);

                    featuredImageFileName = getFeaturedFileName(featuredImageUrl);
                }else {

                    binding.placeHolderLayout.setVisibility(View.VISIBLE);
                    binding.featuredImage.setVisibility(View.GONE);

                    Toast.makeText(getContext(), "No Image Selected", Toast.LENGTH_LONG).show();
                }
            }

            if (requestCode== PICK_MULTIPLE && resultCode == RESULT_OK){
                ClipData clipData = data.getClipData();

                /*User has selected one image*/
                if (data.getData()!=null){

                    Toast.makeText(getContext(), "Please select more than one image", Toast.LENGTH_LONG).show();

                    binding.multipleImagesRV.setVisibility(View.GONE);

                } else if (clipData != null){

                    int totalImages = clipData.getItemCount();

                    for (int i = 0; i<totalImages; i++){

                        ClipData.Item item = clipData.getItemAt(i);
                        uri = item.getUri();
                        Log.e("Selected Images URLs", uri.toString());
                        pathImageName = getFileName(uri);

                        imageNameList.add(String.valueOf(uri));

                        multipleImagesAdpter.notifyDataSetChanged();

                        binding.multipleImagesRV.setVisibility(View.VISIBLE);
                    }
                }else {

                    Toast.makeText(getContext(), "No images selected", Toast.LENGTH_LONG).show();

                    binding.multipleImagesRV.setVisibility(View.GONE);
                }
            }

    }

    private String getFeaturedFileName(Uri featuredImage) {
        String result = null;
        if (featuredImage.getScheme().equals("content")) {
            Cursor cursor = getContext().getContentResolver().query(featuredImage, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = featuredImage.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private void validateFields(String region, String location, String bedrooms, String bath, String price, String desc, Boolean forSale) {

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

            }
            if (cancel) {
                focusView.requestFocus();
            }

            else {

                addPropertyDialog.setCancelable(false);
                addPropertyDialog.show();

                uploadFeaturedImage();

            }
        }
    }

    private void uploadFeaturedImage() {

        StorageReference storageRef = mStorageRef.child("Test Images").child("Featured Images").child(featuredImageFileName);

        UploadTask uploadTaskFeatured = storageRef.putFile(featuredImageUrl);

        Task<Uri> task =uploadTaskFeatured.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                return storageRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                featuredImageDownloadUrl = storageRef.getDownloadUrl().toString();

                uploadExtraImages(featuredImageDownloadUrl);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Error on Featured image: ", e);

            }
        });

/*
        storageRef.putFile(featuredImageUrl).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        featuredImageDownloadUrl = uri.toString();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.d(TAG, "Error getting featured image download url: ", e);
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

                uploadExtraImages(featuredImageDownloadUrl);

            }
        });
*/

    }

    private void uploadExtraImages(String featuredImageDownloadUrl) {

        StorageReference multipleImagesRef = mStorageRef.child("Test Images").child(pathImageName);

        UploadTask uploadTaskMultiple = multipleImagesRef.putFile(uri);

        Task<Uri> task =uploadTaskMultiple.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                return multipleImagesRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                downloadUrls = Collections.singletonList(uri.toString());

                uploadPropertyData(featuredImageDownloadUrl, downloadUrls );


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Error on multiple images: ", e);
            }
        });

/*
        multipleImagesRef.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                multipleImagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        downloadUrls = Collections.singletonList(uri.toString());

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Error getting multiple images download url: ", e);
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                uploadPropertyData(featuredImageDownloadUrl, downloadUrls );
            }
        });
*/
    }

    private void uploadPropertyData(String featuredImageDownloadUrl, List<String> downloadUrls) {

        if (featuredImageDownloadUrl == null || featuredImageDownloadUrl.isEmpty()){

            Toast.makeText(getContext(), "Featured image Download URL is empty", Toast.LENGTH_LONG).show();

        }else if(downloadUrls == null || downloadUrls.isEmpty()){

            Toast.makeText(getContext(), "Multiple images Download URL is empty", Toast.LENGTH_LONG).show();

        } else {

            TestModelClass testModelClass = new TestModelClass(null, null, price, region, location, bedrooms, bath, desc, hasParking, hasCctv, hasWifi, hasAccessibility, hasLaundry, hasPlayground, hasSolar,
                    hasSwimming, forSale, isResidential );

            firestore.collection("Properties").add(testModelClass).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {

                    addPropertyDialog.cancel();

                    binding.town.setText("");
                    binding.location.setText("");
                    binding.bedrooms.setText("");
                    binding.bathrooms.setText("");
                    binding.price.setText("");
                    binding.description.setText("");
                    binding.radioGroup.clearCheck();

                    Toast.makeText(getContext(), "Property has been added Successfully!", Toast.LENGTH_LONG).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Add Property");
    }

}
