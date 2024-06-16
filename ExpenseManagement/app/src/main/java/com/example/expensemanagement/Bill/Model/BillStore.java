package com.example.expensemanagement.Bill.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "BillStore")
//        , foreignKeys = {@ForeignKey(
//                entity = Bill.class,
//                parentColumns = "id",
//                childColumns = "id",
//                onDelete = ForeignKey.CASCADE
//        )})
public class BillStore implements Serializable {
    @PrimaryKey
    private int id;
    private String date;
    private String nameEmploye;
    private Float productCost;
    private Float total;

    public BillStore(){}

    public BillStore(int id,String date, String nameEmploye, Float productCost, Float total) {
        this.id = id;
        this.date = date;
        this.nameEmploye = nameEmploye;
        this.productCost = productCost;
        this.total = total;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getNameEmploye() {
        return nameEmploye;
    }

    public void setNameEmploye(String nameEmploye) {
        this.nameEmploye = nameEmploye;
    }

    public Float getProductCost() {
        return productCost;
    }

    public void setProductCost(Float productCost) {
        this.productCost = productCost;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
