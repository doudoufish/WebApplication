package com.example.final_project.repositories;

import com.example.final_project.models.Equip;

import org.springframework.data.repository.CrudRepository;

public interface EquipRepository extends CrudRepository<Equip,Integer>,CustomizedEquipRepository{
    
}
