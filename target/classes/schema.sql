DROP TABLE IF EXISTS STUDENT1;

CREATE TABLE STUDENT1 (
    student_id int PRIMARY KEY,
    name VARCHAR(20),
    age int
);

DROP TABLE IF EXISTS SUBJECT1;

CREATE TABLE SUBJECT1 (
    id int PRIMARY KEY,
    subject_name VARCHAR(20),
    marks int,
    student_id int,
    FOREIGN KEY (student_id) REFERENCES STUDENT1(student_id)
);
