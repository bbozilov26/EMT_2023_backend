package mk.ukim.finki.usersmanagement.domain.dtos;

import lombok.Data;

import java.util.List;

@Data
public class RoleDTO {

    private final String role;
    private final String label;
    private final List<PrivilegeDTO> privilegeDTOs;
}
