package mk.ukim.finki.usersmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.ids.PrivilegeId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ur_roles_privileges", schema = "userroles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePrivilege extends AbstractEntity<PrivilegeId> {

    @ManyToOne
    private Role role;

    @ManyToOne
    private Privilege privilege;
}
