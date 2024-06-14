package com.example.expensemanagement.Store.Model;

public class StoreInfDetail {
    private int StoreID;
    private String StoreName,
                ManagerName,
                Address,
                Describe,
                Email,
                Phone;
    private float Impost;
    public StoreInfDetail(){}

    public StoreInfDetail(int storeID, String storeName, String managerName, String address, String describe, String email, String phone, float impost) {
        StoreID = storeID;
        StoreName = storeName;
        ManagerName = managerName;
        Address = address;
        Describe = describe;
        Email = email;
        Phone = phone;
        Impost = impost;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int storeID) {
        StoreID = storeID;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getManagerName() {
        return ManagerName;
    }

    public void setManagerName(String managerName) {
        ManagerName = managerName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDescribe() {
        return Describe;
    }

    public void setDescribe(String describe) {
        Describe = describe;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public float getImpost() {
        return Impost;
    }

    public void setImpost(float impost) {
        Impost = impost;
    }
}
