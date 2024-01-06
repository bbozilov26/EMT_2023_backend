package mk.ukim.finki.quizmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizAnswerId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "mm_quiz_answer")
@Data
@AllArgsConstructor
public class QuizAnswer extends AbstractEntity<QuizAnswerId> {

    private String description;

    public QuizAnswer() {
        super(QuizAnswerId.randomId(QuizAnswerId.class));
    }
}
