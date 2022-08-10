package com.example.final_project.repositories;

import java.util.List;

import com.example.final_project.dto.EquipInfo;

interface CustomizedEquipRepository {
        List<EquipInfo> findEquipInfoList();
        List<EquipInfo> findEquipInfoListByPersonId(Integer id);
    
    }
