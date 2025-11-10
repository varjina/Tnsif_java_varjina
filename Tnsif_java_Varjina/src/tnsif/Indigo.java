package com.tnsif;
public class Indigo implements Airfares {
    private int hours;
    private double costPerHour;

    public Indigo() {}

    public Indigo(int hours, double costPerHour) {
        this.hours = hours;
        this.costPerHour = costPerHour;
    }

    @Override
    public double calculateAmount() {
        return hours * costPerHour * 8;
    }
}


}
