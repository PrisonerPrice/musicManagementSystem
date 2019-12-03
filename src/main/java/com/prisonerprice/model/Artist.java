package com.prisonerprice.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "artist")
public class Artist {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column(name = "start_year")
    private int startYear;

    @Column(name = "end_year")
    private int endYear;

    @Column
    private String description;

    @Id
    @Column(name = "serial_num")
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

    public Artist(Artist artist){
        this.id = artist.getId();
        this.name = artist.getName();
        this.startYear = artist.getStartYear();
        this.endYear = artist.getEndYear();
        this.description = artist.getDescription();
        this.serialNumber = artist.getSerialNumber();
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
