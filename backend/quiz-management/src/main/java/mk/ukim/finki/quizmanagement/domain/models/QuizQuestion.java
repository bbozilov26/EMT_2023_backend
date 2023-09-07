package mk.ukim.finki.quizmanagement.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.quizmanagement.domain.models.enums.Difficulty;
import mk.ukim.finki.quizmanagement.domain.models.enums.Topic;
import mk.ukim.finki.quizmanagement.domain.models.ids.QuizQuestionId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mm_quiz_questions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestion extends AbstractEntity<QuizQuestionId> {

    private String question;
    private Double reward;

    @Enumerated(value = EnumType.STRING)
    private Topic topic;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "correct_mm_quiz_answer_id")
    private QuizQuestionAnswer correctQuizAnswer;

    @OneToMany(mappedBy = "quizQuestion", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<QuizQuestionAnswer> quizQuestionAnswers;
}
