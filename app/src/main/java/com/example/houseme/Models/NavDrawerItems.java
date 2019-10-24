package com.example.houseme.Models;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class NavDrawerItems {

    String title;
    int image_resource;
    String image_url;
    boolean activity;
    boolean fragment;
    Class activityName;
    Fragment fragmentName;
    List<NavDrawerItems> subMenus = new ArrayList<>();

    public NavDrawerItems() {
    }

    public NavDrawerItems(String title, int image_resource, String image_url, boolean activity, boolean fragment,
                          Class activityName, Fragment fragmentName, List<NavDrawerItems> subMenus) {
        this.title = title;
        this.image_resource = image_resource;
        this.image_url = image_url;
        this.activity = activity;
        this.fragment = fragment;
        this.activityName = activityName;
        this.fragmentName = fragmentName;
        this.subMenus = subMenus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage_resource() {
        return image_resource;
    }

    public void setImage_resource(int image_resource) {
        this.image_resource = image_resource;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public boolean isFragment() {
        return fragment;
    }

    public void setFragment(boolean fragment) {
        this.fragment = fragment;
    }

    public Class getActivityName() {
        return activityName;
    }

    public void setActivityName(Class activityName) {
        this.activityName = activityName;
    }

    public Fragment getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(Fragment fragmentName) {
        this.fragmentName = fragmentName;
    }

    public List<NavDrawerItems> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<NavDrawerItems> subMenus) {
        this.subMenus = subMenus;
    }
}
