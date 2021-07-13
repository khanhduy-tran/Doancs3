package com.khanhduy.doancuoiki.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khanhduy.doancuoiki.Adapter.LikeRoomAdapter;
import com.khanhduy.doancuoiki.Adapter.PhongTrongAdapter;
import com.khanhduy.doancuoiki.Object.Phong;
import com.khanhduy.doancuoiki.R;
import com.khanhduy.doancuoiki.Server.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhongYeuThichActivity extends AppCompatActivity {
    Toolbar toolbarLikeRoom;
    public static ArrayList<Phong> phongArrayList;
    public static LikeRoomAdapter likeRoomAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_yeu_thich);
        AnhXa();
        ActionToolBar();
        GetDuLieuPhong(Server.urlGetPhongYeuThich);
    }

    private void GetDuLieuPhong(String url) {
        String idUser = HomeActivity.textId();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
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
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0 ;i < jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
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
                            likeRoomAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("idUser",idUser);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarLikeRoom);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLikeRoom.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarLikeRoom = (Toolbar) findViewById(R.id.toolBarLikeRoom);
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewLikeRoom);
        phongArrayList = new ArrayList<>();
        likeRoomAdapter = new LikeRoomAdapter(getApplicationContext(),phongArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(likeRoomAdapter);
    }
}