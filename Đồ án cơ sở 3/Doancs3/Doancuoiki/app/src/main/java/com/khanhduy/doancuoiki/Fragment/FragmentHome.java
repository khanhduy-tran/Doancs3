package com.khanhduy.doancuoiki.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.palette.graphics.Palette;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.khanhduy.doancuoiki.CustomViewPager.CustomViewPager;
import com.khanhduy.doancuoiki.Server.Server;
import com.khanhduy.doancuoiki.ViewPager.HomeViewPageAdapter;
import com.khanhduy.doancuoiki.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    ViewFlipper viewFlipper;
    TabLayout tabLayout;
    CustomViewPager viewPager;
   View view;


    public FragmentHome(){
    }

    public static  FragmentHome newInstance(){
        FragmentHome fragmentHome = new FragmentHome();
        return fragmentHome;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     view =inflater.inflate(R.layout.fragment_home,container,false);
        AnhXa();
        ActionViewFlipper();
        HomeViewPageAdapter adapter = new HomeViewPageAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(false);
        tabLayout.setupWithViewPager(viewPager);
        setTabLayoutAnimation();
        return view;
    }

    private void ActionViewFlipper() {
       ArrayList<String> arrayQuangCao = new ArrayList<>();
       arrayQuangCao.add("http://192.168.1.6/ServicesHotel/image/slide/images.jpg");
       arrayQuangCao.add("http://192.168.1.6/ServicesHotel/image/slide/thiet-ke-noi-that-khach-san-3-533x400.jpg");
       arrayQuangCao.add("http://192.168.1.6/ServicesHotel/image/slide/khach-san-da-lat-view-dep-cho-tam-hon-lang-man-bay-bong-1.jpg");
       arrayQuangCao.add("http://192.168.1.6/ServicesHotel/image/slide/khach-san-Citadines-Bayfront-Nha-Trang-ivivu-11.jpg");
       arrayQuangCao.add("http://192.168.1.6/ServicesHotel/image/slide/163451006.jpg");
       showSlide(arrayQuangCao);
    }
    private void setTabLayoutAnimation(){
        final CollapsingToolbarLayout collapsingToolbarLayout = view.findViewById(R.id.CollapsingToolbarLayout);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.bghotel);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                int myColor = palette.getVibrantColor(getResources().getColor(R.color.themeColor));
                int myDarkColor =  palette.getVibrantColor(getResources().getColor(R.color.black_trans));
                collapsingToolbarLayout.setContentScrimColor(myColor);
                collapsingToolbarLayout.setStatusBarScrimColor(myDarkColor);
            }
        });
    }
    public void showSlide(ArrayList<String> slide){
        for(int j = 0; j<slide.size();j++){
            ImageView imageView = new ImageView(getContext());
            Picasso.with(getContext()).load(slide.get(j)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);

        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void AnhXa(){
        viewFlipper  = (ViewFlipper) view.findViewById(R.id.viewLipperHome);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layoutHome);
        viewPager = (CustomViewPager) view.findViewById(R.id.view_pagerHome);
    }
}
