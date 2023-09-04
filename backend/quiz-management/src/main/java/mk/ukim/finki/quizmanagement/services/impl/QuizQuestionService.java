package mk.ukim.finki.quizmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.quizmanagement.domain.models.exceptions.QuizQuestionNotFoundException;
import mk.ukim.finki.quizmanagement.domain.dtos.QuizAnswerDTO;
import mk.ukim.finki.quizmanagement.domain.dtos.QuizGivenAnswersDTO;
import mk.ukim.finki.quizmanagement.domain.dtos.QuizQuestionDTO;
import mk.ukim.finki.quizmanagement.domain.models.QuizQuestion;
import mk.ukim.finki.quizmanagement.domain.models.QuizQuestionAnswer;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizQuestionId;
import mk.ukim.finki.quizmanagement.domain.repositories.QuizQuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class QuizQuestionService {

    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizQuestionAnswerService quizQuestionAnswerService;

    public List<QuizQuestion> findAll(){
        return quizQuestionRepository.findAll();
    }

    public Optional<QuizQuestion> findById(QuizQuestionId id){
        return Optional.ofNullable(quizQuestionRepository.findById(id).orElseThrow(QuizQuestionNotFoundException::new));
    }

    public QuizQuestion create(QuizQuestionDTO quizQuestionDTO){
        return fillProperties(new QuizQuestion(), quizQuestionDTO);
    }

    public QuizQuestion edit(QuizQuestionId id, QuizQuestionDTO quizQuestionDTO){
        return fillProperties(findById(id).get(), quizQuestionDTO);
    }

    private QuizQuestion fillProperties(QuizQuestion quizQuestion, QuizQuestionDTO quizQuestionDTO){
        quizQuestion.setQuestion(quizQuestionDTO.getQuestion());
        quizQuestion.setReward(quizQuestionDTO.getReward());
        quizQuestion.setTopic(quizQuestionDTO.getTopic());
        quizQuestion.setDifficulty(quizQuestionDTO.getDifficulty());

        quizQuestionRepository.save(quizQuestion);

        quizQuestion.setCorrectQuizAnswer(quizQuestionAnswerService.getOrCreate(quizQuestion, quizQuestionDTO.getCorrectQuizAnswerDTO()));

        List<QuizQuestionAnswer> quizQuestionAnswers = new ArrayList<>();
        quizQuestionDTO.getQuizAnswerDTOs().forEach(quizAnswerDTO ->
                quizQuestionAnswers.add(quizQuestionAnswerService.getOrCreate(quizQuestion, quizAnswerDTO))
        );
        quizQuestion.setQuizQuestionAnswers(quizQuestionAnswers);

        return quizQuestionRepository.save(quizQuestion);
    }

    public void delete(QuizQuestionId id){
        quizQuestionRepository.deleteById(id);
    }

    public Double submitQuiz(QuizGivenAnswersDTO quizGivenAnswersDTO){
        Double quizReward = 0.0;

        for (Map.Entry<QuizQuestionDTO, QuizAnswerDTO> entry : quizGivenAnswersDTO.getQuizMap().entrySet()){
            QuizQuestionDTO quizQuestionDTO = entry.getKey();
            QuizQuestion quizQuestion = quizQuestionRepository.findByQuestion(quizQuestionDTO.getQuestion());

            QuizAnswerDTO quizAnswerDTO = entry.getValue();
            QuizQuestionAnswer quizQuestionAnswer = quizQuestionAnswerService.getOrCreate(quizQuestion, quizAnswerDTO);

            if(quizQuestion.getCorrectQuizAnswer().equals(quizQuestionAnswer)){
                quizReward += quizQuestion.getReward();
            }
        }

        return quizReward;
    }
}
