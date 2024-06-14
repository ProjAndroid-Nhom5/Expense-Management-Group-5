package com.example.expensemanagement.Store.Model;

public class ListStoreEcommerceData {
  
    private String EcommerceName;
    private int EcommerceID,
                FixedFee,
                ServiceFee,
                PaymentFee,
                Receivables;
    private float VAT,
                FreightSurcharge;
    public ListStoreEcommerceData(){}

    public ListStoreEcommerceData(String ecommerceName, int ecommerceID, int fixedFee, int serviceFee, int paymentFee, int receivables, float vat, float freightSurcharge) {
        EcommerceName = ecommerceName;
        EcommerceID = ecommerceID;
        FixedFee = fixedFee;
        ServiceFee = serviceFee;
        PaymentFee = paymentFee;
        Receivables = receivables;
        VAT = vat;
        FreightSurcharge = freightSurcharge;
    }

    public String getEcommerceName() {
        return EcommerceName;
    }

    public void setEcommerceName(String ecommerceName) {
        EcommerceName = ecommerceName;
    }

    public int getEcommerceID() {
        return EcommerceID;
    }

    public void setEcommerceID(int ecommerceID) {
        EcommerceID = ecommerceID;
    }

    public int getFixedFee() {
        return FixedFee;
    }

    public void setFixedFee(int fixedFee) {
        FixedFee = fixedFee;
    }

    public int getServiceFee() {
        return ServiceFee;
    }

    public void setServiceFee(int serviceFee) {
        ServiceFee = serviceFee;
    }

    public int getPaymentFee() {
        return PaymentFee;
    }

    public void setPaymentFee(int paymentFee) {
        PaymentFee = paymentFee;
    }

    public int getReceivables() {
        return Receivables;
    }

    public void setReceivables(int receivables) {
        Receivables = receivables;
    }

    public float getVAT() {
        return VAT;
    }

    public void setVAT(float VAT) {
        this.VAT = VAT;
    }

    public float getFreightSurcharge() {
        return FreightSurcharge;
    }

    public void setFreightSurcharge(float freightSurcharge) {
        FreightSurcharge = freightSurcharge;
    }
}
