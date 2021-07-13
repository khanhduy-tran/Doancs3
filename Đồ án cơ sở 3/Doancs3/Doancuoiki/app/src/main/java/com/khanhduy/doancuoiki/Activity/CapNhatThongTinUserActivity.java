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
import com.khanhduy.doancuoiki.R;
import com.khanhduy.doancuoiki.Server.Server;
import com.khanhduy.doancuoiki.Object.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CapNhatThongTinUserActivity extends AppCompatActivity {
    Toolbar toolBarUpdate;
    EditText edtHoten,edtNgaySinh,edtDiaChi,edtEmail,edtPhone;
    Button btnCapNhat;
    int id = 0;
    String ten = "";
    String ngaysinh = "";
    String diachi = "";
    String email = "";
    String dienthoai = "";
    ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_thong_tin_user);
        AnhXa();
        progressDialog = new ProgressDialog(this);
        ActionToolBar();
        GetInformationUser();
        edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateSelection(edtNgaySinh);
            }
        });
        ButtonClick();

    }

    private void ButtonClick() {
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Vui lòng đợi...");
                progressDialog.show();
                User user = HomeActivity.getUser2();
                id = user.getId();
                ten = edtHoten.getText().toString().trim();
                ngaysinh = edtNgaySinh.getText().toString().trim();
                diachi = edtDiaChi.getText().toString().trim();
                email = edtEmail.getText().toString().trim();
                dienthoai = edtPhone.getText().toString().trim();
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlUpdateUser, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        String message = "";
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.getInt("success") == 1){
                                message = jsonObject.getString("message");
                                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                                sendData();
                            }else{
                                message = jsonObject.getString("message");
                                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
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
                        HashMap<String,String> params = new HashMap<>();
                        params.put("id",String.valueOf(id));
                        params.put("ten",ten);
                        params.put("ngaysinh",ngaysinh);
                        params.put("diachi",diachi);
                        params.put("email",email);
                        params.put("dienthoai",dienthoai);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }

    private void GetInformationUser() {
        User user = HomeActivity.getUser2();
        int idUser = user.getId();
        String tenUser = user.getName();
        Date bd = user.getBirthday();
        DateFormat dateFormat  = new SimpleDateFormat("dd-MM-yyyy");
        String bdUser = dateFormat.format(bd);
        String diachiUser = user.getAddress();
        String emailUser = user.getEmail();
        String phoneUser = user.getPhone();
        edtHoten.setText(tenUser);
        edtNgaySinh.setText(bdUser);
        edtDiaChi.setText(diachiUser);
        edtEmail.setText(emailUser);
        edtPhone.setText(phoneUser);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolBarUpdate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarUpdate.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolBarUpdate = findViewById(R.id.toolBarCNTTCN);
        edtHoten = findViewById(R.id.editTextHoTenUpdate);
        edtNgaySinh = findViewById(R.id.editTextBirtdayUpdate);
        edtDiaChi = findViewById(R.id.editTextDiaChiUpdate);
        edtEmail = findViewById(R.id.editTextEmailUpdate);
        edtPhone = findViewById(R.id.editTextSDTUpdate);
        btnCapNhat = findViewById(R.id.buttonUserUpdate);
    }
    private void DateSelection(EditText edt){
        Calendar calendar = Calendar.getInstance();
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                edt.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },year1,month1,day1);
        datePickerDialog.show();
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
        Intent intent = new Intent(CapNhatThongTinUserActivity.this,HomeActivity.class);
        intent.putExtra("informationUser",user2);
        startActivity(intent);
        finish();
    }
}