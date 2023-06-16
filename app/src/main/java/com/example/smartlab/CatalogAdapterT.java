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

import java.util.List;

public class CatalogAdapterT extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final Context context;
    private final List<Object> listRecyclerItem;
    public CatalogAdapterT(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;}
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        // Присваиваем поля для заполнения элемента RecyclerView
        TextView id, title, description, price,time_result;
        public ItemViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            title=(TextView) itemView.findViewById(R.id.title);
            description=(TextView) itemView.findViewById(R.id.description);
            time_result = (TextView)itemView.findViewById(R.id.time_result);
            price=(TextView) itemView.findViewById(R.id.price);}}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// Создаем представление из Layout
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_layout, parent, false);
        return new ItemViewHolder((v));}
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
// Заполняем элемент данными
        ItemViewHolder _holder = (ItemViewHolder) holder;
        CatalogModel catalogModel = (CatalogModel) listRecyclerItem.get(position);
        _holder.id.setText(catalogModel.getId());
        _holder.title.setText(catalogModel.getTitle());
        _holder.price.setText(catalogModel.getPrice());
        _holder.time_result.setText(catalogModel.getTimeResult());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titledialog = catalogModel.getTitle();
                String descriptiondialog = catalogModel.getDescription();
                String timedialog = catalogModel.getTimeResult();


                showBottomDialog(titledialog,descriptiondialog,timedialog);}});}
    @Override
    public int getItemCount() {
// Получает всёё количесво элементов RecyclerView
        return listRecyclerItem.size();}

    private void showBottomDialog(String title,String description, String time) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.catalog_description_layout);
        TextView titleTXT = dialog.findViewById(R.id.title);
        TextView descriptionTXT = dialog.findViewById(R.id.description);
        TextView timeTXT = dialog.findViewById(R.id.time_result);
        titleTXT.setText(title);
        descriptionTXT.setText(description);
        timeTXT.setText(time);



        ImageView btnClose = dialog.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
}



