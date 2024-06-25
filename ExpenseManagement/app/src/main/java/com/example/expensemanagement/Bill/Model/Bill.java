package com.example.expensemanagement.Bill.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Bill")
public class Bill {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int categoryBill;
    private String transactionMethod;

    public Bill() {
    }
    public Bill(int categoryBill, String transactionMethod) {
        this.categoryBill = categoryBill;
        this.transactionMethod = transactionMethod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryBill() {
        return categoryBill;
    }

    public void setCategoryBill(int categoryBill) {
        this.categoryBill = categoryBill;
    }

    public String getTransactionMethod() {
        return transactionMethod;
    }

    public void setTransactionMethod(String transactionMethod) {
        this.transactionMethod = transactionMethod;
    }
}
