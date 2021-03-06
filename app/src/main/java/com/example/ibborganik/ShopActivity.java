package com.example.ibborganik;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ibborganik.Manager.BasketManager;
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

        findViewById(R.id.homeClick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShopActivity.this, MainActivity.class));
            }
        });

        findViewById(R.id.basketclick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShopActivity.this, myBasketActivity.class));
            }
        });

        findViewById(R.id.searchClick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShopActivity.this, SearchActivity.class));
            }
        });


        findViewById(R.id.profileClick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShopActivity.this, MyProfileActivity.class));
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getVegeFruit(true);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getVegeFruit(false);
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
                                if (tempFruit.getKategoriId().equals("5")){
                                    boolean same = false;
                                    for (Fruit fruit: fruitArray) {
                                        if (fruit.getUrunAd().split(" ")[0].equals(tempFruit.getUrunAd().split(" ")[0])){
                                            same = true;
                                            break;
                                        }
                                    }
                                    if (same) continue;
                                    fruitArray.add(tempFruit);
                                }
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
        GridLayout grid = findViewById(R.id.gridLayout);
        grid.removeAllViews();
        int index = 0;
        for (Fruit fruit:fruits) {
            //if (index == 4) break;
            System.out.println(fruit.getUrunAd() + " " +fruit.getEnDusukFiyat() + " TL olarak eklendi");
            View gridObject = getLayoutInflater().inflate(R.layout.shoplinear, grid, false);
            gridObject.setOnClickListener(view -> {
                BasketManager.getBasketInstance().addFruitToBasket(fruit.getUrunAd(),fruit);
                System.out.println("Sepete eklendi");
            });
            TextView nameText = gridObject.findViewById(R.id.isim);
            nameText.setText(fruit.getUrunAd());
            TextView priceText = gridObject.findViewById(R.id.fiyat);
            priceText.setText(fruit.getEnDusukFiyat()+" TL");
            grid.addView(gridObject);
            index++;
        }
    }
    private void refreshMarketListAsVegetables(ArrayList<Vegetable> vegetables){
        System.out.println("Market sebzelerle dolduruldu");
        GridLayout grid = findViewById(R.id.gridLayout);
        grid.removeAllViews();
        int index = 0;
        for (Vegetable vegetable:vegetables) {
            //if (index == 4) break;
            System.out.println(vegetable.getUrunAd() + " " +vegetable.getEnDusukFiyat() + " TL olarak eklendi");
            View gridObject = getLayoutInflater().inflate(R.layout.shoplinear, grid, false);
            gridObject.setOnClickListener(view -> {
                BasketManager.getBasketInstance().addVegetableToBasket(vegetable.getUrunAd(), vegetable);
                System.out.println("Sepete eklendi");
            });
            TextView nameText = gridObject.findViewById(R.id.isim);
            nameText.setText(vegetable.getUrunAd());
            TextView priceText = gridObject.findViewById(R.id.fiyat);
            priceText.setText(vegetable.getEnDusukFiyat()+" TL");
            grid.addView(gridObject);
            index++;
        }
    }
}

