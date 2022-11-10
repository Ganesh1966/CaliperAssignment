package com.example.AcmeFresh.models;

import com.example.demo.db.jooqs.tables.Farm;

import java.util.List;
import java.util.UUID;

public class order {
    private UUID id;
    private List<UUID> productsList;
    private List<UUID> farmList;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<UUID> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<UUID> productsList) {
        this.productsList = productsList;
    }

    public List<UUID> getFarmList() {
        return farmList;
    }

    public void setFarmList(List<UUID> farmList) {
        this.farmList = farmList;
    }
}
