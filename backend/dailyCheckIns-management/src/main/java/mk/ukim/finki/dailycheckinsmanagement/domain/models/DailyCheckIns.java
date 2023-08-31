package mk.ukim.finki.dailycheckinsmanagement.domain.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.ids.DailyCheckInId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mm_daily_check_ins", schema = "metamodels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyCheckIns extends AbstractEntity<DailyCheckInId> {

    private Double dailyReward;
    private String label;
    private String description;

    @OneToMany(mappedBy = "dailyCheckIns", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserDailyCheckIns> userDailyCheckIns;
}
