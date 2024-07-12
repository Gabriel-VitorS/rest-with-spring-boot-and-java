package br.com.gabrielvitors.rest_with_spring_boot_and_java.mapper;

import br.com.gabrielvitors.rest_with_spring_boot_and_java.data.vo.v1.PersonVO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class OrikaMapper {

    private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    public static <O, D> D parseObject(O origin, Class<D> destination){

        MapperFacade mapper = mapperFactory.getMapperFacade();

        return mapper.map(origin, destination);
    }


    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination){

        MapperFacade mapper = mapperFactory.getMapperFacade();

        List<D> destinationObjects = new ArrayList<D>();

        for (O o: origin) {
            destinationObjects.add(mapper.map(o, destination));
        }

        return destinationObjects;
    }
}
