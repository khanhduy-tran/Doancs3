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
import com.khanhduy.doancuoiki.Object.DichVuDoAn;
import com.khanhduy.doancuoiki.Adapter.DichVuDoAnAdapter;
import com.khanhduy.doancuoiki.R;
import com.khanhduy.doancuoiki.Server.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab2Fragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    ArrayList<DichVuDoAn> dichVuDoAnArrayList;
   public static DichVuDoAnAdapter dichVuDoAnAdapter;


    public Tab2Fragment(){
    }

    public static  Tab2Fragment newInstance(){
        Tab2Fragment tab2Fragment = new Tab2Fragment();
        return tab2Fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_tab2,container,false);
        AnhXa();
        GetDuLieuMonAn(Server.urlTab2Fragment);
        return view;
    }

    private void GetDuLieuMonAn(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    int id = 0;
                    String tenmonan = "";
                    Integer giamonan = 0;
                    String hinhanh = "";
                    String hinhanh1 = "";
                    String hinhanh2 = "";
                    String hinhanh3 = "";
                    String mota = "";
                    int trangthai = 0;
                    for(int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenmonan = jsonObject.getString("tenmonan");
                            giamonan = jsonObject.getInt("giamonan");
                            hinhanh = jsonObject.getString("hinhanh");
                            hinhanh1 = jsonObject.getString("hinhanh1");
                            hinhanh2 = jsonObject.getString("hinhanh2");
                            hinhanh3 = jsonObject.getString("hinhanh3");
                            mota = jsonObject.getString("mota");
                            trangthai = jsonObject.getInt("trangthai");
                            dichVuDoAnArrayList.add(new DichVuDoAn(id,tenmonan,giamonan,hinhanh,hinhanh1,hinhanh2,hinhanh3,mota,trangthai));
                            dichVuDoAnAdapter.notifyDataSetChanged();
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

    private void AnhXa() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleViewTabDichVu);
        dichVuDoAnArrayList = new ArrayList<>();
        dichVuDoAnAdapter = new DichVuDoAnAdapter(getContext(),dichVuDoAnArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(dichVuDoAnAdapter);
    }
}