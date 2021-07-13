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

public class SuperiorAdapter extends BaseAdapter {
    Context context;
    ArrayList<Phong> phongArrayList;

    public SuperiorAdapter(Context context, ArrayList<Phong> phongArrayList) {
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
        ImageView imgSuperior;
        TextView txtTenSuperior,txtGiaSuperior,txtMotaSuperior;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SuperiorAdapter.ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new SuperiorAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_phong_superior,null);
            viewHolder.txtTenSuperior = convertView.findViewById(R.id.textViewTenSuperior);
            viewHolder.txtGiaSuperior = convertView.findViewById(R.id.textViewGiaSuperior);
            viewHolder.txtMotaSuperior = convertView.findViewById(R.id.textViewMoTaSuperior);
            viewHolder.imgSuperior = convertView.findViewById(R.id.imageViewSuperior);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (SuperiorAdapter.ViewHolder) convertView.getTag();
        }
        Phong phong = (Phong) getItem(position);
        viewHolder.txtTenSuperior.setText(phong.getTenphong());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaSuperior.setText("Giá : " + decimalFormat.format(phong.getGiaphong()) + " VNĐ/đêm");

        viewHolder.txtMotaSuperior.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMotaSuperior.setMaxLines(2);
        viewHolder.txtMotaSuperior.setText(phong.getMota());

        Picasso.with(context).load(phong.getHinh1())
                .placeholder(R.drawable.image_48px)
                .error(R.drawable.error_48px)
                .into(viewHolder.imgSuperior);
        return convertView;
    }
}
