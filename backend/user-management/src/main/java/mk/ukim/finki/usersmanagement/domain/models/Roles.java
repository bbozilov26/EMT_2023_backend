package mk.ukim.finki.usersmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.ids.RoleId;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ur_roles", schema = "userroles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles extends AbstractEntity<RoleId> {

    private String role;
    private String label;
}
