package com.cisco.brm.beans;

import java.io.Serializable;

public class CustomerAddressBean implements Serializable {

    private String street;

    private String city;

    private String state;

    private Integer zipcode;

    public CustomerAddressBean() {
    }

    public CustomerAddressBean(String street, String city, String state, Integer zipcode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;   
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }
}
