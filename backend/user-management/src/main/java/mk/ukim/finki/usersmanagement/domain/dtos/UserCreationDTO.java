package mk.ukim.finki.usersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.usersmanagement.domain.models.ids.RoleId;

import java.util.List;

@Data
public class UserCreationDTO {
    private String email;
    private String password;
    private String repeatPassword;
    private PersonCreationDTO personDTO;
//    private RoleId roleId;
}
