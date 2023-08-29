package mk.ukim.finki.usersmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import mk.ukim.finki.emt.productscatalog.domain.models.RatingsAndReviews;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "ur_users", schema = "userroles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users extends AbstractEntity<UserId> {

    private String email;
    private String password;
    private OffsetDateTime dateCreated;
    private OffsetDateTime dateModified;
    private OffsetDateTime dateDeleted;
    private Boolean enabled;
    private Double creditBalance;

    @ManyToOne
    private Persons person;

    @ManyToMany
    private List<UserRoles> userRoles;

    @OneToMany
    private List<Tokens> tokens;
}
