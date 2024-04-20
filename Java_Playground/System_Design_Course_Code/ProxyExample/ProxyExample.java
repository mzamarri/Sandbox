import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.Map;

/* ******************************************************************************************* */
/* Main Program */

public class ProxyExample {
    public static void main(String[] args) {
        // Create Proxy Warehouse
        OrderFulfillment orderFulfillment = new OrderFulfillment();

        Order order = new Order();
        Item item1 = new Item("a02", "Item 1", 25.00);
        Item item2 = new Item("a01", "Item 2", 10.00);
        Item item3 = new Item("a03", "Item 3", 100.00);
        order.addItem(item1, 2);
        order.addItem(item2, 5);
        order.addItem(item3, 20);

        System.out.println("Total Price: " + order.getPrice());
        System.out.println(item1.getName() + " amount: " + order.getItemAmount(item1));
        System.out.println(item2.getName() + " amount: " + order.getItemAmount(item2));
        System.out.println(item3.getName() + " amount: " + order.getItemAmount(item3));

        Warehouse warehouse = new Warehouse("123 street", orderFulfillment);
        warehouse.addStock("a01", 10);
        warehouse.addStock("a02", 10); 
        warehouse.addStock("a03", 10);
        int item1Inventory = warehouse.currentInventory(item1);
        int item2Inventory = warehouse.currentInventory(item2);
        int item3Inventory = warehouse.currentInventory(item3);
        System.out.println("Stock of Item 1: " + item1Inventory);
        System.out.println("Stock of Item 2: " + item2Inventory);
        System.out.println("Stock of Item 3: " + item3Inventory);

        Client client = new Client(order, orderFulfillment);
        client.submitOrder();
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
            System.out.println("Checking if warehouse has all items in stock...");
            for (Item item : order.itemList()) {
                    if (order.getItemAmount(item) > warehouse.currentInventory(item)) {
                        System.out.println("Item not in stock");
                        itemsInStock = false;
                        break;
                    }
                }
            if (itemsInStock == true) {
                System.out.println("Order Fulfilled!");
                warehouse.fulfillOrder(order);
                return;
            }
        }
        System.out.println("Items not in stock!");
        return;
    }

    public void addWarehouse(Warehouse warehouse) {
        this.warehouses.add(warehouse);
    }
}

class Warehouse implements IOrder {
    private String address;
    private Hashtable<String, Integer> stock = new Hashtable<>(); // Hashtable acts like a dictionary

    public Warehouse(String address, OrderFulfillment orderFulfillment) {
        this.address = address;
        orderFulfillment.addWarehouse(this);
    }

    public void addStock(String sku, int itemAmount) {
        if (stock.get(sku) != null) {
            this.stock.put(sku, this.stock.get(sku) + itemAmount);
        } else {
            this.stock.put(sku, itemAmount);
        }
    }
    
    public void removeStock(String sku) {
        this.stock.remove(sku);
    }

    public void fulfillOrder(Order order) {
        for (Item item : order.itemList()) {
            stock.merge(item.getId(), -order.getItemAmount(item), Integer::sum);
            System.out.println("Item: " + item.getName());
            if (stock.get(item.getId()) == 0) {
                System.out.println(item.getName() + ": Out of stock!");
            } 
        }
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

class Client {
    private Order order;
    private IOrder orderFulfillment;

    public Client(Order order, IOrder orderFulfillment) {
        this.order = order;
        this.orderFulfillment = orderFulfillment;
    }

    public void submitOrder() {
        orderFulfillment.fulfillOrder(order);
    }
}
