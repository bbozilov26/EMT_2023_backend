package mk.ukim.finki.dailycheckinsmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.ids.DailyCheckInId;

@Data
public class DailyCheckInDTO {
    private final DailyCheckInId id;
    private final Double dailyReward;
    private final String label;
    private final String description;
}
