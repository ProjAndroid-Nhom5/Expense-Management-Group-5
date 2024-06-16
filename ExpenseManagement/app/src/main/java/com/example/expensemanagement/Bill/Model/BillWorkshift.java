package com.example.expensemanagement.Bill.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "BillWorkshift")
//        , foreignKeys = {@ForeignKey(
//                entity = Bill.class,
//                parentColumns = "id",
//                childColumns = "id",
//                onDelete = ForeignKey.CASCADE
//        )})
public class BillWorkshift implements Serializable {
    @PrimaryKey
    private int id;
    private String nameEmploye;
    private String date;
    private Float totalWorked;
    private Float salary;
    private Float PITV;
    private Float total;

    public BillWorkshift(){}

    public BillWorkshift(int id, String nameEmploye, String date, Float totalWorked, Float salary, Float PITV, Float total) {
        this.id = id;
        this.nameEmploye = nameEmploye;
        this.date = date;
        this.totalWorked = totalWorked;
        this.salary = salary;
        this.PITV = PITV;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEmploye() {
        return nameEmploye;
    }

    public void setNameEmploye(String nameEmploye) {
        this.nameEmploye = nameEmploye;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getTotalWorked() {
        return totalWorked;
    }

    public void setTotalWorked(Float totalWorked) {
        this.totalWorked = totalWorked;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Float getPITV() {
        return PITV;
    }

    public void setPITV(Float PITV) {
        this.PITV = PITV;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
