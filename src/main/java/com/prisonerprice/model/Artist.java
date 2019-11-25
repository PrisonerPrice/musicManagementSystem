package com.prisonerprice.model;

public class Artist {
    private int id;
    private String name;
    private int start_year;
    private int end_year;
    private String description;

    public Artist(String name, int start_year, int end_year, String description) {
        this.name = name;
        this.start_year = start_year;
        this.end_year = end_year;
        this.description = description;
    }

    public Artist(){
        this.id = 0;
        this.name = "NULL";
        this.start_year = 0;
        this.end_year = 0;
        this.description = "NULL";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStart_year() {
        return start_year;
    }

    public int getEnd_year() {
        return end_year;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStart_year(int start_year) {
        this.start_year = start_year;
    }

    public void setEnd_year(int end_year) {
        this.end_year = end_year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", start_year=" + start_year +
                ", end_year=" + end_year +
                ", description='" + description + '\'' +
                '}';
    }
}
