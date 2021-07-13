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

public class LikeRoomAdapter extends RecyclerView.Adapter<LikeRoomAdapter.ItemHolder4> {
    Context context;
    ArrayList<Phong> phongArrayList;
    public LikeRoomAdapter(Context context, ArrayList<Phong> phongArrayList) {
        this.context = context;
        this.phongArrayList = phongArrayList;
    }
    @NonNull
    @Override
    public ItemHolder4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_likeroom,null);
        LikeRoomAdapter.ItemHolder4 itemHolder4 = new LikeRoomAdapter.ItemHolder4(view);
        return itemHolder4;

    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder4 holder, int position) {
        Phong phong = phongArrayList.get(position);
        holder.txtLikeRoom.setText(phong.getTenphong());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiaLikeRoom.setText("Giá : " + decimalFormat.format(phong.getGiaphong()) + " VNĐ/đêm");
        Picasso.with(context).load(phong.getHinh1())
                .placeholder(R.drawable.image_48px)
                .error(R.drawable.error_48px)
                .into(holder.imgLikeRoom);
    }

    @Override
    public int getItemCount() {
        return phongArrayList.size();
    }

    public class ItemHolder4 extends RecyclerView.ViewHolder{
        public ImageView imgLikeRoom;
        public TextView txtLikeRoom,txtGiaLikeRoom;

        public ItemHolder4(@NonNull View itemView) {
            super(itemView);
            imgLikeRoom = itemView.findViewById(R.id.imageViewLikeRoom);
            txtLikeRoom = itemView.findViewById(R.id.textViewLikeRoom);
            txtGiaLikeRoom = itemView.findViewById(R.id.textViewGiaLikeRoom);
            imgLikeRoom.setOnClickListener(new View.OnClickListener() {
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