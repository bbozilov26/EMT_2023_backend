package mk.ukim.finki.usersmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.dtos.PersonDTO;
import mk.ukim.finki.usersmanagement.domain.exceptions.PersonNotFoundException;
import mk.ukim.finki.usersmanagement.domain.models.Person;
import mk.ukim.finki.usersmanagement.domain.models.ids.PersonId;
import mk.ukim.finki.usersmanagement.domain.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Person getOrCreate(PersonDTO personDTO){
        if(personDTO.getId() != null){
            return findById(personDTO.getId()).orElseThrow(PersonNotFoundException::new);
        } else return create(new Person(), personDTO);
    }

    public Person create(Person person, PersonDTO personDTO){
        return fillProperties(person, personDTO);
    }

    public Person edit(PersonId id, PersonDTO personDTO){
        return fillProperties(findById(id).get(), personDTO);
    }

    public Person fillProperties(Person person, PersonDTO personDTO){
        person.setDateCreated(personDTO.getDateCreated());
        person.setDateModified(personDTO.getDateModified());
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setPhoneNumber(personDTO.getPhoneNumber());

        return personRepository.save(person);
    }
}
