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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddProperty extends Fragment {

    FragmentAddPropertyBinding binding;
    String region, location, bedrooms, bath, price, desc;

    StorageReference mStorageRef;

    Uri featuredImage;
    Uri extraImage1;
    Uri extraImage2;
    Uri extraImage3;
    Uri extraImage4;
    Uri extraImage5;

    String featured, extra1, extra2, extra3, extra4, extra5;
    String featuredDownloadUrl, extra1DownloadUrl, extra2DownloadUrl, extra3DownloadUrl, extra4DownloadUrl, extra5DownloadUrl;

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

        mStorageRef = FirebaseStorage.getInstance().getReference();

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

                region = binding.townLayout.getEditText().getText().toString();
                location = binding.locationLayout.getEditText().getText().toString();
                bedrooms = binding.bedroomsLayout.getEditText().getText().toString();
                bath = binding.bathroomLayout.getEditText().getText().toString();
                price = binding.priceLayout.getEditText().getText().toString();
                desc = binding.descriptionLayout.getEditText().getText().toString();

//                mthdUploadProperty();
//                getData(location, bedrooms, bath, price, desc);
                validateFields(region,location, bedrooms, bath, price, desc);
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
                featuredImage = data.getData();
                binding.featuredImage.setImageURI(featuredImage);
                featured = featuredImage.toString();
            }
            if (requestCode== PICK_EXTRA_IMAGE_1 && resultCode == RESULT_OK){
                extraImage1 = data.getData();
                binding.image1.setImageURI(extraImage1);
            }
            if (requestCode== PICK_EXTRA_IMAGE_2 && resultCode == RESULT_OK){
                extraImage2 = data.getData();
                binding.image2.setImageURI(extraImage2);
            }
            if (requestCode== PICK_EXTRA_IMAGE_3 && resultCode == RESULT_OK){
                extraImage3 = data.getData();
                binding.image3.setImageURI(extraImage3);
            }
            if (requestCode== PICK_EXTRA_IMAGE_4 && resultCode == RESULT_OK){
                extraImage4 = data.getData();
                binding.image4.setImageURI(extraImage4);
            }
            if (requestCode== PICK_EXTRA_IMAGE_5 && resultCode == RESULT_OK){
                extraImage5 = data.getData();
                binding.image5.setImageURI(extraImage5);
            }
    }

    private void validateFields(String region, String location, String bedrooms, String bath, String price, String desc) {

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

            boolean cancel = false;
            View focusView = null;

            if (TextUtils.isEmpty(region)) {
                binding.location.setError("Please enter the Region.");
                focusView = binding.location;
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
            }
            if (cancel) {
                focusView.requestFocus();
            }else {

                final StorageReference storageReference = mStorageRef.child("house_images/");

                UploadTask uploadTask = storageReference.putFile(featuredImage);
                UploadTask uploadTask1 = storageReference.putFile(extraImage1);
                UploadTask uploadTask2 = storageReference.putFile(extraImage2);
                UploadTask uploadTask3 = storageReference.putFile(extraImage3);
                UploadTask uploadTask4 = storageReference.putFile(extraImage4);
                UploadTask uploadTask5 = storageReference.putFile(extraImage5);

                Task<Uri> task = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return storageReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                         featuredDownloadUrl = storageReference.getDownloadUrl().toString();
                    }
                });

                Task<Uri> task1 = uploadTask1.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return storageReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        extra1DownloadUrl = storageReference.getDownloadUrl().toString();
                    }
                });

                Task<Uri> task2 = uploadTask2.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return storageReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        extra2DownloadUrl = storageReference.getDownloadUrl().toString();
                    }
                });

                Task<Uri> task3 = uploadTask3.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return storageReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        extra3DownloadUrl = storageReference.getDownloadUrl().toString();
                    }
                });

                Task<Uri> task4 = uploadTask4.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return storageReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        extra4DownloadUrl = storageReference.getDownloadUrl().toString();
                    }
                });
                Task<Uri> task5 = uploadTask5.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return storageReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        extra5DownloadUrl = storageReference.getDownloadUrl().toString();
                    }
                });

                PropertyInfoModelClass propertyInfoModelClass = new PropertyInfoModelClass(featuredDownloadUrl, extra1DownloadUrl, extra2DownloadUrl, extra3DownloadUrl, extra4DownloadUrl, extra5DownloadUrl, price, region, location, bedrooms, bath, desc, null);
//                firestore.collection("Properties").document(region).set(propertyInfoModelClass).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//
//                        binding.town.setText("");
//                        binding.location.setText("");
//                        binding.bedrooms.setText("");
//                        binding.bathrooms.setText("");
//                        binding.price.setText("");
//                        binding.description.setText("");
//
//                        Toast.makeText(getContext(), "Property has been added Successfully!", Toast.LENGTH_LONG).show();
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
//                    }
//                });

                firestore.collection("Properties").add(propertyInfoModelClass).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        binding.town.setText("");
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

                        Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

    private void mthdUploadProperty() {

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        PropertyInfoModelClass propertyInfoModelClass = new PropertyInfoModelClass(null, null, null, null, null, null,region, location, bedrooms, bath, price, desc, null);

        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

        firebaseFirestore.collection("properties").add(propertyInfoModelClass).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

//        firebaseFirestore.collection("users").document("test").set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//
//                Toast.makeText(getContext(), "Success", Toast.LENGTH_LONG).show();
//
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//                Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
//
//            }
//        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Add Property");
    }

}
