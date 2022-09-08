package com.superherosightings.dto;

import java.util.HashMap;
import java.util.Map;

public class Sighting {
    private int sightingId;
    private Map<SuperHero, Location> superHeroLocationMap = new HashMap<>();
}
