package com.example.final_project.dto;

public class PersonInfo {
    private Integer personId;
    private String personName;
    private String emailAddress;
    private Integer equipSize;
    private Integer teamId;
    
    public PersonInfo(Integer personId, String personName, String emailAddress, Integer equipSize, Integer teamId) {
        this.personId = personId;
        this.personName = personName;
        this.emailAddress = emailAddress;
        this.equipSize = equipSize;
        this.teamId = teamId;
    }
    public PersonInfo(){

    }
    
    public Integer getEquipSize() {
        return equipSize;
    }
    public void setEquipSize(Integer equipSize) {
        this.equipSize = equipSize;
    }
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    
    
}
