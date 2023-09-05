package mk.ukim.finki.quizmanagement.domain.converters;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.quizmanagement.domain.dtos.QuizQuestionAnswerDTO;
import mk.ukim.finki.quizmanagement.domain.dtos.QuizQuestionDTO;
import mk.ukim.finki.quizmanagement.domain.models.QuizQuestion;
import mk.ukim.finki.quizmanagement.domain.models.QuizQuestionAnswer;
import mk.ukim.finki.sharedkernel.utils.NullableUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuizQuestionConverter {

    private final QuizAnswerConverter quizAnswerConverter;

    public QuizQuestionDTO toDTO(QuizQuestion quizQuestion){
        return new QuizQuestionDTO(
                quizQuestion.getId(),
                quizQuestion.getQuestion(),
                quizQuestion.getReward(),
                quizQuestion.getTopic(),
                quizQuestion.getDifficulty(),
                NullableUtils.getIfNotNull(quizQuestion.getCorrectQuizAnswer().getQuizAnswer(), quizAnswerConverter::toDTO),
                toQuizQuestionsAnswersDTOList(quizQuestion.getQuizQuestionAnswers())
        );
    }

    public List<QuizQuestionDTO> toDTOList(List<QuizQuestion> quizQuestions){
        return quizQuestions.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<QuizQuestionAnswerDTO> toQuizQuestionsAnswersDTOList(List<QuizQuestionAnswer> quizQuestionAnswers){
        if(quizQuestionAnswers.isEmpty()) return null;

        return quizQuestionAnswers.stream()
                .map(this::toQuizQuestionsAnswersDTO)
                .collect(Collectors.toList());
    }

    public QuizQuestionAnswerDTO toQuizQuestionsAnswersDTO(QuizQuestionAnswer quizQuestionAnswer){
        return new QuizQuestionAnswerDTO(
                quizQuestionAnswer.getId(),
                quizQuestionAnswer.getQuizQuestion().getId(),
                quizQuestionAnswer.getQuizAnswer().getId()
        );
    }
}
