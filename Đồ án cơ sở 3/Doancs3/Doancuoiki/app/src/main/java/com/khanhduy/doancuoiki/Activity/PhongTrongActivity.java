package com.khanhduy.doancuoiki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.khanhduy.doancuoiki.Adapter.PhongTrongAdapter;
import com.khanhduy.doancuoiki.Object.Phong;
import com.khanhduy.doancuoiki.R;
import com.khanhduy.doancuoiki.Server.Server;

import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PhongTrongActivity extends AppCompatActivity {
    Toolbar toolbarPhongTrong;
    public static ArrayList<Phong> phongArrayList;
    public static PhongTrongAdapter phongTrongAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_trong);
        AnhXa();
        ActionToolBar();
        GetDuLieuPhong(Server.urlPhongTrong);
    }

    private void GetDuLieuPhong(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    int ID = 0;
                    String Tenphong = "";
                    Integer Giaphong = 0;
                    String Hinhanhphong = "";
                    String Hinh2 = "";
                    String Hinh3 = "";
                    String Hinh4 = "";
                    int Giamoi = 0;
                    int Giamgia = 0;
                    String Motaphong = "";
                    int IDphong = 0;
                    int Trangthai = 0;
                    for(int i = 0; i < response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            Tenphong = jsonObject.getString("tenphong");
                            Giaphong = jsonObject.getInt("giaphong");
                            Hinhanhphong = jsonObject.getString("hinhanhphong");
                            Hinh2 = jsonObject.getString("hinh2");
                            Hinh3 = jsonObject.getString("hinh3");
                            Hinh4 = jsonObject.getString("hinh4");
                            Giamoi = jsonObject.getInt("giamoi");
                            Giamgia = jsonObject.getInt("giamgia");
                            Motaphong = jsonObject.getString("mota");
                            IDphong = jsonObject.getInt("idphong");
                            Trangthai = jsonObject.getInt("trangthai");
                            phongArrayList.add(new Phong(ID,Tenphong,Giaphong,Hinhanhphong,Hinh2,Hinh3,Hinh4,Giamoi,Giamgia,Motaphong,IDphong,Trangthai));
                            phongTrongAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarPhongTrong);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPhongTrong.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarPhongTrong = (Toolbar) findViewById(R.id.toolBarPhongTrong);
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewPhongTrong);
        phongArrayList = new ArrayList<>();
        phongTrongAdapter = new PhongTrongAdapter(getApplicationContext(),phongArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(phongTrongAdapter);
    }
}