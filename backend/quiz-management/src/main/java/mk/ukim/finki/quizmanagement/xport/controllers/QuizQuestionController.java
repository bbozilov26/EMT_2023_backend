package mk.ukim.finki.quizmanagement.xport.controllers;

import lombok.AllArgsConstructor;
import mk.ukim.finki.quizmanagement.domain.dtos.QuizQuestionDTO;
import mk.ukim.finki.quizmanagement.domain.models.QuizQuestion;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizQuestionId;
import mk.ukim.finki.quizmanagement.services.impl.QuizQuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quiz_questions")
@CrossOrigin("*")
@AllArgsConstructor
public class QuizQuestionController {

    private final QuizQuestionService quizQuestionService;

    @GetMapping("/all")
    public List<QuizQuestion> findAll(){
        return quizQuestionService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<QuizQuestion> findById(@PathVariable QuizQuestionId id){
        return quizQuestionService.findById(id);
    }

    @PostMapping("/create")
    public QuizQuestion create(@RequestBody QuizQuestionDTO quizQuestionDTO){
        return quizQuestionService.create(quizQuestionDTO);
    }

    @PutMapping("/edit/{id}")
    public QuizQuestion edit(@PathVariable QuizQuestionId id, @RequestBody QuizQuestionDTO quizQuestionDTO){
        return quizQuestionService.edit(id, quizQuestionDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable QuizQuestionId id){
        quizQuestionService.delete(id);
    }
}
