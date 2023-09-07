package mk.ukim.finki.usersmanagement.domain.repositories;

import mk.ukim.finki.usersmanagement.domain.models.UserDailyCheckIn;
import mk.ukim.finki.dailycheckinsmanagement.domain.models.ids.DailyCheckInId;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserDailyCheckInId;
import mk.ukim.finki.usersmanagement.domain.models.User;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDailyCheckInsRepository extends JpaRepository<UserDailyCheckIn, UserDailyCheckInId> {

    @Query("select ud from UserDailyCheckIn ud where ud.user.id = :userId and ud.dailyCheckIn.id = :dailyCheckInId")
    UserDailyCheckIn findByUserIdAndDailyCheckInId(UserId userId, DailyCheckInId dailyCheckInId);

    List<UserDailyCheckIn> findAllByUserAndClaimedIsTrue(User user);

    @Query("select ud from UserDailyCheckIn ud where ud.user.id = :userId")
    List<UserDailyCheckIn> findAllByUserId(UserId userId);
}
