CREATE TABLE message (
    key character varying(4096) NOT NULL,
    value character varying(65535)
);

ALTER TABLE ONLY message ADD CONSTRAINT message_pk PRIMARY KEY (key);
