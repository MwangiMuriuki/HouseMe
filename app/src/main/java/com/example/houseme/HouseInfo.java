package com.example.houseme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class HouseInfo extends RecyclerView.Adapter<HouseInfo.ViewHolder> {

    List<ModalClass> modalClassList;
    Context context;
    FirebaseFirestore firebaseFirestore;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    Uri imageUri;

    public HouseInfo(List<ModalClass> modalClassList, Context context, FirebaseFirestore firebaseFirestore) {
        this.modalClassList = modalClassList;
        this.context = context;
        this.firebaseFirestore = firebaseFirestore;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View myView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.houses_card_view, viewGroup, false);
        HouseInfo.ViewHolder viewHolder = new HouseInfo.ViewHolder(myView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder myViewHolder, int position) {
        int pos = myViewHolder.getAdapterPosition();

        ModalClass modalClass = modalClassList.get(pos);

        myViewHolder.price.setText(modalClass.getPrice());
        myViewHolder.location.setText(modalClass.getLocation());
        myViewHolder.size.setText(modalClass.getSize());
        myViewHolder.bathroom.setText(modalClass.getBath());

        imageUri = Uri.parse(modalClass.getPicture());

//        Glide.with(context).load(modalClass.getPicture()).into(myViewHolder.image);
        Glide.with(context).load(modalClass.getDescription()).into(myViewHolder.image);

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
        return modalClassList.size();
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
