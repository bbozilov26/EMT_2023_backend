CREATE SCHEMA IF NOT EXISTS orders;

CREATE TABLE orders.mm_order
(
    id                      BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    date_created            TIMESTAMP,
    date_modified           TIMESTAMP,
    date_closed             TIMESTAMP,
    total_price             DOUBLE PRECISION,
    order_id                VARCHAR(250),
    tracking_number         VARCHAR(250),
    description             VARCHAR(5000),
    order_status            VARCHAR(250),
    ur_user_id              BIGINT NOT NULL,
    ordered_mm_product_id   BIGINT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (order_id, tracking_number)
);

CREATE TABLE orders.mm_ordered_product
(
    id                      BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    date_created            TIMESTAMP,
    date_modified           TIMESTAMP,
    quantity                INTEGER,
    price                   DOUBLE PRECISION,
    total_price             DOUBLE PRECISION,
    title                   VARCHAR(250),
    description             VARCHAR(5000),
    image                   BYTEA,
    mm_product_id           BIGINT NOT NULL,
    ur_user_id              BIGINT NOT NULL,
    mm_order_id             BIGINT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (title)
);

ALTER TABLE orders.mm_order
    ADD CONSTRAINT "mm_order_orders.ordered_mm_product_fk1" FOREIGN KEY (ordered_mm_product_id) REFERENCES orders.mm_ordered_product (id);
ALTER TABLE orders.mm_order
    ADD CONSTRAINT "mm_order_orders.ur_user_fk2" FOREIGN KEY (ur_user_id) REFERENCES userroles.ur_user (id);
ALTER TABLE orders.mm_ordered_product
    ADD CONSTRAINT "mm_ordered_product_orders.mm_product_fk1" FOREIGN KEY (mm_order_id) REFERENCES products.mm_product (id);
ALTER TABLE orders.mm_ordered_product
    ADD CONSTRAINT "mm_ordered_product_orders.ur_user_fk2" FOREIGN KEY (ur_user_id) REFERENCES userroles.ur_user (id);
ALTER TABLE orders.mm_ordered_product
    ADD CONSTRAINT "mm_ordered_product_orders.mm_order_fk3" FOREIGN KEY (mm_order_id) REFERENCES orders.mm_order (id);