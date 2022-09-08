package com.superherosightings.dto;

import java.util.ArrayList;
import java.util.List;

public class SuperHero {
    private String heroName;
    private int heroId;
    private String heroDescription;
    List<Organization> organizations = new ArrayList<>();

    List<Power> powers = new ArrayList<>();

    List<Location> locations = new ArrayList<>();

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public String getHeroDescription() {
        return heroDescription;
    }

    public void setHeroDescription(String heroDescription) {
        this.heroDescription = heroDescription;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<Power> getPowers(){ return powers; }

    public void setPowers(List<Power> powers){ this.powers = powers; }

    public List<Location> getLocations(){ return locations; }

    public void setLocations(List<Location> locations){ this.locations = locations; }
}
