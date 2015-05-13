package com.example.imran.MaterialDesign;


import android.content.Context;
import android.content.SharedPreferences;
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

    public static final String FERE_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARED_DRAWER = "usser_lear_drawer";

    private ActionBarDrawerToggle mdrawerToggle;
    private DrawerLayout mDrawerLayout;

    private boolean mUserlearnerDrawer;
    private boolean mFormSaveInstanceState;

    public View ContainerView;


    public NavigationDrawbarFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserlearnerDrawer = Boolean.valueOf(readFormPreferences(getActivity(), KEY_USER_LEARED_DRAWER, "false"));
        if (savedInstanceState != null) {
            mFormSaveInstanceState = true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_drawbar, container, false);
    }


    public void setUp(int fragmentId,DrawerLayout drawerLayout, final Toolbar toolbar) {

        ContainerView=getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mdrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.draw_open, R.string.draw_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if (!mUserlearnerDrawer) {
                    mUserlearnerDrawer = true;
                    savetoPreferences(getActivity(), KEY_USER_LEARED_DRAWER, mUserlearnerDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                //Log.d("IMU","offset"+slideOffset);
                if (slideOffset<0.6)
                    toolbar.setAlpha(1-slideOffset);

            }
        };
        if (!mUserlearnerDrawer && !mFormSaveInstanceState) {
            mDrawerLayout.openDrawer(ContainerView);
        }

        mDrawerLayout.setDrawerListener(mdrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mdrawerToggle.syncState();
            }
        });

    }

    public void savetoPreferences(Context context, String preferenceName, String preferenceValue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(FERE_FILE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFormPreferences(Context context, String preferenceName, String defealtValue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(FERE_FILE_NAME, context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defealtValue);

    }
}
