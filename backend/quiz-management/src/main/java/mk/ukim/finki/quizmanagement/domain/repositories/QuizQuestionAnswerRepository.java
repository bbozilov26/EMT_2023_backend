package mk.ukim.finki.quizmanagement.domain.repositories;

import mk.ukim.finki.quizmanagement.domain.models.QuizAnswer;
import mk.ukim.finki.quizmanagement.domain.models.QuizQuestion;
import mk.ukim.finki.quizmanagement.domain.models.QuizQuestionAnswer;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizQuestionAnswerId;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizQuestionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizQuestionAnswerRepository extends JpaRepository<QuizQuestionAnswer, QuizQuestionAnswerId> {

    QuizQuestionAnswer findByQuizQuestionAndQuizAnswer(QuizQuestion quizQuestion, QuizAnswer quizAnswer);

    @Query("select qqa from QuizQuestionAnswer qqa where qqa.quizQuestion.id = :questionId")
    List<QuizQuestionAnswer> findAllByQuizQuestionId(QuizQuestionId questionId);
}
