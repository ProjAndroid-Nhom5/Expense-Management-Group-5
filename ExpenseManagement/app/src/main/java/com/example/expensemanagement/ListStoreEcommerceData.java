package com.example.expensemanagement;

public class ListStoreEcommerceData {
    private Integer EcommerceID,
            PaymentFee,
            FixedFee,
            ServiceFee,
            Receivables;
    private String nameStore,
            EcommerceName;
    private Float FreightSurcharge,
                PersonalIncomeTaxVAT;

    public Integer getEcommerceID() {
        return EcommerceID;
    }

    public void setEcommerceID(Integer ecommerceID) {
        EcommerceID = ecommerceID;
    }

    public Integer getPaymentFee() {
        return PaymentFee;
    }

    public void setPaymentFee(Integer paymentFee) {
        PaymentFee = paymentFee;
    }

    public Integer getFixedFee() {
        return FixedFee;
    }

    public void setFixedFee(Integer fixedFee) {
        FixedFee = fixedFee;
    }

    public Integer getServiceFee() {
        return ServiceFee;
    }

    public void setServiceFee(Integer serviceFee) {
        ServiceFee = serviceFee;
    }

    public Integer getReceivables() {
        return Receivables;
    }

    public void setReceivables(Integer receivables) {
        Receivables = receivables;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getEcommerceName() {
        return EcommerceName;
    }

    public void setEcommerceName(String ecommerceName) {
        EcommerceName = ecommerceName;
    }

    public Float getFreightSurcharge() {
        return FreightSurcharge;
    }

    public void setFreightSurcharge(Float freightSurcharge) {
        FreightSurcharge = freightSurcharge;
    }

    public Float getPersonalIncomeTaxVAT() {
        return PersonalIncomeTaxVAT;
    }

    public void setPersonalIncomeTaxVAT(Float personalIncomeTaxVAT) {
        PersonalIncomeTaxVAT = personalIncomeTaxVAT;
    }

    public ListStoreEcommerceData(Integer ecommerceID, Integer paymentFee, Integer fixedFee, Integer serviceFee, Integer receivables, String nameStore, String ecommerceName, Float freightSurcharge, Float personalIncomeTaxVAT) {
        this.EcommerceID = ecommerceID;
        this.PaymentFee = paymentFee;
        this.FixedFee = fixedFee;
        this.ServiceFee = serviceFee;
        this.Receivables = receivables;
        this.nameStore = nameStore;
        this.EcommerceName = ecommerceName;
        this.FreightSurcharge = freightSurcharge;
        this.PersonalIncomeTaxVAT = personalIncomeTaxVAT;
    }
}
