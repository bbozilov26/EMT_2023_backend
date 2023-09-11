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
@Table(name = "ur_user", schema = "userroles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractEntity<UserId> {

    private String email;
    private String password;
    private OffsetDateTime dateCreated;
    private OffsetDateTime dateModified;
    private Boolean enabled;
    private Double creditBalance;
    private Double creditDebt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ur_person_id")
    private Person person;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<UserRole> userRoles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Token> tokens;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserDailyCheckIn> userDailyCheckIns;

    @JsonIgnore
    public List<Role> getRoles() {
        if (userRoles == null) {
            return new ArrayList<>();
        }

        return userRoles.stream().map(UserRole::getRole).collect(Collectors.toList());
    }

    @JsonIgnore
    public List<Privilege> getPrivileges() {
        return userRoles.stream().
                map(UserRole::getRole).
                flatMap(r -> r.getRolePrivileges().stream()).
                map(RolePrivilege::getPrivilege).
                distinct().collect(Collectors.toList());
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
