package com.superherosightings.dao;

import com.superherosightings.dto.Location;
import com.superherosightings.dto.Sighting;
import com.superherosightings.dto.SuperHero;

import java.util.Map;

public interface SightingsDao {
    //Create
    public Sighting addSighting(SuperHero hero, Location location, String date);

    //Read
    public Map<SuperHero, Location> getAllSightings();

    //Read
    public Map<SuperHero, Location> getAllSightingsByDate(String date);

    //Update
    public void editSighting(Sighting sighting);

    //Delete
    public void deleteSightingById(int id);

}
