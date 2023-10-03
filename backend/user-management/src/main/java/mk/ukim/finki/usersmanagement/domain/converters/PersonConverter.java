package mk.ukim.finki.usersmanagement.domain.converters;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.dtos.PersonDTO;
import mk.ukim.finki.usersmanagement.domain.models.Person;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class PersonConverter {

    public PersonDTO toPersonDTO(Person person){
        return new PersonDTO(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getPhoneNumber()
        );
    }
}
