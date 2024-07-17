package br.com.gabrielvitors.rest_with_spring_boot_and_java.services;

import br.com.gabrielvitors.rest_with_spring_boot_and_java.data.vo.v1.PersonVO;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.data.vo.v2.v1.PersonVOV2;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.exceptions.ResourceNotFoundException;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.mapper.OrikaMapper;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.mapper.PersonMapper;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.model.Person;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.repositories.PersonRepository;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
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

    public br.com.gabrielvitors.rest_with_spring_boot_and_java.mapper.custom.PersonMapper mapper = new br.com.gabrielvitors.rest_with_spring_boot_and_java.mapper.custom.PersonMapper();

    public PersonVO findById(Integer id){

        logger.info("Finding one person");

        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!"));


        return PersonMapper.INSTANCE.personToPersonVO(entity);
    }

    public List<PersonVO> findAll(){

        logger.info("Finding all persons");

        return PersonMapper.INSTANCE.listPersonsToPersonVO(repository.findAll());
    }

    public PersonVO create(PersonVO person) {

        logger.info("Creating one person");

        var entity = PersonMapper.INSTANCE.personVOToPerson(person);

        return  PersonMapper.INSTANCE.personToPersonVO(repository.save(entity));
    }

    public PersonVO update(PersonVO person) {

        logger.info("Updating one person");

        var entity = repository.findById(person.getId()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return PersonMapper.INSTANCE.personToPersonVO(repository.save(entity));
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
