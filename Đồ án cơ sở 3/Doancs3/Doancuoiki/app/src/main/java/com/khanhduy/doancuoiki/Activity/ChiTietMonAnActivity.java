package com.khanhduy.doancuoiki.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.khanhduy.doancuoiki.CheckConnection.CheckConnection;
import com.khanhduy.doancuoiki.Object.DichVuDoAn;
import com.khanhduy.doancuoiki.Object.Phong;
import com.khanhduy.doancuoiki.R;
import com.khanhduy.doancuoiki.Server.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class ChiTietMonAnActivity extends AppCompatActivity {
    Toolbar toolBarDetailDV;
    ImageView imgDetailDV,img2DetailDV,img3DetailDV,img4DetailDV,imgLike,imgShare;
    TextView txtTenDetailDV,txtGiaDetailDV;
    JustifyTextView  txtMotaDetailDV;
    Button btnDatMon;
    ShareDialog shareDialog;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon_an);
        AnhXa();
        shareDialog = new ShareDialog(this);
        progressDialog = new ProgressDialog(this);
        ActionToolbar();
        GetInformation();
        CheckLikeRoom();
        LikeRoom();
        ShareRoom();
        BookingButton();
    }

    private void ShareRoom() {
        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap image = ((BitmapDrawable) imgDetailDV.getDrawable()).getBitmap();
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(image)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();
                shareDialog.show(content);
            }
        });
    }

    private void LikeRoom() {
        imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActiveLike();
            }
        });
    }
    private void ActiveLike(){
        progressDialog.setMessage("Vui lòng đợi...");
        progressDialog.show();
        DichVuDoAn dichVuDoAn = (DichVuDoAn) getIntent().getSerializableExtra("thongtinmonan");
        String idUser = HomeActivity.textId();
        int idMonAn = dichVuDoAn.getId();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlLikeDV, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                String message = "";
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getInt("success") == 1){
                        message = jsonObject.getString("message");
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                        imgLike.setColorFilter(Color.RED);
                        imgLike.setEnabled(false);
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
                        progressDialog.dismiss();
                        CheckConnection.ShowToas_Short(getApplicationContext(),error + "");
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("idUser",idUser);
                params.put("idMonAn",String.valueOf(idMonAn));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void CheckLikeRoom() {
        DichVuDoAn dichVuDoAn = (DichVuDoAn) getIntent().getSerializableExtra("thongtinmonan");
        String idUser = HomeActivity.textId();
        int idMonAn = dichVuDoAn.getId();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlCheckLikeDV, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getInt("success") == 1){
                        imgLike.setColorFilter(Color.RED);
                        imgLike.setEnabled(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        CheckConnection.ShowToas_Short(getApplicationContext(),error + "");
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("idUser",idUser);
                params.put("idMonAn",String.valueOf(idMonAn));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void BookingButton() {
        btnDatMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DichVuDoAn dichVuDoAn = (DichVuDoAn) getIntent().getSerializableExtra("thongtinmonan");
                int id = dichVuDoAn.getId();
                String tenmonan = dichVuDoAn.getTenmonan();
                int giamonan = dichVuDoAn.getGiamonan();
                String hinhanh = dichVuDoAn.getHinhanh();
                String hinhanh2 = dichVuDoAn.getHinhanh2();
                String hinhanh3 = dichVuDoAn.getHinhanh3();
                String hinhanh4 = dichVuDoAn.getHinhanh3();
                String mota = dichVuDoAn.getMota();
                int trangthai = dichVuDoAn.getTrangthai();
                DichVuDoAn dichVuDoAn1 = new DichVuDoAn(id, tenmonan,giamonan,hinhanh,hinhanh2,hinhanh3,hinhanh4,mota,trangthai);
                Intent intent = new Intent(ChiTietMonAnActivity.this, ThanhToanMonAnActivity.class);
                intent.putExtra("hoadonmonan",dichVuDoAn1);
                startActivity(intent);
            }
        });
    }

    private void GetInformation() {
        DichVuDoAn dichVuDoAn = (DichVuDoAn) getIntent().getSerializableExtra("thongtinmonan");
        int id = dichVuDoAn.getId();
        String tenmonan = dichVuDoAn.getTenmonan();
        int giamonan = dichVuDoAn.getGiamonan();
        String hinhanh = dichVuDoAn.getHinhanh();
        String hinhanh1 = dichVuDoAn.getHinhanh1();
        String hinhanh2 = dichVuDoAn.getHinhanh2();
        String hinhanh3 = dichVuDoAn.getHinhanh3();
        String mota = dichVuDoAn.getMota();
        int trangthai = dichVuDoAn.getTrangthai();
        txtTenDetailDV.setText(tenmonan);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGiaDetailDV.setText("Giá : " + decimalFormat.format(giamonan) + " VNĐ/món");
        txtMotaDetailDV.setText(mota);
        Picasso.with(getApplicationContext()).load(hinhanh)
                .placeholder(R.drawable.image_48px)
                .error(R.drawable.error_48px)
                .into(imgDetailDV);
        Picasso.with(getApplicationContext()).load(hinhanh1)
                .placeholder(R.drawable.image_48px)
                .error(R.drawable.error_48px)
                .into(img2DetailDV);
        Picasso.with(getApplicationContext()).load(hinhanh2)
                .placeholder(R.drawable.image_48px)
                .error(R.drawable.error_48px)
                .into(img3DetailDV);
        Picasso.with(getApplicationContext()).load(hinhanh3)
                .placeholder(R.drawable.image_48px)
                .error(R.drawable.error_48px)
                .into(img4DetailDV);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolBarDetailDV);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarDetailDV.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolBarDetailDV = (Toolbar) findViewById(R.id.toolBarDetailDV);
        imgDetailDV = (ImageView) findViewById(R.id.imageViewDetailDV);
        img2DetailDV = (ImageView) findViewById(R.id.imageView2DetailDV);
        img3DetailDV = (ImageView) findViewById(R.id.imageView3DetailDV);
        img4DetailDV = (ImageView) findViewById(R.id.imageView4DetailDV);
        txtTenDetailDV = (TextView) findViewById(R.id.textViewTenDetailDV);
        txtGiaDetailDV = (TextView) findViewById(R.id.textViewGiaDetailDV);
        txtMotaDetailDV = (JustifyTextView) findViewById(R.id.textViewMotaDetailDV);
        btnDatMon = (Button) findViewById(R.id.buttonDatMonAn);
        imgLike = (ImageView) findViewById(R.id.imageViewLikeDichVu);
        imgShare = (ImageView) findViewById(R.id.imageViewShareDichVu);
    }
}