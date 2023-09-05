package mk.ukim.finki.dailycheckinsmanagement.domain.dtos;

import lombok.Data;

@Data
public class DailyCheckInCreationDTO {
    private Double dailyReward;
    private String label;
    private String description;
}
