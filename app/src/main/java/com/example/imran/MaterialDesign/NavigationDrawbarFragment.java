package com.example.imran.MaterialDesign;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import com.example.imran.lolopomaterial.R;

import java.util.ArrayList;
import java.util.List;


public class NavigationDrawbarFragment extends Fragment {

    private RecyclerView recyclerView;

    public static final String FERE_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARED_DRAWER = "usser_lear_drawer";

    private ActionBarDrawerToggle mdrawerToggle;
    private DrawerLayout mDrawerLayout;

    private boolean mUserlearnerDrawer;
    private boolean mFormSaveInstanceState;

    public View ContainerView;
    private ViewAdapter adapter;


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
        View layout = inflater.inflate(R.layout.fragment_navigation_drawbar, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.drawList);

        adapter = new ViewAdapter(getActivity(), getData()); // ViewAdapter class ke ekhane initialize korlam
        recyclerView.setAdapter(adapter); // recyclerview e viewadapter initialize korlam
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); // ekhane LinearLayoutManager,coz amar protiti item eker por asbe
        return layout;
    }


    // getdata all item form custome_row
    // eta dynamic hobe karon navigation e json,php,sql theke data niya jabe na
    //

    public static List<Information> getData() {

        List<Information> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher};// array nilam karon shob icon and tile show korar jonne
        String[] titles = {"HTML", "CSS", "JAVA", "Android"};

        for (int i = 0; i < titles.length && i < icons.length; i++) {
            Information current = new Information();// object create korla ,shob value ke assagin korar jonne
            current.iconId = icons[i]; // icons
            current.title = titles[i]; // title
            data.add(current);  // shob value ke add korlam
        }
        return data; // data return korlam ,List<Information> er maddhome

    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {

        ContainerView = getActivity().findViewById(fragmentId);
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
                if (slideOffset < 0.6)
                    toolbar.setAlpha(1 - slideOffset);

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
