package mk.ukim.finki.dailycheckinsmanagement.domain.converters;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.dailycheckinsmanagement.domain.dtos.DailyCheckInDTO;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.DailyCheckIn;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DailyCheckInConverter {

    public DailyCheckInDTO toDTO(DailyCheckIn dailyCheckIn){
        return new DailyCheckInDTO(
                dailyCheckIn.getId(),
                dailyCheckIn.getDailyReward(),
                dailyCheckIn.getLabel(),
                dailyCheckIn.getDescription()
        );
    }

    public List<DailyCheckInDTO> toDTOList(List<DailyCheckIn> dailyCheckIns){
        return dailyCheckIns.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
