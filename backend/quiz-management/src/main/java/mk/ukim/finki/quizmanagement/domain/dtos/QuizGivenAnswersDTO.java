package mk.ukim.finki.quizmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizAnswerId;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizQuestionId;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;

import java.util.List;
import java.util.Map;

@Data
public class QuizGivenAnswersDTO {
    private UserId userId;
    private QuizQuestionId questionId;
    private QuizAnswerId answerId;
}
