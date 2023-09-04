package mk.ukim.finki.usersmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.dtos.PersonCreationDTO;
import mk.ukim.finki.usersmanagement.domain.dtos.PersonDTO;
import mk.ukim.finki.usersmanagement.domain.exceptions.PersonNotFoundException;
import mk.ukim.finki.usersmanagement.domain.models.Person;
import mk.ukim.finki.usersmanagement.domain.models.ids.PersonId;
import mk.ukim.finki.usersmanagement.domain.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Optional<Person> findById(PersonId id){
        return personRepository.findById(id);
    }

    public Person createOrUpdate(Person person, PersonCreationDTO personDTO){
        if(personDTO == null){
            return person;
        }

        if(person == null){
            person = new Person();
            person.setDateCreated(OffsetDateTime.now());
        }

        return fillProperties(person, personDTO);
    }

    public Person fillProperties(Person person, PersonCreationDTO personDTO){
        person.setDateModified(OffsetDateTime.now());
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setPhoneNumber(personDTO.getPhoneNumber());

        return personRepository.save(person);
    }
}
