package com.khanhduy.doancuoiki.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.khanhduy.doancuoiki.Object.LoaiPhong;
import com.khanhduy.doancuoiki.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaiPhongAdapter  extends BaseAdapter {
    ArrayList<LoaiPhong> loaiPhongArrayList;
    Context context;

    public LoaiPhongAdapter(ArrayList<LoaiPhong> loaiPhongArrayList, Context context) {
        this.loaiPhongArrayList = loaiPhongArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return loaiPhongArrayList.size();
    }

    @Override
    public Object getItem(int position1) {
        return loaiPhongArrayList.get(position1);
    }

    @Override
    public long getItemId(int position2) {
        return position2;
    }
    public class ViewHolder{
        TextView txtTenPhong;
        ImageView imgPhong;
    }
    @Override
    public View getView(int position3, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_listview_loaiphong,null);
            viewHolder.txtTenPhong = convertView.findViewById(R.id.textViewLoaiPhong);
            viewHolder.imgPhong = convertView.findViewById(R.id.imageViewLoaiPhong);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();

        }
        LoaiPhong loaiPhong = (LoaiPhong) getItem(position3);
        viewHolder.txtTenPhong.setText(loaiPhong.getTenloaiphong());

        Picasso.with(context).load(loaiPhong.getHinhanhloaiphong())
                .placeholder(R.drawable.image_48px)
                .error(R.drawable.error_48px)
                .into(viewHolder.imgPhong);

        return convertView;
    }
}
