package com.example.final_project.repositories;

import java.util.List;

import com.example.final_project.dto.EquipInfo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class CustomizedEquipRepositoryImpl implements CustomizedEquipRepository {
    private final JdbcTemplate jdbc;

    public CustomizedEquipRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<EquipInfo> findEquipInfoList() {
        String sql = "SELECT * FROM equip";
            
        RowMapper<EquipInfo> rowMapper = (r, i) -> {
            EquipInfo equip = new EquipInfo();
            equip.setEquipId(r.getInt("equip_id"));
            equip.setName(r.getString("name"));
            equip.setDate(r.getDate("date"));
            equip.setPrice(r.getBigDecimal("price"));
            equip.setBroken(r.getBoolean("broken"));
            equip.setMemberId(r.getInt("member_id"));

            return equip;
        };

        return jdbc.query(sql, rowMapper);
    }

    public List<EquipInfo> findEquipInfoListByPersonId(Integer id) {
        String sql = "SELECT * FROM equip WHERE member_id = ?";
            
        RowMapper<EquipInfo> rowMapper = (r, i) -> {
            EquipInfo equip = new EquipInfo();
            equip.setEquipId(r.getInt("equip_id"));
            equip.setName(r.getString("name"));
            equip.setDate(r.getDate("date"));
            equip.setPrice(r.getBigDecimal("price"));
            equip.setBroken(r.getBoolean("broken"));
            equip.setMemberId(r.getInt("member_id"));

            return equip;
        };
        return jdbc.query(sql, rowMapper,new Object[]{id});
        
    }
}
