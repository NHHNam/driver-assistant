package com.example.driverassistant.Function;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.driverassistant.R;

import java.util.List;

public class chucNangAdapter extends RecyclerView.Adapter<chucNangAdapter.ChucNangHolder> {
    private List<ChucNang> mlistchucnang;
    private Context context;

    public chucNangAdapter(List<ChucNang> mlistchucnang, Context context) {
        this.mlistchucnang = mlistchucnang;
        this.context = context;
    }

    @NonNull
    @Override
    public ChucNangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.function_items,parent,false);
        return new ChucNangHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChucNangHolder holder, int position) {
        ChucNang chucNang = mlistchucnang.get(position);
        holder.tv_chuc_nang.setText(chucNang.getName());
    }

    @Override
    public int getItemCount() {
        return mlistchucnang.size();
    }

    class ChucNangHolder extends RecyclerView.ViewHolder{
        TextView tv_chuc_nang;
        public ChucNangHolder(@NonNull View itemView) {
            super(itemView);
            tv_chuc_nang = itemView.findViewById(R.id.tv_chuc_nang);
        }
    }
}
