package br.com.gabrielvitors.rest_with_spring_boot_and_java.services;

import br.com.gabrielvitors.rest_with_spring_boot_and_java.controllers.PersonController;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.data.vo.v1.PersonVO;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.data.vo.v2.v1.PersonVOV2;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.exceptions.ResourceNotFoundException;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.mapper.PersonMapper;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.repositories.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import java.util.List;

import java.util.logging.Logger;


@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    public PersonRepository repository;

    public br.com.gabrielvitors.rest_with_spring_boot_and_java.mapper.custom.PersonMapper mapper = new br.com.gabrielvitors.rest_with_spring_boot_and_java.mapper.custom.PersonMapper();

    public PersonVO findById(Integer id){

        logger.info("Finding one person");

        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!"));

        PersonVO vo = PersonMapper.INSTANCE.personToPersonVO(entity);

        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<PersonVO> findAll(){

        logger.info("Finding all persons");

        var persons = PersonMapper.INSTANCE.listPersonsToPersonVO(repository.findAll());

        persons
        .forEach(p -> p.add(
                linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()
        ));

        return persons;
    }

    public PersonVO create(PersonVO person) {

        logger.info("Creating one person");

        var entity = PersonMapper.INSTANCE.personVOToPerson(person);

        PersonVO vo = PersonMapper.INSTANCE.personToPersonVO(repository.save(entity));

        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO person) {

        logger.info("Updating one person");

        var entity = repository.findById(person.getKey()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonVO vo = PersonMapper.INSTANCE.personToPersonVO(repository.save(entity));

        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Integer id) {

        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!"));

        logger.info("Deleting one person");

        repository.delete(entity);

    }


    public PersonVOV2 createV2(PersonVOV2 person) {

        logger.info("Creating one person");

        //var entity = PersonMapper.INSTANCE.personVOToPerson(person);
        var entity = mapper.converVoToEntity(person);

        //return  PersonMapper.INSTANCE.personToPersonVO(repository.save(entity));

        return mapper.convertEntityToVo(entity);
    }
}
