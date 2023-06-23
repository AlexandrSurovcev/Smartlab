package com.example.smartlab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapterT extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final List<BasketModel> listRecyclerItem;
    private final Context context;
    TextView perem,count;
    public OrderAdapterT(Context context, List<BasketModel> listRecyclerItem,TextView perem,TextView count) {
        this.listRecyclerItem = listRecyclerItem;
        this.context = context;
        this.perem = perem;
        this.count = count;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// Создаем представление из Layout
        return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout_elements, parent, false)) {
        };
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
// Заполняем элемент данными
        TextView title = holder.itemView.findViewById(R.id.title);
        title.setText(listRecyclerItem.get(position).getTitle());
        TextView price = holder.itemView.findViewById(R.id.price);
        price.setText(listRecyclerItem.get(position).getPrice());
        TextView rubl = holder.itemView.findViewById(R.id.rubl);
        TextView checkbox = holder.itemView.findViewById(R.id.checkboxelement);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox.getText().toString().equals("1")){
                    checkbox.setBackgroundResource(R.drawable.checkbox0);
                    checkbox.setText("0");
                    title.setTextColor(ContextCompat.getColor(context, R.color.hintcolor));
                    price.setTextColor(ContextCompat.getColor(context, R.color.hintcolor));
                    rubl.setTextColor(ContextCompat.getColor(context, R.color.hintcolor));
                    perem.setText(price.getText().toString());
                    int m = Integer.parseInt(count.getText().toString());
                    count.setText(String.valueOf(m-1));
                }
                else {
                    checkbox.setBackgroundResource(R.drawable.checkbox1);
                    checkbox.setText("1");
                    title.setTextColor(ContextCompat.getColor(context, R.color.black));
                    price.setTextColor(ContextCompat.getColor(context, R.color.black));
                    rubl.setTextColor(ContextCompat.getColor(context, R.color.black));
                    perem.setText("-"+price.getText().toString());
                    int m = Integer.parseInt(count.getText().toString());
                    count.setText(String.valueOf(m+1));
                }
            }
        });
    }
    @Override
    public int getItemCount() {
// Получает всёё количесво элементов RecyclerView
        return this.listRecyclerItem.size();}
}
