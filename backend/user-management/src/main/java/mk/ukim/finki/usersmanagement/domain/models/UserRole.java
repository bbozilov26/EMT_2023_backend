package mk.ukim.finki.usersmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserRoleId;

import javax.persistence.*;

@Entity
@Table(name = "ur_user_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole extends AbstractEntity<UserRoleId> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ur_user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ur_role_id")
    private Role role;
}
