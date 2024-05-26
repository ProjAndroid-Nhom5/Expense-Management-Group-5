package com.example.expensemanagement.Bill.Model;

public class ListBillStoreData {
    private String id,
            idEmploye,
            date,
            nameProduct,
            nameEmploye;
    private Float productCost,total ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(String idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getNameEmploye() {
        return nameEmploye;
    }

    public void setNameEmploye(String nameEmploye) {
        this.nameEmploye = nameEmploye;
    }

    public Float getProductCost() {
        return productCost;
    }

    public void setProductCost(Float productCost) {
        this.productCost = productCost;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public ListBillStoreData(String id, String idEmploye, String date, String nameProduct, String nameEmploye, Float productCost, Float total) {
        this.id = id;
        this.idEmploye = idEmploye;
        this.date = date;
        this.nameProduct = nameProduct;
        this.nameEmploye = nameEmploye;
        this.productCost = productCost;
        this.total = total;
    }
}
