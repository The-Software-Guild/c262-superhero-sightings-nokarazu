package com.superherosightings.dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Sighting {
    private int sightingId;
    private Location sightingLocation;
    private LocalDate sightingDate;
    private SuperHero sightingHero;
    private Map<SuperHero, Location> superHeroLocationMap = new HashMap<>();

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public Location getSightingLocation() {
        return sightingLocation;
    }

    public void setSightingLocation(Location sightingLocation) {
        this.sightingLocation = sightingLocation;
    }

    public LocalDate getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(LocalDate sightingDate) {
        this.sightingDate = sightingDate;
    }

    public SuperHero getSightingHero() {
        return sightingHero;
    }

    public void setSightingHero(SuperHero sightingHero) {
        this.sightingHero = sightingHero;
    }

    public Map<SuperHero, Location> getSuperHeroLocationMap() {
        return superHeroLocationMap;
    }

    public void setSuperHeroLocationMap(Map<SuperHero, Location> superHeroLocationMap) {
        this.superHeroLocationMap = superHeroLocationMap;
    }
}
