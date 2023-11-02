import java.util.Scanner;

public class Menu {

    private Scanner scan = new Scanner(System.in);
    private Products products = new Products();
    private Cart cart = new Cart();
    private boolean isLoggedIn = false;
    private String password = "abc123";

    public Menu() {
    }

    public static boolean isInt(String testInt) {
        try {
            Integer.parseInt(testInt);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void start() {
        boolean run = true;
        while (run) {
            System.out.print(startMenuText());
            String choice = scan.next();

            switch (choice.trim().toUpperCase()) {
                case "1":
                    productsMenu();
                    break;

                case "2":
                    cartMenu();
                    break;

                case "3":
                    if (this.isLoggedIn) {
                        manageProductsMenu();
                    } else {
                        login();
                    }
                    break;

                case "4":
                    if (this.isLoggedIn) {
                        this.isLoggedIn = false;
                    } else {
                        System.out.println("Choose between 1-3 or Q.");
                    }
                    break;

                case "Q":
                    System.out.println("Have a nice day!");
                    run = false;
                    break;

                default:
                    if (this.isLoggedIn) {
                        if (this.isLoggedIn) {
                            System.out.println("\nChoose between 1-4 or Q.");
                        } else {
                            System.out.println("Choose between 1-3 or Q.");
                        }
                    }
                    break;
            }
        }
    }

    private void productsMenu() {
        boolean run = true;
        while (run) {
            System.out.println("\nMenu - Products" +
                    "\n" + "_".repeat(25));
            products.printList();
            System.out.print("\nB. Back to menu" +
                    "\n" + "_".repeat(25) +
                    "\n -> ");
            String choice = scan.next();

            if (choice.trim().equalsIgnoreCase("B")) {
                run = false;
            } else if (isInt(choice.trim())) {
                int productIndex = Integer.parseInt(choice.trim()) - 1;
                if (productIndex < products.list().size() && productIndex >= 0) {
                    addAmount(products.list().get(productIndex));
                }
            } else {
                if (products.list().size() == 1) {
                    System.out.println("Choose between 1 or B.");
                } else if (products.list().size() == 2) {
                    System.out.println("Choose between 1, 2 or B.");
                } else {
                    System.out.println("Choose between 1-" + products.list().size() + " or B.");
                }
            }
        }
    }

    private void cartMenu() {
        boolean run = true;
        while (run) {
            System.out.println("\nMenu - Cart");
            cart.printList();
            System.out.print("E. Empty cart" +
                    "\nS. Buy items" +
                    "\nB. Go back" +
                    "\n" + "_".repeat(25) +
                    "\n -> ");
            String choice = scan.next();

            switch (choice.trim().toUpperCase()) {
                case "E":
                    emptyCart();
                    break;

                case "S":
                    if (cart.list().size() == 0) {
                        System.out.println("You have no items in the cart!");
                    } else {
                        cart.printReceipt();
                        cart.list().clear();
                    }
                    break;

                case "B":
                    run = false;
                    break;

                default:
                    System.out.println("Choose between E (Empty cart), S (Buy items) and B (Go back).");
                    break;
            }
        }
    }

    private void manageProductsMenu() {
        boolean run = true;
        while (run) {
            System.out.println("Menu - Manage Products");
            products.printList();
            System.out.print("\nB. Back to menu" +
                    "\n" + "_".repeat(25) +
                    "\n -> ");
            String choice = scan.next();

            if (choice.trim().equalsIgnoreCase("B")) {
                run = false;
            } else if (isInt(choice.trim())) {
                int productIndex = Integer.parseInt(choice.trim()) - 1;
                if (productIndex < products.list().size() && productIndex >= 0) {
                    products.editProduct(productIndex);
                }
            } else {
                if (products.list().size() == 1) {
                    System.out.println("Choose between 1 or B.");
                } else if (products.list().size() == 2) {
                    System.out.println("Choose between 1, 2 or B.");
                } else {
                    System.out.println("Choose between 1-" + products.list().size() + " or B.");
                }
            }
        }
    }

    private void login() {
        System.out.print("\nLösenord -> ");
        String password = scan.next();

        if (password.equals(this.password)) {
            this.isLoggedIn = true;
        } else {
            System.out.println("Wrong password!");
        }
    }

    private void addAmount(Product item) {
        try {
            System.out.print(item.namn() + " - How many -> ");
            int amount = scan.nextInt();

            if (amount >= 0 && amount <= item.stock()) {
                item.setStock(item.stock() - amount);
                cart.addToCart(item, amount);
            } else if (amount < 0) {
                System.out.println("You can't add a negativ amount.");
            } else {
                System.out.println("We don't have that many in stock.");
            }
        } catch (NumberFormatException e) {
            System.out.println("You have to typ in a number.");
        }
    }

    private void emptyCart() {
        for (int i = 0; i < cart.list().size(); i++) {
            for (int j = 0; j < products.list().size(); j++) {
                if (cart.list().get(i).namn().equals(products.list().get(j).namn())) {
                    int newStock = cart.list().get(i).stock() + products.list().get(j).stock();
                    products.list().get(j).setStock(newStock);
                }
            }
        }
        cart.list().clear();
    }

    private String startMenuText() {
        if (this.isLoggedIn) {
            return "\nStart Menu" +
                    "\n" + "_".repeat(25) +
                    "\n1. Products" +
                    "\n2. Cart" +
                    "\n3. Manage Products" +
                    "\n4. Logout" +
                    "\nQ. Quit" +
                    "\n" + "_".repeat(25) +
                    "\n -> ";
        }

        return "\nStart Menu" +
                "\n" + "_".repeat(25) +
                "\n1. Products" +
                "\n2. Cart" +
                "\n3. Login" +
                "\nQ. Quit" +
                "\n" + "_".repeat(25) +
                "\n -> ";
    }

}

