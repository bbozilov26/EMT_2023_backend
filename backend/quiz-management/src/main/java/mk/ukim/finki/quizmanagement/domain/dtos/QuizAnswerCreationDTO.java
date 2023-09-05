package mk.ukim.finki.quizmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizAnswerId;

@Data
public class QuizAnswerCreationDTO {
    private String description;
}
