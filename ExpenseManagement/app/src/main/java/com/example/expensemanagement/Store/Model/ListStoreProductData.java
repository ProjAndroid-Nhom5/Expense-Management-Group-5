package com.example.expensemanagement.Store.Model;

public class ListStoreProductData {
    private String ProductName;
    private int ProductID,
                Quantity,
                Price;
    public ListStoreProductData(){}

    public ListStoreProductData(String productName, int productID, int quantity, int price) {
        ProductName = productName;
        ProductID = productID;
        Quantity = quantity;
        Price = price;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}
