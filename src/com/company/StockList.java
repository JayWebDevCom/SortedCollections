package com.company;

import com.sun.istack.internal.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList(){
        this.list = new HashMap<>();
    }

    public int addStock(@NotNull StockItem item){

        // check if we already have quantities of the item
        StockItem inStock = list.getOrDefault(item.getName(), item);

        // if there are already stocks on this item, adjust the quantity
        if (inStock != item) { // i.e. there were already stock items with that name i.e. are in stock
            item.adjustStock(inStock.getQuantityInStock());
        }

        // enter the item to stock
        list.put(item.getName(), item); // new will replace old but doesn't matter

        return 0;
    }

    public int sellStock(String item, int quantity){
        StockItem inStock = list.getOrDefault(item, null);

        if (inStock != null && inStock.getQuantityInStock() >= quantity && quantity > 0){
            inStock.adjustStock(-quantity);
            return quantity;
        }

        return 0; // no stock was reduced

    }

    public StockItem getItem(String key){
        return list.get(key);
    }

    public Map<String, StockItem> getItems(){
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String string = "\nStocklist:\n";
        double totalCost = 0.0;

        for (Map.Entry<String, StockItem> entry : list.entrySet()) {
            StockItem item = entry.getValue();
            int quantity = item.getQuantityInStock();
            double price = item.getPrice();
            double value = quantity * price;
            totalCost += value;
            string += " name: " + item.getName() + " price: " + price + " quantity in stock: "
                    + quantity + " value of stock: " + value;
        }

        string += "\n" + "All stock value: " + totalCost;
        return string;
    }
}
