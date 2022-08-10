
package com.example.final_project.repositories;

import com.example.final_project.models.Person;


import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Integer>,CustomizedPersonRepository{

    
}
