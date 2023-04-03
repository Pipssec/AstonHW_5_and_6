package com.example.astonhw_5.model;


public class ContactPhone {
    private String number;
    private String firstName;
    private String lastName;
    private String id;

    public ContactPhone(String number, String firstName, String lastName, String id) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
