package com.example.expensemanagement.Store.Model;

import java.util.Date;

public class ListStoreEmployeeData {
    private int EmployeeID;
    private String EmployeeName,
                    Email,
                    Phone,
                    Address,
                    Position;
    private Date DayOfWork;
    private int BaseSalary,
                BonusSalary;
    public ListStoreEmployeeData(){}

    public ListStoreEmployeeData(int employeeID, String employeeName, String email, String phone, String address, String position, Date dayOfWork, int baseSalary, int bonusSalary) {
        EmployeeID = employeeID;
        EmployeeName = employeeName;
        Email = email;
        Phone = phone;
        Address = address;
        Position = position;
        DayOfWork = dayOfWork;
        BaseSalary = baseSalary;
        BonusSalary = bonusSalary;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
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

    public Date getDayOfWork() {
        return DayOfWork;
    }

    public void setDayOfWork(Date dayOfWork) {
        DayOfWork = dayOfWork;
    }

    public int getBaseSalary() {
        return BaseSalary;
    }

    public void setBaseSalary(int baseSalary) {
        BaseSalary = baseSalary;
    }

    public int getBonusSalary() {
        return BonusSalary;
    }

    public void setBonusSalary(int bonusSalary) {
        BonusSalary = bonusSalary;
    }
}
