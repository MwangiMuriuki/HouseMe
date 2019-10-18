package com.example.houseme.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

public class SlidingImageAdapter extends PagerAdapter {
    private ArrayList<Integer> IMAGES;
    private LayoutInflater inflater;
    private Context context;

    public SlidingImageAdapter(Context context,ArrayList<Integer> IMAGES) {
        this.context = context;
        this.IMAGES=IMAGES;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return false;
    }
}
