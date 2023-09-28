package mk.ukim.finki.usersmanagement.domain.converters;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.dailycheckinsmanagement.domain.converters.DailyCheckInConverter;
import mk.ukim.finki.usersmanagement.domain.dtos.UserDailyCheckInDTO;
import mk.ukim.finki.usersmanagement.domain.models.UserDailyCheckIn;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDailyCheckInsConverter {

    private final DailyCheckInConverter dailyCheckInConverter;

    public UserDailyCheckInDTO toDTO(UserDailyCheckIn userDailyCheckIn){
        return new UserDailyCheckInDTO(
                userDailyCheckIn.getClaimed(),
                userDailyCheckIn.getUser().getId(),
                dailyCheckInConverter.toDTO(userDailyCheckIn.getDailyCheckIn())
        );
    }

    public List<UserDailyCheckInDTO> toDTOList(List<UserDailyCheckIn> userDailyCheckIns){
        return userDailyCheckIns.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
