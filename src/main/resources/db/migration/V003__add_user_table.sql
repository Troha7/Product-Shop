-- Creating a table
CREATE TABLE IF NOT EXISTS my_store.user
(
    id          SERIAL CONSTRAINT user_id_pkey PRIMARY KEY,
    name        TEXT NOT NULL,
    password    TEXT NOT NULL,
    role        TEXT NOT NULL
);