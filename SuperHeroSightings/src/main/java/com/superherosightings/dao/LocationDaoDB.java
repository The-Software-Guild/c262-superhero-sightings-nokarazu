package com.superherosightings.dao;

import com.superherosightings.dto.Location;
import com.superherosightings.dto.SuperHero;
import com.superherosightings.dto.SuperVillain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class LocationDaoDB implements LocationDao{
    JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationDaoDB(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Location addLocation(Location location) {
        return null;
    }

    @Override
    public Location getLocation(int locationId) {
        return null;
    }

    @Override
    public List<Location> getAllLocation() {
        return null;
    }

    @Override
    public List<SuperHero> getHeroesForLocation(Location location) {
        return null;
    }

    @Override
    public void updateLocation(int locationId) {

    }

    @Override
    public void deleteLocation(int locationId) {

    }

    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location location = new Location();
            location.setLocationId(rs.getInt("location_id"));
            location.setLocationName(rs.getString("location_name"));
            location.setLocationDescription(rs.getString("location_description"));
            location.setAddressInformation(rs.getString("location_address_information"));
            location.setLocationLatitude(rs.getDouble("location_latitude"));
            location.setLocationLongitude(rs.getDouble("location_longitude"));

            return location;
        }
    }
}
