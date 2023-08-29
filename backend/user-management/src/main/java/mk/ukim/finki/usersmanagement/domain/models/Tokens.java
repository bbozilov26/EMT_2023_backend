package mk.ukim.finki.usersmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.ids.TokenId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "ur_tokens", schema = "userroles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tokens extends AbstractEntity<TokenId> {

    private String token;
    private OffsetDateTime dateCreated;
    private OffsetDateTime dateExpiration;

    @ManyToOne
    private Users user;
}
