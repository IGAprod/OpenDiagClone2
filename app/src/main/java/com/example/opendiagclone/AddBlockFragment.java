package com.example.opendiagclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.opendiagclone.adapter.InformationListAdapter;
import com.example.opendiagclone.adapter.PageBlockAdapter;
import com.example.opendiagclone.models.Information;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class AddBlockFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_block,container,false);
        TabLayout tabLayout = view.findViewById(R.id.tabLayoutType);
        TabItem tabVAZ = view.findViewById(R.id.tabVAZ);
        TabItem tabGAZ = view.findViewById(R.id.tabGAZ);
        TabItem tabZAZ = view.findViewById(R.id.tabZAZ);
        ViewPager viewPager = view.findViewById(R.id.viewPagerType);

        PageBlockAdapter pageBlockAdapter = new PageBlockAdapter(getFragmentManager(),
                tabLayout.getTabCount());

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setAdapter(pageBlockAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
