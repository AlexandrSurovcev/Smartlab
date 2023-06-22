package com.example.smartlab;

import static com.example.smartlab.R.layout.fragment_analys;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Analys extends Fragment {
    SharedPreferences preferences;
    TextView btnNext,txtPrice,txtTitle,txtCena,price,perem;
    RelativeLayout layoutbasket;
    JSONArray array,arraybanner,arraycategory,arraybasket;
    private RecyclerView recyclerView,listBanner,listCategory,listBasket;
    private List<Object> viewItems = new ArrayList<>();
    private List<Object> viewItems1 = new ArrayList<>();
    private List<Object> viewItemsCategory = new ArrayList<>();

   Boolean createdcard = false;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(fragment_analys, container, false);
        btnNext = v.findViewById(R.id.btnNext);
        txtPrice = v.findViewById(R.id.price);
        txtTitle = v.findViewById(R.id.txttitle);
        txtCena = v.findViewById(R.id.txtcena);
        layoutbasket = v.findViewById(R.id.layoutbasket);
        //
        new GetTaskBanner().execute(new JSONObject());
        new GetTask().execute(new JSONObject());
        new GetTaskCategory().execute(new JSONObject());
        recyclerView=(RecyclerView) v.findViewById(R.id.listCatalog);
        listBanner=(RecyclerView) v.findViewById(R.id.listBanner);
        listCategory=(RecyclerView)v.findViewById(R.id.listCategory);

        //
        CatalogAdapterT adapter = new CatalogAdapterT(getContext(),viewItems,txtPrice,layoutbasket,txtTitle,txtCena);
        recyclerView.setAdapter(adapter);
        //
        BannerAdapterT adapt = new BannerAdapterT(getContext(),viewItems1);
        listBanner.setAdapter(adapt);
        //
        CategoryAdapterT adapterCat = new CategoryAdapterT(getContext(),viewItemsCategory);
        listCategory.setAdapter(adapterCat);
        //
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String price = removeLastChar(txtPrice.getText().toString());
                String title = txtTitle.getText().toString();
                String priceobj = txtCena.getText().toString();
                showBottomDialog(title,priceobj);
            }
        });
        return v;
    }
    private void showBottomDialog(String title,String cena){
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.basket_layout);
        perem=dialog.findViewById(R.id.perem);
        List<BasketModel> basketModels = new ArrayList<>();
        RecyclerView.Adapter adapterBasket = new BasketAdapterT(basketModels,perem);
        price = dialog.findViewById(R.id.price);
        listBasket = dialog.findViewById(R.id.listBaskets);
        perem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Double pricedeletedanalys = Double.parseDouble(perem.getText().toString());
                Double priceofanalyses = Double.parseDouble(price.getText().toString());
                priceofanalyses = priceofanalyses - pricedeletedanalys;
                BigInteger pricetotal = BigInteger.valueOf(priceofanalyses.intValue());
                price.setText(pricetotal.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        Double totalprice=0.0;
        int countanalises = title.split(";").length - 1;
        listBasket.setAdapter(adapterBasket);
        basketModels.clear();
        modelUpdate(title,cena,totalprice,basketModels,adapterBasket,price);
        //ПЕРЕЙТИ К ЗАКАЗУ
        TextView btnToOrder = dialog.findViewById(R.id.btnNext);
        preferences = getActivity().getSharedPreferences("UserInfo", 0);
        if(preferences.getBoolean("createdcard",false))createdcard=true;
        else {
            btnToOrder.setBackgroundResource(R.drawable.disabledbutton);
        }

        btnToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(createdcard){
                    preferences = getActivity().getSharedPreferences("UserInfo", 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("price", price.getText().toString());
                    editor.putString("countanalises", String.valueOf(countanalises));
                    editor.apply();
                    Intent intent = new Intent(getActivity(), Order.class);
                    startActivity(intent);
                }else{
                    ((ActivityMenu)getActivity()).onNavigationItemMenu(1);
                }
            }
        });
        ImageView btnBack = dialog.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        //ОТЧИСТИТЬ КОРЗИНУ
        ImageView clear =dialog.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                basketModels.clear();
                adapterBasket.notifyDataSetChanged();
                price.setText("0");
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
    private class GetTaskBasket extends AsyncTask<JSONObject, Void, String> {
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
                    arraybasket = root.getJSONArray("results");
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
    public static void modelUpdate(String title, String cena, Double totalprice, List<BasketModel> basketModels, RecyclerView.Adapter adapterBasket, TextView price1){
        String[] titler = title.split(";");
        String[] pricer = cena.split(";");
        for (int i = 0;i<titler.length;i++){
            if(titler[i].length()!=0){
                Double price =Double.parseDouble(pricer[i]);
                BigInteger price2 = BigInteger.valueOf(price.intValue());
                totalprice+=price;
                basketModels.add(new BasketModel("",titler[i],price2.toString()));

                adapterBasket.notifyItemInserted(basketModels.size()-1);

            }
        }
        BigInteger pricetotal = BigInteger.valueOf(totalprice.intValue());
        price1.setText(pricetotal.toString());
    }

//УДАЛИТЬ ПОСЛЕДНЮЮ СТРОКУ
    public static String removeLastChar(String str){
        return (str == null||str.length()==0) ?null
            :(str.substring(0,str.length()-1));
        }
}