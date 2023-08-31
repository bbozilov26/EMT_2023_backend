package mk.ukim.finki.quizmanagement.domain.repositories;

import mk.ukim.finki.quizmanagement.domain.models.QuizAnswer;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizAnswerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizAnswerRepository extends JpaRepository<QuizAnswer, QuizAnswerId> {

    QuizAnswer findByDescription(String description);
}
