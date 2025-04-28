package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person addPerson(Person person){
        return personRepository.save(person);
    }

    public List<Person> findAllPeople(){
        return personRepository.findAll();
    }
}
