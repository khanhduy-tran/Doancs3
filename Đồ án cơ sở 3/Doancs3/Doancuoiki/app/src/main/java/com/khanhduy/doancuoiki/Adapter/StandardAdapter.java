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

public class StandardAdapter extends BaseAdapter {
    Context context;
    ArrayList<Phong> phongArrayList;

    public StandardAdapter(Context context, ArrayList<Phong> phongArrayList) {
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
        ImageView imgStandard;
        TextView txtTenStandard,txtGiaStandard,txtMotaStandard;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_phong_standard,null);
            viewHolder.txtTenStandard = convertView.findViewById(R.id.textViewTenStandard);
            viewHolder.txtGiaStandard = convertView.findViewById(R.id.textViewGiaStandard);
            viewHolder.txtMotaStandard = convertView.findViewById(R.id.textViewMoTaStandard);
            viewHolder.imgStandard = convertView.findViewById(R.id.imageViewStandard);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Phong phong = (Phong) getItem(position);
        viewHolder.txtTenStandard.setText(phong.getTenphong());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaStandard.setText("Giá : " + decimalFormat.format(phong.getGiaphong()) + " VNĐ/đêm");

        viewHolder.txtMotaStandard.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMotaStandard.setMaxLines(2);
        viewHolder.txtMotaStandard.setText(phong.getMota());

        Picasso.with(context).load(phong.getHinh1())
                .placeholder(R.drawable.image_48px)
                .error(R.drawable.error_48px)
                .into(viewHolder.imgStandard);
        return convertView;
    }
}
