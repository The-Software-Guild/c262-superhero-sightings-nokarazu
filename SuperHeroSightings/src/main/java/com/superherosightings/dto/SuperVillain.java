package com.superherosightings.dto;

import java.util.ArrayList;
import java.util.List;

public class SuperVillain {
    private String villainName;
    private int villainId;
    private String villainDescription;
    List<Organization> organizations = new ArrayList<>();

    public String getVillainName() {
        return villainName;
    }

    public void setVillainName(String villainName) {
        this.villainName = villainName;
    }

    public int getVillainId() {
        return villainId;
    }

    public void setVillainId(int villainId) {
        this.villainId = villainId;
    }

    public String getVillainDescription() {
        return villainDescription;
    }

    public void setVillainDescription(String villainDescription) {
        this.villainDescription = villainDescription;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }
}
