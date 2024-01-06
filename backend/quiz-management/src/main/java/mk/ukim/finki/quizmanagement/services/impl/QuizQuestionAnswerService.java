package mk.ukim.finki.quizmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.quizmanagement.domain.dtos.QuizAnswerCreationDTO;
import mk.ukim.finki.quizmanagement.domain.dtos.QuizAnswerDTO;
import mk.ukim.finki.quizmanagement.domain.models.QuizAnswer;
import mk.ukim.finki.quizmanagement.domain.models.QuizQuestion;
import mk.ukim.finki.quizmanagement.domain.models.QuizQuestionAnswer;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizAnswerId;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizQuestionId;
import mk.ukim.finki.quizmanagement.domain.repositories.QuizQuestionAnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class QuizQuestionAnswerService {

    private final QuizQuestionAnswerRepository quizQuestionAnswerRepository;
    private final QuizAnswerService quizAnswerService;

    public QuizQuestionAnswer getOrCreate(QuizQuestion quizQuestion, QuizAnswerCreationDTO quizAnswerDTO){
        QuizAnswer quizAnswer = quizAnswerService.getOrCreate(quizAnswerDTO);
        QuizQuestionAnswer quizQuestionAnswer = quizQuestionAnswerRepository.findByQuizQuestionAndQuizAnswer(quizQuestion, quizAnswer);

        if(quizQuestionAnswer != null) return quizQuestionAnswer;
        else return create(new QuizQuestionAnswer(), quizQuestion, quizAnswer);
    }

    public QuizQuestionAnswer create(QuizQuestionAnswer quizQuestionAnswer, QuizQuestion quizQuestion, QuizAnswer quizAnswer){
        return fillProperties(quizQuestionAnswer, quizQuestion, quizAnswer);
    }

    public QuizQuestionAnswer fillProperties(QuizQuestionAnswer quizQuestionAnswer, QuizQuestion quizQuestion, QuizAnswer quizAnswer){
        quizQuestionAnswer.setQuizQuestion(quizQuestion);
        quizQuestionAnswer.setQuizAnswer(quizAnswer);

        return quizQuestionAnswerRepository.save(quizQuestionAnswer);
    }

    public QuizQuestionAnswer findByQuestionAndAnswer(QuizQuestion question, QuizAnswerDTO answerDTO){
        QuizAnswer quizAnswer = quizAnswerService.findByDescription(answerDTO.getDescription());
        return quizQuestionAnswerRepository.findByQuizQuestionAndQuizAnswer(question, quizAnswer);
    }

    public List<QuizAnswer> getAllAnswersByQuestionId (QuizQuestionId questionId) {
        return quizQuestionAnswerRepository.findAllByQuizQuestionId(questionId)
                .stream()
                .map(QuizQuestionAnswer::getQuizAnswer)
                .collect(Collectors.toList());
    }
}
