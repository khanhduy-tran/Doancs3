package com.khanhduy.doancuoiki.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.khanhduy.doancuoiki.Activity.ChitietphongActivity;
import com.khanhduy.doancuoiki.Object.Phong;
import com.khanhduy.doancuoiki.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PhongTrongAdapter extends RecyclerView.Adapter<PhongTrongAdapter.ItemHolder3> {
    Context context;
    ArrayList<Phong> phongArrayList;
    public PhongTrongAdapter(Context context, ArrayList<Phong> phongArrayList) {
        this.context = context;
        this.phongArrayList = phongArrayList;
    }
    @NonNull
    @Override
    public ItemHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_phongtrong,null);
        PhongTrongAdapter.ItemHolder3 itemHolder3 = new PhongTrongAdapter.ItemHolder3(view);
        return itemHolder3;

    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder3 holder, int position) {
        Phong phong = phongArrayList.get(position);
        holder.txtPhongTrong.setText(phong.getTenphong());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiaPhongTrong.setText("Giá : " + decimalFormat.format(phong.getGiaphong()) + " VNĐ/đêm");
        Picasso.with(context).load(phong.getHinh1())
                .placeholder(R.drawable.image_48px)
                .error(R.drawable.error_48px)
                .into(holder.imgPhongTrong);
    }

    @Override
    public int getItemCount() {
        return phongArrayList.size();
    }

    public class ItemHolder3 extends RecyclerView.ViewHolder{
        public ImageView imgPhongTrong;
        public TextView txtPhongTrong,txtGiaPhongTrong;

        public ItemHolder3(@NonNull View itemView) {
            super(itemView);
            imgPhongTrong = itemView.findViewById(R.id.imageViewPhongTrong);
            txtPhongTrong = itemView.findViewById(R.id.textViewPhongTrong);
            txtGiaPhongTrong = itemView.findViewById(R.id.textViewGiaPhongTrong);
            imgPhongTrong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChitietphongActivity.class);
                    intent.putExtra("thongtinphong",phongArrayList.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
