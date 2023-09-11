CREATE SCHEMA IF NOT EXISTS userroles;

CREATE TABLE userroles.ur_role
(
    id          BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    role        VARCHAR(250) NOT NULL,
    label       VARCHAR(5000),
    PRIMARY KEY (id),
    UNIQUE (role)
);

CREATE TABLE userroles.ur_privilege
(
    id          BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    privilege   VARCHAR(250) NOT NULL,
    label       VARCHAR(5000),
    PRIMARY KEY (id),
    UNIQUE (privilege)
);

CREATE TABLE userroles.ur_role_privilege
(
    id              BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    ur_role_id      BIGINT    NOT NULL,
    ur_privilege_id BIGINT    NOT NULL,
    unique(ur_role_id, ur_privilege_id),
    PRIMARY KEY (id)
);

CREATE TABLE userroles.ur_user
(
    id                          BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    date_created                TIMESTAMP,
    date_modified               TIMESTAMP,
    email                       VARCHAR(250)      NOT NULL,
    password                    VARCHAR(5000),
    enabled                     BOOLEAN   NOT NULL,
    ur_person_id                BIGINT,
    credit_balance              DOUBLE PRECISION,
    credit_debt                 DOUBLE PRECISION,
    ur_user_daily_check_in_id   BIGINT,
    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE userroles.ur_user_role
(
    id         BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    ur_user_id BIGINT    NOT NULL,
    ur_role_id BIGINT    NOT NULL,
    unique(ur_user_id, ur_role_id),
    PRIMARY KEY (id)
);

CREATE TABLE userroles.ur_token
(
    id           BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    token        VARCHAR(250)      NOT NULL,
    date_created TIMESTAMP,
    date_expired TIMESTAMP,
    ur_user_id   BIGINT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (token)
);

CREATE TABLE userroles.ur_person
(
    id            BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    date_created  TIMESTAMP,
    date_modified TIMESTAMP,
    first_name    VARCHAR(5000),
    last_name     VARCHAR(5000),
    phone_number  VARCHAR(5000),
    PRIMARY KEY (id)
);

ALTER TABLE userroles.ur_role_privilege
    ADD CONSTRAINT "ur_role_privilege_userroles.ur_role_fk1" FOREIGN KEY (ur_role_id) REFERENCES userroles.ur_role (id);
ALTER TABLE userroles.ur_role_privilege
    ADD CONSTRAINT "ur_role_privilege_userroles.ur_privilege_fk2" FOREIGN KEY (ur_privilege_id) REFERENCES userroles.ur_privilege (id);
ALTER TABLE userroles.ur_user
    ADD CONSTRAINT "ur_user_userroles.ur_person_fk1" FOREIGN KEY (ur_person_id) REFERENCES userroles.ur_person (id);
ALTER TABLE userroles.ur_user_role
    ADD CONSTRAINT "ur_user_role_userroles.ur_user_fk1" FOREIGN KEY (ur_user_id) REFERENCES userroles.ur_user (id);
ALTER TABLE userroles.ur_user_role
    ADD CONSTRAINT "ur_user_role_userroles.ur_role_fk2" FOREIGN KEY (ur_role_id) REFERENCES userroles.ur_role (id);
ALTER TABLE userroles.ur_token
    ADD CONSTRAINT "ur_token_userroles.ur_user_fk1" FOREIGN KEY (ur_user_id) REFERENCES userroles.ur_user (id);