package com.example.expensemanagement.Bill.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "BillFacility")
//        , foreignKeys = {@ForeignKey(
//                entity = Bill.class,
//                parentColumns = "id",
//                childColumns = "id",
//                onDelete = ForeignKey.CASCADE
//        )})
public class BillFacility implements Serializable {
    @PrimaryKey
    private int id;
    private String nameFacility;
    private String nameManager;
    private String date;
    private String transactionMethod;
    private Float totalPayment;

    public BillFacility(){}
    public BillFacility(int id, String nameFacility, String nameManager, String date, String transactionMethod, Float totalPayment) {
        this.id = id;
        this.nameFacility = nameFacility;
        this.nameManager = nameManager;
        this.date = date;
        this.transactionMethod = transactionMethod;
        this.totalPayment = totalPayment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameFacility() {
        return nameFacility;
    }

    public void setNameFacility(String nameFacility) {
        this.nameFacility = nameFacility;
    }

    public String getNameManager() {
        return nameManager;
    }

    public void setNameManager(String nameManager) {
        this.nameManager = nameManager;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransactionMethod() {
        return transactionMethod;
    }

    public void setTransactionMethod(String transactionMethod) {
        this.transactionMethod = transactionMethod;
    }

    public Float getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Float totalPayment) {
        this.totalPayment = totalPayment;
    }
}
