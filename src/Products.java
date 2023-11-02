import java.util.ArrayList;
import java.util.Scanner;

public class Products {
    private ArrayList<Product> list = new ArrayList<Product>();

    public Products() {
        this.list.add(new Product("Hammer", 150, 50));
        this.list.add(new Product("Saw", 350, 30));
        this.list.add(new Product("Nails 50-pack", 15, 200));
        this.list.add(new Product("Nails 150-pack", 50, 200));
        this.list.add(new Product("Nails 350-pack", 100, 150));
    }

    public ArrayList<Product> list() {
        return list;
    }

    public void printList() {
        if (this.list.isEmpty()) {
            System.out.println("No products");
        } else {
            for (int i = 0; i < this.list.size(); i++) {
                System.out.println((i + 1) + ". " + this.list.get(i).namn() + " -  Stock: " + this.list.get(i).stock());
            }
        }
    }

    public void editProduct(int index){
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        while(run) {
            System.out.print("1. Name -> " + this.list.get(index).namn() +
                    "\n2. Price -> " + this.list.get(index).price() +
                    "\n3. Stock -> " + this.list.get(index).stock() +
                    "\nB. Go Back" +
                    "\n\n -> ");
            String choice = scan.next();

            switch (choice.trim().toUpperCase()){
                case "1":
                    System.out.print("\nName -> ");
                    String name = scan.next();
                    this.list.get(index).setNamn(name);
                    break;

                case "2":
                    System.out.print("\nPrice -> ");
                    String price = scan.next();
                    if (isDouble(price)){
                       this.list.get(index).setPrice(Double.parseDouble(price));
                    } else {
                        System.out.println("Need to type in a valid number.");
                    }
                    break;

                case "3":
                    System.out.print("\nStock -> ");
                    String stock = scan.next();
                    if (isInt(stock)){
                        this.list.get(index).setStock(Integer.parseInt(stock));
                    } else {
                        System.out.println("Need to type in a valid number.");
                    }
                    break;

                case "B":
                    run = false;
                    break;

                default:
                    System.out.println("You must pick between 1-3 and B.");
                    break;
            }
        }
    }

    public static boolean isInt(String testInt){
        try{
            Integer.parseInt(testInt);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public static boolean isDouble(String testDouble){
        try{
            Double.parseDouble(testDouble);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

}
