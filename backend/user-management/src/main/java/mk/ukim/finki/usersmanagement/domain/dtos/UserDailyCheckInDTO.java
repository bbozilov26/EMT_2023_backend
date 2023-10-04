package mk.ukim.finki.usersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.dailycheckinsmanagement.domain.dtos.DailyCheckInDTO;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.ids.DailyCheckInId;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserDailyCheckInId;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;

import java.time.OffsetDateTime;

@Data
public class UserDailyCheckInDTO {
    private final UserDailyCheckInId id;
    private final Boolean claimed;
    private final OffsetDateTime dateModified;
    private final UserId userId;
    private final DailyCheckInDTO dailyCheckInDTO;
}
