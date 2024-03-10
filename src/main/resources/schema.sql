SET search_path TO test;

DROP TABLE IF EXISTS student;
CREATE TABLE IF NOT EXISTS student (
	id int PRIMARY KEY,-- multiple entries in not PK
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	gender VARCHAR(50),
	cityOfBirth VARCHAR(50),
	email VARCHAR(50),
	university VARCHAR(200),
	dob DATE
);

DROP SEQUENCE IF EXISTS student_seq;

CREATE SEQUENCE IF NOT EXISTS student_seq
     AS INT
    INCREMENT BY 2 --Increment by 2
     MAXVALUE 10000
    START WITH 1005
     NO CYCLE
    OWNED BY student.id;

DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
	id UUID PRIMARY KEY,-- multiple entries in not PK
	name VARCHAR(50),
	date_of_birth DATE,
	email VARCHAR(50) not null,
	phone VARCHAR(50) not null
);


-- Create the Ref_Table table
DROP TABLE IF EXISTS Ref_Table;
CREATE TABLE Ref_Table (
    ref_id SERIAL PRIMARY KEY,
    ref_name VARCHAR(255) NOT NULL,
    create_date_time_gmt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date_time_gmt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    delete_date_time_gmt TIMESTAMP
);
