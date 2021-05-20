--liquibase formatted sql
--changeset TestUsers_sql:1

-- Table: public.domains

-- DROP TABLE public.domains;

CREATE TABLE public.domains
(
    id integer NOT NULL,
    domain_name character varying(255) COLLATE pg_catalog."default",
    hotness character varying(255) COLLATE pg_catalog."default",
    price integer,
    x_value double precision,
    yandex_tic integer,
    links integer,
    visitors integer,
    registrar character varying(255) COLLATE pg_catalog."default",
    old integer,
    delete_date timestamp without time zone,
    rkn boolean,
    judicial boolean,
    block boolean,
    CONSTRAINT domains_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.domains
    OWNER to postgres;