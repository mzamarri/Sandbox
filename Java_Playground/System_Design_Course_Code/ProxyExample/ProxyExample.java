import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.Map;

/* ******************************************************************************************* */
/* Main Program */

public class ProxyExample {
    public static void main(String[] args) {
        Order order = new Order();
        Item item1 = new Item("a02", "Item 1", 25.00);
        Item item2 = new Item("a01", "Item 2", 10.00);
        order.addItem(item1, 2);
        order.addItem(item2, 5);

        System.out.println("Total Price: " + order.getPrice());
        System.out.println("Item 1 amount: " + order.getItemAmount(item1));
        System.out.println("Item 2 amount: " + order.getItemAmount(item2));

        Warehouse warehouse = new Warehouse("123 street");
        warehouse.addStock("a01", 10);
        int item1Inventory = warehouse.currentInventory(item1);
        int item2Inventory = warehouse.currentInventory(item2);
        System.out.println("Stock of Item 1: " + item1Inventory);
        System.out.println("Stock of Item 2: " + item2Inventory);
    } 
}

/* ******************************************************************************************* */
/* ******************************************************************************************* */

interface IOrder {
    public void fulfillOrder(Order order);
}

class OrderFulfillment implements IOrder {
    private List<Warehouse> warehouses = new ArrayList<>();

    public void fulfillOrder(Order order) {
        for (Warehouse warehouse : warehouses) {
            boolean itemsInStock = true;
            for (Item item : order.itemList()) {
                    if (order.getItemAmount(item) > warehouse.currentInventory(item)) {
                        itemsInStock = false;
                        break;
                    }
                }
            if (itemsInStock == true) {
                warehouse.fulfillOrder(order);
                return;
            }
        }
        System.out.println("Items not in stock!");
        return;
    }
}

class Warehouse implements IOrder {
    private String address;
    private Hashtable<String, Integer> stock = new Hashtable<>(); // Hashtable functions like a dictionary

    public Warehouse(String address) {
        this.address = address;
    }

    public void addStock(String sku, int itemAmount) {
        System.out.println("Testing point 1");
        if (stock.get(sku) != null) {
            System.out.println("Testing point 2");
            this.stock.put(sku, this.stock.get(sku) + itemAmount);
        } else {
            this.stock.put(sku, itemAmount);
        }
    }
    
    public void removeStock(String sku) {
        this.stock.remove(sku);
    }

    public void fulfillOrder(Order order) {
        return;
    }

    public int currentInventory(Item item) {
        if (stock.containsKey(item.getId())) {
            return stock.get(item.getId());
        }
        return 0;
    }
}

class Order {
    private String id;
    private Map<Item, Integer> items = new Hashtable<>();

    public void addItem(Item item, int amount) {
        this.items.put(item, amount);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    } 

    public int getItemAmount(Item item) {
        return this.items.get(item);
    }

    public Set<Item> itemList() {
        return items.keySet();
    }

    public double getPrice() {
        double totalPrice = 0;
        for (Map.Entry<Item, Integer> item : items.entrySet()) {
            totalPrice += item.getValue() * item.getKey().getPrice();
        }
        return totalPrice;
    }
}

class Item {
    private String sku;
    private String name;
    private double price;

    public Item(String sku, String name, double price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.sku;
    }

    public void changePrice(double newPrice) {
        this.price = newPrice;
    }
}
