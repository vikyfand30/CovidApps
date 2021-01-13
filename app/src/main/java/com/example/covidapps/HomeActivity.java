package com.example.covidapps;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.covidapps.fragment.HelpFragment;
import com.example.covidapps.fragment.HomeFragment;
import com.example.covidapps.fragment.SearchFragment;
import com.example.covidapps.util.NetworkUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    private int fragmentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);


        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setItemIconTintList(null);

        Intent intent = getIntent();
        int key;
        key = intent.getIntExtra("FRAGMENT_KEY", 0);


        GetFragment(key);
    }


    private void GetFragment(int key) {
        if (key == 0) {
            displayFragment(R.id.frame_container, new HomeFragment());

        } else if (key == 1) {
            displayFragment(R.id.frame_container, new SearchFragment());
            navView.getMenu().getItem(1).setChecked(true);

        } else if (key == 2) {
            displayFragment(R.id.frame_container, new HelpFragment());
            navView.getMenu().getItem(2).setChecked(true);

        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home_menu:
                    if (NetworkUtil.isNetworkAvailableHome(HomeActivity.this)) {
                        if (fragmentPos != 0) {
                            displayFragment(R.id.frame_container, new HomeFragment());
                            fragmentPos = 0;
                        }
                        return true;
                    }else {
                        return true;
                    }

                case R.id.search_menu:
                    if (NetworkUtil.isNetworkAvailableHome(HomeActivity.this)){
                        if (fragmentPos != 1){
                            displayFragment(R.id.frame_container, new SearchFragment());
                            fragmentPos = 1;
                        }
                        return true;

                    }
                    else {
                        return true;
                    }

                case R.id.help_menu:
                    if (NetworkUtil.isNetworkAvailableHome(HomeActivity.this)) {
                        if (fragmentPos != 3) {
                            displayFragment(R.id.frame_container, new HelpFragment());
                            fragmentPos = 3;
                        }
                        return true;
                    }else {

                        return true;
                    }

            }

            return false;

        }
    };

    public void displayFragment(int fragmentResourceID, Fragment fragment) {

        try {
            showFragment(fragmentResourceID, fragment);
        } catch (IllegalStateException e) {
            e.printStackTrace();

            showFragmentAllowingStateLoss(fragment, fragmentResourceID);
        }
    }

    public void showFragmentAllowingStateLoss(Fragment fragment, int fragmentResourceID) {

        if (fragment != null) {
            FragmentManager mFragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(fragmentResourceID, fragment);
            fragmentTransaction.detach(fragment);
            fragmentTransaction.attach(fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    public void showFragment(int fragmentResourceID, Fragment fragment) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        {
            transaction.replace(fragmentResourceID, fragment);
            transaction.commit();
//            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        GetFragment(0);
    }
}
