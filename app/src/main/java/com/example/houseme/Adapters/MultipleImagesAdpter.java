package com.example.houseme.Adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.houseme.R;

import java.util.List;

public class MultipleImagesAdpter extends RecyclerView.Adapter<MultipleImagesAdpter.MyViewHolder> {

    List<String> imageNameList;

    public MultipleImagesAdpter(List<String> imageNameList) {
        this.imageNameList = imageNameList;
    }

    @NonNull
    @Override
    public MultipleImagesAdpter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multiple_images_recycler_view_single_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MultipleImagesAdpter.MyViewHolder holder, int position) {
        String imageUrl = imageNameList.get(position);

        holder.imageView.setImageURI(Uri.parse(imageUrl));
        holder.urlString.setText(imageUrl);

    }

    @Override
    public int getItemCount() {
        return imageNameList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView urlString;
         public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.selectedImage);
            urlString = itemView.findViewById(R.id.imageName);
        }
    }
}
