package com.example.expensemanagement.Bill.Relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.expensemanagement.Bill.Model.Bill;
import com.example.expensemanagement.Bill.Model.BillFacility;

public class BillWithBillFacility {
    @Embedded
    public Bill bill;

    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public BillFacility billFacility;
}
