package com.example.smartlab;

import static android.content.Context.MODE_PRIVATE;
import static com.example.smartlab.R.layout.fragment_analys;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Analys extends Fragment {
    TextView btnNext,txtPrice;
    RelativeLayout layoutbasket;
    JSONArray array,arraybanner,arraycategory;
    private RecyclerView recyclerView,listBanner,listCategory;
    private List<Object> viewItems = new ArrayList<>();
    private List<Object> viewItems1 = new ArrayList<>();
    private List<Object> viewItemsCategory = new ArrayList<>();
    ArrayList<Object> catalogItemsList;
    SharedPreferences items;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(fragment_analys, container, false);
        btnNext = v.findViewById(R.id.btnNext);
        txtPrice = v.findViewById(R.id.price);
        layoutbasket = v.findViewById(R.id.layoutbasket);
        //
        new GetTaskBanner().execute(new JSONObject());
        new GetTask().execute(new JSONObject());
        new GetTaskCategory().execute(new JSONObject());
        recyclerView=(RecyclerView) v.findViewById(R.id.listCatalog);
        listBanner=(RecyclerView) v.findViewById(R.id.listBanner);
        listCategory=(RecyclerView)v.findViewById(R.id.listCategory);
        //
        CatalogAdapterT adapter = new CatalogAdapterT(getContext(),viewItems,txtPrice,layoutbasket);
        recyclerView.setAdapter(adapter);
        // Присваиваем LayoutManager что бы изменить направление RecyclerView
        BannerAdapterT adapt = new BannerAdapterT(getContext(),viewItems1);
        listBanner.setAdapter(adapt);
        CategoryAdapterT adapterCat = new CategoryAdapterT(getContext(),viewItemsCategory);
        listCategory.setAdapter(adapterCat);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String price = removeLastChar(txtPrice.getText().toString());
                showBottomDialog(price);
            }
        });
        return v;
    }
    public void initData(){
        items = getContext().getSharedPreferences("ITEMS", MODE_PRIVATE);
        catalogItemsList = new ArrayList<>();
        Gson gson = new Gson();
        for (int i = 1;i<100;i++){
            String json;
            if(items.contains("item "+i)){
                Type type = new TypeToken<List<String>>(){}.getType();
                List<String>arrPackageData = gson.fromJson(json,type);
                ArrayList<String>newItem = new ArrayList<>(arrPackageData);
                CatalogItem catalogItem = new CatalogItem(Integer.parseInt(newItem.get(0)),Integer.parseInt(newItem.get(1)),Integer.parseInt(newItem.get(2)),Integer.parseInt(newItem.get(3)));
                catalogItem.add(catalogItem);
            }
        }
    }
    private void showBottomDialog(String pricesum){
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.basket_layout);
        TextView price = dialog.findViewById(R.id.price);
        price.setText(pricesum);

        new GetTaskBanner().execute(new JSONObject());
        RecyclerView basket = dialog.findViewById(R.id.listBaskets);
        BasketAdapterT adapter = new BasketAdapterT(getContext(),viewItems);
        basket.setAdapter(adapter);

        ImageView btnBack = dialog.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

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

    private class GetTaskCategory extends AsyncTask<JSONObject, Void, String> {
        @Override
        protected String doInBackground(JSONObject... jsonObjects) {
            try {
                InputStream stream = null;
// Для буферизации текста из потока
                BufferedReader reader=null;
                HttpURLConnection connection = null;
                try {
// Присваиваем путь
                    URL url = new URL("http://10.0.2.2:8000/api/category/");
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
                    arraycategory = root.getJSONArray("results");
                    addItemsFromJSON();
                    return(buf.toString());
                } catch (Exception e) {e.getMessage();}
            } catch (Exception e) {e.printStackTrace();}

            return null;
        }
    }

    private void addItemsFromJSON() {
        viewItems.clear();
        viewItems1.clear();
        viewItemsCategory.clear();
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
            for (int i=0;i<arraycategory.length();++i){
                JSONObject itemObj=arraycategory.getJSONObject(i);
                String id = itemObj.getString("id");
                String name = itemObj.getString("name");
                CategoryModel categoryModel = new CategoryModel(id, name);
                viewItemsCategory.add(categoryModel);
            }
        } catch (JSONException e) {
        }
    }


//УДАЛИТЬ ПОСЛЕДНЮЮ СТРОКУ
    public static String removeLastChar(String str){
        return (str == null||str.length()==0) ?null
            :(str.substring(0,str.length()-1));
        }
}