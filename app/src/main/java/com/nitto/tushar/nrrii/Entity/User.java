package com.nitto.tushar.nrrii.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "User")
public class User {

    @PrimaryKey(autoGenerate = true)
    private long userId;

    @ColumnInfo(name = "u_name")
    private String userName;

//    @ColumnInfo(name = "u_password")
//    private String userPassword;

    @ColumnInfo(name = "u_mobile_number")
    private String userMobileNumber;

    @ColumnInfo(name = "u_email_address")
    private String userEmailAddress;

    @ColumnInfo(name = "u_address")
    private String userAddress;

    @ColumnInfo(name = "u_age")
    private String userAge;

    @ColumnInfo(name = "u_gender")
    private String userGender;

    public User(String userName, String userMobileNumber, String userEmailAddress, String userAddress, String userAge, String userGender) {
        this.userName = userName;
        this.userMobileNumber = userMobileNumber;
        this.userEmailAddress = userEmailAddress;
        this.userAddress = userAddress;
        this.userAge = userAge;
        this.userGender = userGender;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobileNumber() {
        return userMobileNumber;
    }

    public void setUserMobileNumber(String userMobileNumber) {
        this.userMobileNumber = userMobileNumber;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }
}
