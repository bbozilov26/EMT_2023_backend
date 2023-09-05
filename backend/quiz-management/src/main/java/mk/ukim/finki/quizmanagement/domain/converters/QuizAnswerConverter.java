package mk.ukim.finki.quizmanagement.domain.converters;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.quizmanagement.domain.dtos.QuizAnswerDTO;
import mk.ukim.finki.quizmanagement.domain.models.QuizAnswer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuizAnswerConverter {

    public QuizAnswerDTO toDTO(QuizAnswer quizAnswer){
        return new QuizAnswerDTO(
                quizAnswer.getId(),
                quizAnswer.getDescription()
        );
    }

    public List<QuizAnswerDTO> toDTOList(List<QuizAnswer> quizAnswers){
        return quizAnswers.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
