package com.onlinefood.services;

import com.onlinefood.model.Restaurant;
import com.onlinefood.model.FoodItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FoodService {
    private List<Restaurant> restaurants = new ArrayList<>();
    private int nextFoodId = 1;

    public void addRestaurant(Restaurant r) { restaurants.add(r); }
    public Optional<Restaurant> findById(int id) { return restaurants.stream().filter(r->r.getId()==id).findFirst(); }
    public List<Restaurant> getAll() { return restaurants; }

    public int getNextFoodId() { return nextFoodId++; }
}
