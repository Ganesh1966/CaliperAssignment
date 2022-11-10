package com.example.AcmeFresh.models;

import java.util.UUID;

public class FarmCost {
    private UUID customerId;
    private int totalCost;

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
