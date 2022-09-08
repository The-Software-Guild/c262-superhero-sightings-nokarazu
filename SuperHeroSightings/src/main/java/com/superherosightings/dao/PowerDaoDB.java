package com.superherosightings.dao;

import com.superherosightings.dto.Power;
import com.superherosightings.dto.SuperHero;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PowerDaoDB implements PowerDao{
    @Override
    public Power addPower(Power power) {
        
    }

    @Override
    public Power getPower(int powerId) {
        return null;
    }

    @Override
    public void editPower(int powerId) {

    }

    @Override
    public boolean deletePower(int powerId) {
        return false;
    }
    public static final class PowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int rowNum) throws SQLException {
            Power power = new Power();
            power.setPowerId(rs.getInt("power_id"));
            power.setPowerType(rs.getString("power_type"));
            power.setPowerDescription(rs.getString("power_description"));

            return power;
        }
    }
}
