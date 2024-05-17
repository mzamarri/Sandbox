package model;

import java.util.ArrayList;

public class Order {
    private ArrayList<OrderItem> itemList = new ArrayList<>();
    private double totalPrice;

    public void addItem(OrderItem item) {
        this.itemList.add(item);
        this.totalPrice += item.getItemPrice();
    }

    public void removeItem(OrderItem item) {
        if (this.itemList.contains(item)) {
            this.itemList.remove(item);
            this.totalPrice -= item.getItemPrice();
            return;
        }
        System.out.println("Item not in Order");
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }
}