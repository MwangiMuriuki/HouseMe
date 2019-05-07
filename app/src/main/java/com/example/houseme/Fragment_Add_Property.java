package com.example.houseme;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Add_Property extends Fragment {

    ImageView imageView;
    TextInputLayout location, size, bath, price, desc;
    Button propertyUpload;
    Uri resultUri;

    public Fragment_Add_Property() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment__add__property, container, false);
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

         propertyUpload = myView.findViewById(R.id.uploadHouse);


        return myView;
    }

    private void selectImage() {
        CropImage.activity()
                .setAspectRatio(2,1)
                .start(getContext(), this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                resultUri = result.getUri();

                imageView.setImageURI(resultUri);

                String downloadUri = resultUri.toString();

                Toast.makeText(getContext(), downloadUri, Toast.LENGTH_LONG).show();

            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Add Property");
    }

}
