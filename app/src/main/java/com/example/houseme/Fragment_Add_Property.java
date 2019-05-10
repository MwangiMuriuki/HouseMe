package com.example.houseme;


import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Add_Property extends Fragment {

    ImageView imageView;
    TextInputLayout location, size, bath, price, desc;
    Button addPictures, propertyUpload;

    HorizontalScrollView galleryHorizontalScrollView;

    Uri resultUri;
    int PICK_IMAGE_SINGLE= 1000;
    int PICK_IMAGE_MULTIPLE = 1;
    List<String> imagesEncodedList;
    String imageEncoded;

    ViewPager myViewPager;
    ArrayList<Integer> ImagesArray = new ArrayList<Integer>();


    public Fragment_Add_Property() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment__add__property, container, false);
        init();
         imageView = myView.findViewById(R.id.selectHouseImage);
         imageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 selectImage();
             }
         });

         location = myView.findViewById(R.id.location);
         size = myView.findViewById(R.id.size);
         bath = myView.findViewById(R.id.bathrooms);
         price = myView.findViewById(R.id.price);
         desc = myView.findViewById(R.id.desc);

         addPictures = myView.findViewById(R.id.selectAdditionalImages);
         addPictures.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

//                 galleryHorizontalScrollView.setVisibility(View.VISIBLE);

//                 selectMultipleImages();
             }
         });
         propertyUpload = myView.findViewById(R.id.uploadHouse);

        return myView;
    }

    private void init() {
    }

    private void selectImage() {

//                CropImage.activity()
//                .setAspectRatio(4,3)
//                .start(getContext(), this);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra("crop","true");
        intent.putExtra("aspectX", 4);
        intent.putExtra("aspectY", 3);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select image"), PICK_IMAGE_SINGLE);

    }

    private void selectMultipleImages() {
//        CropImage.activity()
//                .setAspectRatio(4,3)
//                .start(getContext(), this);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.putExtra("crop","true");
        intent.putExtra("aspectX", 4);
        intent.putExtra("aspectY", 3);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select additonal images"), 1000);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
//            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//
//                resultUri = result.getUri();
//
//                imageView.setImageURI(resultUri);
//
//                String downloadUri = resultUri.toString();
//
//                Toast.makeText(getContext(), downloadUri, Toast.LENGTH_LONG).show();
//
//            }
//        }

            if (requestCode== PICK_IMAGE_SINGLE && resultCode == RESULT_OK){
                resultUri = data.getData();
                imageView.setImageURI(resultUri);
            }
            if (requestCode== PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK){
                if (data.getClipData() != null){



                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    imagesEncodedList = new ArrayList<String>();

                    ClipData mClipData = data.getClipData();
                    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                    for (int i = 0; i < mClipData.getItemCount(); i++){
                        ClipData.Item item = mClipData.getItemAt(i);
                        Uri newUri = item.getUri();
                        mArrayUri.add(newUri);

                        Cursor cursor = getContext().getContentResolver().query(newUri, filePathColumn, null, null,null);
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        imageEncoded = cursor.getString(columnIndex);
                        cursor.close();


                    }

                }else {
                    Toast.makeText(getContext(), "You have not selected any images", Toast.LENGTH_LONG).show();

                }
            }
        } catch (Exception e){
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG)
                    .show();

        }


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Add Property");
    }

}
