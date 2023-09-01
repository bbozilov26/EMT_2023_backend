package mk.ukim.finki.usersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.usersmanagement.domain.models.ids.RoleId;

import java.util.List;

@Data
public class RoleDTO {
    private final RoleId id;
    private final String role;
    private final String label;
    private final List<PrivilegeDTO> privilegeDTOs;
}
