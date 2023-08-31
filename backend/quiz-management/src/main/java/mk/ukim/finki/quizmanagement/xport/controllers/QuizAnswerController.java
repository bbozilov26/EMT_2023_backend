package mk.ukim.finki.quizmanagement.xport.controllers;

import lombok.AllArgsConstructor;
import mk.ukim.finki.quizmanagement.services.impl.QuizAnswerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz_answers")
@CrossOrigin("*")
@AllArgsConstructor
public class QuizAnswerController {

    private final QuizAnswerService quizAnswerService;
}
