package com.example.expensemanagement;

public class ListBillWorkshiftData {
    private String id,
            nameEmploye,
            date;
    private Float totalWorked,salary,pTV,total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Float getpTV() {
        return pTV;
    }

    public void setpTV(Float pTV) {
        this.pTV = pTV;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public ListBillWorkshiftData(String id, String nameEmploye, String date, Float totalWorked, Float salary, Float pTV, Float total) {
        this.id = id;
        this.nameEmploye = nameEmploye;
        this.date = date;
        this.totalWorked = totalWorked;
        this.salary = salary;
        this.pTV = pTV;
        this.total = total;
    }
}
