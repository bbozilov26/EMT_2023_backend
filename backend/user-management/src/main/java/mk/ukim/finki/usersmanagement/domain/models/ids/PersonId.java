package mk.ukim.finki.usersmanagement.domain.models.ids;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

public class PersonId extends DomainObjectId {

    public PersonId() {
        super(PersonId.randomId(PersonId.class).getId());
    }

    public PersonId(@NonNull String uuid) {
        super(uuid);
    }

    public static PersonId of(String uuid) {
        return new PersonId(uuid);
    }
}
