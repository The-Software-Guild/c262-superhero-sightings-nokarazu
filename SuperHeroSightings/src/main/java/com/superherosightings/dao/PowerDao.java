package com.superherosightings.dao;

import com.superherosightings.dto.Power;

public interface PowerDao {
    //Create
    public Power addPower(Power power);

    //Read
    public Power getPower(int powerId);

    //Update
    public void editPower(int powerId);

    //Delete
    public boolean deletePower(int powerId);
}
