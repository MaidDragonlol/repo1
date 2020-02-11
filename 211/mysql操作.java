SQL（Structured Query Language）语句，即结构化查询语言，是操作和检索关系数据库的标准语言。SQL语句一般分为以下几种：

DCL（Database Control Language，数据控制语言）语句：主要由GRANT和REVOKE两个关键字完成
DDL（Database Definition Language，数据定义语言）语句：主要由CREATE、ALTER、DROP和TRUNCATE四个关键字完成
DML（Database Manipulation Language，数据操作语言）语句：主要由INSERT、UPDATE和DELETE三个关键字完成
查询语句：主要由SELECT语句完成
事务控制语句：主要由COMMIT、ROLLBACK和SAVEPOINT三个关键字完成
注：SQL语句不区分大小写，所以create和CREATE是相同的

一.DCL语句

DCL语句就是对用户进行授权和授权收回的操作，可以对不同的用户的权限进行控制，增加数据库安全性，进行数据库的维护。一般都是数据库管理员使用超级用户root进行操作。

MySQL的权限命令是grant，权限撤销的命令时revoke；

1.grant授权格式：

grant 权限列表 on 库.表 to 用户名@'ip' identified by "密码";

2.revoke回收权限格式：

revoke 权限列表 on 库.表 from 用户名@'ip';

二.DDL语句
数据库对象：
表（table）、数据字典、约束（constraint）、视图（view）、索引（index）、函数（function）、存储过程（procedure）、触发器（trigger）
CREATE、ALTER、DELETE关键字分别是创建、修改和删除数据库对象的，此处使用我们使用最多的对表的操作来举例
1.CREATE:

CREATE TABLE [模式名.] 表名(
该表中的列定义
);
CREATE TABLE test(
StuId VARCHAR(8) PRIMARY KEY,
StuName VARCHAR(30) NOT NULL,
StuAge SMALLINT NOT NULL,
StuBirth DATETIME
);
注：查看表结构：DESCRIBE 表名;
2.ALTER:
1）添加列：
ALTER TABLE 表名
ADD column columnName1 datatype [default expr] [FIRST|AFTER colName];
columnName1：新添加的列名；
datatype：数据类型；
default expr：完整性约束；
FIRST|AFTER colName：插入位置，默认是插入在最后一列，FIRST是在第一列，AFTER colName是在指定列后插入
ALTER TABLE test
ADD column StuMajor VARCHAR(20) NOT NULL AFTER StuName;
2）修改列
ALTER TABLE 表名 CHANGE oldName newName datatype; 
ALTER TABLE test CHANGE StuBirth Birthday year;
3）删除列
ALTER TABLE 表名 DROP column columnName;
ALTER TABLE test DROP column StuMajor;
4）修改表名
ALTER TABEL 表名 RENAME TO 新表名;
ALTER TABLE test RENAME TO student;
3.DROP
删除表
DROP TABLE 表名;
DROP TABLE student;
4.TRUNCATE
删除表内所有数据但保留表的结构，叫做“截断”
TRUNCATE TABLE 表名;
TRUNCATE TABLE student;
三.DML语句

1.INSERT

标准SQL语句只允许一次插入一条数据，但MySQL对其进行扩展使其可以一次插入多条数据
插入一条数据：
INSERT INTO 表名 VALUES(value1, value2, ...);
插入多条数据：
INSERT INTO 表名 VALUES(value1,value2,...),(value1,value2,...),(value1,value2,...);
INSERT INTO student VALUES(‘001','Lisa',20,1997),(‘002','Rose',21,1996);
2.UPDATE
UPDATE 表名 SET COLUMN1 = VALUE1[, COLUMN2 = VALUE2]...
[WHERE CONDITION];
UPDATE student SET StuAge = StuAge+1 WHERE StuAge>20;
3.DELETE
DELETE FROM 表名 [WHERE CONDITION];
DELETE FROM student WHERE Birthday = 1997;
四.查询语句

