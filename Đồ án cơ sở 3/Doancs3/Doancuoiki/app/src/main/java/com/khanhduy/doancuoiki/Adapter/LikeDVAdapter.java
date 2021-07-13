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

import com.khanhduy.doancuoiki.Activity.ChiTietMonAnActivity;
import com.khanhduy.doancuoiki.Activity.ChitietphongActivity;
import com.khanhduy.doancuoiki.Object.DichVuDoAn;
import com.khanhduy.doancuoiki.Object.Phong;
import com.khanhduy.doancuoiki.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LikeDVAdapter extends RecyclerView.Adapter<LikeDVAdapter.ItemHolder5> {
    Context context;
    ArrayList<DichVuDoAn> dichVuDoAnArrayList;
    public LikeDVAdapter(Context context, ArrayList<DichVuDoAn> dichVuDoAnArrayList) {
        this.context = context;
        this.dichVuDoAnArrayList = dichVuDoAnArrayList;
    }
    @NonNull
    @Override
    public ItemHolder5 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_likedichvu,null);
        LikeDVAdapter.ItemHolder5 itemHolder5 = new LikeDVAdapter.ItemHolder5(view);
        return itemHolder5;

    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder5 holder, int position) {
        DichVuDoAn dichVuDoAn = dichVuDoAnArrayList.get(position);
        holder.txttenmonan.setText(dichVuDoAn.getTenmonan());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgiamonan.setText("Giá :" + decimalFormat.format(dichVuDoAn.getGiamonan()) + " VNĐ/món");
        Picasso.with(context).load(dichVuDoAn.getHinhanh())
                .placeholder(R.drawable.image_48px)
                .error(R.drawable.error_48px)
                .into(holder.imghinhmonan);
    }

    @Override
    public int getItemCount() {
        return dichVuDoAnArrayList.size();
    }

    public class ItemHolder5 extends RecyclerView.ViewHolder{
        public ImageView imghinhmonan;
        public TextView txttenmonan,txtgiamonan;

        public ItemHolder5(@NonNull View itemView) {
            super(itemView);
            imghinhmonan = itemView.findViewById(R.id.imageViewLikeDV);
            txttenmonan = itemView.findViewById(R.id.textViewLikeDV);
            txtgiamonan = itemView.findViewById(R.id.textViewGiaLikeDV);
            imghinhmonan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChiTietMonAnActivity.class);
                    intent.putExtra("thongtinmonan",dichVuDoAnArrayList.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}