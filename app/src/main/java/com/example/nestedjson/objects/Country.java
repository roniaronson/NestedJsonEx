package com.example.nestedjson.objects;

public class Country {

    private int id;
    private String company;

    public Country(int id, String company) {
        this.id = id;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }
}
