CREATE TABLE logical_one (
    id character varying(1024) NOT NULL,
    sent timestamp,
    text character varying(65535),
    created timestamp DEFAULT NOW()
);

ALTER TABLE ONLY logical_one ADD CONSTRAINT logical_one_pk PRIMARY KEY (id);
