package com.example.expensemanagement.Bill.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "BillProduct")
//        , foreignKeys = {@ForeignKey(
//                entity = Bill.class,
//                parentColumns = "id",
//                childColumns = "id",
//                onDelete = ForeignKey.CASCADE
//        )})
public class BillProduct implements Serializable {
    @PrimaryKey
    private int bill_ProductID;
    private String nameProduct;
    private int billID;
    private Float Quantity ;

    public BillProduct(){}

    public BillProduct(int bill_ProductID, String nameProduct, int billID, Float quantity) {
        this.bill_ProductID = bill_ProductID;
        this.nameProduct = nameProduct;
        this.billID = billID;
        this.Quantity = quantity;
    }

    public int getBill_ProductID() {
        return bill_ProductID;
    }

    public void setBill_ProductID(int bill_ProductID) {
        this.bill_ProductID = bill_ProductID;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public Float getQuantity() {
        return Quantity;
    }

    public void setQuantity(Float quantity) {
        Quantity = quantity;
    }
}
