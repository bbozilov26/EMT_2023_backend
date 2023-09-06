package mk.ukim.finki.quizmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;

import java.util.List;
import java.util.Map;

@Data
public class QuizGivenAnswersDTO {
    private UserId userId;
    private Map<QuizQuestionDTO, QuizAnswerDTO> quizMap;
}
