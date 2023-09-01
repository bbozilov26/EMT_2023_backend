package mk.ukim.finki.dailycheckinsmanagement.domain.dtos;

import lombok.Data;

@Data
public class DailyCheckInDTO {
    private final Double dailyReward;
    private final String label;
    private final String description;
}
