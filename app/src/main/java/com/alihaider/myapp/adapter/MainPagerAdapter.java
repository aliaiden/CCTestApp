package com.alihaider.myapp.adapter;

import com.alihaider.myapp.MainActivity;
import com.alihaider.myapp.fragment.OneFragment;
import com.alihaider.myapp.fragment.ThreeFragment;
import com.alihaider.myapp.fragment.TwoFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(FragmentManager fm, MainActivity mainActivity) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OneFragment();
            case 1:
                return new TwoFragment();
            case 2:
                return new ThreeFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}