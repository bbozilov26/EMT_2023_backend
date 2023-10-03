package mk.ukim.finki.usersmanagement.domain.converters;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.dtos.RoleDTO;
import mk.ukim.finki.usersmanagement.domain.models.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleConverter {

    private final PrivilegeConverter privilegeConverter;

    public RoleDTO toRoleDTO(Role role){
        return new RoleDTO(
                role.getId(),
                role.getRole(),
                role.getLabel(),
                privilegeConverter.toPrivilegeDTOList(role.getRolePrivileges())
        );
    }
}
