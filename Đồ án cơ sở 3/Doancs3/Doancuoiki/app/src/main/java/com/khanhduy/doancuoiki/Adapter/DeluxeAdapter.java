package com.khanhduy.doancuoiki.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.khanhduy.doancuoiki.Object.Phong;
import com.khanhduy.doancuoiki.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DeluxeAdapter extends BaseAdapter {
    Context context;
    ArrayList<Phong> phongArrayList;

    public DeluxeAdapter(Context context, ArrayList<Phong> phongArrayList) {
        this.context = context;
        this.phongArrayList = phongArrayList;
    }

    @Override
    public int getCount() {
        return phongArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return phongArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        ImageView imgDeluxe;
        TextView txtTenDeluxe,txtGiaDeluxe,txtMotaDeluxe;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DeluxeAdapter.ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new DeluxeAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_phong_deluxe,null);
            viewHolder.txtTenDeluxe = convertView.findViewById(R.id.textViewTenDeluxe);
            viewHolder.txtGiaDeluxe = convertView.findViewById(R.id.textViewGiaDeluxe);
            viewHolder.txtMotaDeluxe = convertView.findViewById(R.id.textViewMoTaDeluxe);
            viewHolder.imgDeluxe = convertView.findViewById(R.id.imageViewDeluxe);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (DeluxeAdapter.ViewHolder) convertView.getTag();
        }
        Phong phong = (Phong) getItem(position);
        viewHolder.txtTenDeluxe.setText(phong.getTenphong());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaDeluxe.setText("Giá : " + decimalFormat.format(phong.getGiaphong()) + " VNĐ/đêm");

        viewHolder.txtMotaDeluxe.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMotaDeluxe.setMaxLines(2);
        viewHolder.txtMotaDeluxe.setText(phong.getMota());

        Picasso.with(context).load(phong.getHinh1())
                .placeholder(R.drawable.image_48px)
                .error(R.drawable.error_48px)
                .into(viewHolder.imgDeluxe);
        return convertView;
    }
}
