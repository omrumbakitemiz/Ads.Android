package com.immino.ads.models;

public class CampaignResource {

    private String id;

    private String name;

    private String description;

    private String startDate;

    private String endDate;

    private Company company;

    private Object location;

    private Long threshhold;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public Long getThreshhold() {
        return threshhold;
    }

    public void setThreshhold(Long threshhold) {
        this.threshhold = threshhold;
    }
}
