package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

//import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//it's used to create endpoints that return data as the body of the response.
@RequestMapping("/trove/persons")
//is used to map web requests to specific handler methods in your controller
public class PersonController {
    @Autowired//instant update
    private PersonService personService;

    @PostMapping//to create attributes
    public Person createPerson(@RequestBody Person person) { //
        return personService.createPerson(person);
    }

    @GetMapping//to view 
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long id) {//to define the data type in the http
        java.util.Optional<Person> person = personService.getPersonById(id);
        if (person.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(person.get());
    }

    @PutMapping("/{id}")//to update
    public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") Long id,  @RequestBody Person personDetails) {
        personService.updatePerson(id, personDetails);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")//to delete
    public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok().build();
    }
}
