package mk.ukim.finki.usersmanagement.domain.converters;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.dtos.RoleDTO;
import mk.ukim.finki.usersmanagement.domain.models.Role;
import mk.ukim.finki.usersmanagement.domain.models.UserRole;
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

    public List<RoleDTO> toRoleDTOList(List<UserRole> userRoleList){
        return userRoleList.stream().map(u->toRoleDTO(u.getRole())).collect(Collectors.toList());
    }
}
