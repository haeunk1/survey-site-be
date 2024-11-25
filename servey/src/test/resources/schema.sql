CREATE TABLE IF NOT EXISTS Servey (
    servey_id BIGINT PRIMARY KEY,
    member_id BIGINT NOT NULL,
    title VARCHAR(255),
    type VARCHAR(50),
    per_point INT,
    limit_submit INT,
    startdate TIMESTAMP,
    enddate TIMESTAMP,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    delete_yn CHAR(1)
);