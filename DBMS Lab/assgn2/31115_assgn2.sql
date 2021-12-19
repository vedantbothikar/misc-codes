CREATE DATABASE Assignment2DB;

CREATE TABLE Customer (
cust_no INT,
cust_fname VARCHAR(100),
cust_lname VARCHAR(100),
cust_company VARCHAR(100),
ust_addr VARCHAR(100),
city VARCHAR(100),
cust_phone INT
);


USE Assignment2DB;
SHOW TABLES;

-- 2]
-- Display all customer details with city pune and mumbai and customer first name starting with
-- 'p' or 'h'. 

SELECT * FROM Customer
WHERE (city="PUNE" OR city="MUMBAI") AND (cust_fname LIKE "p%" OR cust_fname LIKE "h%");



-- 3]
-- lists the number of different customer cities.(use of distinct)

SELECT DISTINCT city FROM Customer;


-- 4] 
-- Give 5% increase in price of the books with publishing year 2015. ( use of update)
-- books(ISBN,title,unit_price,author_no,publisher_no,pub_year);

CREATE TABLE Books (
ISBN INT NOT NULL,
title VARCHAR(100),
unit_price INT,
author_no INT,
publisher_no INT,
pub_year INT
);

INSERT INTO Books VALUES 
(1, "Book1", 100, 1, 1, 2001),
(2, "Book2", 200, 2, 2, 2002),
(3, "Book3", 300, 3, 3, 2003),
(4, "Book4", 400, 4, 4, 2004),
(5, "Book5", 500, 5, 5, 2005),
(6, "something", 1000, 1, 1, 2011),
(7, "Book1", 160, 2, 1, 2015),
(8, "Book1", 120, 1, 2, 2018);

INSERT INTO Books VALUES 
(9, "Book9", 100, 1, 1, 2015),
(10, "Book10", 200, 3, 3, 2015);


UPDATE Books 
SET unit_price = unit_price + (unit_price*0.05)
WHERE pub_year = "2015";

SELECT * FROM Books;

-- 5]
-- Delete customer details living in pune. 

DELETE
FROM Customer
WHERE city = "PUNE";


-- 6]
-- Find the names of authors living in India or Australia (use of UNION)
-- authors(author_no,author_name,country) 

CREATE TABLE Authors (
author_no INT,
author_name VARCHAR(100),
country VARCHAR(100)
);

INSERT INTO Authors VALUES 
(1, "Vedant1", "India"),
(2, "Vedant2", "Australia"),
(3, "Vedant3", "US"),
(4, "Vedant4", "France"),
(5, "Vedant5", "Russia"),
(6, "Vedant6", "India");




SELECT author_name FROM Authors
WHERE country = "INDIA" 
UNION
SELECT author_name FROM Authors
WHERE country = "AUSTRALIA";



-- 7]
-- publisher(publisher_no,publisher_name,publisher_addr,year);
-- Find the publishers who are established in year 2015 as well as in 2016 (use of INTERSECT)

CREATE TABLE Publishers (
publisher_no INT,
publisher_name VARCHAR(100),
publisher_addr VARCHAR(100),
year INT
);

INSERT INTO Publishers VALUES 
(1, "Pub1", "Area1", 2001),
(1, "Pub2", "Area2", 2015),
(1, "Pub3", "Area3", 2021),
(1, "Pub4", "Area4", 2015),
(1, "Pub5", "Area5", 2016),
(1, "Pub6", "Area6", 2016);


SELECT * FROM Publishers WHERE year = 2015
INTERSECT
SELECT * FROM Publishers WHERE year = 2016;


-- 8]
-- Find the book having maximum price and find titles of book having price between 300 and
-- 400.(use of max and between)

INSERT INTO Books
VALUES 
(123, "random1", 350, "12", "13", 2017);

SELECT MAX(unit_price) 
FROM Books;

SELECT * 
FROM Books
WHERE unit_price BETWEEN 300 AND 400;


-- 9]
-- Display all titles of books with price and published year in decreasing order of publishing
-- year

SELECT title, unit_price, pub_year
FROM Books
ORDER BY pub_year DESC;



-- 10]
-- Display title,author_no and publisher_no of all books published in 2000,2004,2006. (use of
-- IN)

SELECT title, author_no, publisher_no
FROM Books
WHERE pub_year IN (2000, 2004, 2006);


-- 11]
-- Create view showing the books and authors details. (COMPLEX VIEW)

SELECT * 
FROM Books 
INNER JOIN Authors ON Books.author_no = Authors.author_no;
   

-- RUN OTHER COMMANDS HERE

SELECT * FROM Customer;
SELECT * FROM Books;

INSERT INTO Customer VALUES (12, "Hushkar2", "Bidnur2", "INFY", "HYDERABAD", "New York", 3241);


