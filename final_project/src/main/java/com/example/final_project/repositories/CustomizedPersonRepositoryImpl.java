package com.example.final_project.repositories;

import java.util.List;



import com.example.final_project.dto.PersonInfo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class CustomizedPersonRepositoryImpl implements CustomizedPersonRepository {
    private final JdbcTemplate jdbc;

    public CustomizedPersonRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<PersonInfo> findPersonInfoList() {
        String sql = "SELECT * FROM person";
        
        RowMapper<PersonInfo> rowMapper = (r, i) -> {
            PersonInfo person = new PersonInfo();
            person.setPersonId(r.getInt("person_id"));
            person.setPersonName(r.getString("person_name"));
            person.setEmailAddress(r.getString("email_address"));
            person.setTeamId(r.getInt("team_id"));
            person.setEquipSize(r.getInt("equip_size"));
            return person;
        };

        return jdbc.query(sql, rowMapper);
    }

   
    
    


}
