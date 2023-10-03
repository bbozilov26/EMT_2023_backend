package mk.ukim.finki.usersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.ids.DailyCheckInId;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserDailyCheckInId;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;

@Data
public class UserDailyCheckInClaimDTO {
    private Boolean claimed;
    private UserId userId;
    private UserDailyCheckInId userDailyCheckInId;
}
