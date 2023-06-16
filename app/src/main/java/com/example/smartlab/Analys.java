package com.example.smartlab;

import static com.example.smartlab.R.layout.fragment_analys;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Analys extends Fragment {
    TextView popular,covid, comprehensive,btnNext;
    EnableTextView enableText = new EnableTextView();
    JSONArray array;
    private RecyclerView recyclerView;
    private List<Object> viewItems = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(fragment_analys, container, false);
        popular = v.findViewById(R.id.popular);
        covid = v.findViewById(R.id.covid);
        comprehensive = v.findViewById(R.id.comprehensive);
        btnNext = v.findViewById(R.id.btnNext);
        //
        enableText.onEnable(popular,getContext());
        new GetTask().execute(new JSONObject());
        recyclerView=(RecyclerView) v.findViewById(R.id.listCatalog);
// Присваиваем LayoutManager что бы изменить направление RecyclerView
        CatalogAdapterT adapter = new CatalogAdapterT(getContext(),viewItems);
        recyclerView.setAdapter(adapter);



        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableText.setAble(popular, covid, comprehensive, getContext());
            }
        });
        covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableText.setAble(covid, popular, comprehensive, getContext());
            }
        });
        comprehensive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableText.setAble(comprehensive, popular, covid, getContext());
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Order.class);
                startActivity(intent);
            }
        });
        return v;
    }
    private class GetTask extends AsyncTask<JSONObject, Void, String> {
        @Override
        protected String doInBackground(JSONObject... jsonObjects) {
            try {
                InputStream stream = null;
// Для буферизации текста из потока
                BufferedReader reader=null;
                HttpURLConnection connection = null;
                try {
// Присваиваем путь
                    URL url = new URL("http://10.0.2.2:8000/api/catalog/");
                    connection =(HttpURLConnection)url.openConnection();
// Выбираем метод GET для запроса
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(10000);
                    connection.connect();
// Полученный результат разбиваем с помощью байтовых потоков
                    stream = connection.getInputStream();
                    reader= new BufferedReader(new InputStreamReader(stream));
                    StringBuilder buf=new StringBuilder();
                    String line;
                    while ((line=reader.readLine()) != null) {
                        buf.append(line).append("\n");
                    }
                    JSONObject root = new JSONObject(buf.toString());
                    array = root.getJSONArray("results");
                    addItemsFromJSON();
                    return(buf.toString());
                } catch (Exception e) {e.getMessage();}
            } catch (Exception e) {e.printStackTrace();}

            return null;
        }
    }
    private void addItemsFromJSON() {
        try {
// Заполняем Модель спаршенными данными
            for (int i=0; i<array.length(); ++i) {
                JSONObject itemObj = array.getJSONObject(i);
                String id = itemObj.getString("id");
                String description = itemObj.getString("description");
                String time_result = itemObj.getString("time_result");
                String title = itemObj.getString("name");
                String price = itemObj.getString("price");
                CatalogModel catalogModel = new CatalogModel(id, title, description, price,time_result);
                viewItems.add(catalogModel);}
        } catch (JSONException e) {
        }}


}