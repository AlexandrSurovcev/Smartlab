package com.example.smartlab;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigInteger;
import java.util.List;

public class BannerAdapterT extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final Context context;
    private final List<Object> listRecyclerItem;
    public BannerAdapterT(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;}
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        // Присваиваем поля для заполнения элемента RecyclerView
        TextView id, name, description, price;
        public ItemViewHolder(View itemView) {
            super(itemView);
            id=(TextView) itemView.findViewById(R.id.id);
            name=(TextView) itemView.findViewById(R.id.name);
            description=(TextView) itemView.findViewById(R.id.description);
            price=(TextView) itemView.findViewById(R.id.price);}}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// Создаем представление из Layout
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_layout, parent, false);
        return new ItemViewHolder((v));}
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
// Заполняем элемент данными
        ItemViewHolder _holder = (ItemViewHolder) holder;
        BannerModel bannerModel = (BannerModel) listRecyclerItem.get(position);
        _holder.id.setText(bannerModel.getId());
        _holder.name.setText(bannerModel.getName());
        _holder.description.setText(bannerModel.getDescription());
        Double price = Double.parseDouble(bannerModel.getPrice());
        BigInteger price1 = BigInteger.valueOf(price.intValue());
        _holder.price.setText(price1+" ₽");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, bannerModel.getId(), Toast.LENGTH_SHORT).show();  }});}
    @Override
    public int getItemCount() {
// Получает всёё количесво элементов RecyclerView
        return listRecyclerItem.size();}
}



