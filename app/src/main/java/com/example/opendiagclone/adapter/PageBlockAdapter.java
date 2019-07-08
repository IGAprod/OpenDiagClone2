package com.example.opendiagclone.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.opendiagclone.blockGazFragment;
import com.example.opendiagclone.blockVazFragment;
import com.example.opendiagclone.blockZazFragment;

public class PageBlockAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PageBlockAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return new blockVazFragment();
            case 1:
                return new blockGazFragment();
            case 2:
                return new blockZazFragment();
            default: return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
