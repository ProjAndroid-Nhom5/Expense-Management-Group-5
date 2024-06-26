package com.example.expensemanagement.Home.Inventory;

import java.io.Serializable;

public class Date implements Serializable {
    private String name;
    public Date (){}

    public Date(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
