package br.com.gabrielvitors.rest_with_spring_boot_and_java.services;

import br.com.gabrielvitors.rest_with_spring_boot_and_java.exceptions.ResourceNotFoundException;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.model.Person;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Logger;


@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    public PersonRepository repository;

    public Person findById(Long id){

        logger.info("Finding one person");

        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public List<Person> findAll(){

        logger.info("Finding all persons");

        return repository.findAll();
    }

    public Person create(Person person) {

        logger.info("Creating one person");


        return repository.save(person);
    }

    public Person update(Person person) {

        logger.info("Updating one person");

        var entity = repository.findById(person.getId()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id) {

        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!"));

        logger.info("Deleting one person");

        repository.delete(entity);

    }


}
