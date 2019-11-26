package com.prisonerprice.model;

import java.util.UUID;

public class Artist {
    private int id;
    private String name;
    private int startYear;
    private int endYear;
    private String description;
    private String serialNumber;

    public Artist(int id, String name, int startYear, int endYear, String description, String serialNumber) {
        this.id = id;
        this.name = name;
        this.startYear = startYear;
        this.endYear = endYear;
        this.description = description;
        this.serialNumber = serialNumber;
    }

    public Artist(String name, int start_year, int end_year, String description) {
        this.name = name;
        this.startYear = start_year;
        this.endYear = end_year;
        this.description = description;
        this.serialNumber = UUID.randomUUID().toString();
    }

    public Artist(){
        this.id = 0;
        this.name = "NULL";
        this.startYear = 0;
        this.endYear = 0;
        this.description = "NULL";
        this.serialNumber = UUID.randomUUID().toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", description='" + description + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
