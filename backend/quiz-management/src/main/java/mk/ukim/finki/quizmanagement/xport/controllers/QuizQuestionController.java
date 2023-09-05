package mk.ukim.finki.quizmanagement.xport.controllers;

import lombok.AllArgsConstructor;
import mk.ukim.finki.quizmanagement.domain.converters.QuizQuestionConverter;
import mk.ukim.finki.quizmanagement.domain.dtos.QuizQuestionCreationDTO;
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
    private final QuizQuestionConverter quizQuestionConverter;

    @GetMapping("/all")
    public List<QuizQuestionDTO> findAll(){
        return quizQuestionConverter.toDTOList(quizQuestionService.findAll());
    }

    @GetMapping("/{id}")
    public QuizQuestionDTO findById(@PathVariable QuizQuestionId id){
        return quizQuestionConverter.toDTO(quizQuestionService.findById(id).get());
    }

    @PostMapping("/create")
    public QuizQuestionDTO create(@RequestBody QuizQuestionCreationDTO quizQuestionDTO){
        return quizQuestionConverter.toDTO(quizQuestionService.create(quizQuestionDTO));
    }

    @PutMapping("/edit/{id}")
    public QuizQuestionDTO edit(@PathVariable QuizQuestionId id, @RequestBody QuizQuestionCreationDTO quizQuestionDTO){
        return quizQuestionConverter.toDTO(quizQuestionService.edit(id, quizQuestionDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable QuizQuestionId id){
        quizQuestionService.delete(id);
    }
}
