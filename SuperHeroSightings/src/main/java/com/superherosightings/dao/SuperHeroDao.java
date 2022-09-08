package com.superherosightings.dao;

import com.superherosightings.dto.Organization;
import com.superherosightings.dto.SuperHero;

import java.util.List;

public interface SuperHeroDao {
    //Create
    public SuperHero addToHeroRoster(SuperHero hero);

    //Read
    public SuperHero getSuperFromRoster(int heroId);

    //Read
    public List<SuperHero> getAllHeroes();

    //Update
    public void editHeroRoster(SuperHero hero);

    //Delete
    public void deleteHeroFromRoster(int heroId);
}
