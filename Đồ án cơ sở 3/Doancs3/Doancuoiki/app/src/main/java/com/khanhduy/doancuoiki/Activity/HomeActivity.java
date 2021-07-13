package com.khanhduy.doancuoiki.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.khanhduy.doancuoiki.Fragment.FragmentMap;
import com.khanhduy.doancuoiki.Object.LoaiPhong;
import com.khanhduy.doancuoiki.Adapter.LoaiPhongAdapter;
import com.khanhduy.doancuoiki.R;
import com.khanhduy.doancuoiki.Server.Server;
import com.khanhduy.doancuoiki.Fragment.Tab1Fragment;
import com.khanhduy.doancuoiki.Fragment.Tab2Fragment;
import com.khanhduy.doancuoiki.Fragment.Tab3Fragment;
import com.khanhduy.doancuoiki.Object.User;
import com.khanhduy.doancuoiki.ViewPager.ViewPageAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.text.ParseException;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Toolbar toolBar;
    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    ListView listViewMain;
    ArrayList<LoaiPhong> mangLoaiPhong;
    LoaiPhongAdapter loaiPhongAdapter;
    int id = 0;
    String tenloaiphong = "";
    String hinhanhloaiphong = "";
    SearchView searchView;
    NavigationView navigationView;

   public static TextView txtIdUser,txtNameUser,txtBdUser,txtDiachiUser,txtEmailUser,txtPhoneUser,txtTaiKhoanUser,txtPassUser;
   public static User user2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       AnhXa();
       ActionBar();
       GetDuLieuLoaiPhong(Server.urlLoaiPhong);
       ChonManHinh();
