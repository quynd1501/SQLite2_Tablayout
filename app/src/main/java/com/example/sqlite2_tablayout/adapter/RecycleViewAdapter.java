package com.example.sqlite2_tablayout.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sqlite2_tablayout.R;
import com.example.sqlite2_tablayout.model.CongViec;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.CongViecViewHolder>{
    private List<CongViec> list;
    private ItemListener itemListener;

    public RecycleViewAdapter() {
        list=new ArrayList<>();//khoi tao list
    }


    public void setList(List<CongViec> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public CongViec getCongViec(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public CongViecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new CongViecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CongViecViewHolder holder, int position) {
        CongViec congViec=list.get(position);
        holder.tenCV.setText(congViec.getTenCV());
        holder.noidung.setText(congViec.getNoidungCV());
        holder.ngay.setText(congViec.getNgayHT());
        holder.tinhtrang.setText(congViec.getTinhtrang());
        holder.congtac.setText(congViec.getCongtac());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    //thao tac voi doi tuong Cong viec
    public class CongViecViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener {
        private TextView tenCV,noidung,ngay,tinhtrang,congtac;

        public CongViecViewHolder(@NonNull View itemView) {
            super(itemView);
            tenCV=itemView.findViewById(R.id.tvTenCV);
            noidung=itemView.findViewById(R.id.tvNoiDung);
            ngay=itemView.findViewById(R.id.tvNgay);
            tinhtrang=itemView.findViewById(R.id.tvTinhTrang);
            congtac=itemView.findViewById(R.id.tvCongTac);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListener!=null){
                itemListener.onItemClick(view,getAdapterPosition());
            }
        }
    }

    public interface ItemListener{//bat su kien click
        void onItemClick(View view,int position);
    }
}
