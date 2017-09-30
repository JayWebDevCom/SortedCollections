package com.company;

import com.sun.istack.internal.NotNull;

import java.util.*;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList(){
//        this.list = new HashMap<>(); // has no order
        this.list = new LinkedHashMap<>(); // has order maintained as items are entered
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

//    public int sellStock(String item, int quantity){
//        StockItem inStock = list.getOrDefault(item, null);
//
//        if (inStock != null && inStock.getQuantityInStock() >= quantity && quantity > 0){
//            inStock.adjustStock(-quantity);
//            return quantity;
//        }
//        return 0; // no stock was reduced
//    }

    public int sellStock(String item, int quantity){
        StockItem inStock = list.getOrDefault(item, null);

        if (inStock != null && quantity > 0){
            return inStock.finalizeStock(quantity);
        }
        return 0; // no stock was reduced
    }


    public int reserveStock(String itemName, int quantity) {
        StockItem item = list.get(itemName);

        if (item != null && quantity > 0) {
            item.reserveStock(quantity);
            return quantity;
        }

        return 0;
    }

    public int unreserveStock(String itemName, int quantity) {
        StockItem item = list.get(itemName);

        if (item != null && quantity > 0) {
            item.unreserveStock(quantity);
            return quantity;
        }

        return 0;
    }


    public StockItem getItem(String key){
        return list.get(key);
    }

    public Map<String, StockItem> getItems(){
        return Collections.unmodifiableMap(list); // returns a readonly view of the list
    }

    public Map<String, Double> priceList(){
        Map<String, Double> priceList = new LinkedHashMap<>();

        for (Map.Entry<String, StockItem> entry : list.entrySet() ){
            priceList.put(entry.getKey(), entry.getValue().getPrice());
        }

        return Collections.unmodifiableMap(priceList);
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
            string += "Name: " + item.getName() + " price: " + String.format("%.2f", price) + " quantity in stock: "
                    + quantity + " value of stock: " + String.format("%.2f", value) + "\n";
        }

        string += "\n" + "All stock value: " + String.format("%.2f", totalCost);
        return string;
    }
}
