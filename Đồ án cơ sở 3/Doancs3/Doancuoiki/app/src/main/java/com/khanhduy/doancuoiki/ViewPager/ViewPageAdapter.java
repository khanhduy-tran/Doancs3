package com.khanhduy.doancuoiki.ViewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.khanhduy.doancuoiki.Fragment.FragmentBooking;
import com.khanhduy.doancuoiki.Fragment.FragmentHome;
import com.khanhduy.doancuoiki.Fragment.FragmentMap;
import com.khanhduy.doancuoiki.Fragment.FragmentProfile;

public class ViewPageAdapter extends FragmentStatePagerAdapter {
    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new FragmentHome();
            case 1:
                return  new FragmentMap();
            case 2:
                return  new FragmentBooking();
            case 3:
                return new FragmentProfile();

            default:
                return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
