package com.example.mall.entity;

import java.util.Date;

public class Order {

    private int orderId;
    private int userId;
    private int shopId;
    private int goodId;
    private int purchaseQuantity;
    private int totalPrice;
    private String goodName;
    private String goodOption;
    private int orderStatus;  // 0-未完成; 1-已发货; 2-已收货; 3-待评价; 4-已评价
    private String address;
    private String telNumber;
    private String buyerName;

    public Order(int orderId, int userId, int shopId, int goodId, int purchaseQuantity, int totalPrice, String goodName,
                 String goodOption, int orderStatus, String address, String telNumber, String buyerName) {
        this.orderId = orderId;
        this.userId = userId;
        this.shopId = shopId;
        this.goodId = goodId;
        this.purchaseQuantity = purchaseQuantity;
        this.totalPrice = totalPrice;
        this.goodName = goodName;
        this.goodOption = goodOption;
        this.orderStatus = orderStatus;
        this.address = address;
        this.telNumber = telNumber;
        this.buyerName = buyerName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodOption() {
        return goodOption;
    }

    public void setGoodOption(String goodOption) {
        this.goodOption = goodOption;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", shopId=" + shopId +
                ", goodId=" + goodId +
                ", purchaseQuantity=" + purchaseQuantity +
                ", totalPrice=" + totalPrice +
                ", goodName='" + goodName + '\'' +
                ", goodOption='" + goodOption + '\'' +
                ", orderStatus=" + orderStatus +
                ", address='" + address + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", buyerName='" + buyerName + '\'' +
                '}';
    }

}
