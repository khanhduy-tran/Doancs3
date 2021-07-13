package com.khanhduy.doancuoiki.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.khanhduy.doancuoiki.Object.Phong;
import com.khanhduy.doancuoiki.Adapter.PhongAdapter;
import com.khanhduy.doancuoiki.R;
import com.khanhduy.doancuoiki.Server.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab1Fragment extends Fragment {
    RecyclerView recyclerView;
    View view;
    public static ArrayList<Phong> phongArrayList;
    public static PhongAdapter phongAdapter;

    public Tab1Fragment(){
    }

    public static  Tab1Fragment newInstance(){
        Tab1Fragment tab1Fragment = new Tab1Fragment();
        return tab1Fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_tab1,container,false);
        AnhXa();
        GetDuLieuPhong(Server.urlTab1Fragment);
        return view;
    }

    private void GetDuLieuPhong(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
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
                    for(int i = 0; i < response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            Tenphong = jsonObject.getString("tenphong");
                            Giaphong = jsonObject.getInt("giaphong");
                            Hinhanhphong = jsonObject.getString("hinhanhphong");
                            Hinh2 = jsonObject.getString("hinh2");
                            Hinh3 = jsonObject.getString("hinh3");
                            Hinh4 = jsonObject.getString("hinh4");
                            Giamoi = jsonObject.getInt("giamoi");
                            Giamgia = jsonObject.getInt("giamgia");
                            Motaphong = jsonObject.getString("mota");
                            IDphong = jsonObject.getInt("idphong");
                            Trangthai = jsonObject.getInt("trangthai");
                            phongArrayList.add(new Phong(ID,Tenphong,Giaphong,Hinhanhphong,Hinh2,Hinh3,Hinh4,Giamoi,Giamgia,Motaphong,IDphong,Trangthai));
                            phongAdapter.notifyDataSetChanged();
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

    private void AnhXa(){
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleViewTabPhong);
        phongArrayList = new ArrayList<>();
        phongAdapter = new PhongAdapter(getContext(),phongArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        recyclerView.setAdapter(phongAdapter);
    }
}