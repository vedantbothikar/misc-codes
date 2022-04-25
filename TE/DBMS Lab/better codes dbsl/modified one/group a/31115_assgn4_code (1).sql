-- create database 
CREATE DATABASE Assignment4DB;

-- use database
USE Assignment4DB;

-- tables
CREATE TABLE Borrower (
roll INT NOT NULL AUTO_INCREMENT,
name VARCHAR(100) NOT NULL,
date_of_issue DATE NOT NULL,
name_of_book VARCHAR(200) NOT NULL,
status VARCHAR(200) DEFAULT 'I',
PRIMARY KEY(roll)
);

CREATE TABLE Fine (
roll INT NOT NULL,
fdate DATE NOT NULL,
amt INT NOT NULL,
FOREIGN KEY(roll)
	REFERENCES Borrower(roll)
);


INSERT INTO Borrower 
VALUES
(1, 'Person1', '2015-02-22', 'Book1', 'I'),
(2, 'Person2', '2015-03-22', 'Book2', 'I')
;

INSERT INTO Borrower 
VALUES
(3, 'Person3', '2015-03-22', 'Book3', 'I'),
(4, 'Person4', '2015-04-22', 'Book4', 'I')
;

delimiter //
create procedure finefunc (IN rollno INT, bookname VARCHAR(200))
	BEGIN
    DECLARE dateofissue DATE;
    DECLARE noofdays INT;
    DECLARE fineamt INT;
    -- exception
    DECLARE EXIT HANDLER FOR SQLEXCEPTION SELECT 'Table not found';
    
    SELECT date_of_issue INTO dateofissue FROM Borrower where roll = rollno AND name_of_book = bookname;
    SET noofdays = datediff(CURDATE(), dateofissue);
    IF (noofdays > 30) THEN
    SET fineamt = noofdays*50;
    ELSEIF (noofdays >= 15 and noofdays <= 30) THEN
    SET fineamt = noofdays*5;
    END IF;
    
    INSERT INTO Fine 
    VALUES (rollno, CURDATE(), fineamt);
    
    UPDATE Borrower
    SET status = 'R' 
    WHERE roll = rollno AND name_of_book = bookname;
    
    END // 
delimiter ;
    

call finefunc(1, 'Book1');










/*



Delimiters other than the default ; are typically used when defining functions, stored procedures, and triggers wherein you must define multiple statements. You define a different delimiter like $$ which is used to define the end of the entire procedure, but inside it, individual statements are each terminated by ;. That way, when the code is run in the mysql client, the client can tell where the entire procedure ends and execute it as a unit rather than executing the individual statements inside.

Note that the DELIMITER keyword is a function of the command line mysql client (and some other clients) only and not a regular MySQL language feature. It won't work if you tried to pass it through a programming language API to MySQL. Some other clients like PHPMyAdmin have other methods to specify a non-default delimiter.



Example:

DELIMITER $$
This is a complete statement, not part of the procedure, so use the custom delimiter $$ 
DROP PROCEDURE my_procedure$$

Now start the procedure code 
CREATE PROCEDURE my_procedure ()
BEGIN    
  Inside the procedure, individual statements terminate with ;
  CREATE TABLE tablea (
     col1 INT,
     col2 INT
  );

  INSERT INTO tablea
    SELECT * FROM table1;

  CREATE TABLE tableb (
     col1 INT,
     col2 INT
  );
  INSERT INTO tableb
    SELECT * FROM table2;
  
 whole procedure ends with the custom delimiter 
END$$

 Finally, reset the delimiter to the default ; 
DELIMITER ;



*/








