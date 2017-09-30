package com.company;

public class StockItem implements Comparable {

    private final String name;
    private double price;
    private int quantityStock = 0;
    private int quantityReserved = 0;

    public StockItem(String name, double price) { // overloaded constructor
        this.name = name;
        this.price = price;
    }

    public StockItem(String name, double price, int quantityStock) { // overloaded constructor
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }

    public int availableQuantity() {
        return quantityStock - quantityReserved;
    }

    public void adjustStock(int quantity){
        int newQuantity = this.quantityStock + quantity;
        if (newQuantity >= 0) {
            this.quantityStock = newQuantity;
        }
    }

    public int reserveStock(int quantity){

        if ( quantity <= availableQuantity()){
            quantityReserved += quantity;
            return quantity;
        }

        System.out.println("Could not reserve " + this.getName());
        return 0;
    }

    public int unreserveStock(int quantity) {

        if ( quantity <= quantityReserved ){
            quantityReserved -= quantity;
            return quantity;
        }

        System.out.println("Could not unreserve " + this.getName());
        return 0;
    }

    public int finalizeStock(int quantity) {
        if(quantity <= quantityReserved){
            quantityStock -= quantity;
            quantityReserved -= quantity;
            return quantity;
        }
        System.out.println("unable to finalize stock for " + quantity + " of " + this.getName());
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering StockItem.equals");
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        String objName = ((StockItem) obj).getName();
        return (objName.equals(this.getName()));
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31;
    }

    @Override
    public int compareTo(Object o) {
//        System.out.println("Entering StockItem.compareTo");
        if (this == o) {
            return 0;
        }

        if (o != null) {
            return this.name.compareTo(((StockItem)o).getName());
        }

        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.name + " " + this.price + " reserved " + this.quantityReserved;
    }

    public int getQuantityInStock() {
        return quantityStock;
    }

    public void adjustReservedStock(int quantityInbasket) {
        int reservedAfter = quantityReserved + quantityInbasket;

        if (reservedAfter >= 0) {
            quantityReserved += quantityInbasket;
        }

    }

    public static class StaticClass {
        String name;

        public StaticClass(String name) {
            this.name = name;
        }
    }

    public class InnerClass {
        String name;

        public InnerClass(String name) {
            this.name = name;
        }
    }

    public InnerClass createInner(String name){
        return new InnerClass(name);
    }

    public static StaticClass createStatic(String name){
        return new StaticClass(name);
    }


}
