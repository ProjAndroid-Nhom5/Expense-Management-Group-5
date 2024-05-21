package com.example.expensemanagement;

import java.util.Date;

public class ListStoreEmployeeData {
    private String EmployeeID;
    private String EmployeeName;
    private String StoreName;
    private String Email;
    private String Phone;
    private String Address;
    private String Position;
    private String DayOfWork;
    private Integer BaseSalary;
    private Integer BonusSalary;

    public ListStoreEmployeeData(String employeeID, String employeeName, String storeName, String email, String phone, String address, String position, String dayOfWork, Integer baseSalary, Integer bonusSalary) {
        EmployeeID = employeeID;
        EmployeeName = employeeName;
        StoreName = storeName;
        Email = email;
        Phone = phone;
        Address = address;
        Position = position;
        DayOfWork = dayOfWork;
        BaseSalary = baseSalary;
        BonusSalary = bonusSalary;
    }

    // Getters and setters for all fields
    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getDayOfWork() {
        return DayOfWork;
    }

    public void setDayOfWork(String dayOfWork) {
        DayOfWork = dayOfWork;
    }

    public Integer getBaseSalary() {
        return BaseSalary;
    }

    public void setBaseSalary(Integer baseSalary) {
        BaseSalary = baseSalary;
    }

    public Integer getBonusSalary() {
        return BonusSalary;
    }

    public void setBonusSalary(Integer bonusSalary) {
        BonusSalary = bonusSalary;
    }
}
