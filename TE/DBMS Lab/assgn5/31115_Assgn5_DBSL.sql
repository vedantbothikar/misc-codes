CREATE DATABASE Assignment5DB;

USE Assignment5DB;

CREATE TABLE STUD_MARKS 
( 
name VARCHAR(200) NOT NULL,
total_marks INT NOT NULL
);

CREATE TABLE RESULT
(
roll int not null auto_increment,,
name varchar(200) NOT NULL,
class varchar(200) NOT NULL,
PRIMARY KEY (roll)
);


INSERT INTO STUD_MARKS 
VALUES 
('a',900),
('b',1500),
('c',1000),
('d',989),
('e',990),
('f',980),
('g',825),
('h',899);

delimiter //

CREATE PROCEDURE proc_Grade(IN studname VARCHAR(200))
	BEGIN 
    DECLARE marks INT;
    
    SELECT total_marks 
    INTO marks 
    FROM STUD_MARKS 
    WHERE name = studname;
    
    IF (marks>=990 and marks<=1500) THEN
    INSERT INTO RESULT
    VALUES
    (studname, 'Distinction');
    
    ELSEIF (marks>=900 and marks<=989) THEN
	INSERT INTO RESULT
    VALUES
    (studname, 'First'); 
    
	ELSE 
    INSERT INTO RESULT
    VALUES
    (studname, 'Higher Second');
    
    END IF;
    
    END //
delimiter ;


call proc_Grade('a');



