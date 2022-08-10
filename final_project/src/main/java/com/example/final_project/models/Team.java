package com.example.final_project.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Integer teamId;
    private String teamName;
    private Integer teamSize;
    private Double teamRate;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Person> members;

    public Team(String teamName, Integer teamSize,Double teamRate, Set<Person> members) {
        this.teamName = teamName;
        this.teamSize = teamSize;
        this.teamRate = teamRate;
        this.members = new HashSet<Person>();
        
    }
    public Team(){
        
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(Integer teamSize) {
        this.teamSize = teamSize;
    }

    public Double getTeamRate() {
        return teamRate;
    }
    public void setTeamRate(Double teamRate) {
        this.teamRate = teamRate;
    }
    
    public Set<Person> getMembers() {
        return members;
    }
    public void setMembers(Set<Person> members) {
        this.members = members;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Team other = (Team) obj;
        if (teamId == null) {
            if (other.teamId != null)
                return false;
        } else if (!teamId.equals(other.teamId))
            return false;
        return true;
    }

    

}
