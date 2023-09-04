package mk.ukim.finki.usersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.usersmanagement.domain.models.ids.RoleId;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class UserCreationDTO {
    private final String email;
    private final String password;
    private final PersonCreationDTO personDTO;
    private final List<RoleId> roleIds;
}
