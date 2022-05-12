package com.example.ibborganik.Manager;

import com.example.ibborganik.Model.Fruit;
import com.example.ibborganik.Model.Vegetable;

import java.util.HashMap;

public class BasketManager {
    private static BasketManager basketInstance;
    private HashMap<String, Fruit> fruitHashMap;
    private HashMap<String, Vegetable> vegetableHashMap;
    private BasketManager(){
        fruitHashMap = new HashMap<>();
        vegetableHashMap = new HashMap<>();
    }

    public static BasketManager getBasketInstance(){
        if (basketInstance == null)
            basketInstance = new BasketManager();

        return basketInstance;
    }

    public void addFruitToBasket(String key, Fruit fruit){
        fruitHashMap.put(key, fruit);
    }

    public void addVegetableToBasket(String key, Vegetable vegetable){
        vegetableHashMap.put(key, vegetable);
    }

    public void clearVegetables(){
        fruitHashMap.clear();
    }

    public void clearFruits(){
        vegetableHashMap.clear();
    }

    public void clearBasket(){
        clearFruits();
        clearVegetables();
    }

}
