package com.example.houseme.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.houseme.Activities.ActivityViewProperty;
import com.example.houseme.Models.ModelClassHouseInfo;
import com.example.houseme.Models.PropertyInfoModelClass;
import com.example.houseme.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class AdapterHouseInfo extends RecyclerView.Adapter<AdapterHouseInfo.ViewHolder> {

    List<ModelClassHouseInfo> modelClassHouseInfoList;
    List<PropertyInfoModelClass> propertyInfoModelClassList;
    Context context;
    FirebaseFirestore firebaseFirestore;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    Uri imageUri;
    Boolean isForSale;

    public AdapterHouseInfo(List<PropertyInfoModelClass> propertyInfoModelClassList, Context context, FirebaseFirestore firebaseFirestore) {
        this.propertyInfoModelClassList = propertyInfoModelClassList;
        this.context = context;
        this.firebaseFirestore = firebaseFirestore;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View myView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.houses_card_view, viewGroup, false);
        AdapterHouseInfo.ViewHolder viewHolder = new AdapterHouseInfo.ViewHolder(myView);

        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder myViewHolder, int position) {
        int pos = myViewHolder.getAdapterPosition();

        PropertyInfoModelClass propertyInfoModelClass = propertyInfoModelClassList.get(pos);

        int housePrice = Integer.parseInt(propertyInfoModelClass.getPrice());

        myViewHolder.price.setText("Ksh. " + NumberFormat.getNumberInstance(Locale.US).format(housePrice));
        myViewHolder.region.setText(propertyInfoModelClass.getRegion());
        myViewHolder.location.setText(propertyInfoModelClass.getLocation());
        myViewHolder.bedrooms.setText(propertyInfoModelClass.getBedrooms());
        myViewHolder.bathroom.setText(propertyInfoModelClass.getBathrooms());

        imageUri = Uri.parse(propertyInfoModelClass.getFeatured_image());
        isForSale = propertyInfoModelClass.getForSale();

        if(isForSale){

            myViewHolder.status.setText(R.string.for_sale);
        }else {

            myViewHolder.status.setText(R.string.rental);
        }

        if (imageUri!=null){

            Glide.with(context).load(propertyInfoModelClass.getFeatured_image()).into(myViewHolder.image);
        }


        Log.e(TAG, "Item: " + position);

        myViewHolder.cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityViewProperty.class);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
//        return modelClassHouseInfoList.size();
        return propertyInfoModelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView price,region, location,bedrooms, bathroom, description, picUrl, status;
        LinearLayout cardLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.housePic);
            price = itemView.findViewById(R.id.housePrice);
            location = itemView.findViewById(R.id.houseLocation);
            region = itemView.findViewById(R.id.houseRegion);
            status = itemView.findViewById(R.id.status);
            bedrooms = itemView.findViewById(R.id.houseSize);
            bathroom = itemView.findViewById(R.id.houseBaths);
            cardLayout = itemView.findViewById(R.id.cardLayout);

        }
    }
}
