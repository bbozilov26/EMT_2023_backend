package mk.ukim.finki.usersmanagement.domain.dtos;

import lombok.Data;

@Data
public class PrivilegeDTO {

    private final String privilege;
    private final String label;
    private final String description;
}
