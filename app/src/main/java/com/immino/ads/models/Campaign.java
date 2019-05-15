package com.immino.ads.models;

import java.util.Date;

public class Campaign {

    private String Id;

    private String Name;

    private String Description;

    private Date StartDate;

    private Date EndDate;

    private Company Company;

    private Object Location;

    private long Threshold;

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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public com.immino.ads.models.Company getCompany() {
        return Company;
    }

    public void setCompany(com.immino.ads.models.Company company) {
        Company = company;
    }

    public Object getLocation() {
        return Location;
    }

    public void setLocation(Object location) {
        Location = location;
    }

    public long getThreshold() {
        return Threshold;
    }

    public void setThreshold(long threshold) {
        Threshold = threshold;
    }
}
