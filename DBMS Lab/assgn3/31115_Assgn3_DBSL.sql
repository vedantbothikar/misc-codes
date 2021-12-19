-- create database 
CREATE DATABASE Assignment3DB;

-- use database
USE Assignment3DB;


-- ---------------------------
-- create & inserting

-- departments table

-- creation
-- Departments (dept_id , dept_name )
CREATE TABLE departments (
dept_id INT NOT NULL AUTO_INCREMENT,
dept_name VARCHAR(100),
PRIMARY KEY(dept_id)
);

-- insertion
INSERT 
INTO departments (dept_name)
VALUES 
('Engineering'),
('Science'),
('Human resources'),
('Development'),
('Research'),
('Marketing'),
('Sales')
;

-- view
SELECT * FROM departments;

-- ---------------------------
-- professors table

-- creation

-- Professors (prof_id ,prof_fname , prof_lname, dept_id,designation,salary,doj,email,phone,city )
-- NOTE- DATE values in ‘YYYY-MM-DD’ format.

CREATE TABLE professors (
prof_id INT NOT NULL AUTO_INCREMENT,
prof_fname  VARCHAR(100),
prof_lname VARCHAR(100),
dept_id INT NOT NULL,
designation VARCHAR(100),
salary INT NOT NULL,
doj DATE NOT NULL,
email VARCHAR(100),
phone INT(11) NOT NULL,
city VARCHAR(100),
FOREIGN KEY (dept_id) 
	REFERENCES departments(dept_id)
    ON DELETE CASCADE,
PRIMARY KEY(prof_id)
);


-- insertion
INSERT 
INTO professors (prof_fname , prof_lname, dept_id, designation, salary, doj, email, phone, city)
VALUES 
('Marc', 'Zuckerberg', 2, 'Senior Prof', '200', '2020-02-22', 'zuckerber@gmail.com', 1234567890, 'London' ),
('Jeff', 'Bezos', 1, 'Asst Prof', '200', '2020-03-13', 'Bezos@gmail.com', 1234567891, 'New York' ),
('Bill', 'Gates', 2, 'Senior Prof', '200', '2020-02-22', 'Gates@gmail.com', 1234567892, 'Washington DC' ),
('Warren', 'Buffet', 2, 'Senior Prof', '200', '2020-02-22', 'Buffet@gmail.com', 1234567893, 'Texas' ),
('Elon', 'Musk', 2, 'Senior Prof', '200', '2020-02-22', 'Musk@gmail.com', 1234567894, 'Manchester' )
;

INSERT 
INTO professors (prof_fname , prof_lname, dept_id, designation, salary, doj, email, phone, city)
VALUES 
('Vedant', 'Bothikar', 1, 'Principal', '1000', '2015-02-22', 'bothikar@gmail.com', 1234567899, 'Pune' )
;


-- view
SELECT * FROM professors;

-- ---------------------------
-- shifts table
-- Shift (prof_id,shift,working_hours)

-- creation
CREATE TABLE shifts(
prof_id INT NOT NULL,
shift VARCHAR(100),
working_hours INT,
FOREIGN KEY(prof_id)
	REFERENCES professors(prof_id)
);

-- insertion
INSERT 
INTO shifts
VALUES 
(3, 'evening', 7), 
(2, 'afternoon', 9),
(2, 'morning', 5), 
(5, 'evening', 7),
(1, 'evening', 7), 
(1, 'morning', 7)
;

SELECT * FROM shifts;

-- ---------------------------
-- QUERIES
-- 1. Find the professor details and department details using NATURAL JOIN.

SELECT prof_fname, dept_name
FROM departments
NATURAL JOIN professors;

-- 2. Find the prof_id, prof_name and shift. (INNER JOIN)
SELECT p.prof_id, p.prof_fname, p.prof_lname, s.shift
FROM professors AS p
INNER JOIN shifts AS s
ON p.prof_id = s.prof_id
;

-- 3. List all the department details and the corresponding names of professors in the same 
-- department.(left outer join)

SELECT d.dept_id, d.dept_name, p.prof_fname, p.prof_lname
FROM departments AS d
LEFT JOIN professors AS p
ON d.dept_id = p.dept_id
;

-- 4. List all the professors and the corresponding names of department.(right outer join)

SELECT p.prof_fname, p.prof_lname, d.dept_name
FROM professors AS p
RIGHT JOIN departments AS d
ON p.dept_id = d.dept_id
;

-- 5. Display professor name, dept_name, shift, salary where prof_id = 101;(multitable join)

SELECT p.prof_fname, p.prof_lname,  d.dept_name, s.shift, p.salary
FROM professors AS p
INNER JOIN departments AS d
ON p.dept_id = d.dept_id
INNER JOIN shifts AS s
ON p.prof_id = s.prof_id
WHERE p.prof_id = 1
;

-- 6. list the total number of professor in each department.(count and any join,groupby)

SELECT COUNT(p.prof_id) AS noOfProf, d.dept_name
FROM professors AS p
INNER JOIN departments AS d
ON p.dept_id = d.dept_id
GROUP BY d.dept_name
;


-- 7. List the prof_id associated department and the dept_name having name ‘computer’;(subquery)

SELECT p.prof_id, p.prof_fname, p.prof_lname, d.dept_name
FROM professors AS p
INNER JOIN departments AS d
ON p.dept_id = d.dept_id
WHERE d.dept_name = 'Science'
;


-- 8. Find the names of all departments where the professors joined in year 2015 (or date of joining
-- is 1-1-2015).(subquery)

SELECT d.dept_name
FROM departments AS d
INNER JOIN professors AS p
ON d.dept_id = p.dept_id
WHERE year(doj) = 2015
;

