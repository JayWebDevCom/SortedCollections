package com.company;

import com.sun.istack.internal.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> basket;

    public Basket(String name) {
        this.name = name;
        this.basket = new HashMap<>();
    }

    public int addToBasket(@NotNull StockItem item, @NotNull int quantity){

        // check if item is in basket already, default to zero if its not there
        int inBasket = basket.getOrDefault(item, 0);

        // add item to basket
        basket.put(item, inBasket + quantity);

        return inBasket;

    }

    public Map<StockItem, Integer> getBasket(){
        return Collections.unmodifiableMap(basket);
    }

    @Override
    public String toString() {
        String string = "Basket " + this.name + " contains " + this.basket.size() + " items" + "\n";
        double totalCost = 0.0;
        for( Map.Entry<StockItem, Integer> entry : basket.entrySet() ){
            int count = entry.getValue();
            StockItem item = entry.getKey();
            double price = item.getPrice();
            string += "name: " + item.getName() + " price: " + price + " purchased: " + count + "\n";
            totalCost += price * count;
        }

        return string + "Total cost: " + totalCost + "\n";
    }
}
