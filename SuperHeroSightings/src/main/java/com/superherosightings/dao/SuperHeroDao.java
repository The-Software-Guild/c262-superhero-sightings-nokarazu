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
    public List<Organization> getOrgBelongingToHero(SuperHero hero);

    //Update
    public void editHeroRoster(int heroId);

    //Delete
    public boolean deleteHeroFromRoster(int heroId);
}
