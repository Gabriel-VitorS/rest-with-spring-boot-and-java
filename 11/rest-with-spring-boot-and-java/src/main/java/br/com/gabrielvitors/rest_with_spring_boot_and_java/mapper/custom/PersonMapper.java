package br.com.gabrielvitors.rest_with_spring_boot_and_java.mapper.custom;


import br.com.gabrielvitors.rest_with_spring_boot_and_java.data.vo.v1.PersonVO;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.data.vo.v2.v1.PersonVOV2;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.List;


public class PersonMapper {

    public PersonVOV2 convertEntityToVo(Person person){
        PersonVOV2 vo = new PersonVOV2();

        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setGender(person.getGender());
        vo.setBirthDay(new Date());

        return vo;

    }

    public Person converVoToEntity(PersonVOV2 personVOV2){
        Person entity = new Person();

        entity.setId(personVOV2.getId());
        entity.setAddress(personVOV2.getAddress());
        entity.setFirstName(personVOV2.getFirstName());
        entity.setLastName(personVOV2.getLastName());
        entity.setGender(personVOV2.getGender());
        //vo.setBirthDay(new Date());

        return entity;

    }
}
