package com.example.firstapplicationmiage;
import java.io.Serializable;  // To make the class Serializable

public class Person implements Serializable {

    private String name;
    private String firstName;
    private String gender;
    private String birthDate;
    private String email;
    private String address;
    private String phoneNumber;

    public Person(String name, String firstName, String gender, String birthDate, String email, String address, String phoneNumber) {
        this.name = name;
        this.firstName = firstName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    };

    public String getFirstName(){
      return firstName;
    };

    public String getGender() {
      return gender;
    };

    public String getBirthDate() {
        return birthDate;
    };

    public String getEmail() {
      return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }





}
