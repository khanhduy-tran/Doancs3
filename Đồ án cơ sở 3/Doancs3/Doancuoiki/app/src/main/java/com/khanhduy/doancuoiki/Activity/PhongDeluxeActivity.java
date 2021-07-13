package com.khanhduy.doancuoiki.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khanhduy.doancuoiki.Adapter.DeluxeAdapter;
import com.khanhduy.doancuoiki.Object.Phong;
import com.khanhduy.doancuoiki.R;
import com.khanhduy.doancuoiki.Server.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhongDeluxeActivity extends AppCompatActivity {
    Toolbar toolBarDeluxe;
    ListView listViewDeluxe;
    DeluxeAdapter deluxeAdapter;
    ArrayList<Phong> phongArrayList;
    int idPhong = 0;
    int page=1;
    View footerView;
    boolean isLoading = false;
    PhongDeluxeActivity.mHandler mHandler;
    boolean limitData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_deluxe);
        AnhXa();
        GetIdLoaiPhong();
        ActionToolBar();
        GetData(page);
        Loadmoredata();
    }
    private void Loadmoredata() {
        listViewDeluxe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ChitietphongActivity.class);
                intent.putExtra("thongtinphong",phongArrayList.get(position));
                startActivity(intent);
            }
        });
        listViewDeluxe.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && isLoading == false && limitData == false){
                    isLoading = true;
                    PhongDeluxeActivity.ThreadData threadData = new PhongDeluxeActivity.ThreadData();
                    threadData.start();
                }

            }
        });
    }
    private void GetData(int page1) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String pagePhong = Server.urlPhongTheoLoai + String.valueOf(page1);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, pagePhong, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
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
                if(response != null && response.length() != 2){
                    listViewDeluxe.removeFooterView(footerView);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0;i<jsonArray.length();i++){
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
                            deluxeAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Lỗi hệ thống",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    limitData = true;
                    listViewDeluxe.removeFooterView(footerView);
                    Toast.makeText(getApplicationContext(),"Đã hết dữ liệu",Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Lỗi hệ thống",Toast.LENGTH_SHORT).show();

                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("id",String.valueOf(idPhong));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void ActionToolBar() {
        setSupportActionBar(toolBarDeluxe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarDeluxe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void GetIdLoaiPhong() {
        idPhong = getIntent().getIntExtra("id",-1);
        Log.d("Giá trị loại sản phẩm",idPhong+"");
    }
    private void AnhXa() {
        toolBarDeluxe = (Toolbar) findViewById(R.id.toolBarDeluxe);
        listViewDeluxe = (ListView) findViewById(R.id.listViewDeluxe);
        phongArrayList = new ArrayList<>();
        deluxeAdapter = new DeluxeAdapter(getApplicationContext(),phongArrayList);
        listViewDeluxe.setAdapter(deluxeAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerView = inflater.inflate(R.layout.progessbar_load,null);
        mHandler = new PhongDeluxeActivity.mHandler();
    }
    public class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    listViewDeluxe.addFooterView(footerView);
                    break;
                case 1:
                    GetData(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }
}