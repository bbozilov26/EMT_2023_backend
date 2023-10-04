package mk.ukim.finki.usersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.usersmanagement.domain.models.ids.PersonId;

import java.time.OffsetDateTime;

@Data
public class PersonCreationDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
}
