package com.example.expensemanagement.Bill.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "BillEcommerce")
//        , foreignKeys = {@ForeignKey(
//                entity = Bill.class,
//                parentColumns = "id",
//                childColumns = "id",
//                onDelete = ForeignKey.CASCADE
//        )})
public class BillEcommerce implements Serializable {
    @PrimaryKey
    private int id;
    private String nameEcommerce;
    private String date;
    private String nameCustomer;
    private String phone;
    private String address;
    private String paymentTime;
    private Float productCost;
    private Float shipCost;
    private Float totalPayment;

    public BillEcommerce() {
    }

    public BillEcommerce(int id, String nameEcommerce, String date, String nameCustomer, String phone, String address, String paymentTime, Float productCost, Float shipCost, Float totalPayment) {
        this.id = id;
        this.nameEcommerce = nameEcommerce;
        this.date = date;
        this.nameCustomer = nameCustomer;
        this.phone = phone;
        this.address = address;
        this.paymentTime = paymentTime;
        this.productCost = productCost;
        this.shipCost = shipCost;
        this.totalPayment = totalPayment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEcommerce() {
        return nameEcommerce;
    }

    public void setNameEcommerce(String nameEcommerce) {
        this.nameEcommerce = nameEcommerce;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Float getProductCost() {
        return productCost;
    }

    public void setProductCost(Float productCost) {
        this.productCost = productCost;
    }

    public Float getShipCost() {
        return shipCost;
    }

    public void setShipCost(Float shipCost) {
        this.shipCost = shipCost;
    }

    public Float getTotalPayment() {
        return totalPayment;
    }
    public void setTotalPayment(Float totalPayment) {
        this.totalPayment = totalPayment;
    }
}
