package com.example.expensemanagement.Bill.Model;

public class ListBillProductData {
    private String bill_ProductID,
            nameProduct,
            billID;
    private Float Quantity ;

    public String getBill_ProductID() {
        return bill_ProductID;
    }

    public void setBill_ProductID(String bill_ProductID) {
        this.bill_ProductID = bill_ProductID;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public Float getQuantity() {
        return Quantity;
    }

    public void setQuantity(Float quantity) {
        Quantity = quantity;
    }

    public ListBillProductData(String bill_ProductID, String nameProduct, String billID, Float quantity) {
        this.bill_ProductID = bill_ProductID;
        this.nameProduct = nameProduct;
        this.billID = billID;
        this.Quantity = quantity;
    }
}
