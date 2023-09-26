package mk.ukim.finki.usersmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.ids.TokenId;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "ur_token")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token extends AbstractEntity<TokenId> {

    private String token;
    private OffsetDateTime dateCreated;
    private OffsetDateTime dateExpiration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ur_user_id")
    private User user;
}
