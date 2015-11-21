package com.thangtv.surrounding.model;

import android.graphics.Bitmap;

import java.io.File;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by uendno on 21/11/2015.
 */
public class User implements Serializable{

    private int id;
    private String email;
    private String password;
    private String name;
    private Calendar dob;
    private String gender;
    private String phoneNumber;
    private Bitmap avatar;
    private String description;
    private String carrer;

    public User() {
    }

    public User(int id, String email, String password, String name, Calendar dob, String gender, String phoneNumber, Bitmap avatar, String description, String carrer) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.description = description;
        this.carrer = carrer;
    }

    public String getCarrer() {
        return carrer;
    }

    public void setCarrer(String carrer) {
        this.carrer = carrer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getDob() {
        return dob;
    }

    public void setDob(Calendar dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
