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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigInteger;
import java.util.List;

public class CatalogAdapterT extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final Context context;
    private final List<Object> listRecyclerItem;
    private final TextView txtPrice;
    Double totalPrice = 0.0;
    public CatalogAdapterT(Context context, List<Object> listRecyclerItem,TextView txtPrice) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
        this.txtPrice = txtPrice;}
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        // Присваиваем поля для заполнения элемента RecyclerView
        TextView id, title, description, price,time_result,btnAdd;
        public ItemViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            title=(TextView) itemView.findViewById(R.id.title);
            description=(TextView) itemView.findViewById(R.id.description);
            time_result = (TextView)itemView.findViewById(R.id.time_result);
            price=(TextView) itemView.findViewById(R.id.price);
            btnAdd=(TextView)itemView.findViewById(R.id.add);
            btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adddeletebtnstyle(btnAdd,price);
                BigInteger price = BigInteger.valueOf(totalPrice.intValue());
                txtPrice.setText(price.toString()+" ₽");
            }
        });}}

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
                String preparationdialog=catalogModel.getPreparation();
                String biodialog=catalogModel.getBio();
                Double pricedb = Double.parseDouble(catalogModel.getPrice());

                showBottomDialog(titledialog,descriptiondialog,timedialog,preparationdialog,biodialog,pricedb,_holder.btnAdd);}});}
    @Override
    public int getItemCount() {
// Получает всёё количесво элементов RecyclerView
        return listRecyclerItem.size();}

    private void showBottomDialog(String title,String description, String time,String preparation, String bio,Double price,TextView button) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.catalog_description_layout);
        TextView titleTXT = dialog.findViewById(R.id.title);
        TextView descriptionTXT = dialog.findViewById(R.id.description);
        TextView timeTXT = dialog.findViewById(R.id.time_result);
        TextView preparationTXT = dialog.findViewById(R.id.preparation);
        TextView bioTXT=dialog.findViewById(R.id.bio);
        TextView btnAdd1=dialog.findViewById(R.id.btnAdd);




        btnAdd1.setText("Добавить за "+price+" ₽");
        titleTXT.setText(title);
        descriptionTXT.setText(description);
        timeTXT.setText(time);
        preparationTXT.setText(preparation);
        bioTXT.setText(bio);
        ImageView btnClose = dialog.findViewById(R.id.btnClose);
        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adddeletebtnstyle1(button,price);
                dialog.dismiss();
            }
        });
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
    protected void adddeletebtnstyle(TextView textView,TextView price){
        if(textView.getText().equals("Добавить")){
            textView.setText("Убрать");
            totalPrice+=Double.parseDouble(price.getText().toString());
            textView.setBackgroundResource(R.drawable.addpatientstyle);
            textView.setTextColor(ContextCompat.getColor(context, R.color.blue));
        }else{
            textView.setText("Добавить");
            totalPrice-=Double.parseDouble(price.getText().toString());
            textView.setBackgroundResource(R.drawable.enabledtextview);
            textView.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
    }
    protected void adddeletebtnstyle1(TextView textView,Double price){
        if(textView.getText().equals("Добавить")){
            textView.setText("Убрать");
            totalPrice+=price;
            BigInteger price1 = BigInteger.valueOf(totalPrice.intValue());
            txtPrice.setText(price1.toString()+" ₽");
            textView.setBackgroundResource(R.drawable.addpatientstyle);
            textView.setTextColor(ContextCompat.getColor(context, R.color.blue));
        }else{
            textView.setText("Добавить");
            totalPrice-=price;
            BigInteger price1 = BigInteger.valueOf(totalPrice.intValue());
            txtPrice.setText(price1.toString()+" ₽");
            textView.setBackgroundResource(R.drawable.enabledtextview);
            textView.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
    }
}



