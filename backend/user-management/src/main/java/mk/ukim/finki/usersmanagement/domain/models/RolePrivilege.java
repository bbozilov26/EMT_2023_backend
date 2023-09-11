package mk.ukim.finki.usersmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.ids.PrivilegeId;

import javax.persistence.*;

@Entity
@Table(name = "ur_role_privilege", schema = "userroles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePrivilege extends AbstractEntity<PrivilegeId> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ur_role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ur_privilege_id")
    private Privilege privilege;
}
