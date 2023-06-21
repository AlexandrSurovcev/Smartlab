package com.example.smartlab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigInteger;
import java.util.List;

public class BasketAdapterT extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<Object> listRecyclerItem;
    public BasketAdapterT(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        // Присваиваем поля для заполнения элемента RecyclerView
        TextView id, title, price;
        public ItemViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            title=(TextView) itemView.findViewById(R.id.title);
            price=(TextView) itemView.findViewById(R.id.price);}}
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// Создаем представление из Layout
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.basket_layout_elements, parent, false);
        return new BasketAdapterT.ItemViewHolder((v));}
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
// Заполняем элемент данными
        BasketAdapterT.ItemViewHolder _holder = (BasketAdapterT.ItemViewHolder) holder;
        CatalogModel catalogModel = (CatalogModel) listRecyclerItem.get(position);
        for(int i = 1;i<=getItemCount();i++){
            if(catalogModel.getId().equals("1")){
                _holder.title.setText(catalogModel.getTitle());
                Double pricedb = Double.parseDouble(catalogModel.getPrice());
                BigInteger price = BigInteger.valueOf(pricedb.intValue());
                _holder.price.setText(price.toString());
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                }});}
    @Override
    public int getItemCount() {
// Получает всёё количесво элементов RecyclerView
        return listRecyclerItem.size();}
}
