SHOW TABLES;
DROP TABLE class;
DROP TABLE student;
DROP TABLE SUBJECT;
CREATE TABLE student;
CREATE TABLE class(
	classid INT (5) PRIMARY KEY,
	teachername VARCHAR(20) UNIQUE
);
CREATE TABLE Student(
	studentno VARCHAR(10) PRIMARY KEY,
	classid INT(5) ,
	NAME VARCHAR(10) NOT NULL,
	sex CHAR(3) NOT NULL,
	age INT(3) NOT NULL,
	CONSTRAINT Student_classid_fk FOREIGN KEY(classid) REFERENCES class(classid)
);
CREATE TABLE SUBJECT(
	subjectid INT(3) PRIMARY KEY,
	subjectname VARCHAR(20) NOT NULL,
	classid INT(3),
	CONSTRAINT subject_classid_fk FOREIGN KEY(classid) REFERENCES class(classid)
);
INSERT INTO class(classid,teachername) VALUES(1,"诸葛亮");
INSERT INTO class(classid,teachername) VALUES(2,"赵云");
INSERT INTO class(classid,teachername) VALUES(3,"李白");
INSERT INTO student(studentno,classid,NAME,sex,age) VALUES("1001",1,"汪大东","男",24);
INSERT INTO student(studentno,classid,NAME,sex,age) VALUES("1002",1,"汪大西","女",20);
INSERT INTO student(studentno,classid,NAME,sex,age) VALUES("1003",2,"李白","男",25);
INSERT INTO student(studentno,classid,NAME,sex,age) VALUES("1004",2,"诸葛亮","男",28);
INSERT INTO student(studentno,classid,NAME,sex,age) VALUES("1005",3,"伽罗","女",28);
INSERT INTO student(studentno,classid,NAME,sex,age) VALUES("1006",3,"赵云","男",29);
INSERT INTO SUBJECT(subjectid,subjectname,classid)VALUES(1,"Java",3);
INSERT INTO SUBJECT(subjectid,subjectname,classid)VALUES(2,"springboot",2);
INSERT INTO SUBJECT(subjectid,subjectname,classid)VALUES(3,"mysql",1);
SELECT * FROM student WHERE studentno="1003";
SELECT * FROM SUBJECT
查询学号为1001的学生所有信息
SELECT * FROM student WHERE studentno="1003";
根据班级名称查询每个班级有哪些学生
SELECT teachername,NAME FROM class LEFT OUTER JOIN Student ON class.classid=Student.classid;
根据 学生学号 查询学了哪些课程
SELECT studentno,NAME,gradename FROM Student INNER JOIN Grade ON Grade.gradeid=Student.gradeid WHERE studentno=1002; 
每个年级的学生所学的课程
SELECT Grade.gradename,Student.name, subject.subjectname FROM 
	Grade INNER JOIN Student ON Grade.gradeid = Student.gradeid 
	INNER JOIN SUBJECT ON Grade.gradeid=subject.gradeid;
事务回滚（不回滚在其他地方就看不到改动）
BEGIN;
INSERT INTO class VALUES(5,'aaa');
INSERT INTO class VALUES(4,'bbb');
INSERT INTO class VALUES(6,'ccc');
SELECT * FROM class;                ①
ROLLBACK;
SELECT * FROM class; 
