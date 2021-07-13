package com.khanhduy.doancuoiki.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khanhduy.doancuoiki.Activity.ChiTietHoaDonActivity;
import com.khanhduy.doancuoiki.Activity.ChiTietHoaDonMonAnActivity;
import com.khanhduy.doancuoiki.Activity.HomeActivity;
import com.khanhduy.doancuoiki.Object.DonDatMonAn;
import com.khanhduy.doancuoiki.Object.DonDatPhong;
import com.khanhduy.doancuoiki.Adapter.DonDatPhongAdapter;
import com.khanhduy.doancuoiki.Adapter.DonDichVuAdapter;
import com.khanhduy.doancuoiki.R;
import com.khanhduy.doancuoiki.Server.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FragmentBooking extends Fragment {
    View view;
    ListView listViewDonPhong;
    ArrayList<DonDatPhong> donDatPhongArrayList;
    DonDatPhongAdapter donDatPhongAdapter;

    ListView listViewDonDichVu;
    ArrayList<DonDatMonAn> donDatMonAnArrayList;
    DonDichVuAdapter donDichVuAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_booking,container,false);
        AnhXa();
        GetDonPhong(Server.urlHoaDonPhong);
        GetDonDichVu(Server.urlHoaDonMonAn);
        CatOnItemListViewDP();
        CatOnItemListViewDV();
        return view;
    }

    private void GetDonDichVu(String url) {
        String idUser = HomeActivity.textId();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response !=null){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            int maphong = jsonObject.getInt("madonphong");
                            String tenKH = jsonObject.getString("tenkhachhang");
                            String email = jsonObject.getString("emailkhachhang");
                            donDatMonAnArrayList.add(new DonDatMonAn(maphong,tenKH,email));
                            donDichVuAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("ErrorFragmentBooking","Lỗi :" + e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ErrorFragmentBooking","Lỗi :" + error);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("idUser",idUser);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void CatOnItemListViewDP() {
        listViewDonPhong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ChiTietHoaDonActivity.class);
                intent.putExtra("maphong",donDatPhongArrayList.get(position).getMaDonPhong());
                startActivity(intent);
            }
        });
    }
    private void CatOnItemListViewDV() {
        listViewDonDichVu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ChiTietHoaDonMonAnActivity.class);
                intent.putExtra("mamonan",donDatMonAnArrayList.get(position).getMaDonPhong());
                startActivity(intent);
            }
        });
    }

    private void GetDonPhong(String url) {
        String idUser = HomeActivity.textId();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response !=null){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            int maphong = jsonObject.getInt("madonphong");
                            String tenKH = jsonObject.getString("tenkhachhang");
                            String email = jsonObject.getString("emailkhachhang");
                            donDatPhongArrayList.add(new DonDatPhong(maphong,tenKH,email));
                            donDatPhongAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("ErrorFragmentBooking","Lỗi :" + e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ErrorFragmentBooking","Lỗi :" + error);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("idUser",idUser);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa() {
        listViewDonPhong = (ListView) view.findViewById(R.id.lisViewBooking);
        donDatPhongArrayList = new ArrayList<>();
        donDatPhongAdapter = new DonDatPhongAdapter(donDatPhongArrayList,getContext(), android.R.layout.simple_list_item_1);
        listViewDonPhong.setAdapter(donDatPhongAdapter);


        listViewDonDichVu =(ListView) view.findViewById(R.id.lisViewBookingDV);
        donDatMonAnArrayList = new ArrayList<>();
        donDichVuAdapter = new DonDichVuAdapter(donDatMonAnArrayList,getContext(), android.R.layout.simple_list_item_1);
        listViewDonDichVu.setAdapter(donDichVuAdapter);

    }

}