package com.example.mall.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Store {

    private Integer storeId;
    private String storeName;
    private Integer totalSales;
    private String instruction;

    public Store(Integer storeId, String storeName, int totalSales, String instruction) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.totalSales = totalSales;
        this.instruction = instruction;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeId='" + storeId + '\'' +
                ", storeName='" + storeName + '\'' +
                ", totalSales=" + totalSales +
                ", instruction='" + instruction + '\'' +
                '}';
    }

}
