package com.khanhduy.doancuoiki.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

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

public class ChiTietHoaDonActivity extends AppCompatActivity {
TextView txtTenKH,txtDiaChiKH,txtPhoneKH,txtMaHD,txtTenPhong,txtTongTien,txtNgayDat,txtNgayTra,txtTrangThai;
Button btnHuy,btnNextHome;
Toolbar toolbarCTDP;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);
        AnhXa();
        ActionToolBar();
        GetChiTietHoaDon(Server.urlChiTietHoaDonDatPhong);
       progressDialog = new ProgressDialog(this);
        ButtonClick();
    }

    private void ButtonClick() {
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ChiTietHoaDonActivity.this);

                b.setTitle("Xác nhận");
                b.setMessage("Bạn có muốn hủy đơn đặt phòng này không?");

                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        progressDialog.setMessage("Vui lòng đợi...");
                        progressDialog.show();
                        HuyDon(Server.urlHuyDonDatPhong);
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
        btnNextHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
    }

    private void HuyDon(String url){
        int maphong = getIntent().getIntExtra("maphong",-1);
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
                        Toast.makeText(ChiTietHoaDonActivity.this,message,Toast.LENGTH_SHORT).show();
                        sendData();
                    }else{
                        message = jsonObject.getString("message");
                        Toast.makeText(ChiTietHoaDonActivity.this,message,Toast.LENGTH_SHORT).show();
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
                params.put("maphong",String.valueOf(maphong));
                return params;
            }
        };
        requestQueue.add(stringRequest);

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
        Intent intent = new Intent(ChiTietHoaDonActivity.this,HomeActivity.class);
        intent.putExtra("informationUser",user2);
        startActivity(intent);
        finish();
    }

    private void GetChiTietHoaDon(String url) {
        int maphong = getIntent().getIntExtra("maphong",-1);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String tenKH = jsonObject.getString("tenkhachhang");
                    String diachiKH = jsonObject.getString("diachi");
                    String phoneKH = jsonObject.getString("phone");
                    String tenPhong = jsonObject.getString("tenphong");
                    int giaPhong = jsonObject.getInt("giaphong");
                    String ngayDat = jsonObject.getString("ngaydat");
                    String ngayTra = jsonObject.getString("ngaytra");
                    int trangThai = jsonObject.getInt("trangthai");
                     txtTenKH.setText(tenKH);
                     txtDiaChiKH.setText(diachiKH);
                     txtPhoneKH.setText(phoneKH);
                     txtMaHD.setText(String.valueOf(maphong));
                     txtTenPhong.setText(tenPhong);
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    String tongTien = decimalFormat.format(giaPhong);
                     txtTongTien.setText(tongTien + " Đ");
                     txtNgayDat.setText(ngayDat);
                     txtNgayTra.setText(ngayTra);
                     if(trangThai == 1){
                         txtTrangThai.setText("Đã xác nhận!");
                         btnHuy.setEnabled(false);
                         btnHuy.setBackgroundColor(Color.LTGRAY);
                     }else{
                         txtTrangThai.setText("Chưa Xác Nhận!");
                         btnHuy.setEnabled(true);
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
                params.put("maphong",String.valueOf(maphong));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarCTDP);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarCTDP.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        txtTenKH = findViewById(R.id.tenKHCTDP);
        txtDiaChiKH = findViewById(R.id.diachiKHCTDP);
        txtPhoneKH = findViewById(R.id.dtKHCTDP);
        txtMaHD = findViewById(R.id.maHDCTDP);
        txtTenPhong = findViewById(R.id.tenPhongCTDP);
        txtTongTien = findViewById(R.id.tongtienCTDP);
        txtNgayDat = findViewById(R.id.ngaydatCTDP);
        txtNgayTra = findViewById(R.id.ngaytraCTDP);
        txtTrangThai = findViewById(R.id.trangthaiCTDP);
        btnHuy = findViewById(R.id.buttonHuyDon);
        btnNextHome = findViewById(R.id.buttonNextHome);
        toolbarCTDP =(Toolbar) findViewById(R.id.toolBarCTHD);
    }
}