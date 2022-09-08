package com.superherosightings.dao;

import com.superherosightings.dto.Member;
import com.superherosightings.dto.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrganizationDaoDB implements OrganizationDao{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public OrganizationDaoDB(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    @Transactional
    public Organization addOrganization(Organization org) {
        final String INSERT_ORGANIZATION = "INSERT INTO Organization(organization_name, organization_address, organization_number)" + "VALUES(?, ?, ?)";
        jdbcTemplate.update(INSERT_ORGANIZATION, org.getOrgName(), org.getOrgAddress(), org.getOrgNumber());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        org.setOrgId(newId);
        return org;
    }

    @Override
    public Organization getOrganizationById(int orgId) {
        try {
            final String SELECT_ORG_BY_ID = "SELECT * FROM Organization WHERE organization_id = ?";
            Organization organization = jdbcTemplate.queryForObject(SELECT_ORG_BY_ID, new OrganizationMapper(), orgId);
            organization.setMembers(getMembersForOrganization(organization));
            return organization;
        }catch(DataAccessException ex){
            return null;
        }
    }
    private List<Member> getMembersForOrganization(Organization organization){
        final String SELECT_MEMBERS_FOR_ORGANIZATION = "SELECT * FROM Member" + "JOIN Organization ON Member.organization_id = Organization.organization_id WHERE Organization.organization_id = ?";
        return jdbcTemplate.query(SELECT_MEMBERS_FOR_ORGANIZATION, new MemberDaoDB.MemberMapper(), organization.getOrgId());
    }

    @Override
    public void editOrganization(Organization organization) {
        final String UPDATE_ORG = "UPDATE Organization SET organization_name = ?, organization_address = ?, organization_number = ?"
                + "WHERE organization_id = ?";
        jdbcTemplate.update(UPDATE_ORG,organization.getOrgName(), organization.getOrgAddress(), organization.getOrgNumber(), organization.getOrgId());
    }

    @Override
    @Transactional
    public void deleteOrganization(int orgId) {
        final String DELETE_FROM_MEMBERS = "DELETE FROM Member WHERE organization_id = ?";
        jdbcTemplate.update(DELETE_FROM_MEMBERS, orgId);

        final String DELETE_FROM_ORG = "DELETE FROM Organization WHERE organization_id = ?";
        jdbcTemplate.update(DELETE_FROM_ORG, orgId);

        final String DELETE_FROM_SUPERHERO_ORG = "DELETE FROM SuperHeroOrganization WHERE organization_id = ?";
        jdbcTemplate.update(DELETE_FROM_SUPERHERO_ORG, orgId);
    }
    public static final class OrganizationMapper implements RowMapper<Organization>{

        @Override
        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
            Organization organization = new Organization();
            organization.setOrgId(rs.getInt("organization_id"));
            organization.setOrgName(rs.getString("organization_name"));
            organization.setOrgAddress(rs.getString("organization_address"));
            organization.setOrgNumber(rs.getString("organization_number"));

            return organization;
        }
    }
}
