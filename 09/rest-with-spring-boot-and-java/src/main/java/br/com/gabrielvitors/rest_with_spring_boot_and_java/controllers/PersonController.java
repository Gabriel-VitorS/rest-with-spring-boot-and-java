package br.com.gabrielvitors.rest_with_spring_boot_and_java.controllers;

import br.com.gabrielvitors.rest_with_spring_boot_and_java.data.vo.v1.PersonVO;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.model.Person;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.services.PersonServices;
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
    public PersonVO findById(
            @PathVariable(value = "id") Integer id){

        return services.findById(id);
    }

    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO create(
            @RequestBody PersonVO PersonVO){

        return services.create(PersonVO);
    }

    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO update(
            @RequestBody PersonVO PersonVO){

        return services.update(PersonVO);
    }


    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVO> findAll(){

        return services.findAll();
    }

}
