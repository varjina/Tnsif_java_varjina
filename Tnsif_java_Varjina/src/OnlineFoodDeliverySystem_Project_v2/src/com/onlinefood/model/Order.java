package com.onlinefood.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
    private int orderId;
    private Customer customer;
    private Map<FoodItem,Integer> items = new LinkedHashMap<>();
    private String status = "Pending";
    private DeliveryPerson dp;

    public Order(int orderId, Customer customer) { this.orderId = orderId; this.customer = customer; }

    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Map<FoodItem,Integer> getItems() { return items; }
    public String getStatus() { return status; }
    public void setStatus(String s) { status = s; }
    public void setDeliveryPerson(DeliveryPerson d) { dp = d; }
    public DeliveryPerson getDeliveryPerson() { return dp; }

    public void addItem(FoodItem f, int qty) { items.put(f, items.getOrDefault(f,0)+qty); }
    public double getTotal() { double t=0; for (Map.Entry<FoodItem,Integer> e: items.entrySet()) t += e.getKey().getPrice()*e.getValue(); return t; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append(" | Customer: ").append(customer.getUsername()).append(" | Status: ").append(status);
        if (dp!=null) sb.append(" | Delivery: ").append(dp.getName());
        sb.append("\nItems:\n");
        for (Map.Entry<FoodItem,Integer> e: items.entrySet()) {
            sb.append(e.getKey().getName()).append(" x").append(e.getValue()).append(" -> Rs. ").append(e.getKey().getPrice()*e.getValue()).append("\n");
        }
        sb.append("Total: Rs. ").append(getTotal());
        return sb.toString();
    }
}
