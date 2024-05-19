package com.example.expensemanagement;

public class ListBillEcommerceData {
    private String id,
            nameEcommerce,
            date,
            nameCustomer,
            phone,
            address,
            paymentTime;
    private Float productCost,shipCost,totalPayment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public ListBillEcommerceData(String id, String nameEcommerce, String date, String nameCustomer, String phone, String address, String paymentTime, Float productCost, Float shipCost, Float totalPayment) {
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
}
