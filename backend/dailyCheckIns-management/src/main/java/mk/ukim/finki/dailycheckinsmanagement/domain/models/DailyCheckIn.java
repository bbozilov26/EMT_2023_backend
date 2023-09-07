package mk.ukim.finki.dailycheckinsmanagement.domain.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.ids.DailyCheckInId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "mm_daily_check_ins", schema = "metamodels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyCheckIn extends AbstractEntity<DailyCheckInId> {

    private Double dailyReward;
    private String label;
    private String description;

}
