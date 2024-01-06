package mk.ukim.finki.quizmanagement.domain.models.ids;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

public class QuizQuestionAnswerId extends DomainObjectId {
    public QuizQuestionAnswerId() {
        super(QuizQuestionAnswerId.randomId(QuizQuestionAnswerId.class).getId());
    }

    public QuizQuestionAnswerId(@NonNull String uuid) {
        super(uuid);
    }

    public static QuizQuestionAnswerId of(String uuid) {
        return new QuizQuestionAnswerId(uuid);
    }
}
