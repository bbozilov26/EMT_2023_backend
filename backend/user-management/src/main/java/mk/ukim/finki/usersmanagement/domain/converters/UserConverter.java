package mk.ukim.finki.usersmanagement.domain.converters;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.dtos.UserDTO;
import mk.ukim.finki.usersmanagement.domain.models.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserConverter {
    private final PersonConverter personConverter;
    private final RoleConverter roleConverter;
    private final UserDailyCheckInsConverter userDailyCheckInsConverter;

    public UserDTO toUserDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getDateCreated(),
                user.getDateModified(),
                user.getEnabled(),
                user.getCreditBalance(),
                user.getStreak(),
                personConverter.toPersonDTO(user.getPerson()),
                roleConverter.toRoleDTO(user.getRole())
//                userDailyCheckInsConverter.toDTOList(user.getUserDailyCheckIns())
        );
    }

    public List<UserDTO> toUserDTOList(List<User> users){
        return users.stream()
                .map(this::toUserDTO)
                .collect(Collectors.toList());
    }
}
