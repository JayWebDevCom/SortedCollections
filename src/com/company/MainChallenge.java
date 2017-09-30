package com.company;

import java.util.Map;

public class MainChallenge {
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
        temp = new StockItem("cup", 0.45, 50);
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


        Basket basket = new Basket("Customer Basket One");
        sellItem(basket, "car", 1);
        System.out.println(basket);
        sellItem(basket, "car", 1);
        System.out.println(basket);

        if (sellItem(basket, "car", 1) != 1) {
            System.out.println("there are no more cars in stock");
        }

        sellItem(basket, "non-existant", 5);
        sellItem(basket, "juice", 4);
        sellItem(basket, "cup", 12);
        sellItem(basket, "bread", 1);

        Basket secondBasket = new Basket("Customer Basket Two");
        sellItem(secondBasket, "cup", 100);
        sellItem(secondBasket, "juice", 5);
        removeItem(secondBasket, "cup", 1);
        System.out.println(secondBasket);

        removeItem(basket, "car", 1);
        removeItem(basket, "cup", 9);
        removeItem(basket, "car", 1);
        System.out.println("cars removed: " + removeItem(basket, "car", 1));

        System.out.println(basket);

        // removing all items
        removeItem(basket, "bread", 1);
        removeItem(basket, "cup", 3);
        removeItem(basket, "juice", 4);
        removeItem(basket, "cup", 3);
        System.out.println(basket); // should be empty

        System.out.println("\nDisplay stocklist before and after checkout");
        System.out.println(secondBasket);
        System.out.println(stockList);
        checkout(secondBasket);
        System.out.println(secondBasket);
        System.out.println(stockList);

        StockItem car = stockList.getItems().get("car");
        if(car != null){
            car.adjustStock(2000);
        }

        if(car != null){
            car.adjustStock(-1000);
        }

        stockList.getItems().get("car").adjustStock(2000);
        stockList.getItems().get("car").adjustStock(-1000);
        System.out.println(stockList);

        checkout(basket);
        System.out.println(basket);
    }

    public static int sellItem(Basket basket, String itemName, int quantity){
        StockItem theItem = stockList.getItem(itemName);
        if (theItem == null) {
            System.out.println("Sorry that item is not sold - " + itemName);
            return 0;
        }
        if (stockList.reserveStock(itemName, quantity) != 0) {
            return basket.addToBasket(theItem, quantity);
        }
        return 0; // stock insufficient
    }

    public static int removeItem(Basket basket, String itemName, int quantity){
        StockItem theItem = stockList.getItem(itemName);
        if (theItem == null) {
            System.out.println("We don;t sell - " + itemName);
            return 0;
        }
        if (basket.removeFromBasket(theItem, quantity) == 0) {
            return stockList.unreserveStock(itemName, quantity);
        }
        return 0; // stock insufficient
    }

    public static void checkout(Basket basket){
        double totalCost = 0;
        int totalItems = 0;
        for( Map.Entry<StockItem, Integer> entry : basket.getBasket().entrySet() ) {

            StockItem item = entry.getKey();
            stockList.sellStock(item.getName(), entry.getValue());

            totalItems += entry.getValue();
            totalCost += item.getPrice() * entry.getValue();

            basket.clearBasket();
        }
        System.out.println("Total Items: " + totalItems + " Total Cost: " + totalCost);
    }
}
