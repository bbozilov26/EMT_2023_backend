package mk.ukim.finki.usersmanagement.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.utils.NullableUtils;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "ur_user")
@Data
@AllArgsConstructor
public class User extends AbstractEntity<UserId> {

    private String email;
    private String password;
    private OffsetDateTime dateCreated;
    private OffsetDateTime dateModified;
    private Boolean enabled;
    private Double creditBalance;
    private Double creditDebt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ur_person_id")
    private Person person;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ur_role_id")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Token> tokens;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserDailyCheckIn> userDailyCheckIns;

    public User() {
        super(UserId.randomId(UserId.class));
    }

    @JsonIgnore
    public Role getRole() {
        if (role == null) {
            return new Role();
        }

        return role;
    }

    @JsonIgnore
    public List<Privilege> getPrivileges() {
        return role
                .getRolePrivileges()
                .stream()
                .map(RolePrivilege::getPrivilege)
                .distinct()
                .collect(Collectors.toList());
    }

    @JsonIgnore
    public String getFullName() {
        return NullableUtils.getIfNotNull(person, Person::getFullName, () -> "");
    }

    @JsonIgnore
    public void setPassword(String password, PasswordEncoder encoder) {
        this.password = encoder.encode(password);
    }
}
