package com.booking.reservas.controller;

import java.util.Date;

public class BookingDto {


    private String name;
    private String email;
    private long phone;
    private Date date;
     String password;



    public BookingDto() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getPhone() {
        return phone;
    }

    public Date getDate() {
        return date;
    }

    public String getPassword() {
        return password;
    }
}
