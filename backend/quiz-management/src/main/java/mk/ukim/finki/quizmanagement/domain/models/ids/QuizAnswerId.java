package mk.ukim.finki.quizmanagement.domain.models.ids;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

public class QuizAnswerId extends DomainObjectId {
    public QuizAnswerId() {
        super(QuizAnswerId.randomId(QuizAnswerId.class).getId());
    }

    public QuizAnswerId(@NonNull String uuid) {
        super(uuid);
    }

    public static QuizAnswerId of(String uuid) {
        return new QuizAnswerId(uuid);
    }
}
