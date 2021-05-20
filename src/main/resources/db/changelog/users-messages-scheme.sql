--liquibase formatted sql
--changeset TestUsers_sql:2
-- Table: public.users
-- Table: public.messages
-- DROP TABLE public.users;
-- DROP TABLE public.messages;

CREATE TABLE public.users
(
    id integer NOT NULL,
    chat_id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    last_message_at timestamp without time zone NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

CREATE TABLE public.messages
(
    id integer NOT NULL,
    user_id integer NOT NULL,
    question character varying(255) COLLATE pg_catalog."default" NOT NULL,
    answer character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT messages_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.messages
    OWNER to postgres;

ALTER TABLE public.users
    OWNER to postgres;