package mk.ukim.finki.usersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.usersmanagement.domain.models.Role;
import mk.ukim.finki.usersmanagement.domain.models.User;

@Data
public class UserDetailsDTO {
    private String username;
    private Role role;

    public static UserDetailsDTO of(User user) {
        UserDetailsDTO details = new UserDetailsDTO();
        details.username = user.getEmail();
        details.role = user.getRole();
        return details;
    }

}
