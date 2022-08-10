package com.example.final_project.cotrollers;



import java.util.List;

import com.example.final_project.dto.EquipInfo;
import com.example.final_project.dto.MessageDetails;
import com.example.final_project.models.Equip;
import com.example.final_project.models.Person;
import com.example.final_project.repositories.EquipRepository;
import com.example.final_project.repositories.PersonRepository;


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
public class EquipController {
    private final EquipRepository equipRepository;
    private final PersonRepository personRepository;

   

    

    public EquipController(EquipRepository equipRepository, PersonRepository personRepository) {
        this.equipRepository = equipRepository;
        this.personRepository = personRepository;
    }

    @GetMapping("/equips")
    public List<EquipInfo> getAllEquipInfoList(){
        List<EquipInfo> equips = equipRepository.findEquipInfoList();
        return equips;
    }

    @GetMapping("/equips/{personId}")
    public List<EquipInfo> getAllEquipByPersonId(@PathVariable Integer personId){
        List<EquipInfo> equips = equipRepository.findEquipInfoListByPersonId(personId);
        return equips;
    }

    @PostMapping("/equips")
    public ResponseEntity<MessageDetails> addEquip(@RequestBody EquipInfo equip){
        Person person = personRepository.findById(equip.getMemberId()).get();
        Equip newEquip  = new Equip(equip.getName(),equip.getDate(),equip.getPrice(),equip.getBroken(),person);
        equipRepository.save(newEquip);
        person.getEquips().add(newEquip);
        person.setEquipSize(person.getEquipSize()+1);
        personRepository.save(person);
   
        MessageDetails msg = new MessageDetails(" The new equip was adding successfully.");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(msg);
    }

    @PutMapping("/equips")
    public ResponseEntity<MessageDetails>updateEquips(@RequestBody EquipInfo equip){
        Equip oldEquip = equipRepository.findById(equip.getEquipId()).get();
        Person person = personRepository.findById(equip.getMemberId()).get();

        if( oldEquip.getMember().getPersonId().equals(equip.getMemberId())){
            Equip newEquip  = new Equip(equip.getName(),equip.getDate(),equip.getPrice(),equip.getBroken(),person);
            newEquip.setEquipId(equip.getEquipId());
            equipRepository.save(newEquip);

        } else {
            Person oldPerson = personRepository.findById(oldEquip.getMember().getPersonId()).get();
            Equip newEquip  = new Equip(equip.getName(),equip.getDate(),equip.getPrice(),equip.getBroken(),oldPerson);
            newEquip.setEquipId(equip.getEquipId());

            person.getEquips().remove(newEquip);
            person.setEquipSize(person.getEquipSize()-1);
            personRepository.save(person);

            newEquip.setMember(person);
            equipRepository.save(newEquip);

            person.getEquips().add(newEquip);
            person.setEquipSize(person.getEquipSize()+1);
            personRepository.save(person);

            
        }

        MessageDetails msg = new MessageDetails("The equip was updated successfully.");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(msg);
    }

    @DeleteMapping("/equips/{equipId}")
    public ResponseEntity<MessageDetails> removeTeam(@PathVariable Integer equipId ){
        Equip equip = equipRepository.findById(equipId).get();
        Person member = personRepository.findById(equip.getMember().getPersonId()).get();
        member.getEquips().remove((equip));
        member.setEquipSize(member.getEquipSize()-1);
        personRepository.save(member);
        equipRepository.delete(equip);
      

        MessageDetails msg = new MessageDetails("The equip was removed successfully.");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(msg);
    }
}
//curl -i -X PUT localhost:8080/equips -H 'Content-type:application/json' -d '{equipId,"name":"aaaa", "date":"Database", "price":99.99, "broken":true,Integer,memberId}'