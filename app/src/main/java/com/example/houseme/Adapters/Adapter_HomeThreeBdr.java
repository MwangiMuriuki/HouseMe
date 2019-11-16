package com.example.houseme.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.houseme.Activities.ActivityViewProperty;
import com.example.houseme.Models.PropertyInfoModelClass;
import com.example.houseme.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Adapter_HomeThreeBdr extends RecyclerView.Adapter<Adapter_HomeThreeBdr.MyViewHolder> {

    private static final String TAG = null;
    List<PropertyInfoModelClass> propertyInfoModelClassList;
    Context context;
    FirebaseFirestore firebaseFirestore;

    String viewFeaturedImage, viewPrice, viewRegion, viewLocation, viewBedroom, viewBathrooms, viewParking, viewForSale, viewDesc;
    Uri imageUri;
    Boolean isForSale;

    public Adapter_HomeThreeBdr(List<PropertyInfoModelClass> propertyInfoModelClassList, Context context, FirebaseFirestore firebaseFirestore) {
        this.propertyInfoModelClassList = propertyInfoModelClassList;
        this.context = context;
        this.firebaseFirestore = firebaseFirestore;
    }

    @NonNull
    @Override
    public Adapter_HomeThreeBdr.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_view_layouts, parent, false);

        Adapter_HomeThreeBdr.MyViewHolder myViewHolder = new Adapter_HomeThreeBdr.MyViewHolder(myView);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_HomeThreeBdr.MyViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();

        final PropertyInfoModelClass propertyInfoModelClass = propertyInfoModelClassList.get(pos);

        int housePrice = Integer.parseInt(propertyInfoModelClass.getPrice());

//        myViewHolder.price.setText("Ksh. " + NumberFormat.getNumberInstance(Locale.US).format(housePrice));
        holder.region.setText(propertyInfoModelClass.getRegion());
        holder.location.setText(propertyInfoModelClass.getLocation());
        holder.bedrooms.setText(propertyInfoModelClass.getBedrooms() + " bdr");
        holder.bathroom.setText(propertyInfoModelClass.getBathrooms());

        imageUri = Uri.parse(propertyInfoModelClass.getFeatured_image());
        isForSale = propertyInfoModelClass.getForSale();

        if(isForSale){

            holder.status.setText(R.string.for_sale);
            holder.status.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.status.setBackground(context.getDrawable(R.drawable.rounded_corners_button_white));
            holder.price.setText("Ksh. " + NumberFormat.getNumberInstance(Locale.US).format(housePrice));

        }else {

            holder.status.setText(R.string.rental);
            holder.price.setText("Ksh. " + NumberFormat.getNumberInstance(Locale.US).format(housePrice) + " .p/m");
        }

        if (imageUri!=null){

            Glide.with(context).load(propertyInfoModelClass.getFeatured_image()).into(holder.image);
        }

        Log.e(TAG, "Item: " + position);

        holder.cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewFeaturedImage = propertyInfoModelClass.getFeatured_image();
                viewPrice = holder.price.getText().toString();
                viewRegion = holder.region.getText().toString();
                viewBathrooms = holder.bathroom.getText().toString();
                viewBedroom = holder.bedrooms.getText().toString();
                viewForSale = holder.status.getText().toString();
                viewLocation = holder.location.getText().toString();
                viewDesc = propertyInfoModelClass.getDescription().toString();

                Intent viewProperty = new Intent(context, ActivityViewProperty.class);

                viewProperty.putExtra("featuredImage", viewFeaturedImage);
                viewProperty.putExtra("price", viewPrice);
                viewProperty.putExtra("region", viewRegion);
                viewProperty.putExtra("bathrooms", viewBathrooms);
                viewProperty.putExtra("bedrooms", viewBedroom);
                viewProperty.putExtra("forSale", viewForSale);
                viewProperty.putExtra("propertyLocation", viewLocation);
                viewProperty.putExtra("description", viewDesc);

                context.startActivity(viewProperty);

            }
        });
    }

    @Override
    public int getItemCount() {

        return propertyInfoModelClassList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView price,region, location,bedrooms, bathroom, description, picUrl, status;
        LinearLayout cardLayout;

        public MyViewHolder(@NonNull View itemView) {
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
