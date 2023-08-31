package mk.ukim.finki.usersmanagement.domain.dtos;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class UserDTO {
    private final String email;
    private final String password;
    private final OffsetDateTime dateCreated;
    private final OffsetDateTime dateModified;
    private final Boolean enabled;
    private final Double creditBalance;
    private final PersonDTO personDTO;
    private final List<RoleDTO> roleDTO;
}
