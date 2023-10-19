package mk.ukim.finki.usersmanagement.domain.models.ids;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

public class UserDailyCheckInId extends DomainObjectId {

    public UserDailyCheckInId() {
        super(UserDailyCheckInId.randomId(UserDailyCheckInId.class).getId());
    }

    public UserDailyCheckInId(@NonNull String uuid) {
        super(uuid);
    }

    public static UserDailyCheckInId of(String uuid) {
        return new UserDailyCheckInId(uuid);
    }
}
