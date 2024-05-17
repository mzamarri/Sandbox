public class Product {
    private String name;
    private String sku;

    public Product(String sku, String name) {
        this.sku = sku;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getSKU() {
        return this.sku;
    }
}