package com.khanhduy.doancuoiki.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khanhduy.doancuoiki.Object.Phong;
import com.khanhduy.doancuoiki.R;
import com.khanhduy.doancuoiki.Server.Server;
import com.khanhduy.doancuoiki.Object.User;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ThanhtoanActivity extends AppCompatActivity {
    Toolbar toolBarHoaDon;
    EditText edtHoTenThanhToan,edtDiaChiThanhToan,edtEmailThanhToan,edtPhoneThanhToan,edtLoaiPhongThanhToan,edtTenPhongThanhToan,
           edtGiaPhongThanhToan, edtNgayDatPhongThanhToan,edtNgayTraPhongThanhToan;
    Button btnXacNhanThanhToan;
    int id = 0;
    String ten = "";
    int gia = 0;
    String hinhanh = "";
    String mota = "";
    int idphong = 0;
    int trangthai = 0;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);
        AnhXa();
        progressDialog = new ProgressDialog(this);
        ActionToolBar();
        GetThongTinHoaDon();
        DatePicker(edtNgayDatPhongThanhToan,edtNgayTraPhongThanhToan);
        ButtonBooking();

    }

    private void ButtonBooking() {
        btnXacNhanThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Vui lòng đợi...");
                progressDialog.show();
                String email1 = edtEmailThanhToan.getText().toString().trim();
                if(checkEditText(edtHoTenThanhToan) &&
                        checkEditText(edtDiaChiThanhToan) &&
                        checkEditText(edtEmailThanhToan) &&
                        checkEditText(edtPhoneThanhToan) &&
                        checkEditText(edtNgayDatPhongThanhToan) &&
                        checkEditText(edtNgayTraPhongThanhToan) &&
                        isValidEmail(email1) ){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String ngay1 = edtNgayDatPhongThanhToan.getText().toString().trim();
                    String ngay2 = edtNgayTraPhongThanhToan.getText().toString().trim();

                    try {
                      Date date1 =  simpleDateFormat.parse(ngay1);
                      Date date2 =  simpleDateFormat.parse(ngay2);
                       long start = date1.getTime();
                       long end = date2.getTime();
                        if(end >= start){
                            long tmp = Math.abs(start - end);
                            int result = (int) (tmp/(24*3600*1000));
                            Phong phong = (Phong) getIntent().getSerializableExtra("hoadonphong");
                            gia = phong.getGiaphong();
                            int giamoi = gia*result;
                            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                            edtGiaPhongThanhToan.setText("");
                            edtGiaPhongThanhToan.setText(decimalFormat.format(giamoi)+ "đ");

                            String tenkhachhang = edtHoTenThanhToan.getText().toString().trim();
                            String diachi = edtDiaChiThanhToan.getText().toString().trim();
                            String email = edtEmailThanhToan.getText().toString().trim();
                            String phone = edtPhoneThanhToan.getText().toString().trim();
                            int maphong = phong.getId();
                            String tenphong = phong.getTenphong();
                            int giaphong = giamoi;
                            int loaiphong = phong.getIdPhong();
                            String ngaydat = edtNgayDatPhongThanhToan.getText().toString().trim();
                            String ngaytra = edtNgayTraPhongThanhToan.getText().toString().trim();

                            String idUser = HomeActivity.textId();

                            AddHoaDon(Server.urlDatPhong,tenkhachhang,diachi,email,phone,maphong,tenphong,giaphong,loaiphong,ngaydat,ngaytra,idUser);
                        }else{
                            progressDialog.dismiss();
                            edtNgayDatPhongThanhToan.setError("Vui lòng xem lại ngày đặt phòng");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
    private void AddHoaDon(String url,
                           String tenkhachhang,
                           String diachi,
                           String email1,
                           String phone,
                           int maphong,
                           String tenphong,
                           int giaphong,
                           int loaiphong,
                           String ngaydat,
                           String ngaytra,
                           String idKhachHang){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String thongbao1) {
                String message = "";
                try {
                    JSONObject jsonObject = new JSONObject(thongbao1);
                    if(jsonObject.getInt("success") == 1){
                        String madonhang = jsonObject.getString("madonphong");
                        String email2 = jsonObject.getString("email");

                        RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
                        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Server.urlChiTietDatPhong, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                String message1 = "";
                                try {
                                    JSONObject jsonObject1 = new JSONObject(response);
                                    if(jsonObject1.getInt("success") == 1){
                                        message1 = jsonObject1.getString("message");
                                        Toast.makeText(ThanhtoanActivity.this,message1,Toast.LENGTH_SHORT).show();
                                        sendData();
                                    }else{
                                        message1 = jsonObject1.getString("message");
                                        Toast.makeText(ThanhtoanActivity.this,message1,Toast.LENGTH_SHORT).show();
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
                                hashMap.put("madonphong",madonhang);
                                hashMap.put("email",email2);
                                hashMap.put("maphong",String.valueOf(maphong));
                                hashMap.put("tenphong",tenphong);
                                hashMap.put("giaphong",String.valueOf(giaphong));
                                hashMap.put("loaiphong",String.valueOf(loaiphong));
                                hashMap.put("ngaydat",ngaydat);
                                hashMap.put("ngaytra",ngaytra);
                                return hashMap;
                            }
                        };
                        requestQueue1.add(stringRequest1);


                    }else{
                        progressDialog.dismiss();
                        message = jsonObject.getString("message");
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
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
                params.put("tenkhachhang",tenkhachhang);
                params.put("diachi",diachi);
                params.put("email1",email1);
                params.put("phone",phone);
                params.put("idKhachHang",idKhachHang);
                params.put("ngaydat",ngaydat);
                params.put("maphong",String.valueOf(maphong));
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void GetThongTinHoaDon() {
        Phong phong = (Phong) getIntent().getSerializableExtra("hoadonphong");
        id = phong.getId();
        ten = phong.getTenphong();
        gia = phong.getGiaphong();
        hinhanh = phong.getHinh1();
        mota = phong.getMota();
        idphong = phong.getIdPhong();
        trangthai = phong.getTrangthai();
        String loaiphong = String.valueOf(idphong);
      edtLoaiPhongThanhToan.setText(loaiphong);
      edtLoaiPhongThanhToan.setEnabled(false);
      edtTenPhongThanhToan.setText(ten);
      edtTenPhongThanhToan.setEnabled(false);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
      edtGiaPhongThanhToan.setText(decimalFormat.format(gia));
      edtGiaPhongThanhToan.setEnabled(false);

      User user2 = HomeActivity.getUser2();
      String tenKH = user2.getName();
      String diachiKH = user2.getAddress();
      String emailKH = user2.getEmail();
      String phoneUser = user2.getPhone();

      edtHoTenThanhToan.setText(tenKH);
      edtDiaChiThanhToan.setText(diachiKH);
      edtEmailThanhToan.setText(emailKH);
      edtPhoneThanhToan.setText(phoneUser);

    }
    private void ActionToolBar() {
        setSupportActionBar(toolBarHoaDon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarHoaDon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolBarHoaDon = (Toolbar) findViewById(R.id.toolBarHoaDon);
        edtHoTenThanhToan = (EditText) findViewById(R.id.editTextTenThanhToan);
        edtDiaChiThanhToan = (EditText) findViewById(R.id.editTextDiaChiThanhToan);
        edtEmailThanhToan = (EditText) findViewById(R.id.editTextEmailThanhToan);
        edtPhoneThanhToan = (EditText) findViewById(R.id.editTextPhoneThanhToan);
        edtLoaiPhongThanhToan = (EditText) findViewById(R.id.editTextLoaiPhongThanhToan);
        edtTenPhongThanhToan = (EditText) findViewById(R.id.editTextTenPhongThanhToan);
        edtNgayDatPhongThanhToan = (EditText) findViewById(R.id.editTextNgayDatPhongThanhToan);
        edtNgayTraPhongThanhToan = (EditText) findViewById(R.id.editTextNgayTraPhongThanhToan);
        btnXacNhanThanhToan = (Button) findViewById(R.id.buttonXacNhanThanhToan);
        edtGiaPhongThanhToan = (EditText) findViewById(R.id.editTextGiaPhongThanhToan);
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
            edtEmailThanhToan.setError("Email sai định dạng!");
        }
        return false;
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
    private void DatePicker(EditText edt1, EditText edt2){
        edt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateSelection(edt1);
            }
        });
        edt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateSelection(edt2);
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
        Intent intent = new Intent(ThanhtoanActivity.this,HomeActivity.class);
        intent.putExtra("informationUser",user2);
        startActivity(intent);
        finish();
    }
}

