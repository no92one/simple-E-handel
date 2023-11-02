import java.util.ArrayList;

public class Cart {
    private ArrayList<Product> list = new ArrayList<Product>();

    public Cart() {}

    public void addToCart(Product item, int amount) {
        int index = alreadyInCart(item.namn());
        if (index >= 0){
            this.list.get(index).setStock(this.list.get(index).stock() + amount);
        } else {
            this.list.add(new Product(item.namn(), item.price(), amount));
        }
    }

    public ArrayList<Product> list() {
        return list;
    }

    public void printList() {
        if (this.list.isEmpty()) {
            System.out.println("_".repeat(25) +
                    "\nNo products" +
                    "\n" + "_".repeat(25));
        } else {
            double totalCost = 0;
            System.out.println("_".repeat(25));
            for (int i = 0; i < this.list.size(); i++) {
                double cost = this.list.get(i).stock() * this.list.get(i).price();
                totalCost += cost;

                System.out.println("\n" + this.list.get(i).namn() +
                        "\nAmount: " + this.list.get(i).stock() +
                        "\nCost: " + cost);
            }
            System.out.println("\n\nTotal Cost: " + totalCost +
                    "\n" + "_".repeat(25));
        }
    }

    public void printReceipt() {
        double totalPrice = 0;
        System.out.println("\nReceipt");
        for (int i = 0; i < this.list.size(); i++) {
            double price = this.list.get(i).price() * this.list.get(i).stock();
            System.out.println("_".repeat(25) +
                    "\n" + this.list.get(i).namn() +
                    "\nPrice: " + this.list.get(i).price() +
                    "\nAmount: " + this.list.get(i).stock() +
                    "\nPrice : " + price);
            totalPrice += price;
        }
        System.out.println("_".repeat(25) +
                "\nTotal Price: " + totalPrice + " :-");
    }

    private int alreadyInCart(String itemName) {
        for (int i = 0; i < this.list.size(); i++) {
            if(this.list.get(i).namn().equals(itemName)) {
                return i;
            }
        }
        return -1;
    }
}
