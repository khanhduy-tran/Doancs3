package com.khanhduy.doancuoiki.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khanhduy.doancuoiki.Object.DichVuDoAn;
import com.khanhduy.doancuoiki.R;
import com.khanhduy.doancuoiki.Server.Server;
import com.khanhduy.doancuoiki.Object.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ThanhToanMonAnActivity extends AppCompatActivity {
    Toolbar toolBarHoaDonDV;
    EditText edtTenKH,edtDiaChi,edtEmail,edtPhone,edtTenMon,edtSoLuong,edtGiaTien,edtNgayDat,edtGioNhan;
    Button btnXacTTDV;
    Spinner spinnerTenPhong;
    ArrayList<String> listTenPhong = new ArrayList<>();
    ArrayAdapter<String> adapterTenPhong;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan_mon_an);
        AnhXa();
        progressDialog = new ProgressDialog(this);
        ActionToolBar();
        getTenPhong(Server.urlDataTenPhong);
        DatePicker(edtNgayDat);
        TimePicker(edtGioNhan);
        GetThongTinHoaDon();
        ButtonBooking();

    }

    private void ButtonBooking() {
        btnXacTTDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Vui lòng đợi...");
                progressDialog.show();
                String email1 = edtEmail.getText().toString().trim();
                if(checkEditText(edtTenKH) &&
                checkEditText(edtDiaChi) &&
                checkEditText(edtEmail)&&
                checkEditText(edtPhone)
                && checkEditText(edtSoLuong) &&
                checkEditText(edtNgayDat) &&
                checkEditText(edtGioNhan) &&
                isValidEmail(email1)){
                    String soLuong = edtSoLuong.getText().toString().trim();
                    int customSoLuong = Integer.parseInt(soLuong);
                    DichVuDoAn dichVuDoAn = (DichVuDoAn) getIntent().getSerializableExtra("hoadonmonan");
                    int giamonan = dichVuDoAn.getGiamonan();
                    int tongTien = giamonan * customSoLuong;
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    edtGiaTien.setText("");
                    edtGiaTien.setText(decimalFormat.format(tongTien)+ " Đ");

                    String tenkhachhang = edtTenKH.getText().toString().trim();
                    String diachi = edtDiaChi.getText().toString().trim();
                    String email = edtEmail.getText().toString().trim();
                    String phone = edtPhone.getText().toString().trim();
                    int mamonan = dichVuDoAn.getId();
                    String tenmonan = dichVuDoAn.getTenmonan();
                    int soLuongMon = customSoLuong;
                    int giamon = tongTien;
                    String tenphongnhan = spinnerTenPhong.getSelectedItem().toString();
                    String ngaynhan = edtNgayDat.getText().toString().trim();
                    String gioNhan = edtGioNhan.getText().toString().trim();
                    String idUser = HomeActivity.textId();
                    AddHoaDon(Server.urlDatMon,tenkhachhang,diachi,email,phone,mamonan,tenmonan,soLuongMon,giamon,tenphongnhan,ngaynhan,gioNhan,idUser);

                }
            }
        });
    }
    private void AddHoaDon(String url,String tenKH,String diachi,String email,String phone,int mamonan,String tenmonan,int soluong,int gia,String phongnhan,String ngaynhan,String gionhan,String idKH){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String madonhang) {
                try {
                    JSONObject jsonObject1 = new JSONObject(madonhang);
                    if(jsonObject1.getInt("success") == 1){

                        String emailUser = jsonObject1.getString("email");
                        String madonhang1 = jsonObject1.getString("mahoadon");

                        RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
                        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Server.urlChiTietHoaDonMonAn, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                String message = "";
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if(jsonObject.getInt("success") == 1){
                                        message = jsonObject.getString("message");
                                        Toast.makeText(ThanhToanMonAnActivity.this,message,Toast.LENGTH_SHORT).show();
                                        sendData();
                                    }else{
                                        message = jsonObject.getString("message");
                                        Toast.makeText(ThanhToanMonAnActivity.this,message,Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("email",emailUser);
                                hashMap.put("madonhang",madonhang1);
                                hashMap.put("mamonan",String.valueOf(mamonan));
                                hashMap.put("tenmonan",tenmonan);
                                hashMap.put("soluong",String.valueOf(soluong));
                                hashMap.put("giamonan",String.valueOf(gia));
                                hashMap.put("phongnhan",phongnhan);
                                hashMap.put("ngaynhan",ngaynhan);
                                hashMap.put("gionhan",gionhan);
                                return hashMap;
                            }
                        };
                        requestQueue1.add(stringRequest1);

                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"lỗi",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("tenKH",tenKH);
                params.put("diachi",diachi);
                params.put("email",email);
                params.put("phone",phone);
                params.put("idKH",idKH);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void GetThongTinHoaDon() {
        DichVuDoAn dichVuDoAn = (DichVuDoAn) getIntent().getSerializableExtra("hoadonmonan");
        String tenmonan = dichVuDoAn.getTenmonan();
        int giamonan = dichVuDoAn.getGiamonan();
        edtTenMon.setText(tenmonan);
        edtTenMon.setEnabled(false);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        edtGiaTien.setText(decimalFormat.format(giamonan) + " Đ");
        edtGiaTien.setEnabled(false);

        User user2 = HomeActivity.getUser2();
        String tenKH = user2.getName();
        String diachiKH = user2.getAddress();
        String emailKH = user2.getEmail();
        String phoneUser = user2.getPhone();

        edtTenKH.setText(tenKH);
        edtDiaChi.setText(diachiKH);
        edtEmail.setText(emailKH);
        edtPhone.setText(phoneUser);

    }
    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0) {
            return true;
        } else {
            progressDialog.dismiss();
            editText.setError("Vui lòng nhập dữ liệu");
        }
        return false;

    }
    private boolean isValidEmail(String target) {
        if (target.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))
            return true;
        else {
            progressDialog.dismiss();
            edtEmail.setError("Email sai định dạng!");
        }
        return false;
    }

    private void getTenPhong(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for(int i = 0; i < response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String tenPhong = jsonObject.optString("tenphong");

                            listTenPhong.add(tenPhong);
                            adapterTenPhong = new ArrayAdapter<String>(ThanhToanMonAnActivity.this,
                                    android.R.layout.simple_spinner_item,
                                    listTenPhong);
                            adapterTenPhong.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerTenPhong.setAdapter(adapterTenPhong);
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
        setSupportActionBar(toolBarHoaDonDV);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarHoaDonDV.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolBarHoaDonDV = (Toolbar) findViewById(R.id.toolBarHoaDonDV);
        edtTenKH = findViewById(R.id.editTextTenThanhToanDV);
        edtDiaChi = findViewById(R.id.editTextDiaChiThanhToanDV);
        edtEmail = findViewById(R.id.editTextEmailThanhToanDV);
        edtPhone = findViewById(R.id.editTextPhoneThanhToanDV);
        edtTenMon = findViewById(R.id.editTextTenMonThanhToanDV);
        edtSoLuong = findViewById(R.id.editTextSoLuongThanhToanDV);
        edtGiaTien = findViewById(R.id.editTextGiaMonAnThanhToanDV);
        edtNgayDat = findViewById(R.id.editTextNgayDatThanhToanDV);
        edtGioNhan = findViewById(R.id.editTextThoiGianThanhToanDV);
        btnXacTTDV = findViewById(R.id.buttonXacNhanThanhToanDV);
        spinnerTenPhong = findViewById(R.id.spinnerDVThanhToan);
    }
    private void DateSelection(EditText edtText){
        Calendar calendar = Calendar.getInstance();
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                edtText.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },year1,month1,day1);
        datePickerDialog.show();
    }
    private void DatePicker(EditText edt1){
        edt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateSelection(edt1);
            }
        });

    }
    private void TimeSelection(EditText edt){
        Calendar calendar = Calendar.getInstance();
        int gio  = calendar.get(Calendar.HOUR_OF_DAY);
        int phut = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                calendar.set(0,0,0,hourOfDay,minute);
                edt.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },gio,phut,true);
        timePickerDialog.show();
    }
    private void TimePicker(EditText editTex){
        editTex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeSelection(editTex);
            }
        });
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
        Intent intent = new Intent(ThanhToanMonAnActivity.this,HomeActivity.class);
        intent.putExtra("informationUser",user2);
        startActivity(intent);
        finish();
    }


}