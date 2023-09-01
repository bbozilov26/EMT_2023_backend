package mk.ukim.finki.dailycheckinsmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dailycheckinsmanagement.domain.dtos.UserDailyCheckInDTO;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.DailyCheckIn;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.UserDailyCheckIn;
import mk.ukim.finki.dailycheckinsmanagement.domain.repositories.UserDailyCheckInsRepository;
import mk.ukim.finki.usersmanagement.domain.exceptions.UserNotFoundException;
import mk.ukim.finki.usersmanagement.domain.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserDailyCheckInsService {

    private final UserDailyCheckInsRepository userDailyCheckInsRepository;
    private final DailyCheckInsService dailyCheckInsService;

    public List<UserDailyCheckIn> bindWithUser(User user){
        List<DailyCheckIn> dailyCheckIns = dailyCheckInsService.findAll();
        List<UserDailyCheckIn> userDailyCheckIns = new ArrayList<>();

        dailyCheckIns.forEach(dailyCheckIn ->
                userDailyCheckIns.add(this.create(new UserDailyCheckIn(), user, dailyCheckIn))
        );

        return userDailyCheckIns;
    }

    public UserDailyCheckIn create(UserDailyCheckIn userDailyCheckIn, User user, DailyCheckIn dailyCheckIn){
        userDailyCheckIn.setUser(user);
        userDailyCheckIn.setDailyCheckIn(dailyCheckIn);
        userDailyCheckIn.setClaimed(false);
        userDailyCheckIn.setDateCreated(OffsetDateTime.now());
        userDailyCheckIn.setDateModified(OffsetDateTime.now());

        return userDailyCheckInsRepository.save(userDailyCheckIn);
    }

    public UserDailyCheckIn claimDailyCheckIn(UserDailyCheckInDTO userDailyCheckInDTO){
        UserDailyCheckIn userDailyCheckIn = userDailyCheckInsRepository.findByUserIdAndDailyCheckInId(userDailyCheckInDTO.getUserId(), userDailyCheckInDTO.getDailyCheckInId());
        userDailyCheckIn.setClaimed(true);
        userDailyCheckIn.setDateModified(OffsetDateTime.now());

        return userDailyCheckInsRepository.save(userDailyCheckIn);
    }

    public void resetDailyCheckInWeekly(){
        List<UserDailyCheckIn> userDailyCheckIns = userDailyCheckInsRepository.findAll();

        userDailyCheckIns.forEach(userDailyCheckIn -> {
            userDailyCheckIn.setClaimed(false);
            userDailyCheckIn.setDateModified(OffsetDateTime.now());
            userDailyCheckInsRepository.save(userDailyCheckIn);
        });
    }

    public List<UserDailyCheckIn> findAllClaimedByUser(User user){
        return userDailyCheckInsRepository.findAllByUserAndClaimedIsTrue(user);
    }
}
