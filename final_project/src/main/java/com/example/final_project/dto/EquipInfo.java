package com.example.final_project.dto;

import java.math.BigDecimal;
import java.util.Date;


public class EquipInfo {
    private Integer equipId;
    private String name;
    private Date date;
    private BigDecimal price;
    private Boolean broken;
    private Integer memberId;

    public EquipInfo(Integer equipId, String name, Date date, BigDecimal price, Boolean broken, Integer memberId) {
        this.equipId = equipId;
        this.name = name;
        this.date = date;
        this.price = price;
        this.broken = broken;
        this.memberId = memberId;
    }

    public EquipInfo(){
        
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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    
    
}
