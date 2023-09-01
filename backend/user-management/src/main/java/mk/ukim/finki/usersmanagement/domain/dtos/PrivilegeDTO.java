package mk.ukim.finki.usersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.usersmanagement.domain.models.ids.PrivilegeId;

@Data
public class PrivilegeDTO {
    private final PrivilegeId id;
    private final String privilege;
    private final String label;
    private final String description;
}
