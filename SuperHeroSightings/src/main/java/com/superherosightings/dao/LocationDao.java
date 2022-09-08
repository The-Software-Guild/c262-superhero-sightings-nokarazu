package com.superherosightings.dao;

import com.superherosightings.dto.Location;
import com.superherosightings.dto.SuperHero;
import com.superherosightings.dto.SuperVillain;

import java.util.List;
import java.util.Map;

public interface LocationDao {
    //Create
    public Location addLocation(Location location);

    //Read
    public Location getLocation(int locationId);

    //Read
    public List<Location> getAllLocation();

    public List<SuperHero> getHeroesForLocation(Location location);

    //Update
    public void updateLocation(int locationId);

    //Delete
    public void deleteLocation(int locationId);
}
