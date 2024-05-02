CREATE TABLE simple_one (
    id varchar(255) NOT NULL,
    sent bigint,
    text lvarchar(30000),
    saved bigint,
    latency bigint
);

ALTER TABLE simple_one ADD CONSTRAINT PRIMARY KEY (id);
