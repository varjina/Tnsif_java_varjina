package com.onlinefood.services;

import com.onlinefood.model.DeliveryPerson;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeliveryService {
    private List<DeliveryPerson> dps = new ArrayList<>();
    private int nextId = 1;
    public DeliveryPerson add(String name, String phone) {
        DeliveryPerson dp = new DeliveryPerson(nextId++, name, phone);
        dps.add(dp);
        return dp;
    }
    public Optional<DeliveryPerson> findById(int id) { return dps.stream().filter(x->x.getId()==id).findFirst(); }
    public List<DeliveryPerson> getAll() { return dps; }
}
