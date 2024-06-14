package com.example.expensemanagement.Store.Model;

public class ListStoreSupplierData {
    private int SupplierID;
    private String SupplierName;
    public ListStoreSupplierData(){}
    public ListStoreSupplierData(int supplierID, String supplierName) {
        SupplierID = supplierID;
        SupplierName = supplierName;
    }

    public int getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(int supplierID) {
        SupplierID = supplierID;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String supplierName) {
        SupplierName = supplierName;
    }
}
