package com.khanhduy.doancuoiki.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.khanhduy.doancuoiki.Object.DonDatPhong;
import com.khanhduy.doancuoiki.R;

import java.util.ArrayList;

public class DonDatPhongAdapter extends BaseAdapter {
    ArrayList<DonDatPhong> donDatPhongArrayList;
    Context context;
    int layout;

    public DonDatPhongAdapter(ArrayList<DonDatPhong> donDatPhongArrayList, Context context, int layout) {
        this.donDatPhongArrayList = donDatPhongArrayList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return donDatPhongArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return donDatPhongArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        TextView txtMaDonKH,txtTenKH,txtEmailKH;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater  = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_dondatphong,null);
            viewHolder.txtMaDonKH = convertView.findViewById(R.id.textViewMaDonKH);
            viewHolder.txtTenKH = convertView.findViewById(R.id.textViewTenKH);
            viewHolder.txtEmailKH = convertView.findViewById(R.id.textViewEmailKH);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        DonDatPhong donDatPhong = (DonDatPhong) getItem(position);
        viewHolder.txtMaDonKH.setText(donDatPhong.getMaDonPhong()+"");
        viewHolder.txtTenKH.setText(donDatPhong.getTenKhachHang());
        viewHolder.txtEmailKH.setText(donDatPhong.getEmail());
        return convertView;
    }
}
