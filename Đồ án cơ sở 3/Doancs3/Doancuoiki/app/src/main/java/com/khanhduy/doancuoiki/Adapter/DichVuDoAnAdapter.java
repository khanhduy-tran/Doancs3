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


import com.khanhduy.doancuoiki.Activity.ChiTietMonAnActivity;
import com.khanhduy.doancuoiki.Object.DichVuDoAn;
import com.khanhduy.doancuoiki.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DichVuDoAnAdapter extends RecyclerView.Adapter<DichVuDoAnAdapter.Item2Holder> implements Filterable {
    Context context;
    ArrayList<DichVuDoAn> dichVuDoAnArrayList;
    ArrayList<DichVuDoAn> dichVuDoAnArrayListOld;

    public DichVuDoAnAdapter(Context context, ArrayList<DichVuDoAn> dichVuDoAnArrayList) {
        this.context = context;
        this.dichVuDoAnArrayList = dichVuDoAnArrayList;
        this.dichVuDoAnArrayListOld = dichVuDoAnArrayList;
    }

    @NonNull
    @Override
    public Item2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_dichvumonan,null);
        Item2Holder item2Holder = new Item2Holder(view);
        return item2Holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Item2Holder holder, int position) {
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


    public class Item2Holder extends  RecyclerView.ViewHolder{
    public ImageView imghinhmonan;
    public TextView txttenmonan,txtgiamonan;

    public Item2Holder(@NonNull View itemView) {
        super(itemView);
        imghinhmonan = itemView.findViewById(R.id.imageViewDVMonAn);
        txttenmonan = itemView.findViewById(R.id.textViewTenDVMonAn);
        txtgiamonan = itemView.findViewById(R.id.textViewGiaDVMonAn);
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
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    dichVuDoAnArrayList = dichVuDoAnArrayListOld;
                }else{
                    ArrayList<DichVuDoAn> list = new ArrayList<>();
                    for(DichVuDoAn dichVuDoAn : dichVuDoAnArrayList){
                        if(dichVuDoAn.getGiamonan().toString().contains(strSearch)){
                            list.add(dichVuDoAn);
                        }
                        else if(dichVuDoAn.getTenmonan().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(dichVuDoAn);
                        }
                    }
                    dichVuDoAnArrayList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = dichVuDoAnArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dichVuDoAnArrayList = (ArrayList<DichVuDoAn>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
