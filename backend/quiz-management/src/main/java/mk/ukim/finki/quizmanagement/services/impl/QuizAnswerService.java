package mk.ukim.finki.quizmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.quizmanagement.domain.dtos.QuizAnswerDTO;
import mk.ukim.finki.quizmanagement.domain.dtos.QuizQuestionDTO;
import mk.ukim.finki.quizmanagement.domain.models.QuizAnswer;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizAnswerId;
import mk.ukim.finki.quizmanagement.domain.repositories.QuizAnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class QuizAnswerService {

    private final QuizAnswerRepository quizAnswerRepository;

    public Optional<QuizAnswer> findById(QuizAnswerId id){
        return quizAnswerRepository.findById(id);
    }

    public QuizAnswer findByDescription(String description){
        return quizAnswerRepository.findByDescription(description);
    }

    public QuizAnswer getOrCreate(QuizAnswerDTO quizAnswerDTO){
        QuizAnswer quizAnswer = findByDescription(quizAnswerDTO.getDescription());

        if(quizAnswer != null) return quizAnswer;
        else return create(new QuizAnswer(), quizAnswerDTO);
    }

    public QuizAnswer create(QuizAnswer quizAnswer, QuizAnswerDTO quizAnswerDTO){
        return fillProperties(quizAnswer, quizAnswerDTO);
    }

    public QuizAnswer edit(QuizAnswerId id, QuizAnswerDTO quizAnswerDTO){
        return fillProperties(findById(id).get(), quizAnswerDTO);
    }

    public QuizAnswer fillProperties(QuizAnswer quizAnswer, QuizAnswerDTO quizAnswerDTO){
        quizAnswer.setDescription(quizAnswerDTO.getDescription());

        return quizAnswerRepository.save(quizAnswer);
    }
}
