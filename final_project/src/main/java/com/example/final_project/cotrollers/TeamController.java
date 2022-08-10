package com.example.final_project.cotrollers;

import com.example.final_project.dto.MessageDetails;
import com.example.final_project.models.Team;
import com.example.final_project.repositories.TeamRepository;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {
    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    @GetMapping("/teams")
    public Iterable<Team> getTeams(){
        return teamRepository.findAll();
    }
    
    @PostMapping("/teams")
    public ResponseEntity<MessageDetails> addTeam(@RequestBody Team team){
        team.setTeamSize(0);
        teamRepository.save(team);
     
        MessageDetails msg = new MessageDetails(" The new team was adding successfully.");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(msg);
    }
    @PutMapping("/teams")
    public ResponseEntity<MessageDetails>updateTeam(@RequestBody Team team){
        Team team2 = teamRepository.findById(team.getTeamId()).get();
        team2.setTeamName(team.getTeamName());
        team2.setTeamRate(team.getTeamRate());
        teamRepository.save(team2);
        MessageDetails msg = new MessageDetails("The team was updated successfully.");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(msg);
    }

    @DeleteMapping("/teams/{teamId}")
    public ResponseEntity<MessageDetails> removeTeam(@PathVariable Integer teamId ){
        Team team = teamRepository.findById(teamId).get();
        if(team.getTeamSize() != 0){
            MessageDetails msg = new MessageDetails("There are still have member in the teams.");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(msg);
        }else{
            teamRepository.delete(team);
            MessageDetails msg = new MessageDetails("The team was removed successfully.");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(msg);
        }
    }
}
