package com.khanhduy.doancuoiki.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.khanhduy.doancuoiki.CheckConnection.CheckConnection;
import com.khanhduy.doancuoiki.Object.Phong;
import com.khanhduy.doancuoiki.R;
import com.khanhduy.doancuoiki.Server.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class ChitietphongActivity extends AppCompatActivity {
    Toolbar toolBarDetailRoom;
    ImageView imgDetailRoom,img2DetailRoom,img3DetailRoom,img4DetailRoom,imgLike,imgShare;
    TextView txtTenDetailRoom,txtGiaDetailRoom;
    JustifyTextView txtMotaDetailRoom;
    Button btnDatPhong;
    ShareDialog shareDialog;
    ProgressDialog progressDialog;
    int id = 0;
    String ten = "";
    int gia = 0;
    int giamgia = 0;
    int giamoi = 0;
    String hinhanh = "";
    String hinhanh2 = "";
    String hinhanh3 = "";
    String hinhanh4 = "";
    String mota = "";
    int idphong = 0;
    int trangthai = 0;
    int ID = 0;
    String Tenphong = "";
    Integer Giaphong = 0;
    String Hinhanhphong = "";
    String Hinh2 = "";
    String Hinh3 = "";
    String Hinh4 = "";
    int Giamoi = 0;
    int Giamgia = 0;
    String Motaphong = "";
    int IDphong = 0;
    int Trangthai = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietphong);
        AnhXa();
        shareDialog = new ShareDialog(this);
        progressDialog = new ProgressDialog(this);
        ActionToolBar();
        GetInformation();
        CheckLikeRoom();
        LikeRoom();
        ShareRoom();
        BookingButton();

    }

    private void CheckLikeRoom() {
        Phong phong1 = (Phong) getIntent().getSerializableExtra("thongtinphong");
        String idUser = HomeActivity.textId();
        int idPhong = phong1.getId();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlCheckLikeRoom, new Response.Listener<String>() {
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
                params.put("idPhong",String.valueOf(idPhong));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ShareRoom() {
        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap image = ((BitmapDrawable) imgDetailRoom.getDrawable()).getBitmap();
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
        Phong phong1 = (Phong) getIntent().getSerializableExtra("thongtinphong");
        String idUser = HomeActivity.textId();
        int idPhong = phong1.getId();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlLikeRoom, new Response.Listener<String>() {
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
                params.put("idPhong",String.valueOf(idPhong));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void BookingButton() {
        btnDatPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Phong phong1 = (Phong) getIntent().getSerializableExtra("thongtinphong");
                ID = phong1.getId();
                Tenphong = phong1.getTenphong();
                Hinhanhphong = phong1.getHinh1();
                Hinh2 = phong1.getHinh2();
                Hinh3 = phong1.getHinh3();
                Hinh4 = phong1.getHinh4();
                Giamoi = phong1.getGiamoi();
                Giamgia = phong1.getGiamgia();
                if(Giamgia > 0){
                    Giaphong = Giamoi;
                }else{
                    Giaphong = phong1.getGiaphong();
                }
                Motaphong = phong1.getMota();
                IDphong = phong1.getIdPhong();
                Trangthai = phong1.getTrangthai();

               Phong phongObject = new Phong(ID,Tenphong,Giaphong,Hinhanhphong,Hinh2,Hinh3,Hinh4,Giamoi,Giamgia,Motaphong,IDphong,Trangthai);
                Intent intent = new Intent(ChitietphongActivity.this, ThanhtoanActivity.class);
                intent.putExtra("hoadonphong",phongObject);
                startActivity(intent);
            }
        });
    }

    private void GetInformation() {
        Phong phong = (Phong) getIntent().getSerializableExtra("thongtinphong");
        id = phong.getId();
        ten = phong.getTenphong();
        giamgia = phong.getGiamgia();
        giamoi = phong.getGiamoi();
        if(giamgia > 0){
            gia = giamoi;
        }else{
            gia = phong.getGiaphong();
        }
        hinhanh = phong.getHinh1();
        hinhanh2=phong.getHinh2();
        hinhanh3 = phong.getHinh3();
        hinhanh4 = phong.getHinh4();
        mota = phong.getMota();
        idphong = phong.getIdPhong();
        trangthai = phong.getTrangthai();
        txtTenDetailRoom.setText(ten);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGiaDetailRoom.setText("Giá : " + decimalFormat.format(gia) + " VNĐ/đêm");
        txtMotaDetailRoom.setText(mota);
        Picasso.with(getApplicationContext()).load(hinhanh)
                .placeholder(R.drawable.image_48px)
                .error(R.drawable.error_48px)
                .into(imgDetailRoom);
        Picasso.with(getApplicationContext()).load(hinhanh2)
                .placeholder(R.drawable.image_48px)
                .error(R.drawable.error_48px)
                .into(img2DetailRoom);
        Picasso.with(getApplicationContext()).load(hinhanh3)
                .placeholder(R.drawable.image_48px)
                .error(R.drawable.error_48px)
                .into(img3DetailRoom);
        Picasso.with(getApplicationContext()).load(hinhanh4)
                .placeholder(R.drawable.image_48px)
                .error(R.drawable.error_48px)
                .into(img4DetailRoom);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolBarDetailRoom);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarDetailRoom.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolBarDetailRoom = (Toolbar) findViewById(R.id.toolBarDetailRoom);
        imgDetailRoom = (ImageView) findViewById(R.id.imageViewDetailRoom);
        img2DetailRoom = (ImageView) findViewById(R.id.imageView2DetailRoom);
        img3DetailRoom = (ImageView) findViewById(R.id.imageView3DetailRoom);
        img4DetailRoom = (ImageView) findViewById(R.id.imageView4DetailRoom);
        txtTenDetailRoom = (TextView) findViewById(R.id.textViewTenDetailRoom);
        txtGiaDetailRoom = (TextView) findViewById(R.id.textViewGiaDetailRoom);
        txtMotaDetailRoom = (JustifyTextView) findViewById(R.id.textViewMotaDetailRoom);
        btnDatPhong = (Button) findViewById(R.id.buttonDatPhong);
        imgLike = (ImageView) findViewById(R.id.imageViewLike);
        imgShare = (ImageView) findViewById(R.id.imageViewShare);
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