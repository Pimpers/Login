package com.oz.date;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private MainMenuHomeFragment fragmentHome = new MainMenuHomeFragment();
    private MainMenuReviewFragment fragmentReview = new MainMenuReviewFragment();
    private MainMenuLiveFragment fragmentLive = new MainMenuLiveFragment();
    private MainMenuWeatherFragment fragmentWeather = new MainMenuWeatherFragment();
    private MainMenuMoreFragment fragmentMore = new MainMenuMoreFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.menu_frame_layout, fragmentHome).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.menu_home:
                    transaction.replace(R.id.menu_frame_layout, fragmentHome).commitAllowingStateLoss();
                    break;
                case R.id.menu_review:
                    transaction.replace(R.id.menu_frame_layout, fragmentReview).commitAllowingStateLoss();
                    break;
                case R.id.menu_live:
                    transaction.replace(R.id.menu_frame_layout, fragmentLive).commitAllowingStateLoss();
                    break;
                case R.id.menu_weather:
                    transaction.replace(R.id.menu_frame_layout, fragmentWeather).commitAllowingStateLoss();
                    break;
                case R.id.menu_more:
                    transaction.replace(R.id.menu_frame_layout, fragmentMore).commitAllowingStateLoss();
                    break;

            }

            return true;
        }
    }
}