package com.khanhduy.doancuoiki.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khanhduy.doancuoiki.CheckConnection.CheckConnection;
import com.khanhduy.doancuoiki.R;
import com.khanhduy.doancuoiki.Server.Server;
import com.khanhduy.doancuoiki.Object.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PhoneLoginActivity extends AppCompatActivity {
    public static final String TAG = PhoneLoginActivity.class.getSimpleName();
    EditText edtUser, edtPassword;
    Button btnLogin, btnSignin;
    CheckBox cbNhoTaiKhoan;
    TextView txtQMK;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);
        AnhXa();

        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);

        edtUser.setText(sharedPreferences.getString("taikhoan",""));
        edtPassword.setText(sharedPreferences.getString("matkhau",""));

        cbNhoTaiKhoan.setChecked(sharedPreferences.getBoolean("checked",false));

        txtQMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneLoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneLoginActivity.this,SignUpActivity.class);
                startActivity(intent);

            }
        });

      btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckConnection.haveNetworkConnection(getApplicationContext())) {
                    GetUser(Server.urlLogin);
                }else{
                    CheckConnection.ShowToas_Short(getApplicationContext(),"Kiểm tra lại kết nối Internet của bạn!");
                }
            }
        });
    }

    private void GetUser(String url) {
        if(checkEditText(edtUser) && checkEditText(edtPassword)){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                        String message = "";
                        int id = 0;
                        String name = "";
                        String birthday = "";
                        String address = "";
                        String email = "";
                        String phone = "";
                        String taikhoan = "";
                        String password = "";
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getInt("success") == 1) {
                                id = jsonObject.getInt("id");
                                name = jsonObject.getString("name");
                                birthday = jsonObject.getString("birthday");
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date birday1 = simpleDateFormat.parse(birthday);
                                address = jsonObject.getString("address");
                                email = jsonObject.getString("email");
                                phone = jsonObject.getString("phone");
                                taikhoan = jsonObject.getString("taikhoan");
                                password = jsonObject.getString("password");
                                message = jsonObject.getString("message");
                                Toast.makeText(PhoneLoginActivity.this, message +" "+ name, Toast.LENGTH_SHORT).show();
                                User user = new User(id,name,birday1,address,email,phone,taikhoan,password);

                                if (cbNhoTaiKhoan.isChecked()) {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("taikhoan", edtUser.getText().toString().trim());
                                    editor.putString("matkhau", edtPassword.getText().toString().trim());
                                    editor.putBoolean("checked", true);
                                    editor.commit();
                                } else {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.remove("taikhoan");
                                    editor.remove("matkhau");
                                    editor.remove("checked");
                                    editor.commit();
                                }
                                Intent intent = new Intent(PhoneLoginActivity.this, HomeActivity.class);
                                intent.putExtra("informationUser",user);
                                startActivity(intent);
                                finish();

                            } else {
                                message = jsonObject.getString("message");
                                Toast.makeText(PhoneLoginActivity.this, message, Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            VolleyLog.d(TAG, "Error: " + e.getMessage());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.d(TAG, "Error: " + error.getMessage());
                            CheckConnection.ShowToas_Short(getApplicationContext(),"Hệ thống đang bảo trì vui lòng quay lại sau");
                        }
                    }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("user",edtUser.getText().toString().trim());
                    params.put("pass",edtPassword.getText().toString().trim());
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
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


    private void AnhXa(){
        edtUser = (EditText) findViewById(R.id.inputuser);
        edtPassword = (EditText) findViewById(R.id.inputpassword);
        btnLogin = (Button) findViewById(R.id.login_btn);
        btnSignin = (Button) findViewById(R.id.sign_btn);
        cbNhoTaiKhoan = (CheckBox) findViewById(R.id.checkBoxTaiKhoan);
        txtQMK = (TextView) findViewById(R.id.textViewQMK);
    }
}