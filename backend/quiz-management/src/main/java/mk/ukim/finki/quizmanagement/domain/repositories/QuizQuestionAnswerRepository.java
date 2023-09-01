package mk.ukim.finki.quizmanagement.domain.repositories;

import mk.ukim.finki.quizmanagement.domain.models.QuizAnswer;
import mk.ukim.finki.quizmanagement.domain.models.QuizQuestion;
import mk.ukim.finki.quizmanagement.domain.models.QuizQuestionAnswer;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizQuestionAnswerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionAnswerRepository extends JpaRepository<QuizQuestionAnswer, QuizQuestionAnswerId> {

    QuizQuestionAnswer findByQuizQuestionAndQuizAnswer(QuizQuestion quizQuestion, QuizAnswer quizAnswer);
}
