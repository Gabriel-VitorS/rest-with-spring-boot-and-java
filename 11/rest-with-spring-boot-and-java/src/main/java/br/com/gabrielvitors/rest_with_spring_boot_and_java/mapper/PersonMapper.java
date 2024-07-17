package br.com.gabrielvitors.rest_with_spring_boot_and_java.mapper;


import br.com.gabrielvitors.rest_with_spring_boot_and_java.data.vo.v1.PersonVO;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonVO personToPersonVO(Person person);

    Person personVOToPerson(PersonVO personVO);

    List<PersonVO> listPersonsToPersonVO(List<Person> persons);
}
