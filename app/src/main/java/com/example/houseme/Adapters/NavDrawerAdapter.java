package com.example.houseme.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.houseme.Models.NavDrawerItems;
import com.example.houseme.R;

import java.util.List;

public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.MyViewHolder> {
    Context context;
    List<NavDrawerItems> mData;
    NavDrawerListener drawerListener;

    public interface NavDrawerListener{
        void OnNavMenuItemSelected(NavDrawerItems navDrawerItems);
    }

    public NavDrawerAdapter(Context context, List<NavDrawerItems> mData) {
        this.context = context;
        this.mData = mData;
    }


    public void setDrawerListener(NavDrawerListener drawerListener) {
        this.drawerListener = drawerListener;
    }

    @NonNull
    @Override
    public NavDrawerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.nav_item_single_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NavDrawerAdapter.MyViewHolder holder, int position) {

        final NavDrawerItems navDrawerItems = mData.get(position);

        holder.navItem.setText(navDrawerItems.getTitle());
        holder.icon.setImageResource(navDrawerItems.getImage_resource());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawerListener.OnNavMenuItemSelected(navDrawerItems);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView navItem;
        ImageView icon;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            navItem = (TextView) itemView.findViewById(R.id.navItem);
            icon = itemView.findViewById(R.id.icon);
            linearLayout = itemView.findViewById(R.id.navLinearLayout);
        }
    }
}
