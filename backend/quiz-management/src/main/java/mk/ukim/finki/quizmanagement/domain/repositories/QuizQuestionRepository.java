package mk.ukim.finki.quizmanagement.domain.repositories;

import mk.ukim.finki.quizmanagement.domain.models.QuizQuestion;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizQuestionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, QuizQuestionId> {

    QuizQuestion findByQuestion(String question);
}
