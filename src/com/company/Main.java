package com.company;

import java.util.Map;

public class Main {

    private static StockList stockList = new StockList();

    public static void main(String[] args) {

        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.00, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 200);
        stockList.addStock(temp);
        temp = new StockItem("cup", 0.55, 50);
        stockList.addStock(temp); // now have 250 cups of price 0.55 each

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

//        trying inner classes
//        StockItem.InnerClass innerClass = temp.createInner("A Name");
//        StockItem.StaticClass staticClass = StockItem.createStatic("A Name");
//
//        StockItem.InnerClass anotherinnerClass = temp.new InnerClass("A Name");
//        StockItem.StaticClass anotherstaticClass = new StockItem.StaticClass("A Name");

        System.out.println(stockList);
        System.out.println();

        for (String s : stockList.getItems().keySet()){
            System.out.println(s);
        }

        Basket basket = new Basket("Customer Basket");
        sellItem(basket, "car", 1);
        System.out.println(basket);
        sellItem(basket, "car", 1);
        System.out.println(basket);

        if (sellItem(basket, "car", 1) != 1) {
            System.out.println("\nThere are no more in stock\n");
        }

        System.out.println(basket);
        sellItem(basket, "non-existant", 1);
        System.out.println(basket);
        sellItem(basket, "juice", 4);
        System.out.println(basket);
        sellItem(basket, "cup", 12);
        System.out.println(basket);
        sellItem(basket, "bread", 1);
        System.out.println(basket);

        System.out.println(stockList);

        // temp = new StockItem("pen", 0.12);
        // stockList.getItems().put(temp.getName(), temp);
        // will give UnSupportedOperation Exception because getItems() returns an unmodifiable map
        // objects contained can be modified but the list cannot

        System.out.println("Price List is...");
        for (Map.Entry<String, Double> entry : stockList.priceList().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }


    }

    public static int sellItem(Basket basket, String itemName, int quantity){
        StockItem theItem = stockList.getItem(itemName);

        if (theItem == null) {
            System.out.println("Sorry that item is not sold- " + itemName);
            return 0;
        }

        if (stockList.sellStock(itemName, quantity) != 0) {
            basket.addToBasket(theItem, quantity);
            return quantity;
        }

        return 0; // stock insufficient

    }
}
