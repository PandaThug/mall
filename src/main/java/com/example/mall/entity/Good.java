package com.example.mall.entity;

import java.util.Arrays;

public class Good {

    private int id;
    private String goodName;
    private int goodPrice;
    private int goodCategory;
    private String goodIntroduction;
    private int goodSales;
    private String goodOptions;
    private byte[] goodPicture;
    private String goodStore;

    public Good(int id, String goodName, int goodPrice, int goodCategory, String goodIntroduction,
                int goodSales, String goodOptions, byte[] goodPicture, String goodStore) {
        this.id = id;
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.goodCategory = goodCategory;
        this.goodIntroduction = goodIntroduction;
        this.goodSales = goodSales;
        this.goodOptions = goodOptions;
        this.goodPicture = goodPicture;
        this.goodStore = goodStore;
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

    public int getGoodCategory() {
        return goodCategory;
    }

    public void setGoodCategory(int goodCategory) {
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

    public byte[] getGoodPicture() {
        return goodPicture;
    }

    public void setGoodPicture(byte[] goodPicture) {
        this.goodPicture = goodPicture;
    }

    public String getGoodStore() {
        return goodStore;
    }

    public void setGoodStore(String goodStore) {
        this.goodStore = goodStore;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", goodName='" + goodName + '\'' +
                ", goodPrice=" + goodPrice +
                ", goodCategory=" + goodCategory +
                ", goodIntroduction='" + goodIntroduction + '\'' +
                ", goodSales=" + goodSales +
                ", goodOptions='" + goodOptions + '\'' +
                ", goodPicture=" + Arrays.toString(goodPicture) +
                ", goodStore='" + goodStore + '\'' +
                '}';
    }

}