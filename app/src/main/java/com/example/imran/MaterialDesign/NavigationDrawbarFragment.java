package com.example.imran.MaterialDesign;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import com.example.imran.lolopomaterial.R;


public class NavigationDrawbarFragment extends android.support.v4.app.Fragment {


    private ActionBarDrawerToggle mdrawerToggle;
    private DrawerLayout mDrawerLayout;

    public NavigationDrawbarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_drawbar, container, false);
    }


    public void setUp(DrawerLayout drawerLayout, Toolbar toolbar) {
        mDrawerLayout=drawerLayout;
       mdrawerToggle=new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.draw_open,R.string.draw_close);


    }
}
