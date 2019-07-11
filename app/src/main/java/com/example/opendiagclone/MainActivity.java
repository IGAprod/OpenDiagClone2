package com.example.opendiagclone;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;
import android.bluetooth.*;

import java.util.Set;


public class MainActivity extends AppCompatActivity implements OnDataPass {
    private static final int REQUEST_CODE_BLUETOOTH = 1;
    private TextView mTextMessage;
    private TabLayout tabLayoutType;
    private TabLayout tabLayoutMark;
    private ViewPager viewPagerType;
    private ViewPager viewPagerMark;

    private String name = "";
    private String address = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**Сделать 2 менюхи в одну*/
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

    /**Сделать 2 менюхи в одну*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                selectedFragment = new InfoFragment();
                openFragment(selectedFragment);
                return super.onOptionsItemSelected(item);
            case R.id.navigation_dashboard:
                selectedFragment = new ParametersFragment();
                openFragment(selectedFragment);
                return super.onOptionsItemSelected(item);
            case R.id.navigation_notifications:
                selectedFragment = new ErrorsFragment();
                openFragment(selectedFragment);
                return super.onOptionsItemSelected(item);
            case R.id.add_block:
                selectedFragment = new AddBlockFragment();
                openFragment(selectedFragment);
                return super.onOptionsItemSelected(item);
            case R.id.bluetoothConnection:
                selectedFragment = new bluetoothFragment();
                //     Intent intent = new Intent(this,bluetoothFragment.class);
             //   selectedFragment.startActivityForResult(intent,REQUEST_CODE_BLUETOOTH);
                openFragment(selectedFragment);
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        String address = "";
        String name = "";
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_BLUETOOTH:
                    address = data.getExtras()
                            .getString(bluetoothFragment.EXTRA_DEVICE_ADDRESS);
                    name = data.getExtras()
                            .getString(bluetoothFragment.EXTRA_DEVICE_NAME);
                    break;
            }
            // если вернулось не ОК
        } else {
            Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Имя :" + name + "  Адрес :" + address , Toast.LENGTH_SHORT).show();
    }

    private void openFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                fragment).commit();
    }


    @Override
    public void onDataPass(String address, String name) {
        Toast.makeText(this, "Имя :" + name+ "  Адрес :" + address, Toast.LENGTH_SHORT).show();
    }

}
