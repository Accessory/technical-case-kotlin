CREATE SEQUENCE IF NOT EXISTS public.job_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.job
(
    id bigint NOT NULL,
    "timestamp" timestamp without time zone NOT NULL,
    commands bigint NOT NULL,
    result bigint NOT NULL,
    duration double precision NOT NULL,
    CONSTRAINT jobs_pkey PRIMARY KEY (id)
);