package mk.ukim.finki.dailycheckinsmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.ids.DailyCheckInId;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;

import java.time.OffsetDateTime;

@Data
public class UserDailyCheckInDTO {
    private final Boolean claimed;
    private final OffsetDateTime dateCreated;
    private final OffsetDateTime dateModified;
    private final UserId userId;
    private final DailyCheckInId dailyCheckInId;
}
