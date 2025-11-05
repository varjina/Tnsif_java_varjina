package com.onlinefood.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private Map<FoodItem,Integer> items = new LinkedHashMap<>();

    public void addItem(FoodItem f, int qty) {
        if (qty <= 0) return;
        items.put(f, items.getOrDefault(f, 0) + qty);
    }

    public void removeItem(FoodItem f) { items.remove(f); }

    public Map<FoodItem,Integer> getItems() { return items; }

    public double getTotal() {
        double t=0; for (Map.Entry<FoodItem,Integer> e: items.entrySet()) t+= e.getKey().getPrice()*e.getValue();
        return t;
    }

    public boolean isEmpty() { return items.isEmpty(); }

    public void clear() { items.clear(); }
}
