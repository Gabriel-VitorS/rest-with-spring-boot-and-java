package br.com.gabrielvitors.rest_with_spring_boot_and_java.controllers;

import br.com.gabrielvitors.rest_with_spring_boot_and_java.model.Person;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices services;

    private final AtomicLong counter = new AtomicLong();

    @GetMapping(value = "/{id}",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(
            @PathVariable(value = "id") Long id){

        return services.findById(id);
    }

    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person create(
            @RequestBody Person person){

        return services.create(person);
    }

    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person update(
            @RequestBody Person person){

        return services.update(person);
    }


    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll(){

        return services.findAll();
    }

}
