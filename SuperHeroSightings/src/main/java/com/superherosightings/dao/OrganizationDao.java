package com.superherosightings.dao;

import com.superherosightings.dto.Member;
import com.superherosightings.dto.Organization;

import java.util.List;

public interface OrganizationDao {
    //Create
    public Organization addOrganization(Organization org);

    //Read
    public Organization getOrganizationById(int orgId);

    //Read
    public List<Member> getMemberOfOrganization(int orgId);

    //Update
    public void editOrganization(int orgId);

    //Delete
    public boolean deleteOrganization(int orgId);

}
