CREATE TABLE simple_one (
    id character varying(1024) NOT NULL,
    sent bigint,
    text character varying(65535),
    saved bigint DEFAULT cast(extract(epoch from (now() at time zone 'utc')) * 1000 as bigint),
    latency bigint GENERATED ALWAYS AS (saved - sent) STORED
);

ALTER TABLE ONLY simple_one ADD CONSTRAINT simple_one_pk PRIMARY KEY (id);
