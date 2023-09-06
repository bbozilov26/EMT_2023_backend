package mk.ukim.finki.usersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.dailycheckinsmanagement.domain.dtos.DailyCheckInCreationDTO;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;

import java.time.OffsetDateTime;

@Data
public class UserDailyCheckInCreationDTO {
    private Boolean claimed;
    private DailyCheckInCreationDTO dailyCheckInCreationDTO;
}
