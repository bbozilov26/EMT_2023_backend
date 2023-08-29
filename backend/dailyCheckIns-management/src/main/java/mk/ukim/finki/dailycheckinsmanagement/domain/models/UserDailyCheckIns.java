package mk.ukim.finki.dailycheckinsmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.ids.UserDailyCheckInsId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.Users;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "mm_user_daily_check_ins", schema = "metamodels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDailyCheckIns extends AbstractEntity<UserDailyCheckInsId> {

    private Boolean claimed; //TODO: needs to be claimable daily
                             //      1: as a List ?
                             //      2: 7 booleans, Monday to Sunday
                             //      3: enum types: CLAIMED, MISSED, NOT_CLAIMED

    @ManyToMany
    private List<DailyCheckIns> dailyCheckIns;

    @ManyToOne
    private Users user;
}
