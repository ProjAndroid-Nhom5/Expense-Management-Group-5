package com.example.expensemanagement.Store.Model;

public class ListStoreProductData {
    private String ProductID,
                ProductName,
                StoreName;

    private Integer Quantity,
                    Price;

    public ListStoreProductData(String productID, String productName, String storeName, Integer quantity, Integer price) {
        ProductID = productID;
        ProductName = productName;
        StoreName = storeName;
        Quantity = quantity;
        Price = price;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }
}
