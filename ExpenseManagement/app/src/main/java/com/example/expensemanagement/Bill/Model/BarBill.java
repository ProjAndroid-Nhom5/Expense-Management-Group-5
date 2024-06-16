package com.example.expensemanagement.Bill.Model;

import java.util.Date;

public class BarBill {
    private Date date;
    private Float total;

    public BarBill(Date date, Float total) {
        this.date = date;
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
