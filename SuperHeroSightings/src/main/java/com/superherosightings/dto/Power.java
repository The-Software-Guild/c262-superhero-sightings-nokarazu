package com.superherosightings.dto;

public class Power {
    private int powerId;
    private String powerType;
    private String powerDescription;

    public int getPowerId() {
        return powerId;
    }

    public void setPowerId(int powerId) {
        this.powerId = powerId;
    }

    public String getPowerType() {
        return powerType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }

    public String getPowerDescription() {
        return powerDescription;
    }

    public void setPowerDescription(String powerDescription) {
        this.powerDescription = powerDescription;
    }
}
