package com.company;

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



    }
}
