package com.example.ibborganik;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ibborganik.Model.Fruit;
import com.example.ibborganik.Model.Vegetable;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        findViewById(R.id.returnShopAndDonateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShopActivity.this, ShopAndDonate.class));
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getVegeFruit(true);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getVegeFruit(false);
            }
        });

    }
    private void getVegeFruit(boolean isFruitOrVege){
        String url = "https://halfiyatlaripublicdata.ibb.gov.tr/api/HalManager/getProductPricebyDay";
        Map<String, String> itemParam = new HashMap<>();
        itemParam.put("Day","2022-05-11");
        Map<String, Map<String, String>> params = new HashMap<>();
        params.put("item", itemParam);
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params),
                response -> {
                    try {
                        JSONArray objectArray = (JSONArray) response.get("Results");
                        Gson gson = new Gson();
                        if (isFruitOrVege) {
                            ArrayList<Fruit> fruitArray = new ArrayList<>();
                            for (int i = 0; i < objectArray.length(); ++i) {
                                Fruit tempFruit = gson.fromJson(String.valueOf(objectArray.getJSONObject(i)),Fruit.class);
                                if (tempFruit.getKategoriId().equals("5"))
                                    fruitArray.add(tempFruit);
                            }
                            refreshMarketListAsFruits(fruitArray);
                        }
                        else {
                            ArrayList<Vegetable> vegetableArray = new ArrayList<>();
                            for (int i = 0; i < objectArray.length(); ++i) {
                                Vegetable tempVegetable = gson.fromJson(String.valueOf(objectArray.getJSONObject(i)),Vegetable.class);
                                if (tempVegetable.getKategoriId().equals("6"))
                                    vegetableArray.add(tempVegetable);
                            }
                            refreshMarketListAsVegetables(vegetableArray);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(this,error.networkResponse.statusCode,Toast.LENGTH_SHORT).show());
        queue.add(jsonObjReq);
    }
    private void refreshMarketListAsFruits(ArrayList<Fruit> fruits){
        System.out.println("Market meyvelerle dolduruldu");
        GridLayout grid = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);
        grid.removeAllViews();
        for (Fruit fruit:fruits) {
            System.out.println(fruit.getUrunAd() + " " +fruit.getEnDusukFiyat() + " TL olarak eklendi");
            //Butun meyveler hazirlanip eklenecek
        }
    }
    private void refreshMarketListAsVegetables(ArrayList<Vegetable> vegetables){
        System.out.println("Market sebzelerle dolduruldu");
        GridLayout grid = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);
        grid.removeAllViews();
        for (Vegetable vegetable:vegetables) {
            System.out.println(vegetable.getUrunAd() + " " +vegetable.getEnDusukFiyat() + " TL olarak eklendi");
            //Butun sebzeler hazirlanip eklenecek
        }
    }
}

