package com.Api.Fooddelivery;

public class DeliveryPartner {
    private String id;
    private int numberOfDeliveries;

    public DeliveryPartner(String id) {
        this.id = id;
        this.numberOfDeliveries=0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumberOfDeliveries() {
        return numberOfDeliveries;
    }

    public void setNumberOfDeliveries(int numberOfDeliveries) {
        this.numberOfDeliveries = numberOfDeliveries;
    }
}
