package mk.ukim.finki.usersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.usersmanagement.domain.models.ids.PersonId;

import java.time.OffsetDateTime;

@Data
public class PersonDTO {
    private final PersonId id;
    private final OffsetDateTime dateCreated;
    private final OffsetDateTime dateModified;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
}
