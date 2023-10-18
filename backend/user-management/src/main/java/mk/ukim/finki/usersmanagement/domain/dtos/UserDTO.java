package mk.ukim.finki.usersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class UserDTO {
    private final UserId id;
    private final String email;
    private final OffsetDateTime dateCreated;
    private final OffsetDateTime dateModified;
    private final Boolean enabled;
    private final Double creditBalance;
    private final Integer streak;
    private final PersonDTO personDTO;
    private final RoleDTO roleDTO;
//    private final List<UserDailyCheckInDTO> userDailyCheckIns;
}
