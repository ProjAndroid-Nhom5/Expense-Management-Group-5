package com.example.expensemanagement.Bill.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "BillSupply")
//        , foreignKeys = {@ForeignKey(
//                entity = Bill.class,
//                parentColumns = "id",
//                childColumns = "id",
//                onDelete = ForeignKey.CASCADE
//        )})
public class BillSupply implements Serializable {
    @PrimaryKey
    private int id;
    private String nameSupplier;
    private String date;
    private int number;
    private Float productCost;
    private Float shipCost;
    private Float total;

    public BillSupply(){}

    public BillSupply(int id, String nameSupplier, String date, int number, Float productCost, Float shipCost, Float total) {
        this.id = id;
        this.nameSupplier = nameSupplier;
        this.date = date;
        this.number = number;
        this.productCost = productCost;
        this.shipCost = shipCost;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
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
}
