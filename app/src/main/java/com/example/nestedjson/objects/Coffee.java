package com.example.nestedjson.objects;

public class Coffee {

    private Region[] regions;
    private Country country;

    public Coffee(Region[] regions, Country country) {
        this.regions = regions;
        this.country = country;
    }

    public Region[] getRegions() {
        return regions;
    }

    public Country getCountry() {
        return country;
    }

    public String toStringRegions(){
        String s = "";
        for (int i = 0; i < regions.length; i++) {
            s += "name "+regions[i].getName()+" " +
                    "";
            s+= "id"+regions[i].getId()+"\n";
        }
        return s;
    }
}
