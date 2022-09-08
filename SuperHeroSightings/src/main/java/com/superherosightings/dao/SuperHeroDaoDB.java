package com.superherosightings.dao;

import com.superherosightings.dto.Location;
import com.superherosightings.dto.Organization;
import com.superherosightings.dto.Power;
import com.superherosightings.dto.SuperHero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SuperHeroDaoDB implements SuperHeroDao{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public SuperHeroDaoDB(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public SuperHero addToHeroRoster(SuperHero hero) {
        final String INSERT_HERO = "INSERT INTO SuperHero(hero_name, hero_description)" + "VALUES(?,?)";
        jdbcTemplate.update(INSERT_HERO, hero.getHeroName(), hero.getHeroDescription());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hero.setHeroId(newId);

        insertHeroOrganization(hero);
        insertHeroPower(hero);
        return hero;
    }

    //Insert into intermediary table
    private void insertHeroOrganization(SuperHero hero){
        final String INSERT_HERO_ORGANIZATION = "INSERT INTO SuperHeroOrganization(superhero_id, organization_id)" + "VALUES(?,?)";
        for (Organization org : hero.getOrganizations()){
            jdbcTemplate.update(INSERT_HERO_ORGANIZATION, hero.getHeroId(), org.getOrgId());
        }
    }

    private void insertHeroPower(SuperHero hero){
        final String INSERT_HERO_POWER = "INSERT INTO SuperHeroPowers(superhero_id, power_id) VALUES(?,?)";
        for (Power power : hero.getPowers()){
            jdbcTemplate.update(INSERT_HERO_POWER, hero.getHeroId(), power.getPowerId());
        }
    }

    //Obtain list of organizations associated with hero
    private List<Organization> getOrganizationForHero(SuperHero hero){
        final String SELECT_ORGANIZATION_FOR_HERO = "SELECT * FROM" +
                "Organization JOIN SuperHeroOrganization ON Organization.organization_id = SuperHeroOrganization.organization_id WHERE SuperHeroOrganization.superhero_id = ?";
        return jdbcTemplate.query(SELECT_ORGANIZATION_FOR_HERO, new OrganizationDaoDB.OrganizationMapper(), hero.getHeroId());
    }

    private List<Power> getPowersForHero(SuperHero hero){
        final String SELECT_POWER_FOR_HERO = "SELECT * FROM POWER" + "JOIN SuperHeroPowers ON Power.power_id = SuperHeroPowers.power_id WHERE SuperHeroPowers.superhero_id = ?";
        return jdbcTemplate.query(SELECT_POWER_FOR_HERO, new PowerDaoDB.PowerMapper(), hero.getHeroId());
    }

    private List<Location> getLocationsForHero(SuperHero hero){
        final String SELECT_LOCATION_FOR_HERO = "SELECT * FROM Location" + "JOIN SuperHeroLocation ON Location.location_id = SuperHeroLocation.location_id WHERE SuperHeroLocation.superhero_id = ?";
        return jdbcTemplate.query(SELECT_LOCATION_FOR_HERO, new LocationDaoDB.LocationMapper(), hero.getHeroId());
    }

    //Get hero by id and set the list of orgs they belong to.
    @Override
    public SuperHero getSuperFromRoster(int heroId) {
        try{
            final String SELECT_SUPERHERO_FROM_ROSTER = "SELECT * FROM SuperHero WHERE SuperHero.superhero_id = ?";
            SuperHero hero = jdbcTemplate.queryForObject(SELECT_SUPERHERO_FROM_ROSTER, new HeroMapper(), heroId);
            hero.setOrganizations(getOrganizationForHero(hero));
            hero.setPowers(getPowersForHero(hero));
            hero.setLocations(getLocationsForHero(hero));
            return hero;
        } catch(DataAccessException ex) {
            return null;
        }
    }
    private void addOrgAndPowerAndLocationtoHero(List<SuperHero> heroes){
        for (SuperHero hero: heroes){
            hero.setOrganizations(getOrganizationForHero(hero));
            hero.setPowers(getPowersForHero(hero));
            hero.setLocations(getLocationsForHero(hero));
        }
    }
    @Override
    public List<SuperHero> getAllHeroes(){
        final String SELECT_ALL_HEROES = "SELECT * FROM SuperHero";
        List<SuperHero> heroes = jdbcTemplate.query(SELECT_ALL_HEROES, new HeroMapper());
        addOrgAndPowerAndLocationtoHero(heroes);

        return heroes;
    }

    @Override
    @Transactional
    public void editHeroRoster(SuperHero hero) {
        final String UPDATE_HERO = "UPDATE SuperHero" + "SET hero_name = ?, hero_description = ? WHERE superhero_id = ?";
        jdbcTemplate.update(UPDATE_HERO, hero.getHeroName(), hero.getHeroDescription(), hero.getHeroId());

        final String DELETE_HERO_ORGANIZATION = "DELETE FROM SuperHeroOrganization" + "WHERE superhero_id = ?";
        jdbcTemplate.update(DELETE_HERO_ORGANIZATION, hero.getHeroId());
        insertHeroOrganization(hero);

        final String DELETE_HERO_POWER = "DELETE FROM SuperHeroPowers" + "WHERE superhero_id = ?";
        jdbcTemplate.update(DELETE_HERO_POWER);
        insertHeroPower(hero);
    }

    @Override
    public void deleteHeroFromRoster(int heroId) {
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM SuperHeroOrganization" + "WHERE superhero_id = ?";
        jdbcTemplate.update(DELETE_HERO_ORGANIZATION, heroId);

        final String DELETE_HERO_POWER = "DELETE FROM SuperHeroPowers" + "WHERE superhero_id = ?";
        jdbcTemplate.update(DELETE_HERO_POWER);

        final String DELETE_HERO_LOCATION = "DELETE FROM SuperHeroLocation" + "WHERE superhero_id = ?";
        jdbcTemplate.update(DELETE_HERO_LOCATION, heroId);

        final String DELETE_HERO = "DELETE FROM SuperHero" + "WHERE superhero_id = ?";
        jdbcTemplate.update(DELETE_HERO, heroId);
    }

    public static final class HeroMapper implements RowMapper<SuperHero> {

        @Override
        public SuperHero mapRow(ResultSet rs, int rowNum) throws SQLException {
            SuperHero hero = new SuperHero();
            hero.setHeroId(rs.getInt("superhero_id"));
            hero.setHeroName(rs.getString("hero_name"));
            hero.setHeroDescription(rs.getString("hero_description"));

            return hero;
        }
    }
}
