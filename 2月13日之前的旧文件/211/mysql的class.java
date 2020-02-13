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
INSERT INTO class(classid,teachername) VALUES(1,"�����");
INSERT INTO class(classid,teachername) VALUES(2,"����");
INSERT INTO class(classid,teachername) VALUES(3,"���");
INSERT INTO student(studentno,classid,NAME,sex,age) VALUES("1001",1,"����","��",24);
INSERT INTO student(studentno,classid,NAME,sex,age) VALUES("1002",1,"������","Ů",20);
INSERT INTO student(studentno,classid,NAME,sex,age) VALUES("1003",2,"���","��",25);
INSERT INTO student(studentno,classid,NAME,sex,age) VALUES("1004",2,"�����","��",28);
INSERT INTO student(studentno,classid,NAME,sex,age) VALUES("1005",3,"٤��","Ů",28);
INSERT INTO student(studentno,classid,NAME,sex,age) VALUES("1006",3,"����","��",29);
INSERT INTO SUBJECT(subjectid,subjectname,classid)VALUES(1,"Java",3);
INSERT INTO SUBJECT(subjectid,subjectname,classid)VALUES(2,"springboot",2);
INSERT INTO SUBJECT(subjectid,subjectname,classid)VALUES(3,"mysql",1);
SELECT * FROM student WHERE studentno="1003";
SELECT * FROM SUBJECT
��ѯѧ��Ϊ1001��ѧ��������Ϣ
SELECT * FROM student WHERE studentno="1003";
���ݰ༶���Ʋ�ѯÿ���༶����Щѧ��
SELECT teachername,NAME FROM class LEFT OUTER JOIN Student ON class.classid=Student.classid;
���� ѧ��ѧ�� ��ѯѧ����Щ�γ�
SELECT studentno,NAME,gradename FROM Student INNER JOIN Grade ON Grade.gradeid=Student.gradeid WHERE studentno=1002; 
ÿ���꼶��ѧ����ѧ�Ŀγ�
SELECT Grade.gradename,Student.name, subject.subjectname FROM 
	Grade INNER JOIN Student ON Grade.gradeid = Student.gradeid 
	INNER JOIN SUBJECT ON Grade.gradeid=subject.gradeid;
����ع������ع��������ط��Ϳ������Ķ���
BEGIN;
INSERT INTO class VALUES(5,'aaa');
INSERT INTO class VALUES(4,'bbb');
INSERT INTO class VALUES(6,'ccc');
SELECT * FROM class;                ��
ROLLBACK;
SELECT * FROM class; 
