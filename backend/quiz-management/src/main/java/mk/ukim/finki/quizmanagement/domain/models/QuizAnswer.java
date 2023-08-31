package mk.ukim.finki.quizmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizAnswerId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mm_quiz_answers", schema = "metamodels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizAnswer extends AbstractEntity<QuizAnswerId> {

    private String description;

    @ManyToOne
    private QuizQuestion quizQuestion;
}
