package mk.ukim.finki.quizmanagement.domain.dtos;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class QuizGivenAnswersDTO {
    private final Map<QuizQuestionDTO, QuizAnswerDTO> quizMap;
}
