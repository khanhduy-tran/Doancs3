package com.khanhduy.doancuoiki.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    EditText edtBD,edtFullName,edtAddress,edtEmail,edtPhoneNumber,edtUser,edtPass;
    Button btnXNDK;
    Toolbar toolBarDKTK;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        AnhXa();
        ActionToolBar();
        progressDialog = new ProgressDialog(this);
        edtBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateSelection();
            }
        });
        btnXNDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Vui lòng đợi...");
                progressDialog.show();
               CreateUser(Server.urlCreate);
            }
        });
    }

    private void ActionToolBar() {
        setSupportActionBar(toolBarDKTK);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarDKTK.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa(){
        edtFullName = (EditText) findViewById(R.id.editTextHoTen);
        edtBD = (EditText) findViewById(R.id.editTextBirtday);
        edtAddress = (EditText) findViewById(R.id.editTextDiaChi);
        edtEmail = (EditText) findViewById(R.id.editTextEmail);
        edtPhoneNumber = (EditText) findViewById(R.id.editTextSDT);
        edtUser = (EditText) findViewById(R.id.editTextTaiKhoan);
        edtPass = (EditText) findViewById(R.id.editTextMatKhau);
        btnXNDK = (Button) findViewById(R.id.buttonDangKy);
        toolBarDKTK = findViewById(R.id.toolBarDKTK);
    }
    private void DateSelection(){
        Calendar calendar = Calendar.getInstance();
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                edtBD.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },year1,month1,day1);
        datePickerDialog.show();
    }
    private void CreateUser(String url){
        if(checkEditText(edtFullName) && checkEditText(edtBD) &&
                checkEditText(edtAddress) && checkEditText(edtEmail) &&
                checkEditText(edtPhoneNumber) && checkEditText(edtUser) && checkEditText(edtPass)){

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

                            Toast.makeText(SignUpActivity.this,message,Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(SignUpActivity.this,PhoneLoginActivity.class);
                            startActivity(intent);
                        }else{
                            message = jsonObject.getString("message");
                            Toast.makeText(SignUpActivity.this,message,Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("error","error" + e.getMessage());
                    }

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("error","error" + error.getMessage());
                        }
                    }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("fullname",edtFullName.getText().toString().trim());
                    params.put("birthday",edtBD.getText().toString().trim());
                    params.put("address",edtAddress.getText().toString().trim());
                    params.put("email",edtEmail.getText().toString().trim());
                    params.put("phonenumber",edtPhoneNumber.getText().toString().trim());
                    params.put("user",edtUser.getText().toString().trim());
                    params.put("password",edtPass.getText().toString().trim());
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }

    }
    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0) {
            return true;
        } else {
            editText.setError("Vui lòng nhập dữ liệu");
        }
        return false;
    }
}