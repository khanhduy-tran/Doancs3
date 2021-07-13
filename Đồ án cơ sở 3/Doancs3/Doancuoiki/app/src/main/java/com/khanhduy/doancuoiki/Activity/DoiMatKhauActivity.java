package com.khanhduy.doancuoiki.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Map;

public class DoiMatKhauActivity extends AppCompatActivity {
    Toolbar toolBarDoiMatKhau;
    EditText edtMatKhauCu,edtMatKhauMoi,edtXNMatKhauMoi;
    Button btnXNMK;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        AnhXa();
        progressDialog = new ProgressDialog(this);
        ActionToobar();
        ButtonClick(Server.urlDoiMatKhau);
    }

    private void ButtonClick(String url) {
        btnXNMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Vui lòng đợi...");
                progressDialog.show();
                User user = HomeActivity.getUser2();
                String matkhaucu = user.getPassword().trim();

                int idUser = user.getId();
               if(checkEditText(edtMatKhauCu)  && checkEditText(edtMatKhauMoi) && checkEditText(edtXNMatKhauMoi)){
                   String matkhaucu1= edtMatKhauCu.getText().toString().trim();

                   String matkhaumoi = edtMatKhauMoi.getText().toString().trim();
                   String xacnhanmatkhaumoi = edtXNMatKhauMoi.getText().toString().trim();
                   if(matkhaucu1.equals(matkhaucu)){
                       if(xacnhanmatkhaumoi.equals(matkhaumoi)){
                           RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                           StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                               @Override
                               public void onResponse(String response) {
                                   progressDialog.dismiss();
                                   String message = "";
                                   try {
                                       JSONObject jsonObject = new JSONObject(response);
                                       if(jsonObject.getInt("success") == 1){
                                           message = jsonObject.getString("message");
                                           Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                                           finish();
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
                                   params.put("matkhaumoi",matkhaumoi);
                                   params.put("idUser",String.valueOf(idUser));

                                   return params;
                               }
                           };
                           requestQueue.add(stringRequest);

                       }else{
                           progressDialog.dismiss();
                           edtXNMatKhauMoi.setError("Mật khẩu mới và xác nhận mật khẩu mới không khớp!");
                       }
                   }else{
                       progressDialog.dismiss();
                       edtMatKhauCu.setError("Mật khẩu không đúng,vui lòng nhập lại!");
                   }
               }
            }
        });
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

    private void ActionToobar() {
        setSupportActionBar(toolBarDoiMatKhau);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarDoiMatKhau.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolBarDoiMatKhau = findViewById(R.id.toolBarDoiMatKhau);
        edtMatKhauCu = findViewById(R.id.editTextMatKhauCu);
        edtMatKhauMoi = findViewById(R.id.editTextMatKhauMoi);
        edtXNMatKhauMoi = findViewById(R.id.editTextNhapLaiMatKhauMoi);
        btnXNMK = findViewById(R.id.buttonXNDMK);
    }
}