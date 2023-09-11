CREATE SCHEMA IF NOT EXISTS products;

CREATE TABLE products.mm_product
(
    id            BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    date_created  TIMESTAMP,
    date_modified TIMESTAMP,
    title         VARCHAR(250),
    image         BYTEA,
    description   VARCHAR(5000),
    price         DOUBLE PRECISION,
    quantity      INTEGER,
    category      VARCHAR(250),
    PRIMARY KEY (id),
    UNIQUE (title)
);