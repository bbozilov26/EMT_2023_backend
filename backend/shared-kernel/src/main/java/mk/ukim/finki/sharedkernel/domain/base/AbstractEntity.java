package mk.ukim.finki.sharedkernel.domain.base;

import lombok.Getter;
import lombok.NonNull;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
@Getter
public class AbstractEntity<T extends DomainObjectId> {

    @EmbeddedId
    private T id;

    protected AbstractEntity(@NonNull T id) {
        this.id = Objects.requireNonNull(id, "Id must not be null");
    }

    protected AbstractEntity(@NonNull AbstractEntity<T> source) {
        this.id = Objects.requireNonNull(source.id, "Id must not be null");
    }

    public AbstractEntity() {

    }
}
