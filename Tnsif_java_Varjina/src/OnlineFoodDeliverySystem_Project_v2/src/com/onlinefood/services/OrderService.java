package com.onlinefood.services;

import com.onlinefood.model.Order;
import com.onlinefood.model.Customer;
import com.onlinefood.model.DeliveryPerson;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private List<Order> orders = new ArrayList<>();
    private int nextOrderId = 1;

    public Order createOrder(Customer customer) {
        Order o = new Order(nextOrderId++, customer);
        customer.getCart().getItems().forEach((f,q)-> o.addItem(f,q));
        customer.getCart().clear();
        orders.add(o);
        return o;
    }

    public Optional<Order> findById(int id) { return orders.stream().filter(o->o.getOrderId()==id).findFirst(); }
    public List<Order> getAll() { return orders; }

    public void assignDeliveryPerson(int orderId, DeliveryPerson dp) {
        findById(orderId).ifPresent(o-> { o.setDeliveryPerson(dp); o.setStatus("Assigned"); });
    }
}
