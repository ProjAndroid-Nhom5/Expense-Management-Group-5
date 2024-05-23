package com.example.expensemanagement;

public class ListBillSupplyData {
    private String id,
            nameSupplier,
            date,
            number;
    private Float productCost,shipCost,total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameSupplier() {
        return nameSupplier;
    }

    public void setNameSupplier(String nameSupplier) {
        this.nameSupplier = nameSupplier;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public ListBillSupplyData(String id, String nameSupplier, String date, String number, Float productCost, Float shipCost, Float total) {
        this.id = id;
        this.nameSupplier = nameSupplier;
        this.date = date;
        this.number = number;
        this.productCost = productCost;
        this.shipCost = shipCost;
        this.total = total;
    }
}
