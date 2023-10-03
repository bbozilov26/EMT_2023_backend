package mk.ukim.finki.usersmanagement.domain.converters;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.usersmanagement.domain.dtos.PrivilegeDTO;
import mk.ukim.finki.usersmanagement.domain.models.Privilege;
import mk.ukim.finki.usersmanagement.domain.models.RolePrivilege;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PrivilegeConverter {

    public PrivilegeDTO toPrivilegeDTO(Privilege privilege){
        return new PrivilegeDTO(
                privilege.getId(),
                privilege.getPrivilege(),
                privilege.getLabel(),
                privilege.getDescription()
        );
    }

    public List<PrivilegeDTO> toPrivilegeDTOList(List<RolePrivilege> rolePrivilegeList){
        return rolePrivilegeList.stream()
                .map(rp -> toPrivilegeDTO(rp.getPrivilege()))
                .collect(Collectors.toList());
    }
}
