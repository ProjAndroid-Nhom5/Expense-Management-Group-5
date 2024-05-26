package com.example.expensemanagement.Store.Model;

public class ListStoreSupplierData {
    private String SupplierID,
            SupplierName,
            StoreName;

    public ListStoreSupplierData(String supplierID, String supplierName, String storeName) {
        SupplierID = supplierID;
        SupplierName = supplierName;
        StoreName = storeName;
    }

    public String getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(String supplierID) {
        SupplierID = supplierID;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String supplierName) {
        SupplierName = supplierName;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }
}
