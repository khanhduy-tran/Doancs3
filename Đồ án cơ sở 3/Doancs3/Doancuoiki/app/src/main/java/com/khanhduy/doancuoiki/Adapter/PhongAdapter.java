package com.khanhduy.doancuoiki.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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

public class PhongAdapter extends RecyclerView.Adapter<PhongAdapter.ItemHolder> implements Filterable {
    Context context;
    ArrayList<Phong> phongArrayList;
    ArrayList<Phong> phongArrayListOld;

    public PhongAdapter(Context context, ArrayList<Phong> phongArrayList) {
        this.context = context;
        this.phongArrayList = phongArrayList;
        this.phongArrayListOld = phongArrayList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_phong_khachsan,null);
        ItemHolder itemHolder = new  ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Phong phong = phongArrayList.get(position);
        holder.txtPhong.setText(phong.getTenphong());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiaPhong.setText("Giá : " + decimalFormat.format(phong.getGiaphong()) + " VNĐ/đêm");
        Picasso.with(context).load(phong.getHinh1())
        .placeholder(R.drawable.image_48px)
        .error(R.drawable.error_48px)
        .into(holder.imgPhong);
    }

    @Override
    public int getItemCount() {
        return phongArrayList.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgPhong;
        public TextView txtPhong,txtGiaPhong;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgPhong = itemView.findViewById(R.id.imageViewPhongKS);
            txtPhong = itemView.findViewById(R.id.textViewPhongKS);
            txtGiaPhong = itemView.findViewById(R.id.textViewGiaKS);
            imgPhong.setOnClickListener(new View.OnClickListener() {
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
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    phongArrayList = phongArrayListOld;
                }else{
                    ArrayList<Phong> list = new ArrayList<>();
                    for(Phong phong : phongArrayList){
                        if(phong.getGiaphong().toString().contains(strSearch.toLowerCase())){
                            list.add(phong);
                        }
                        else if(phong.getTenphong().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(phong);
                        }
                    }
                    phongArrayList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = phongArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                phongArrayList = (ArrayList<Phong>) results.values;
                notifyDataSetChanged();
            }
        };
    }


}
