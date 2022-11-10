package com.example.AcmeFresh.models;

import java.util.UUID;

public class farm {
    private UUID id;
    private String farmTypes;
    private int cost;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFarmTypes() {
        return farmTypes;
    }

    public void setFarmTypes(String farmTypes) {
        this.farmTypes = farmTypes;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public farm(UUID id, String farmTypes, int cost) {
        this.id = id;
        this.farmTypes = farmTypes;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "farm{" +
                "id=" + id +
                ", farmTypes='" + farmTypes + '\'' +
                ", cost=" + cost +
                '}';
    }
}
