package com.onlinefood.app;

import com.onlinefood.model.*;
import com.onlinefood.services.*;
import java.util.Scanner;
import java.util.List;

public class App {
    private static FoodService foodService = new FoodService();
    private static CustomerService customerService = new CustomerService();
    private static OrderService orderService = new OrderService();
    private static DeliveryService deliveryService = new DeliveryService();

    public static void main(String[] args) {
        seedSampleData();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Admin Menu\n2. Customer Menu\n3. Exit\nChoose an option: ");
            String opt = sc.nextLine().trim();
            if (opt.equals("1")) adminMenu(sc);
            else if (opt.equals("2")) customerMenu(sc);
            else if (opt.equals("3")) { System.out.println("Exiting..."); break; }
            else System.out.println("Invalid option. Try again."); 
        }
        sc.close();
    }

    private static void adminMenu(Scanner sc) {
        while (true) {
            System.out.println("\nAdmin Menu:\n1. Add Restaurant\n2. Add Food Item to Restaurant\n3. Remove Food Item from Restaurant\n4. View Restaurants and Menus\n5. View Orders\n6. Add Delivery Person\n7. Assign Delivery Person to Order\n8. Exit\nChoose an option: ");
            String opt = sc.nextLine().trim();
            switch(opt) {
                case "1": addRestaurant(sc); break;
                case "2": addFoodItem(sc); break;
                case "3": removeFoodItem(sc); break;
                case "4": viewRestaurants(); break;
                case "5": viewOrders(); break;
                case "6": addDeliveryPerson(sc); break;
                case "7": assignDeliveryPerson(sc); break;
                case "8": return;
                default: System.out.println("Invalid option."); break;
            }
        }
    }

    private static void customerMenu(Scanner sc) {
        System.out.println("\nCustomer Menu:\n1. Register Customer\n2. View Restaurants and Menus\n3. Add Item to Cart\n4. View Cart\n5. Place Order\n6. Exit\nChoose an option: ");
        while (true) {
            String opt = sc.nextLine().trim();
            switch(opt) {
                case "1": registerCustomer(sc); break;
                case "2": viewRestaurants(); break;
                case "3": addItemToCart(sc); break;
                case "4": viewCart(sc); break;
                case "5": placeOrder(sc); break;
                case "6": return;
                default: System.out.println("Invalid option."); break;
            }
            System.out.println("\nCustomer Menu:\n1. Register Customer\n2. View Restaurants and Menus\n3. Add Item to Cart\n4. View Cart\n5. Place Order\n6. Exit\nChoose an option: ");
        }
    }

    private static void seedSampleData() {
        Restaurant r1 = new Restaurant(101, "HariOmDhaba");
        r1.addFoodItem(new FoodItem(foodService.getNextFoodId(), "PunjabiThali", 340));
        r1.addFoodItem(new FoodItem(foodService.getNextFoodId(), "PavBhaji", 140));
        foodService.addRestaurant(r1);
        Restaurant r2 = new Restaurant(102, "ExpressInn");
        r2.addFoodItem(new FoodItem(foodService.getNextFoodId(), "Veg Biryani", 180));
        foodService.addRestaurant(r2);
        System.out.println("Sample data loaded.");
    }

    private static void addRestaurant(Scanner sc) {
        try {
            System.out.print("Enter Restaurant ID: "); int id = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Enter Restaurant Name: "); String name = sc.nextLine().trim();
            foodService.addRestaurant(new Restaurant(id, name));
            System.out.println("Restaurant added successfully!");
        } catch (Exception e) { System.out.println("Invalid input."); }
    }

    private static void addFoodItem(Scanner sc) {
        try {
            System.out.print("Enter Restaurant ID: "); int rid = Integer.parseInt(sc.nextLine().trim());
            foodService.findById(rid).ifPresentOrElse(r -> {
                try {
                    System.out.print("Enter Food Name: "); String fname = sc.nextLine().trim();
                    System.out.print("Enter Price: "); double price = Double.parseDouble(sc.nextLine().trim());
                    r.addFoodItem(new FoodItem(foodService.getNextFoodId(), fname, price));
                    System.out.println("Food item added successfully!"); 
                } catch (Exception ex) { System.out.println("Invalid input for food price."); }
            }, () -> System.out.println("Restaurant not found."));
        } catch (Exception e) { System.out.println("Invalid input."); }
    }

    private static void removeFoodItem(Scanner sc) {
        try {
            System.out.print("Enter Restaurant ID: "); int rid = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Enter Food Item ID to remove: "); int fid = Integer.parseInt(sc.nextLine().trim());
            foodService.findById(rid).ifPresentOrElse(r -> {
                boolean removed = r.removeFoodItemById(fid);
                System.out.println(removed ? "Food item removed." : "Food item not found in restaurant.");
            }, () -> System.out.println("Restaurant not found."));
        } catch (Exception e) { System.out.println("Invalid input."); }
    }

    private static void viewRestaurants() {
        System.out.println("\n--- Restaurants and Menus ---");
        for (Restaurant r: foodService.getAll()) {
            System.out.println(r);
            for (FoodItem f: r.getMenu()) System.out.println("   " + f);
        }
    }

    private static void viewOrders() {
        System.out.println("\n--- Orders ---");
        for (Order o: orderService.getAll()) System.out.println(o);
    }

    private static void addDeliveryPerson(Scanner sc) {
        System.out.print("Enter Delivery Person Name: "); String name = sc.nextLine().trim();
        System.out.print("Enter Phone Number: "); String phone = sc.nextLine().trim();
        DeliveryPerson dp = deliveryService.add(name, phone);
        System.out.println("Delivery person added: " + dp);
    }

    private static void assignDeliveryPerson(Scanner sc) {
        try {
            System.out.print("Enter Order ID: "); int oid = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Enter Delivery Person ID: "); int did = Integer.parseInt(sc.nextLine().trim());
            orderService.findById(oid).ifPresentOrElse(o -> {
                deliveryService.findById(did).ifPresentOrElse(dp -> {
                    orderService.assignDeliveryPerson(oid, dp);
                    System.out.println("Delivery person assigned."); 
                }, () -> System.out.println("Delivery person not found."));
            }, () -> System.out.println("Order not found."));
        } catch (Exception e) { System.out.println("Invalid input."); }
    }

    private static void registerCustomer(Scanner sc) {
        System.out.print("Enter Customer Name: "); String name = sc.nextLine().trim();
        System.out.print("Enter Address: "); String addr = sc.nextLine().trim();
        Customer c = customerService.addCustomer(name, addr);
        System.out.println("Customer registered with ID: " + c.getUserId());
    }

    private static void addItemToCart(Scanner sc) {
        try {
            System.out.print("Enter Customer ID: "); int cid = Integer.parseInt(sc.nextLine().trim());
            customerService.findById(cid).ifPresentOrElse(c -> {
                viewRestaurants();
                try {
                    System.out.print("Enter Restaurant ID: "); int rid = Integer.parseInt(sc.nextLine().trim());
                    foodService.findById(rid).ifPresentOrElse(r -> {
                        System.out.print("Enter Food Item ID: "); int fid = Integer.parseInt(sc.nextLine().trim());
                        r.getMenu().stream().filter(f->f.getId()==fid).findFirst().ifPresentOrElse(fi -> {
                            System.out.print("Enter Quantity: ");
                            try {
                                int q = Integer.parseInt(new java.util.Scanner(System.in).nextLine().trim());
                                c.getCart().addItem(fi, q);
                                System.out.println("Item added to cart.");
                            } catch (Exception ex) { System.out.println("Invalid quantity."); }
                        }, () -> System.out.println("Food item not found."));
                    }, () -> System.out.println("Restaurant not found."));
                } catch (Exception ex) { System.out.println("Invalid input."); }
            }, () -> System.out.println("Customer not found."));
        } catch (Exception e) { System.out.println("Invalid input."); }
    }

    private static void viewCart(Scanner sc) {
        try {
            System.out.print("Enter Customer ID: "); int cid = Integer.parseInt(sc.nextLine().trim());
            customerService.findById(cid).ifPresentOrElse(c -> {
                Cart cart = c.getCart();
                if (cart.isEmpty()) { System.out.println("Cart is empty."); return; }
                System.out.println("--- Cart ---");
                cart.getItems().forEach((f,q)-> System.out.println(f.getName()+" x"+q+" -> Rs. "+(f.getPrice()*q)));
                System.out.println("Total: Rs. " + cart.getTotal());
            }, () -> System.out.println("Customer not found."));
        } catch (Exception e) { System.out.println("Invalid input."); }
    }

    private static void placeOrder(Scanner sc) {
        try {
            System.out.print("Enter Customer ID: "); int cid = Integer.parseInt(sc.nextLine().trim());
            customerService.findById(cid).ifPresentOrElse(c -> {
                if (c.getCart().isEmpty()) { System.out.println("Cart is empty."); return; }
                Order o = orderService.createOrder(c);
                System.out.println("Order placed. Order ID: " + o.getOrderId());
            }, () -> System.out.println("Customer not found."));
        } catch (Exception e) { System.out.println("Invalid input."); }
    }
}
