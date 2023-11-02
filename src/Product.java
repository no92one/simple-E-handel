public class Product {
    private String namn;
    private double price;
    private int stock;

    public Product(String namn, double price, int inStock) {
        this.namn = namn;
        this.price = price;
        this.stock = inStock;
    }

    public String namn() {
        return namn;
    }

    public Product setNamn(String namn) {
        this.namn = namn;
        return this;
    }

    public double price() {
        return price;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    public int stock() {
        return stock;
    }

    public Product setStock(int inStock) {
        this.stock = inStock;
        return this;
    }
}