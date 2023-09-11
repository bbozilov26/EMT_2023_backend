CREATE SCHEMA IF NOT EXISTS daily_check_ins;

CREATE TABLE daily_check_ins.mm_daily_check_in
(
    id                         BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    label                      VARCHAR(250),
    description                VARCHAR(5000),
    daily_reward               DOUBLE PRECISION,
    PRIMARY KEY (id),
    UNIQUE(label)
);

CREATE TABLE daily_check_ins.mm_user_daily_check_in
(
    id                          BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    ur_user_id                  BIGINT    NOT NULL,
    mm_daily_check_in_id        BIGINT    NOT NULL,
    unique(ur_user_id, mm_daily_check_in_id),
    PRIMARY KEY (id)
);

ALTER TABLE daily_check_ins.mm_user_daily_check_in
    ADD CONSTRAINT "ur_user_daily_check_in_userroles.ur_user_fk1" FOREIGN KEY (ur_user_id) REFERENCES userroles.ur_user (id);
ALTER TABLE daily_check_ins.mm_user_daily_check_in
    ADD CONSTRAINT "ur_user_daily_check_in_daily_check_ins.mm_daily_check_in_fk2" FOREIGN KEY (mm_daily_check_in_id) REFERENCES daily_check_ins.mm_daily_check_in (id);