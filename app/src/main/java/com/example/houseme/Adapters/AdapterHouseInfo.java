package com.example.houseme.Adapters;

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
import com.example.houseme.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class AdapterHouseInfo extends RecyclerView.Adapter<AdapterHouseInfo.ViewHolder> {

    List<ModelClassHouseInfo> modelClassHouseInfoList;
    Context context;
    FirebaseFirestore firebaseFirestore;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    Uri imageUri;

    public AdapterHouseInfo(List<ModelClassHouseInfo> modelClassHouseInfoList, Context context, FirebaseFirestore firebaseFirestore) {
        this.modelClassHouseInfoList = modelClassHouseInfoList;
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

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder myViewHolder, int position) {
        int pos = myViewHolder.getAdapterPosition();

        ModelClassHouseInfo modelClassHouseInfo = modelClassHouseInfoList.get(pos);

        myViewHolder.price.setText(modelClassHouseInfo.getPrice());
        myViewHolder.location.setText(modelClassHouseInfo.getLocation());
        myViewHolder.size.setText(modelClassHouseInfo.getSize());
        myViewHolder.bathroom.setText(modelClassHouseInfo.getBath());

        imageUri = Uri.parse(modelClassHouseInfo.getPicture());

//        Glide.with(context).load(modelClassHouseInfo.getPicture()).into(myViewHolder.image);
        Glide.with(context).load(modelClassHouseInfo.getDescription()).into(myViewHolder.image);

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
        return modelClassHouseInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView price,location,size, bathroom, description, picUrl;
        LinearLayout cardLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.housePic);
            price = itemView.findViewById(R.id.housePrice);
            location = itemView.findViewById(R.id.houseLocation);
            size = itemView.findViewById(R.id.houseSize);
            bathroom = itemView.findViewById(R.id.houseBaths);
            cardLayout = itemView.findViewById(R.id.cardLayout);

        }
    }
}
