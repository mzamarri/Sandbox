package model;

public class OrderItem {
    private int numOfItems;
    private Item item;

    public OrderItem(int numOfItems, Item item) {
        this.numOfItems = numOfItems;
        this.item = item;
    }

    public int getNumOfItems() {
        return this.numOfItems;
    }

    public void addNumOfItems(int n) {
        this.numOfItems += n;
    }

    public double getItemPrice() {
        return this.item.getPrice();
    }

    public GameObject getItemObject() {
        return item.getObject();
    }
}