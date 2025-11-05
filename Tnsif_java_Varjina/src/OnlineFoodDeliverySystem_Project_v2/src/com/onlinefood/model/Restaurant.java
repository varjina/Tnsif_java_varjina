package com.onlinefood.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private int id;
    private String name;
    private List<FoodItem> menu = new ArrayList<>();

    public Restaurant(int id, String name) { this.id = id; this.name = name; }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<FoodItem> getMenu() { return menu; }

    public void addFoodItem(FoodItem f) { menu.add(f); }
    public boolean removeFoodItemById(int fid) {
        return menu.removeIf(x -> x.getId() == fid);
    }

    @Override
    public String toString() {
        return id + ". " + name;
    }
}