1.单表查询：
SELECT COLUMN1， COLUMN2...
FROM 数据源
[WHERE CONDITION]
[GROUP BY columnName]
[ORDER BY columnName DESC|ASC]
e.g.将计算机科学专业的学生选出来并按照学号降序排列，只显示学生姓名
SELECT StuName FROM student 
WHERE StuMajor = 'CS'
ORDER BY StuId DESC;
2.多表查询：
1）简单的外连接方式
SELECT VALUE1[,VALUE2]...
FROM tableName1，tableName2
WHERE tableName1.column1 = tableName2.column2[AND ...];
WHERE 后跟的是连接条件和查询条件
2）自连接：有时需要自己和自己进行连接，叫做自连接
有如下的表temp
CREATE TABLE emp(
id INT AUTO_INCRETMENT PRIMARY KEY,
name VARCAHR(255),
mangerId INT,
FOREIGN KEY(managerId) references temp(id)
);
其中有四条记录
id                     name                 managerId
1                       aaa                        null
2                       bbb                         1
3                       ccc                          1
4                       ddd                         1

对该表进行查询操作：
SELECT employee.id, employee.name 员工名, manager.name 经理名
FROM emp employee, emp manager
WHERE employee.managerId = manager.id;
该查询语句使用自连接显示员工和经理的关系，其中：
 employee.name 员工名, manager.name 经理名 员工名和经理名是重命名，在显示列时显示员工名和经理名；
 FROM emp employee, emp manager 两个相同的表需要进行区分，起不同的名字；
 WHERE employee.managerId = manager.id 是连接条件
五.事务处理
1.事务是由一步或几步数据库操作序列组成的逻辑执行单元。

这系列操作要么全部执行，要么全部放弃执行。程序和事务是完全两种不同的概念。一般而言，一段程序中可能包含多个事务。在MySQL中，有多种引擎，最常用的两个引擎：InnoDB和MyISAM，其中InnoDB是支持事务的，而MyISAM是不支持的，可以在config配置文件中对其进行修改。

2.事务的四个特性：

原子性（Atomicity）：事务是应用中最小的执行单位。
一致性（Consistency）：事务执行的结果必须让数据库从一个一致性的状态变成另外一个一致性的状态。一致性是通过原子性保证的
隔离性（Isolation）：各个事务执行相互不干扰。
持续性（Durability）：也成为持久性（Persistence），指事务一旦提交，将数据做的任何改变都保存进物理数据库。
这四个特性也叫ACID性

3.数据库的事务由一组DML语句、一条DDL语句和一条DCL语句组成

DML语句对数据进行操作
DDL和DCL都各只有一条，因为DDL和DCL语句都会将事务提交
4.事务的提交：

显示提交：commit
自动提交：DDL/DCL语句
MySQL默认关闭事务（自动提交），在默认情况下，用户输入一条DML语句也会提交该操作，为了开启事务可以通过以下语句对自动提交进行设置

SET AUTOCOMMIT = {0|1}       0是关闭自动提交（开启事务），1是开启自动提交（关闭事务）
5.事务的回滚（rollback）

事务包含的任意一个数据库操作执行失败后执行回滚事务，将该事务中进行的操作全部失效。两种方式：

显示回滚：rollback
自动回滚：系统错误或强行退出
6.例子：

若只是临时开启一个事务可以通过：start transaction或begin开启临时事务，在其之后的DML语句都不会立即执行，直到出现事务的提交或回滚才结束事务。

BEGIN;
INSERT INTO student VALUES(NULL,'001','aaa');
INSERT INTO student VALUES(NULL,'002','bbb');
INSERT INTO student VALUES(NULL,'003','ccc');
SELECT * FROM student;                ①
ROLLBACK;
SELECT * FROM student;                ②
①语句查询的结果中包含插入的数据，但如果此时在别的命令行窗口中执行该语句，也不会看到以上的三条数据，体现了事务的隔离性，这三条数据其实并没有写入物理数据库；
在执行了回滚操作后，在②的查询语句的结果中看不到begin之后的那三条数据

INSERT INTO student VALUES(NULL,'001','aaa');
INSERT INTO student VALUES(NULL,'002','bbb');
SAVEPOINT p;
INSERT INTO student VALUES(NULL,'003','ccc');
SELECT * FROM student;                ①
ROLLBACK TO p;
SELECT * FROM student;                ②
MySQL还提供关键字SAVEPOINT设置中间点，可以设置回滚的位置，①处的查询语句结果中包含三条插入数据的结果，但②处的查询结果中不包含中间点p之后插入的数据。需要注意的是，回到中间点的回滚不会结束事务。