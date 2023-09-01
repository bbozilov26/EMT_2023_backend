package mk.ukim.finki.usersmanagement.domain.converters;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.dtos.UserDTO;
import mk.ukim.finki.usersmanagement.domain.models.User;
import org.springframework.data.util.NullableUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserConverter {
    private final PersonConverter personConverter;
    private final RoleConverter roleConverter;

    public UserDTO toUserDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getDateCreated(),
                user.getDateModified(),
                user.getEnabled(),
                user.getCreditBalance(),
                personConverter.toPersonDTO(user.getPerson()),
                roleConverter.toRoleDTOList(user.getUserRoles())
        );
    }

    public List<UserDTO> toUserDTOList(List<User> users){
        return users.stream()
                .map(this::toUserDTO)
                .collect(Collectors.toList());
    }
}
