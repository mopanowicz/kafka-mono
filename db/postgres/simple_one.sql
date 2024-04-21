CREATE TABLE simple_one (
    id character varying(1024) NOT NULL,
    sent bigint,
    text character varying(65535),
    created timestamp DEFAULT NOW()
);

ALTER TABLE ONLY simple_one ADD CONSTRAINT simple_one_pk PRIMARY KEY (id);
