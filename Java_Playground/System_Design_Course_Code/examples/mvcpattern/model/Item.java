package model;

public class Item {
    private double price;
    private String sku;
    private GameObject gameItem;

    public Item(double price, String sku, GameObject gameItem) {
        this.price = price;
        this.sku = sku;
        this.gameItem = gameItem;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public String getSKU() {
        return this.sku;
    }

    public GameObject getObject() {
        return this.gameItem;
    }
}   