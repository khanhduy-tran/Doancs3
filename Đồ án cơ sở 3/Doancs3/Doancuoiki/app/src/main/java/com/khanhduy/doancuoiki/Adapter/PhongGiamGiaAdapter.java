package com.khanhduy.doancuoiki.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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

public class PhongGiamGiaAdapter extends RecyclerView.Adapter<PhongGiamGiaAdapter.Item3Holder> implements Filterable {
    Context context;
    ArrayList<Phong> phongArrayList1;
    ArrayList<Phong> phongArrayListOld1;
    private Item3Holder item3Holder;

    public PhongGiamGiaAdapter(Context context, ArrayList<Phong> phongArrayList1) {
        this.context = context;
        this.phongArrayList1 = phongArrayList1;
        this.phongArrayListOld1 = phongArrayList1;
    }

    @NonNull
    @Override
    public Item3Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_phonggiamgia,null);
        Item3Holder item3Holder = new Item3Holder(view);
        return item3Holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Item3Holder holder, int position) {
        Phong phong = phongArrayList1.get(position);
        holder.txtGiamGia.setText(phong.getGiamgia() + "%");
        holder.txtTenPhong.setText(phong.getTenphong());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiaMoi.setText("Giá : " + decimalFormat.format(phong.getGiamoi()) + " VNĐ/đêm");
        holder.txtGiaCu.setText("Giá : " + decimalFormat.format(phong.getGiaphong()) + " VNĐ/đêm");
        holder.txtGiaCu.setPaintFlags(holder.txtGiaCu.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Picasso.with(context).load(phong.getHinh1())
                .placeholder(R.drawable.image_48px)
                .error(R.drawable.error_48px)
                .into(holder.imgPhong);
    }

    @Override
    public int getItemCount() {
        return phongArrayList1.size();
    }


    public class Item3Holder extends RecyclerView.ViewHolder{
        public ImageView imgPhong;
        public TextView txtGiamGia,txtTenPhong,txtGiaMoi,txtGiaCu;
        public Item3Holder(@NonNull View itemView) {
            super(itemView);
            imgPhong = itemView.findViewById(R.id.imageViewPhongUudai);
            txtGiamGia = itemView.findViewById(R.id.textViewGiamGia);
            txtTenPhong = itemView.findViewById(R.id.textViewPhongUudai);
            txtGiaMoi = itemView.findViewById(R.id.textViewGiamoi);
            txtGiaCu = itemView.findViewById(R.id.textViewGiacu);
            imgPhong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChitietphongActivity.class);
                    intent.putExtra("thongtinphong",phongArrayList1.get(getPosition()));
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
                    phongArrayList1 = phongArrayListOld1;
                }else{
                    ArrayList<Phong> list = new ArrayList<>();
                    for(Phong phong : phongArrayList1){
                        if(phong.getGiaphong().toString().contains(strSearch)){
                            list.add(phong);
                        }
                        else if(phong.getTenphong().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(phong);
                        }
                    }
                    phongArrayList1 = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = phongArrayList1;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                phongArrayList1 = (ArrayList<Phong>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
