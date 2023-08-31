package mk.ukim.finki.usersmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserRoleId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ur_user_roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole extends AbstractEntity<UserRoleId> {

    @ManyToOne
    private User user;

    @ManyToOne
    private Role role;
}
