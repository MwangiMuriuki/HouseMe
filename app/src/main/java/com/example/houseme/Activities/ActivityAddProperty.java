package com.example.houseme.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.view.MenuItem;

import com.example.houseme.Fragments.FragmentAddPhotos;
import com.example.houseme.Fragments.FragmentInfo;
import com.example.houseme.R;
import com.example.houseme.databinding.ActivityAddPropertyBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ActivityAddProperty extends AppCompatActivity {

    ActivityAddPropertyBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_property);
        binding.addPropertyToolBar.setNavigationIcon(R.drawable.ic_close);
        binding.addPropertyToolBar.setTitle("Add Property");
        binding.addPropertyToolBar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        setSupportActionBar(binding.addPropertyToolBar);

        setupViewPager(binding.viewpager);
        binding.tabLayout.setupWithViewPager(binding.viewpager);
    }

    private void setupViewPager(ViewPager viewpager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentAddPhotos(), "Photos");
        adapter.addFragment(new FragmentInfo(), "Description");
        viewpager.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
