package com.immino.ads.models;

public class Company {

    private String Id;

    private String Name;

    private String Address;

    private Long DefaultThreshold;

    private Object Location;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Long getDefaultThreshold() {
        return DefaultThreshold;
    }

    public void setDefaultThreshold(Long defaultThreshold) {
        DefaultThreshold = defaultThreshold;
    }

    public Object getLocation() {
        return Location;
    }

    public void setLocation(Object location) {
        Location = location;
    }
}
