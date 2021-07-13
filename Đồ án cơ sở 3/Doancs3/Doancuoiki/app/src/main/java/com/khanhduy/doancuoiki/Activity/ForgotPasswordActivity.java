package com.khanhduy.doancuoiki.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText edtLMK;
    Button btnLMK;
    ProgressDialog progressDialog;
    Toolbar toolBarLMK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        toolBarLMK = findViewById(R.id.toolBarLMK);
        ActionToolBar();
        edtLMK = (EditText) findViewById(R.id.editTextLMK);
        btnLMK = (Button) findViewById(R.id.buttonLMK);
        progressDialog = new ProgressDialog(this);
        btnLMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Vui lòng đợi...");
                progressDialog.show();
                ForgotPassword(Server.urlLayMatKhau);
            }
        });
    }

    private void ActionToolBar() {
        setSupportActionBar(toolBarLMK);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarLMK.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ForgotPassword(String url){
        RequestQueue requestQueue =  Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                String message = "";
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getInt("success") == 1){
                        message = jsonObject.getString("message");
                        Toast.makeText(ForgotPasswordActivity.this,message,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgotPasswordActivity.this, PhoneLoginActivity.class);
                        startActivity(intent);
                    }else{
                        message = jsonObject.getString("message");
                        Toast.makeText(ForgotPasswordActivity.this,message,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("error","error" + e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error","error" + error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("emailAccount",edtLMK.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}