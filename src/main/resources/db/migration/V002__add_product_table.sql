-- Creating a table
CREATE TABLE IF NOT EXISTS my_store.product
(
    id          SERIAL CONSTRAINT product_id_pkey PRIMARY KEY,
    name        TEXT NOT NULL,
    price       DOUBLE PRECISION
);