package com.example.mall.entity;

import java.util.Date;

public class Order {

    private int orderId;
    private int userId;
    private int goodId;
    private int purchaseQuantity;
    private String goodName;
    private Date orderTime;
    private int orderStatus;  // 0-未完成; 1-已完成未评价; 2-已完成已评价
    private String address;
    private int telNumber;
    private String buyerName;

    public Order(int orderId, int userId, int goodId, int purchaseQuantity, String goodName,
                 Date orderTime, int orderStatus, String address, int telNumber, String buyerName) {
        this.orderId = orderId;
        this.userId = userId;
        this.goodId = goodId;
        this.purchaseQuantity = purchaseQuantity;
        this.goodName = goodName;
        this.orderTime = orderTime;
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

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
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

    public int getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(int telNumber) {
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
                ", goodId=" + goodId +
                ", purchaseQuantity=" + purchaseQuantity +
                ", goodName='" + goodName + '\'' +
                ", orderTime=" + orderTime +
                ", orderStatus=" + orderStatus +
                ", address='" + address + '\'' +
                ", telNumber=" + telNumber +
                ", buyerName='" + buyerName + '\'' +
                '}';
    }

}
