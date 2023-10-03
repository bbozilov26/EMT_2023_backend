package mk.ukim.finki.usersmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.DailyCheckIn;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserDailyCheckInId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "mm_user_daily_check_in")
@Data
@AllArgsConstructor
public class UserDailyCheckIn extends AbstractEntity<UserDailyCheckInId> {

    private Boolean claimed;
    private OffsetDateTime dateCreated;
    private OffsetDateTime dateModified;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mm_daily_check_in_id")
    private DailyCheckIn dailyCheckIn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ur_user_id")
    private User user;

    public UserDailyCheckIn() {
        super(UserDailyCheckInId.randomId(UserDailyCheckInId.class));
    }
}
