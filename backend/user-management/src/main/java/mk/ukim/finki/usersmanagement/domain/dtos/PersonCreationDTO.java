package mk.ukim.finki.usersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.usersmanagement.domain.models.ids.PersonId;

import java.time.OffsetDateTime;

@Data
public class PersonCreationDTO {
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
}
