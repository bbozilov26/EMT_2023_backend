package mk.ukim.finki.dailycheckinsmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.ids.UserDailyCheckInsId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.Users;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mm_user_daily_check_ins", schema = "metamodels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDailyCheckIns extends AbstractEntity<UserDailyCheckInsId> {

    private Boolean claimed;

    @ManyToOne
    private DailyCheckIns dailyCheckIns;

    @ManyToOne
    private Users user;
}
