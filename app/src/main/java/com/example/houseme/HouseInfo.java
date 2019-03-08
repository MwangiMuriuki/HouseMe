package com.example.houseme;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class HouseInfo extends RecyclerView.Adapter<HouseInfo.ViewHolder> {

    List<ModalClass> modalClassList;
    Context context;
    FirebaseFirestore firebaseFirestore;

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
    public void onBindViewHolder(@NonNull ViewHolder myViewHolder, int position) {

        ModalClass modalClass = modalClassList.get(position);

        myViewHolder.price.setText(modalClass.getPrice());
        myViewHolder.location.setText(modalClass.getLocation());
        myViewHolder.size.setText(modalClass.getSize());
        myViewHolder.bathroom.setText(modalClass.getBath());

        String imageUrl = modalClass.getPicture();

        Glide.with(context).load(imageUrl).placeholder(R.drawable.house3).into(myViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return modalClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView price,location,size, bathroom, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.housePic);
            price = itemView.findViewById(R.id.housePrice);
            location = itemView.findViewById(R.id.houseLocation);
            size = itemView.findViewById(R.id.houseSize);
            bathroom = itemView.findViewById(R.id.houseBaths);
        }
    }
}
