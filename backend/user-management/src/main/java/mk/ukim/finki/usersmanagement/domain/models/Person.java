package mk.ukim.finki.usersmanagement.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.ids.PersonId;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "ur_person")
@Data
@AllArgsConstructor
public class Person extends AbstractEntity<PersonId> {

    private OffsetDateTime dateCreated;
    private OffsetDateTime dateModified;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<User> users;

    public Person() {
        super(PersonId.randomId(PersonId.class));
    }

    @JsonIgnore
    public String getFullName() {
        StringBuilder sb = new StringBuilder();

        if (firstName != null) {
            sb.append(firstName);

            if (lastName != null) {
                sb.append(" ");
            }
        }

        if (lastName != null) {
            sb.append(lastName);
        }

        return sb.toString();
    }
}
