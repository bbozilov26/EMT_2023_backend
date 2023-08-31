package mk.ukim.finki.quizmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.quizmanagement.domain.models.enums.Difficulty;
import mk.ukim.finki.quizmanagement.domain.models.enums.Topic;

import java.util.List;

@Data
public class QuizQuestionDTO {
    private final String question;
    private final Double reward;
    private final Topic topic;
    private final Difficulty difficulty;
    private final QuizAnswerDTO correctQuizAnswerDTO;
    private final List<QuizAnswerDTO> quizAnswerDTOs;
}
