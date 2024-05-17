public class Item extends Product {
    private double price;

    public Item(String sku, String name, double price) {
        super(sku, name);
        this.price = price;
    }

    public double getPrice() {
        // System.out.println("Item cost: " + Double.toString(this.price));
        return this.price;
    }
}