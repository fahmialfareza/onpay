package com.dinokeylas.onpay;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment selectedFragment = null;
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        selectedFragment = HomeFragment.newInstance();
                        break;
                    case R.id.navigation_chat:
                        selectedFragment = ChatFragment.newInstance();
                        break;
                    case R.id.navigation_notification:
                        selectedFragment = NotificationFragment.newInstance();
                        break;
                    case R.id.navigation_profile:
                        selectedFragment = AccountFragment.newInstance();
                        break;
                }
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                assert selectedFragment != null;
                fragmentTransaction.replace(R.id.frame_container, selectedFragment);
                fragmentTransaction.commit();
                return true;
            }
        });
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, HomeFragment.newInstance());
        fragmentTransaction.commit();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, QRScannerActivity.class);
                startActivity(intent);
            }
        });
    }
}
