package mk.ukim.finki.quizmanagement.domain.models.ids;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

public class QuizQuestionId extends DomainObjectId {
    public QuizQuestionId() {
        super(QuizQuestionId.randomId(QuizQuestionId.class).getId());
    }

    public QuizQuestionId(@NonNull String uuid) {
        super(uuid);
    }

    public static QuizQuestionId of(String uuid) {
        return new QuizQuestionId(uuid);
    }
}
