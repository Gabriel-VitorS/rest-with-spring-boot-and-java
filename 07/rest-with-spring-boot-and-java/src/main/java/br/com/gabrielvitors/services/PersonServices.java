package br.com.gabrielvitors.services;

import br.com.gabrielvitors.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;


@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){

        logger.info("Finding one person");

        Person person = new Person();

        person.setId(counter.incrementAndGet());
        person.setFirstName("Gabriel");
        person.setLastName("Vitor");
        person.setAddress("Recife");
        person.setGender("Male");

        return person;
    }

    public List<Person> findAll(){

        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);

            persons.add(person);
        }

        logger.info("Finding all persons");

        return persons;
    }

    public Person create(Person person) {

        logger.info("Creating one person");

        return person;
    }

    public Person update(Person person) {

        logger.info("Updating one person");

        return person;
    }

    public void delete(String id) {

        logger.info("Deleting one person");

    }

    private Person mockPerson(int i) {
        Person person = new Person();

        person.setId(counter.incrementAndGet());
        person.setFirstName("First name " + i);
        person.setLastName("Last name"  + i);
        person.setAddress("Address"  + i);
        person.setGender("Gender"  + i);

        return person;
    }


}
