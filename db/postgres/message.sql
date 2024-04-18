CREATE TABLE message (
    key character varying(4096) NOT NULL,
    value character varying(65535),
    received timestamp,
    saved timestamp DEFAULT NOW()
);

ALTER TABLE ONLY message ADD CONSTRAINT message_pk PRIMARY KEY (key);
