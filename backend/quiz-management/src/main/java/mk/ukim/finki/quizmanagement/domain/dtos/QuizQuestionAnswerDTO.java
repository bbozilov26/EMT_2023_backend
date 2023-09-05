package mk.ukim.finki.quizmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizAnswerId;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizQuestionAnswerId;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizQuestionId;

@Data
public class QuizQuestionAnswerDTO {
    private final QuizQuestionAnswerId id;
    private final QuizQuestionId quizQuestionId;
    private final QuizAnswerId quizAnswerId;
}
