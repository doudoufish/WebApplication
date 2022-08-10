package com.example.final_project.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "equip")
public class Equip {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer equipId;
    private String name;
    private Date date;
    private BigDecimal price;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean broken;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "memberId",nullable = false)
    @JsonIgnore
    private Person member;

    public Equip(String name, Date date2, BigDecimal price, Boolean broken, Person member) {
        this.name = name;
        this.date = date2;
        this.price = price;
        this.broken = broken;
        this.member = member;
    }
    public Equip(){

    }
    public Integer getEquipId() {
        return equipId;
    }
    public void setEquipId(Integer equipId) {
        this.equipId = equipId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Boolean getBroken() {
        return broken;
    }
    public void setBroken(Boolean broken) {
        this.broken = broken;
    }
    public Person getMember() {
        return member;
    }
    public void setMember(Person member) {
        this.member = member;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((equipId == null) ? 0 : equipId.hashCode());
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
        Equip other = (Equip) obj;
        if (equipId == null) {
            if (other.equipId != null)
                return false;
        } else if (!equipId.equals(other.equipId))
            return false;
        return true;
    }

    
}
