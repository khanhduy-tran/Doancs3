package com.khanhduy.doancuoiki.ViewPager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.khanhduy.doancuoiki.Fragment.Tab1Fragment;
import com.khanhduy.doancuoiki.Fragment.Tab2Fragment;
import com.khanhduy.doancuoiki.Fragment.Tab3Fragment;

public class HomeViewPageAdapter extends FragmentStatePagerAdapter {
    public HomeViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new Tab1Fragment();
            case 1:
                return  new Tab2Fragment();
            case 2:
                return  new Tab3Fragment();

            default:
                return new Tab1Fragment();
        }
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public  CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Phòng";
            case 1:
                return "Dịch Vụ";
            case 2:
                return "Ưu đãi";
            default:
                return "Phòng";
        }
    }
}
