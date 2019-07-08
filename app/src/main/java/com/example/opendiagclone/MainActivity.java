package com.example.opendiagclone;

import android.os.Bundle;

import com.example.opendiagclone.adapter.InformationListAdapter;
import com.example.opendiagclone.adapter.PageBlockAdapter;
import com.example.opendiagclone.models.Information;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private TabLayout tabLayoutType;
    private TabLayout tabLayoutMark;
    private ViewPager viewPagerType;
    private ViewPager viewPagerMark;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new InfoFragment();
                    break;
                case R.id.navigation_dashboard:
                    selectedFragment = new ParametersFragment();
                    break;
                case R.id.navigation_notifications:
                    selectedFragment = new ErrorsFragment();
                    break;
            }
            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new InfoFragment()).commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.up_right_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                selectedFragment = new InfoFragment();
                break;
            case R.id.navigation_dashboard:
                selectedFragment = new ParametersFragment();
                break;
            case R.id.navigation_notifications:
                selectedFragment = new ErrorsFragment();
                break;
            case R.id.add_block:
                selectedFragment = new AddBlockFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();

        return super.onOptionsItemSelected(item);
    }

}
