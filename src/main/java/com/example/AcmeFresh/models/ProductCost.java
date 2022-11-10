package com.example.AcmeFresh.models;

import java.util.UUID;

public class ProductCost {

    private UUID clientOrder;
    private int total_cost;

    public UUID getClientOrder() {
        return clientOrder;
    }

    public void setClientOrder(UUID clientOrder) {
        this.clientOrder = clientOrder;
    }

    public int getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }

    @Override
    public String toString() {
        return "ProductCost{" +
                "clientOrder=" + clientOrder +
                ", total_cost=" + total_cost +
                '}';
    }
}
