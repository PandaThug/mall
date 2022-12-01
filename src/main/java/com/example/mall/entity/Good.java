package com.example.mall.entity;

import java.util.Arrays;

public class Good {

    private int id;
    private String goodName;
    private int goodPrice;
    private String goodCategory;
    private String goodIntroduction;
    private int goodSales;
    private String goodOptions;
    private String goodPicture;
    private int goodStore;
    private int goodCommentCount;
    private int realInventory;  // 实际库存
    private int virtualInventory;  // 虚拟库存

    public Good(int id, String goodName, int goodPrice, String goodCategory, String goodIntroduction, int goodSales,
                String goodOptions, String goodPicture, int goodStore, int goodCommentCount, int realInventory, int virtualInventory) {
        this.id = id;
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.goodCategory = goodCategory;
        this.goodIntroduction = goodIntroduction;
        this.goodSales = goodSales;
        this.goodOptions = goodOptions;
        this.goodPicture = goodPicture;
        this.goodStore = goodStore;
        this.goodCommentCount = goodCommentCount;
        this.realInventory = realInventory;
        this.virtualInventory = virtualInventory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(int goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getGoodCategory() {
        return goodCategory;
    }

    public void setGoodCategory(String goodCategory) {
        this.goodCategory = goodCategory;
    }

    public String getGoodIntroduction() {
        return goodIntroduction;
    }

    public void setGoodIntroduction(String goodIntroduction) {
        this.goodIntroduction = goodIntroduction;
    }

    public int getGoodSales() {
        return goodSales;
    }

    public void setGoodSales(int goodSales) {
        this.goodSales = goodSales;
    }

    public String getGoodOptions() {
        return goodOptions;
    }

    public void setGoodOptions(String goodOptions) {
        this.goodOptions = goodOptions;
    }

    public String getGoodPicture() {
        return goodPicture;
    }

    public void setGoodPicture(String goodPicture) {
        this.goodPicture = goodPicture;
    }

    public int getGoodStore() {
        return goodStore;
    }

    public void setGoodStore(int goodStore) {
        this.goodStore = goodStore;
    }

    public int getGoodCommentCount() {
        return goodCommentCount;
    }

    public void setGoodCommentCount(int goodCommentCount) {
        this.goodCommentCount = goodCommentCount;
    }

    public int getRealInventory() {
        return realInventory;
    }

    public void setRealInventory(int realInventory) {
        this.realInventory = realInventory;
    }

    public int getVirtualInventory() {
        return virtualInventory;
    }

    public void setVirtualInventory(int virtualInventory) {
        this.virtualInventory = virtualInventory;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", goodName='" + goodName + '\'' +
                ", goodPrice=" + goodPrice +
                ", goodCategory='" + goodCategory + '\'' +
                ", goodIntroduction='" + goodIntroduction + '\'' +
                ", goodSales=" + goodSales +
                ", goodOptions='" + goodOptions + '\'' +
                ", goodPicture='" + goodPicture + '\'' +
                ", goodStore=" + goodStore +
                ", goodCommentCount=" + goodCommentCount +
                ", realInventory=" + realInventory +
                ", virtualInventory=" + virtualInventory +
                '}';
    }

}
