package com.example.expensemanagement.Admin.model;

public class User {
    private static int nextId = 1; // Biến static để tự động tăng ID
    private int id; // Sử dụng int cho ID để đơn giản hóa
    private String name;
    private String email;
    private String phone;
    private String gender;
    private String birthday;

    public User() {
        // Empty constructor needed for Firebase
    }

    public User(String name, String email, String phone, String gender, String birthday) {
        this.id = nextId++; // Gán ID và tăng nextId lên cho người dùng tiếp theo
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
