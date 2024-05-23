package com.example.expensemanagement;

public class ListBillFacilityData {
    private String id,
            nameFacility,
            date,
            nameStore,
            transactionMethod;
    private Float totalPayment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameFacility() {
        return nameFacility;
    }

    public void setNameFacility(String nameFacility) {
        this.nameFacility = nameFacility;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getTransactionMethod() {
        return transactionMethod;
    }

    public void setTransactionMethod(String transactionMethod) {
        this.transactionMethod = transactionMethod;
    }

    public Float getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Float totalPayment) {
        this.totalPayment = totalPayment;
    }

    public ListBillFacilityData(String id, String nameFacility, String date, String nameStore, String transactionMethod, Float totalPayment) {
        this.id = id;
        this.nameFacility = nameFacility;
        this.date = date;
        this.nameStore = nameStore;
        this.transactionMethod = transactionMethod;
        this.totalPayment = totalPayment;
    }
}
