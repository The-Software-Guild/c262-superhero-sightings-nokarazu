package com.superherosightings.dao;

import com.superherosightings.dto.Organization;
import com.superherosightings.dto.SuperVillain;

import java.util.List;

public interface SuperVillainDao {
    //Create
    public SuperVillain addToVillainRoster(SuperVillainDao villain);

    //Read
    public SuperVillain getSuperFromRoster(int villainId);

    //Read
    public List<Organization> getOrgBelongingToVillain(SuperVillain villain);

    //Update
    public void editVillainRoster(int villainId);

    //Delete
    public boolean deleteVillainFromRoster(int villainId);
}
