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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khanhduy.doancuoiki.Adapter.LikeDVAdapter;
import com.khanhduy.doancuoiki.Adapter.LikeRoomAdapter;
import com.khanhduy.doancuoiki.Object.DichVuDoAn;
import com.khanhduy.doancuoiki.Object.Phong;
import com.khanhduy.doancuoiki.R;
import com.khanhduy.doancuoiki.Server.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DichVuYeuThichActivity extends AppCompatActivity {
    Toolbar toolbarLikeDV;
    public static ArrayList<DichVuDoAn> dichVuDoAnArrayList;
    public static LikeDVAdapter likeDVAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dich_vu_yeu_thich);
        AnhXa();
        ActionToolBar();
        GetDuLieuDV(Server.urlGetDVyeuthich);
    }

    private void GetDuLieuDV(String url) {
        String idUser = HomeActivity.textId();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response != null){
                    int ID = 0;
                    String Tenmon = "";
                    Integer Giamon = 0;
                    String Hinhanhmon = "";
                    String Hinh2 = "";
                    String Hinh3 = "";
                    String Hinh4 = "";
                    String Motamon = "";
                    int Trangthai = 0;
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0 ;i < jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            Tenmon = jsonObject.getString("tenmonan");
                            Giamon = jsonObject.getInt("giamonan");
                            Hinhanhmon = jsonObject.getString("hinhanh");
                            Hinh2 = jsonObject.getString("hinhanh1");
                            Hinh3 = jsonObject.getString("hinhanh2");
                            Hinh4 = jsonObject.getString("hinhanh3");

                            Motamon = jsonObject.getString("mota");

                            Trangthai = jsonObject.getInt("trangthai");
                            dichVuDoAnArrayList.add(new DichVuDoAn(ID,Tenmon,Giamon,Hinhanhmon,Hinh2,Hinh3,Hinh4,Motamon,Trangthai));
                            likeDVAdapter.notifyDataSetChanged();
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
        setSupportActionBar(toolbarLikeDV);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLikeDV.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarLikeDV = (Toolbar) findViewById(R.id.toolBarLikeDV);
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewLikeDV);
        dichVuDoAnArrayList = new ArrayList<>();
        likeDVAdapter = new LikeDVAdapter(getApplicationContext(),dichVuDoAnArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(likeDVAdapter);
    }
}