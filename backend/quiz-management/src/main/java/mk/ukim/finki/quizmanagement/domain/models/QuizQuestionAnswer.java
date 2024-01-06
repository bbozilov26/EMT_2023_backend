package mk.ukim.finki.quizmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizQuestionAnswerId;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizQuestionId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "mm_quiz_question_answer")
@Data
@AllArgsConstructor
public class QuizQuestionAnswer extends AbstractEntity<QuizQuestionAnswerId> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mm_quiz_question_id")
    private QuizQuestion quizQuestion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mm_quiz_answer_id")
    private QuizAnswer quizAnswer;

    public QuizQuestionAnswer() {
        super(QuizQuestionAnswerId.randomId(QuizQuestionAnswerId.class));
    }
}
