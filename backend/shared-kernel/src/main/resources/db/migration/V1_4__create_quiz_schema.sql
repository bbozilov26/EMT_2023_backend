CREATE SCHEMA IF NOT EXISTS quiz;

CREATE TABLE quiz.mm_quiz_question
(
    id                              BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    question                        VARCHAR(250),
    reward                          DOUBLE PRECISION,
    topic                           VARCHAR(250),
    difficulty                      VARCHAR(250),
    correct_mm_quiz_answer_id       BIGINT NOT NULL,
    mm_quiz_question_answer_id      BIGINT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE(question)
);

CREATE TABLE quiz.mm_quiz_answer
(
    id                              BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    answer                          VARCHAR(250),
    PRIMARY KEY (id),
    UNIQUE(answer)
);

CREATE TABLE quiz.mm_quiz_question_answer
(
    id                          BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    mm_quiz_question_id         BIGINT    NOT NULL,
    mm_quiz_answer_id           BIGINT    NOT NULL,
    UNIQUE (mm_quiz_question_id, mm_quiz_answer_id),
    PRIMARY KEY (id)
);

ALTER TABLE quiz.mm_quiz_question
    ADD CONSTRAINT "mm_quiz_question_quiz.mm_correct_quiz_answer_fk1" FOREIGN KEY (correct_mm_quiz_answer_id) REFERENCES quiz.mm_quiz_answer (id);
ALTER TABLE quiz.mm_quiz_question_answer
    ADD CONSTRAINT "mm_quiz_question_answer_quiz.mm_quiz_question_fk1" FOREIGN KEY (mm_quiz_question_id) REFERENCES quiz.mm_quiz_question (id);
ALTER TABLE quiz.mm_quiz_question_answer
    ADD CONSTRAINT "mm_quiz_question_answer_quiz.mm_quiz_answer_fk2" FOREIGN KEY (mm_quiz_answer_id) REFERENCES quiz.mm_quiz_answer (id);