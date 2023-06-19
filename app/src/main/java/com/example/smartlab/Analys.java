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
    TextView popular,covid, comprehensive,btnNext,txtPrice;
    EnableTextView enableText = new EnableTextView();
    JSONArray array,arraybanner;
    private RecyclerView recyclerView,listBanner;
    private List<Object> viewItems = new ArrayList<>();
    private List<Object> viewItems1 = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(fragment_analys, container, false);
        popular = v.findViewById(R.id.popular);
        covid = v.findViewById(R.id.covid);
        comprehensive = v.findViewById(R.id.comprehensive);
        btnNext = v.findViewById(R.id.btnNext);
        enableText.onEnable(popular,getContext());
        txtPrice = v.findViewById(R.id.price);
        //
        new GetTaskBanner().execute(new JSONObject());
        new GetTask().execute(new JSONObject());
        recyclerView=(RecyclerView) v.findViewById(R.id.listCatalog);
        listBanner=(RecyclerView) v.findViewById(R.id.listBanner);
        //
        CatalogAdapterT adapter = new CatalogAdapterT(getContext(),viewItems,txtPrice);
        recyclerView.setAdapter(adapter);
        // Присваиваем LayoutManager что бы изменить направление RecyclerView
        BannerAdapterT adapt = new BannerAdapterT(getContext(),viewItems1);
        listBanner.setAdapter(adapt);





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
    private class GetTaskBanner extends AsyncTask<JSONObject, Void, String> {
        @Override
        protected String doInBackground(JSONObject... jsonObjects) {
            try {
                InputStream stream = null;
// Для буферизации текста из потока
                BufferedReader reader=null;
                HttpURLConnection connection = null;
                try {
// Присваиваем путь
                    URL url = new URL("http://10.0.2.2:8000/api/news/");
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
                    arraybanner = root.getJSONArray("results");
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
                String preparation=itemObj.getString("preparation");
                String bio = itemObj.getString("bio");
                CatalogModel catalogModel = new CatalogModel(id, title, description, price,time_result,preparation,bio);
                viewItems.add(catalogModel);
            }
            for (int i=0;i<arraybanner.length();++i){
                JSONObject itemObj=arraybanner.getJSONObject(i);
                String id = itemObj.getString("id");
                String name = itemObj.getString("name");
                String description=itemObj.getString("description");
                String price = itemObj.getString("price");
                BannerModel bannerModel = new BannerModel(id, name, description, price);
                viewItems1.add(bannerModel);
            }
        } catch (JSONException e) {
        }
    }



}