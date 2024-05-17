import java.util.HashMap;

// This example does not implement the state pattern but is one implementation of a vending machine system

public class NotStatePatternExample {
    public static void main(String[] args) {
        Item xbox = new Item(500.00, "Xbox");
        Item ps = new Item(400.00, "Play Station");

        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.addItem(xbox, 12);
        vendingMachine.addItem(ps, 8);

        vendingMachine.ejectMoney();
        double money = 1000.00;
        vendingMachine.insertMoney(money);
        vendingMachine.ejectMoney();
        vendingMachine.insertMoney(money);
        vendingMachine.dispenseItem(ps);
        vendingMachine.dispenseItem(xbox);
        vendingMachine.dispenseItem(xbox);
    } 
}

final class State {
    private State() {}

    public final static State IdleState = new State();
    public final static State ReadyToDispenseState = new State();
    public final static State OutOfStockState = new State();
} 

// @Deprecated
class VendingMachine {
    private State currentState;
    private double totalAmount;
    private HashMap<Item, Integer> stock = new HashMap<>();
    
    private void determineState(Item item) {
        if (stock.get(item) < 1) {
            this.currentState = State.OutOfStockState;
        } else if (item.getPrice() <= this.totalAmount) {
            this.currentState = State.ReadyToDispenseState;
        }
    }

    public void insertMoney(double amount) {
        if (amount > 0) {
            this.totalAmount += amount;
        System.out.println("Total amount is now: " + Double.toString(totalAmount));
        } else {
            System.out.println("Insert Money");
        }
    }

    // ejectMoney prints the amount returned if totalAmount > 0. Otherwise, prints statement "No money loaded. Please insert money."
    public void ejectMoney() {
        if (this.totalAmount > 0) {
            System.out.println("Returning total amount: " + Double.toString(totalAmount));
            this.totalAmount = 0;
        } else {
            System.out.println("Machine not loaded. No money returned");
        }
    }
    // Dispenses the item if it meets the following conditions: 1. currentState = State.IdleState  2. Enough money is provided
    public void dispenseItem(Item item) {
        determineState(item);
        if (currentState == State.OutOfStockState) {
            System.out.println("Out of Stock!");
        } else if (currentState == State.ReadyToDispenseState) {
            System.out.println("Selection approved. Now Dispensing " + item.getName());
            this.totalAmount -= item.getPrice();
            System.out.println("Money left: " + this.totalAmount);
        } else {
            System.out.println("Not enough money. Insert more");
        }
        currentState = State.IdleState; 
    }

    public void addItem(Item item, int amount) {
        if (amount < 0) {
            System.out.println("Amount cannot be less than 0");    
        } else {
            stock.put(item, amount);
        }
    }

    public int getItemAmount(Item item) {
        return stock.get(item);
    }
}

class Item {
    private double price;
    private String name;

    public Item(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }
}
