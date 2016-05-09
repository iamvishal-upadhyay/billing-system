package com.cisco.brm.beans;

import java.io.Serializable;

public class CustomerBean implements Serializable {

    private String id;

    private String name;

    private CustomerAddressBean address;

    public CustomerBean() {
    }

    public CustomerBean(String id, String name, CustomerAddressBean address) {
        this.id = id;
        this.name = name;
        this.address = address;    
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerAddressBean getAddress() {
        return this.address;
    }

    public void setAddress(CustomerAddressBean address) {
        this.address = address;
    }
}
