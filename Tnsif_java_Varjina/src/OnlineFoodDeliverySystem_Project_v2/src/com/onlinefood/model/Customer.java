package com.onlinefood.model;

public class Customer extends User {
    private String address;
    private Cart cart = new Cart();

    public Customer(int userId, String username, String address) {
        super(userId, username);
        this.address = address;
    }

    public String getAddress() { return address; }
    public Cart getCart() { return cart; }
}
