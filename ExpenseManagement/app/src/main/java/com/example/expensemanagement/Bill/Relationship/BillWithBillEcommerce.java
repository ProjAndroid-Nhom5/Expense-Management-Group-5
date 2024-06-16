package com.example.expensemanagement.Bill.Relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.expensemanagement.Bill.Model.Bill;
import com.example.expensemanagement.Bill.Model.BillEcommerce;

public class BillWithBillEcommerce {
    @Embedded
    public Bill bill;

    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public BillEcommerce billEcommerce;
}
