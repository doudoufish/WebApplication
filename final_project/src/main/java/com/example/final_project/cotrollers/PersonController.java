package com.example.final_project.cotrollers;


import java.util.List;

import com.example.final_project.dto.MessageDetails;
import com.example.final_project.dto.PersonInfo;
import com.example.final_project.models.Person;
import com.example.final_project.models.Team;
import com.example.final_project.repositories.PersonRepository;
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
public class PersonController {
    private final TeamRepository teamRepository;
    private final PersonRepository personRepository;

   

    public PersonController(TeamRepository teamRepository, PersonRepository personRepository) {
        this.teamRepository = teamRepository;
        this.personRepository = personRepository;
    }

    @GetMapping("/members")
    public List<PersonInfo> getAllPerson(){
        List<PersonInfo> persons = personRepository.findPersonInfoList();
        return persons;
    }
    
    @PostMapping("/members")
    public ResponseEntity<MessageDetails> addMember(@RequestBody PersonInfo member){
        Team team = teamRepository.findById(member.getTeamId()).get();
        Person newMember = new Person(member.getPersonName(), member.getEmailAddress(),0,team);
        personRepository.save(newMember);
        team.getMembers().add(newMember);
        team.setTeamSize(team.getTeamSize()+1);
        teamRepository.save(team);
     
        MessageDetails msg = new MessageDetails(" The new member was adding successfully.");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(msg);
    }

    @PutMapping("/members")
    public ResponseEntity<MessageDetails>updateMember(@RequestBody PersonInfo newMember){
        Person oldMember = personRepository.findById(newMember.getPersonId()).get();
        Team team = teamRepository.findById(newMember.getTeamId()).get();

        // Check if the publisher id has been changed
        if( oldMember.getTeam().getTeamId().equals(newMember.getTeamId())){
            
            Person member = new Person(newMember.getPersonName(), newMember.getEmailAddress(),newMember.getEquipSize(),team);
            member.setPersonId(newMember.getPersonId());
            personRepository.save(member);
            
        } else {
            Team oldTeam = teamRepository.findById(oldMember.getTeam().getTeamId()).get();
            Person member = new Person(newMember.getPersonName(), newMember.getEmailAddress(),newMember.getEquipSize(),oldTeam);
            member.setPersonId(newMember.getPersonId());

            oldTeam.getMembers().remove(member);
            oldTeam.setTeamSize(oldTeam.getTeamSize()-1);
            teamRepository.save(oldTeam);

            member.setTeam(team);
            personRepository.save(member);

            team.getMembers().add(member);
            team.setTeamSize(team.getTeamSize()+1);
            teamRepository.save(team);

            
        }

        MessageDetails msg = new MessageDetails("The book was updated successfully.");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(msg);
    }

    @DeleteMapping("/members/{memberId}")
    public ResponseEntity<MessageDetails> removeTeam(@PathVariable Integer memberId ){
        Person member = personRepository.findById(memberId).get();
        Team team = teamRepository.findById(member.getTeam().getTeamId()).get();
        if (member.getEquipSize() != 0){
            MessageDetails msg = new MessageDetails("The member still have equip.");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(msg);

        }else{
            team.getMembers().remove(member);
        team.setTeamSize(team.getTeamSize()-1);
        teamRepository.save(team);
        personRepository.delete(member);

        MessageDetails msg = new MessageDetails("The team was removed successfully.");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(msg);
        }
        
    }
}
