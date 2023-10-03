package mk.ukim.finki.usersmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.ids.LoginSessionId;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ur_login_session")
public class LoginSession extends AbstractEntity<LoginSessionId> {

    @Column(name = "login_date")
    private OffsetDateTime loginDate;

    @Column(name = "logout_date")
    private OffsetDateTime logoutDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ur_user_id")
    private User user;
}