//       CatchOnItemLisView();
       getIdUser();
       setupDrawerContent(navigationView);

    }

    public void getIdUser() {
        User user = (User) getIntent().getSerializableExtra("informationUser");
        int idUser = user.getId();
        String nameUser = user.getName();
        Date bd = user.getBirthday();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String bdUser = simpleDateFormat.format(bd);
        String diachiUser = user.getAddress();
        String emailUser = user.getEmail();
        String phoneUser = user.getPhone();
        String taikhoanUser = user.getTaikhoan();
        String passUser = user.getPassword();
        txtIdUser.setText(idUser+"");
        txtNameUser.setText(nameUser);
        txtBdUser.setText(bdUser);
        txtDiachiUser.setText(diachiUser);
        txtEmailUser.setText(emailUser);
        txtPhoneUser.setText(phoneUser);
        txtTaiKhoanUser.setText(taikhoanUser);
        txtPassUser.setText(passUser);

    }
    public static User getUser2(){

        try {
            int idUser =Integer.parseInt(txtIdUser.getText().toString().trim()) ;
            String nameUser = txtNameUser.getText().toString().trim();
            String bd = txtBdUser.getText().toString().trim();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date bdUser = dateFormat.parse(bd);
            String diachiUser = txtDiachiUser.getText().toString().trim();
            String emailUser = txtEmailUser.getText().toString().trim();
            String phoneUser = txtPhoneUser.getText().toString().trim();
            String taikhoanUser = txtTaiKhoanUser.getText().toString().trim();
            String passUser = txtPassUser.getText().toString().trim();
            user2 = new User(idUser,nameUser,bdUser,diachiUser,emailUser,phoneUser,taikhoanUser,passUser);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return user2;
    }

    public static String textId(){
       String idtext = txtIdUser.getText().toString().trim();
       return idtext;
 }


// private void CatchOnItemLisView() {
//        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                switch (position){
//                    case 0:
//                        sendData();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                    case 1:
//                        Intent intent1 = new Intent(HomeActivity.this, PhongStandardActivity.class);
//                        intent1.putExtra("id",mangLoaiPhong.get(position).getId());
//                        startActivity(intent1);
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                    case 2:
//                        Intent intent2 = new Intent(HomeActivity.this, PhongSuperiorActivity.class);
//                        intent2.putExtra("id",mangLoaiPhong.get(position).getId());
//                        startActivity(intent2);
//
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                    case 3:
//                        Intent intent3 = new Intent(HomeActivity.this, PhongDeluxeActivity.class);
//                        intent3.putExtra("id",mangLoaiPhong.get(position).getId());
//                        startActivity(intent3);
//
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                    case 4:
//
//                        break;
//                    case 5:
//                        Intent intent5 = new Intent(HomeActivity.this, PhoneLoginActivity.class);
//                        startActivity(intent5);
//                        finish();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                }
//            }
//        });
//    }

    private void ChonManHinh() {
        ViewPageAdapter adapter  = new ViewPageAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_maps).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_bookings).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_profile).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_maps:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.navigation_bookings:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.navigation_profile:
                        viewPager.setCurrentItem(3);
                        break;
                }

                return true;
            }
        });
    }

    private void AnhXa(){
        drawerLayout =(DrawerLayout) findViewById(R.id.drawbleMain);
        toolBar = (Toolbar) findViewById(R.id.toolbarMain);
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView  = findViewById(R.id.nav_bottomMain);
//        listViewMain = (ListView) findViewById(R.id.listViewMain);
        mangLoaiPhong = new ArrayList<>();
//        mangLoaiPhong.add(0,new LoaiPhong(0,"Trang chủ","http://dienmayonline24h.com/wp-content/uploads/2017/09/home.png"));
        loaiPhongAdapter = new LoaiPhongAdapter(mangLoaiPhong,getApplicationContext());
//        listViewMain.setAdapter(loaiPhongAdapter);
        txtIdUser = findViewById(R.id.textViewIdUserHome);
        txtNameUser = findViewById(R.id.textViewNameUserHome);
        txtBdUser = findViewById(R.id.textViewBdUserHome);
        txtDiachiUser = findViewById(R.id.textViewDiachiUserHome);
        txtEmailUser = findViewById(R.id.textViewEmailUserHome);
        txtPhoneUser = findViewById(R.id.textViewPhoneUserHome);
        txtTaiKhoanUser = findViewById(R.id.textViewTaikhoanUserHome);
        txtPassUser = findViewById(R.id.textViewPassUserHome);
        navigationView = findViewById(R.id.navigationMain);

    }
    private void ActionBar() {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        toolBar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }
    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked

        switch(menuItem.getItemId()) {
            case R.id.nav_home:
                sendData();
                break;
            case R.id.phongStandard:
                Intent intent1 = new Intent(HomeActivity.this, PhongStandardActivity.class);
                      intent1.putExtra("id",mangLoaiPhong.get(0).getId());
                        startActivity(intent1);
                break;
            case R.id.phongSuperior:
                Intent intent2 = new Intent(HomeActivity.this, PhongSuperiorActivity.class);
                  intent2.putExtra("id",mangLoaiPhong.get(1).getId());
                       startActivity(intent2);
                break;
            case R.id.phongDeluxe:
                Intent intent3 = new Intent(HomeActivity.this, PhongDeluxeActivity.class);
                     intent3.putExtra("id",mangLoaiPhong.get(2).getId());
                        startActivity(intent3);
                break;
            case R.id.phongTrong:
                Intent intent4 = new Intent(HomeActivity.this,PhongTrongActivity.class);
                startActivity(intent4);
                break;
            case R.id.gioithieu:
                Intent intent5 = new Intent(HomeActivity.this,GioiThieuActivity.class);
                startActivity(intent5);
                break;
            case R.id.hotline:
                Intent intent6 = new Intent(HomeActivity.this,HotlineActivity.class);
                startActivity(intent6);
                break;
            case R.id.likeroom:
                Intent intent7 = new Intent(HomeActivity.this,PhongYeuThichActivity.class);
                startActivity(intent7);
                break;
            case R.id.likedichvu:
                Intent intent8 = new Intent(HomeActivity.this,DichVuYeuThichActivity.class);
                startActivity(intent8);
                break;
            case R.id.logout:
                Intent intent9 = new Intent(HomeActivity.this, PhoneLoginActivity.class);
                       startActivity(intent9);
                        finish();
                break;

            default:
               sendData();
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title

        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawerLayout.closeDrawers();
    }
    private void GetDuLieuLoaiPhong(String url){

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if(response != null){

                    for(int i = 0;i < response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenloaiphong = jsonObject.getString("tenloaiphong");
                            hinhanhloaiphong = jsonObject.getString("hinhanhloaiphong");
                            mangLoaiPhong.add(new LoaiPhong(id,tenloaiphong,hinhanhloaiphong));
                            loaiPhongAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
//                    mangLoaiPhong.add(4,new LoaiPhong(0,"Liên hệ","https://i.pinimg.com/originals/93/75/e5/9375e50b51f54ce8e4b1b0d6cf126b7c.png"));
//                    mangLoaiPhong.add(5,new LoaiPhong(0,"Đăng xuất","https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/OOjs_UI_icon_logOut-ltr.svg/1024px-OOjs_UI_icon_logOut-ltr.svg.png"));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Lỗi hệ thống",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_file,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(Tab1Fragment.phongAdapter != null){
                    Tab1Fragment.phongAdapter.getFilter().filter(query);
                }
                if(Tab2Fragment.dichVuDoAnAdapter != null){
                    Tab2Fragment.dichVuDoAnAdapter.getFilter().filter(query);
                }
                if (Tab3Fragment.phongGiamGiaAdapter != null){
                    Tab3Fragment.phongGiamGiaAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(Tab1Fragment.phongAdapter != null){
                    Tab1Fragment.phongAdapter.getFilter().filter(newText);
                }
                if(Tab2Fragment.dichVuDoAnAdapter != null){
                    Tab2Fragment.dichVuDoAnAdapter.getFilter().filter(newText);
                }
                if (Tab3Fragment.phongGiamGiaAdapter != null){
                    Tab3Fragment.phongGiamGiaAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });
        return true;
    }
    private void sendData(){
        User user = HomeActivity.getUser2();
        int id = user.getId();
        String name = user.getName();
        Date bd = user.getBirthday();
        String address = user.getAddress();
        String email = user.getEmail();
        String phone = user.getPhone();
        String taikhoan = user.getTaikhoan();
        String pass = user.getPassword();
        User user2 = new User(id,name,bd,address,email,phone,taikhoan,pass);
        Intent intent = new Intent(HomeActivity.this,HomeActivity.class);
        intent.putExtra("informationUser",user2);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}
