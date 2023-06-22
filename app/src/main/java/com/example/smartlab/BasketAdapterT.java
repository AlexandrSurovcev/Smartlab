package com.example.smartlab;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class BasketAdapterT extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<BasketModel> listRecyclerItem;
    private final TextView perem;
    public BasketAdapterT( List<BasketModel> listRecyclerItem, TextView perem) {
        this.listRecyclerItem = listRecyclerItem;
        this.perem = perem;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// Создаем представление из Layout
        return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.basket_layout_elements, parent, false)) {
        };
        }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
// Заполняем элемент данными
        TextView title = holder.itemView.findViewById(R.id.title);
        title.setText(listRecyclerItem.get(position).getTitle());
        TextView price = holder.itemView.findViewById(R.id.price);
        price.setText(listRecyclerItem.get(position).getPrice());
        ImageView pluspatient = holder.itemView.findViewById(R.id.pluspatient);
        ImageView minuspatient = holder.itemView.findViewById(R.id.minuspatient);
        TextView patient = holder.itemView.findViewById(R.id.patient);
        TextView countofpatient = holder.itemView.findViewById(R.id.countofpatient);
        //УДАЛЕНИЕ ЭЛЕМЕНТОВ ИЗ КОРЗИНЫ
        ImageView remove = holder.itemView.findViewById(R.id.removeelement);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int str = Integer.parseInt(listRecyclerItem.get(position).getPrice());
                int count = Integer.parseInt(countofpatient.getText().toString());
                str = str*count;
                perem.setText(String.valueOf(str));
                listRecyclerItem.remove(position);
                notifyItemRemoved(position);
            }
        });
        //ИЗМЕНЕНИЕ КОЛ-ВА ПАЦИЕНТОВ
        pluspatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(countofpatient.getText().toString());
                if(count<7){
                    count++;
                    countofpatient.setText(String.valueOf(count));
                    int price1 = -Integer.parseInt(listRecyclerItem.get(position).getPrice());
                    String str = String.valueOf(price1);
                    perem.setText(str);
                }
                if(count>1&&count<5)patient.setText("пациента");
                else if(count>=5)patient.setText("пациентов");
                else patient.setText("пациент");
            }
        });
        minuspatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(countofpatient.getText().toString());
                if(count>1){
                    count--;
                    countofpatient.setText(String.valueOf(count));
                    String str = listRecyclerItem.get(position).getPrice();
                    perem.setText(str);
                }
                if(count>1&&count<5)patient.setText("пациента");
                else if(count>=5)patient.setText("пациентов");
                else patient.setText("пациент");
            }
        });
       }
    @Override
    public int getItemCount() {
// Получает всёё количесво элементов RecyclerView
        return this.listRecyclerItem.size();}
}
