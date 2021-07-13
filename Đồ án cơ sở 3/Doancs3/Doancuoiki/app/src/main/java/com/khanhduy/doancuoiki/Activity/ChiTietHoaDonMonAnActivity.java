package com.khanhduy.doancuoiki.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khanhduy.doancuoiki.R;
import com.khanhduy.doancuoiki.Server.Server;
import com.khanhduy.doancuoiki.Object.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChiTietHoaDonMonAnActivity extends AppCompatActivity {
    TextView tenKHDV,diachiKHDV,dienthoaiKHDV,mahoadonDV,tenmonanDV,soluongDV,giamonanDV,phongnhanDV,ngaynhanDV,gionhanDV,trangthaiDV;
    Button btnHuyDV,btnNexHomeDV;
    Toolbar toolBarCTDV;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don_mon_an);
        AnhXa();
        progressDialog = new ProgressDialog(this);
        ActionToolBar();
        GetChiTietHoaDon(Server.urlChiTietHoaDonDatMonAn);
        ButtonClick();

    }
    private void HuyDon(String url){
        int maphong = getIntent().getIntExtra("mamonan",-1);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                String message = "";
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getInt("success") == 1){
                        message = jsonObject.getString("message");
                        Toast.makeText(ChiTietHoaDonMonAnActivity.this,message,Toast.LENGTH_SHORT).show();
                        sendData();
                    }else{
                        message = jsonObject.getString("message");
                        Toast.makeText(ChiTietHoaDonMonAnActivity.this,message,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
                params.put("mamonan",String.valueOf(maphong));
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void ButtonClick() {
        btnHuyDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ChiTietHoaDonMonAnActivity.this);

                b.setTitle("Xác nhận");
                b.setMessage("Bạn có muốn hủy đơn đặt món này không?");

                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        progressDialog.setMessage("Vui lòng đợi...");
                        progressDialog.show();
                        HuyDon(Server.urlHuyDonDatMonAn);
                    }

                });
                b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                b.show();
            }
        });
        btnNexHomeDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
    }

    private void GetChiTietHoaDon(String url) {
        int maphong = getIntent().getIntExtra("mamonan",-1);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String tenKH = jsonObject.getString("tenkhachhang");
                    String diachiKH = jsonObject.getString("diachi");
                    String phoneKH = jsonObject.getString("phone");
                    String tenMonAn = jsonObject.getString("tenmonan");
                    int soLuong = jsonObject.getInt("soluong");
                    int giaMonAn = jsonObject.getInt("giamonan");
                    String phongNhan = jsonObject.getString("phongnhan");
                    String ngayNhan = jsonObject.getString("ngaynhan");
                    String thoiGianNhan = jsonObject.getString("thoigiannhan");
                    int trangThai = jsonObject.getInt("trangthai");

                    tenKHDV.setText(tenKH);
                    diachiKHDV.setText(diachiKH);
                    dienthoaiKHDV.setText(phoneKH);
                    mahoadonDV.setText(String.valueOf(maphong));
                    tenmonanDV.setText(tenMonAn);
                    soluongDV.setText(String.valueOf(soLuong));

                    phongnhanDV.setText(phongNhan);
                    ngaynhanDV.setText(ngayNhan);
                    gionhanDV.setText(thoiGianNhan);
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    String tongTien = decimalFormat.format(giaMonAn);
                    giamonanDV.setText(tongTien + " Đ");


                    if(trangThai == 1){
                        trangthaiDV.setText("Đã xác nhận!");
                        btnHuyDV.setEnabled(false);
                        btnHuyDV.setBackgroundColor(Color.LTGRAY);
                    }else{
                        trangthaiDV.setText("Chưa Xác Nhận!");
                        btnHuyDV.setEnabled(true);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
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
                params.put("mamonan",String.valueOf(maphong));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void ActionToolBar() {
        setSupportActionBar(toolBarCTDV);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarCTDV.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        tenKHDV = findViewById(R.id.tenKHCTDV);
        diachiKHDV = findViewById(R.id.diachiKHCTDV);
        dienthoaiKHDV = findViewById(R.id.dtKHCTDV);
        mahoadonDV = findViewById(R.id.maHDCTDV);
        tenmonanDV = findViewById(R.id.tenMonCTDV);
        soluongDV = findViewById(R.id.soluongCTDV);
        giamonanDV = findViewById(R.id.giatienCTDV);
        phongnhanDV = findViewById(R.id.phongnhanCTDV);
        ngaynhanDV = findViewById(R.id.ngaynhanCTDV);
        gionhanDV = findViewById(R.id.gionhanCTDV);
        trangthaiDV = findViewById(R.id.trangthaiCTDV);
        btnHuyDV = findViewById(R.id.buttonHuyDonDV);
        btnNexHomeDV = findViewById(R.id.buttonNextHomeDV);
        toolBarCTDV = findViewById(R.id.toolBarCTDV);
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
        Intent intent = new Intent(ChiTietHoaDonMonAnActivity.this,HomeActivity.class);
        intent.putExtra("informationUser",user2);
        startActivity(intent);
        finish();
    }
}