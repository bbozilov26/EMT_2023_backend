package mk.ukim.finki.quizmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.quizmanagement.domain.models.enums.Difficulty;
import mk.ukim.finki.quizmanagement.domain.models.enums.Topic;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizQuestionId;

import java.util.List;

@Data
public class QuizQuestionCreationDTO {
    private String question;
    private Double reward;
    private Topic topic;
    private Difficulty difficulty;
    private QuizAnswerCreationDTO correctQuizAnswerDTO;
    private List<QuizAnswerCreationDTO> quizAnswerDTOs;
}
