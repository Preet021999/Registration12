package com.example.registration;

public class Customer {
    String uname,phone,address,email,pass,c_pass;

    public Customer(String uname, String phone, String address, String email, String pass, String c_pass) {
        this.uname = uname;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.pass = pass;
        this.c_pass = c_pass;
    }

    public String getUname() {
        return uname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getC_pass() {
        return c_pass;
    }
}
