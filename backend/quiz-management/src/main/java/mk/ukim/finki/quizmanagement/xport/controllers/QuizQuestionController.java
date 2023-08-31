package mk.ukim.finki.quizmanagement.xport.controllers;

import lombok.AllArgsConstructor;
import mk.ukim.finki.quizmanagement.services.impl.QuizQuestionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz_questions")
@CrossOrigin("*")
@AllArgsConstructor
public class QuizQuestionController {

    private final QuizQuestionService quizQuestionService;
}
