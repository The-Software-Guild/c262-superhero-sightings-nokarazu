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
    //public List<Member> getMembersOfOrganization(int orgId);

    //Update
    public void editOrganization(Organization organization);

    //Delete
    public void deleteOrganization(int orgId);

}
