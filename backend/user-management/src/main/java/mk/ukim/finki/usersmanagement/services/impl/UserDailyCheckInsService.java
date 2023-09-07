package mk.ukim.finki.usersmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.constants.DailyCheckInConstants;
import mk.ukim.finki.dailycheckinsmanagement.services.impl.DailyCheckInsService;
import mk.ukim.finki.usersmanagement.domain.dtos.UserDailyCheckInDTO;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.DailyCheckIn;
import mk.ukim.finki.usersmanagement.domain.models.UserDailyCheckIn;
import mk.ukim.finki.usersmanagement.domain.repositories.UserDailyCheckInsRepository;
import mk.ukim.finki.usersmanagement.domain.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class UserDailyCheckInsService {

    private final UserDailyCheckInsRepository userDailyCheckInsRepository;
    private final DailyCheckInsService dailyCheckInsService;

    public List<UserDailyCheckIn> bindWithUser(User user){
        List<DailyCheckIn> dailyCheckIns = dailyCheckInsService.findAll();
        List<UserDailyCheckIn> userDailyCheckIns = user.getUserDailyCheckIns();
        if(userDailyCheckIns.isEmpty()) {
            dailyCheckIns.forEach(dailyCheckIn ->
                    userDailyCheckIns.add(this.create(new UserDailyCheckIn(), user, dailyCheckIn))
            );

            return userDailyCheckIns;
        } else {
            return user.getUserDailyCheckIns();
        }
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

    public List<UserDailyCheckIn> resetDailyCheckInWeekly(User user){
        List<UserDailyCheckIn> userDailyCheckIns = userDailyCheckInsRepository.findAllByUserId(user.getId());
        UserDailyCheckIn firstDailyCheckIn = userDailyCheckIns.stream()
                .filter(userDailyCheckIn ->
                        userDailyCheckIn.getClaimed() &&
                                userDailyCheckIn.getDailyCheckIn().getLabel().equals(DailyCheckInConstants.MONDAY))
                .findFirst()
                .get();

        if(ChronoUnit.DAYS.between(firstDailyCheckIn.getDateModified().toLocalDate(), OffsetDateTime.now().toLocalDate()) == 7){
            userDailyCheckInsRepository.deleteAllById(userDailyCheckIns.stream().map(UserDailyCheckIn::getId).collect(Collectors.toList()));
            return bindWithUser(user);
        }

        return userDailyCheckIns;
    }

    public List<UserDailyCheckIn> findAllClaimedByUser(User user){
        return userDailyCheckInsRepository.findAllByUserAndClaimedIsTrue(user);
    }

    public List<UserDailyCheckIn> findAllByClaimedIsTrueOrderByIdAndUserAsc(){
        return userDailyCheckInsRepository.findAllByClaimedIsTrueOrderByIdAndUserAsc();
    }
}
