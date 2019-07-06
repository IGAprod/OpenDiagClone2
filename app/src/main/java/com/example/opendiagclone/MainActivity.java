package com.example.opendiagclone;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

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
                    mTextMessage.setText(R.string.title_information);
                    selectedFragment = new InfoFragment();
                    break;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_parametrs);
                    selectedFragment = new ParametersFragment();
                    break;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_errors);
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
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new InfoFragment()).commit();

      //  viewPagerType = (ViewPager) findViewById(R.id.viewPagerType);
        tabLayoutType = findViewById(R.id.tabLayoutType);
        tabLayoutMark = findViewById(R.id.tabLayoutMark);

        tabLayoutType.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


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
                mTextMessage.setText(R.string.title_information);
                selectedFragment = new InfoFragment();
                break;
            case R.id.navigation_dashboard:
                mTextMessage.setText(R.string.title_parametrs);
                selectedFragment = new ParametersFragment();
                break;
            case R.id.navigation_notifications:
                mTextMessage.setText(R.string.title_errors);
                selectedFragment = new ErrorsFragment();
                break;
            case R.id.add_block:
                mTextMessage.setText(R.string.add_block);
                selectedFragment = new AddBlockFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();

        return super.onOptionsItemSelected(item);
    }

}
