package mk.ukim.finki.dailycheckinsmanagement.domain.models.ids;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

public class DailyCheckInId extends DomainObjectId {

    public DailyCheckInId() {
        super(DailyCheckInId.randomId(DailyCheckInId.class).getId());
    }

    public DailyCheckInId(@NonNull String uuid) {
        super(uuid);
    }

    public static DailyCheckInId of(String uuid) {
        return new DailyCheckInId(uuid);
    }
}
