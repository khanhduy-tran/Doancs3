package com.khanhduy.doancuoiki.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.khanhduy.doancuoiki.Object.DonDatMonAn;
import com.khanhduy.doancuoiki.R;

import java.util.ArrayList;

public class DonDichVuAdapter extends BaseAdapter {
    ArrayList<DonDatMonAn> donDatMonAnArrayList;
    Context context;
    int layout;

    public DonDichVuAdapter(ArrayList<DonDatMonAn> donDatMonAnArrayList, Context context, int layout) {
        this.donDatMonAnArrayList = donDatMonAnArrayList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return donDatMonAnArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return donDatMonAnArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        TextView txtMaDonKHDV,txtTenKHDV,txtEmailKHDV;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater  = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_dondatmonan,null);
            viewHolder.txtMaDonKHDV = convertView.findViewById(R.id.textViewMaDonKHDV);
            viewHolder.txtTenKHDV = convertView.findViewById(R.id.textViewTenKHDV);
            viewHolder.txtEmailKHDV = convertView.findViewById(R.id.textViewEmailKHDV);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
       DonDatMonAn donDatMonAn = (DonDatMonAn) getItem(position);
        viewHolder.txtMaDonKHDV.setText(donDatMonAn.getMaDonPhong()+"");
        viewHolder.txtTenKHDV.setText(donDatMonAn.getTenKhachHang());
        viewHolder.txtEmailKHDV.setText(donDatMonAn.getEmail());
        return convertView;
    }
}
