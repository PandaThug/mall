package com.example.mall.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Store {

    private Date createTime;
    private int totalSales;
    private String instruction;
    private String storeName;
    private int storeId;
    private int goodId;

    public Store(Date createTime, int totalSales, String instruction, String storeName, int storeId, int goodId) {
        this.createTime = createTime;
        this.totalSales = totalSales;
        this.instruction = instruction;
        this.storeName = storeName;
        this.storeId = storeId;
        this.goodId = goodId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    @Override
    public String toString() {
        return "Store{" +
                "createTime=" + createTime +
                ", totalSales=" + totalSales +
                ", instruction='" + instruction + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeId=" + storeId +
                ", goodId=" + goodId +
                '}';
    }

}
