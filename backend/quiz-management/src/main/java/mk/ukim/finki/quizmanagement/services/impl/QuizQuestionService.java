package mk.ukim.finki.quizmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.quizmanagement.domain.dtos.QuizQuestionDTO;
import mk.ukim.finki.quizmanagement.domain.models.QuizAnswer;
import mk.ukim.finki.quizmanagement.domain.models.QuizQuestion;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizQuestionId;
import mk.ukim.finki.quizmanagement.domain.repositories.QuizQuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class QuizQuestionService {

    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizAnswerService quizAnswerService;

    public List<QuizQuestion> findAll(){
        return quizQuestionRepository.findAll();
    }

    public Optional<QuizQuestion> findById(QuizQuestionId id){
        return quizQuestionRepository.findById(id);
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
        quizQuestion.setCorrectQuizAnswer(quizAnswerService.getOrCreate(quizQuestionDTO.getCorrectQuizAnswerDTO()));

        List<QuizAnswer> quizAnswers = new ArrayList<>();
        quizQuestionDTO.getQuizAnswerDTOs().forEach(quizAnswerDTO ->
                quizAnswers.add(quizAnswerService.getOrCreate(quizAnswerDTO))
        );
        quizQuestion.setQuizAnswers(quizAnswers);

        return quizQuestionRepository.save(quizQuestion);
    }

    public void delete(QuizQuestionId id){
        quizQuestionRepository.deleteById(id);
    }
}
