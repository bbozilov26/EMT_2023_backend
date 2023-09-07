package mk.ukim.finki.usersmanagement.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.ids.PrivilegeId;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "ur_privileges", schema = "userroles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Privilege extends AbstractEntity<PrivilegeId> {

    private String privilege;
    private String label;
    private String description;
}
