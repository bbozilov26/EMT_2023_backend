package mk.ukim.finki.usersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.usersmanagement.domain.models.Role;
import mk.ukim.finki.usersmanagement.domain.models.User;
import mk.ukim.finki.usersmanagement.domain.models.UserRole;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDetailsDTO {
    private String username;
    private List<Role> roles;

    public static UserDetailsDTO of(User user) {
        UserDetailsDTO details = new UserDetailsDTO();
        details.username = user.getEmail();
        details.roles = user.getUserRoles().stream().map(UserRole::getRole).collect(Collectors.toList());
        return details;
    }

}
