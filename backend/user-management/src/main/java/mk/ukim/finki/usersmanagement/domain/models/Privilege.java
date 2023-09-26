package mk.ukim.finki.usersmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.ids.PrivilegeId;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ur_privilege")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Privilege extends AbstractEntity<PrivilegeId> {

    private String privilege;
    private String label;
    private String description;
}
