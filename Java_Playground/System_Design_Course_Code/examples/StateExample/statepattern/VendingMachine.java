import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

/* The VendingMachine will have items that each have a state. When an input is provided 
(A person might input a sequence of characters for an item) the vending machine checks the state of the
item.
 */

public class VendingMachine {
    private State idleState = new IdleState();
    private State readyToDispenseState = new ReadyToDispenseState();
    private State outOfStockState = new OutOfStockState();

    private double totalAmount = 0;
    private HashMap<String, ItemStock> stock = new HashMap<>();

    public class ItemStock {
        private Item item;
        private int amount;
        private State state;

        public ItemStock(Item item, int amount) {
            this.item = item;
            this.amount = amount;
        }

        public Item getItem() {
            return this.item;
        }

        public double getPrice() {
            return this.item.getPrice();
        }

        public String getName() {
            return this.item.getName();
        }

        public int amountInStock() {
            return this.amount;
        }

        public void addStock(int amount) {
            this.amount += amount;
        }

        public void setState(State state) {
            this.state = state;
        }

        public State getState() {
            return this.state;
        }

        public void dispenseItem() {
            state.dispenseItem(this);
        }
    }

    public interface State {
        public void dispenseItem(ItemStock itemStock);
    }

    public class IdleState implements State {
        public void dispenseItem(ItemStock itemStock) {
            double remainingAmount = itemStock.getPrice() - VendingMachine.this.totalAmount;
            System.out.println("Not enough money, add " + Double.toString(remainingAmount) + " to dispense item");
        }
    }

    public class ReadyToDispenseState implements State {
        public void dispenseItem(ItemStock itemStock) {
            System.out.println("Dispensing: " + itemStock.getName());
            VendingMachine.this.totalAmount -= itemStock.getPrice();
            if (VendingMachine.this.totalAmount < 0) {
                VendingMachine.this.totalAmount = 0;
            }
            System.out.println("Balance remaining: $" + VendingMachine.this.totalAmount);
            itemStock.addStock(-1);
            if (itemStock.amountInStock() < 1) {
                itemStock.setState(outOfStockState);
            } else if (VendingMachine.this.totalAmount < itemStock.getPrice()) {
                itemStock.setState(idleState);
            }
        }
    }

    public class OutOfStockState implements State {
        public void dispenseItem(ItemStock itemStock) {
            System.out.println(itemStock.getName() + " out of stock!");
        }
    }

    public double getTotalAmount() {
        return this.totalAmount;
    }

    public void addMoney(double amount) {
        this.totalAmount += amount;
        for (ItemStock itemStock : stock.values()) {
            if (itemStock.getPrice() <= this.totalAmount && !(itemStock.getState() instanceof OutOfStockState)) {
                itemStock.setState(readyToDispenseState);
            }
        }
        System.out.println("$" + Double.toString(this.totalAmount) + " Added");
    }

    public void ejectMoney() {
        System.out.println("$" + Double.toString(this.totalAmount) + " returned.");
        for (ItemStock itemStock : stock.values()) {
            if (itemStock.getState() != outOfStockState) {
                itemStock.setState(idleState);
            }
        }
        this.totalAmount = 0;
    }

    public void dispense(String id) {
        ItemStock itemStock = stock.get(id);
        itemStock.dispenseItem();
        if (itemStock.amountInStock() < 1) {
            itemStock.setState(outOfStockState);
        }
    }

    public void addStock(String id, Item item, int amount) {
        if (this.stock.containsKey(id)) {
            this.stock.get(id).addStock(amount);
        } else {
            ItemStock newItemStock = new ItemStock(item, amount);
            this.stock.put(id, newItemStock);
        }
    }
}
