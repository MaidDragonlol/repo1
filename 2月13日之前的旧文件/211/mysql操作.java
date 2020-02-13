SQL��Structured Query Language����䣬���ṹ����ѯ���ԣ��ǲ����ͼ�����ϵ���ݿ�ı�׼���ԡ�SQL���һ���Ϊ���¼��֣�

DCL��Database Control Language�����ݿ������ԣ���䣺��Ҫ��GRANT��REVOKE�����ؼ������
DDL��Database Definition Language�����ݶ������ԣ���䣺��Ҫ��CREATE��ALTER��DROP��TRUNCATE�ĸ��ؼ������
DML��Database Manipulation Language�����ݲ������ԣ���䣺��Ҫ��INSERT��UPDATE��DELETE�����ؼ������
��ѯ��䣺��Ҫ��SELECT������
���������䣺��Ҫ��COMMIT��ROLLBACK��SAVEPOINT�����ؼ������
ע��SQL��䲻���ִ�Сд������create��CREATE����ͬ��

һ.DCL���

DCL�����Ƕ��û�������Ȩ����Ȩ�ջصĲ��������ԶԲ�ͬ���û���Ȩ�޽��п��ƣ��������ݿⰲȫ�ԣ��������ݿ��ά����һ�㶼�����ݿ����Աʹ�ó����û�root���в�����

MySQL��Ȩ��������grant��Ȩ�޳���������ʱrevoke��

1.grant��Ȩ��ʽ��

grant Ȩ���б� on ��.�� to �û���@'ip' identified by "����";

2.revoke����Ȩ�޸�ʽ��

revoke Ȩ���б� on ��.�� from �û���@'ip';

��.DDL���
���ݿ����
��table���������ֵ䡢Լ����constraint������ͼ��view����������index����������function�����洢���̣�procedure������������trigger��
CREATE��ALTER��DELETE�ؼ��ֱַ��Ǵ������޸ĺ�ɾ�����ݿ����ģ��˴�ʹ������ʹ�����ĶԱ�Ĳ���������
1.CREATE:

CREATE TABLE [ģʽ��.] ����(
�ñ��е��ж���
);
CREATE TABLE test(
StuId VARCHAR(8) PRIMARY KEY,
StuName VARCHAR(30) NOT NULL,
StuAge SMALLINT NOT NULL,
StuBirth DATETIME
);
ע���鿴��ṹ��DESCRIBE ����;
2.ALTER:
1������У�
ALTER TABLE ����
ADD column columnName1 datatype [default expr] [FIRST|AFTER colName];
columnName1������ӵ�������
datatype���������ͣ�
default expr��������Լ����
FIRST|AFTER colName������λ�ã�Ĭ���ǲ��������һ�У�FIRST���ڵ�һ�У�AFTER colName����ָ���к����
ALTER TABLE test
ADD column StuMajor VARCHAR(20) NOT NULL AFTER StuName;
2���޸���
ALTER TABLE ���� CHANGE oldName newName datatype; 
ALTER TABLE test CHANGE StuBirth Birthday year;
3��ɾ����
ALTER TABLE ���� DROP column columnName;
ALTER TABLE test DROP column StuMajor;
4���޸ı���
ALTER TABEL ���� RENAME TO �±���;
ALTER TABLE test RENAME TO student;
3.DROP
ɾ����
DROP TABLE ����;
DROP TABLE student;
4.TRUNCATE
ɾ�������������ݵ�������Ľṹ���������ضϡ�
TRUNCATE TABLE ����;
TRUNCATE TABLE student;
��.DML���

1.INSERT

��׼SQL���ֻ����һ�β���һ�����ݣ���MySQL���������չʹ�����һ�β����������
����һ�����ݣ�
INSERT INTO ���� VALUES(value1, value2, ...);
����������ݣ�
INSERT INTO ���� VALUES(value1,value2,...),(value1,value2,...),(value1,value2,...);
INSERT INTO student VALUES(��001','Lisa',20,1997),(��002','Rose',21,1996);
2.UPDATE
UPDATE ���� SET COLUMN1 = VALUE1[, COLUMN2 = VALUE2]...
[WHERE CONDITION];
UPDATE student SET StuAge = StuAge+1 WHERE StuAge>20;
3.DELETE
DELETE FROM ���� [WHERE CONDITION];
DELETE FROM student WHERE Birthday = 1997;
��.��ѯ���

1.�����ѯ��
SELECT COLUMN1�� COLUMN2...
FROM ����Դ
[WHERE CONDITION]
[GROUP BY columnName]
[ORDER BY columnName DESC|ASC]
e.g.���������ѧרҵ��ѧ��ѡ����������ѧ�Ž������У�ֻ��ʾѧ������
SELECT StuName FROM student 
WHERE StuMajor = 'CS'
ORDER BY StuId DESC;
2.����ѯ��
1���򵥵������ӷ�ʽ
SELECT VALUE1[,VALUE2]...
FROM tableName1��tableName2
WHERE tableName1.column1 = tableName2.column2[AND ...];
WHERE ����������������Ͳ�ѯ����
2�������ӣ���ʱ��Ҫ�Լ����Լ��������ӣ�����������
�����µı�temp
CREATE TABLE emp(
id INT AUTO_INCRETMENT PRIMARY KEY,
name VARCAHR(255),
mangerId INT,
FOREIGN KEY(managerId) references temp(id)
);
������������¼
id                     name                 managerId
1                       aaa                        null
2                       bbb                         1
3                       ccc                          1
4                       ddd                         1

�Ըñ���в�ѯ������
SELECT employee.id, employee.name Ա����, manager.name ������
FROM emp employee, emp manager
WHERE employee.managerId = manager.id;
�ò�ѯ���ʹ����������ʾԱ���;���Ĺ�ϵ�����У�
 employee.name Ա����, manager.name ������ Ա�����;�������������������ʾ��ʱ��ʾԱ�����;�������
 FROM emp employee, emp manager ������ͬ�ı���Ҫ�������֣���ͬ�����֣�
 WHERE employee.managerId = manager.id ����������
��.������
1.��������һ���򼸲����ݿ����������ɵ��߼�ִ�е�Ԫ��

��ϵ�в���Ҫôȫ��ִ�У�Ҫôȫ������ִ�С��������������ȫ���ֲ�ͬ�ĸ��һ����ԣ�һ�γ����п��ܰ������������MySQL�У��ж������棬��õ��������棺InnoDB��MyISAM������InnoDB��֧������ģ���MyISAM�ǲ�֧�ֵģ�������config�����ļ��ж�������޸ġ�

2.������ĸ����ԣ�

ԭ���ԣ�Atomicity����������Ӧ������С��ִ�е�λ��
һ���ԣ�Consistency��������ִ�еĽ�����������ݿ��һ��һ���Ե�״̬�������һ��һ���Ե�״̬��һ������ͨ��ԭ���Ա�֤��
�����ԣ�Isolation������������ִ���໥�����š�
�����ԣ�Durability����Ҳ��Ϊ�־��ԣ�Persistence����ָ����һ���ύ�������������κθı䶼������������ݿ⡣
���ĸ�����Ҳ��ACID��

3.���ݿ��������һ��DML��䡢һ��DDL����һ��DCL������

DML�������ݽ��в���
DDL��DCL����ֻ��һ������ΪDDL��DCL��䶼�Ὣ�����ύ
4.������ύ��

��ʾ�ύ��commit
�Զ��ύ��DDL/DCL���
MySQLĬ�Ϲر������Զ��ύ������Ĭ������£��û�����һ��DML���Ҳ���ύ�ò�����Ϊ�˿����������ͨ�����������Զ��ύ��������

SET AUTOCOMMIT = {0|1}       0�ǹر��Զ��ύ���������񣩣�1�ǿ����Զ��ύ���ر�����
5.����Ļع���rollback��

�������������һ�����ݿ����ִ��ʧ�ܺ�ִ�лع����񣬽��������н��еĲ���ȫ��ʧЧ�����ַ�ʽ��

��ʾ�ع���rollback
�Զ��ع���ϵͳ�����ǿ���˳�
6.���ӣ�

��ֻ����ʱ����һ���������ͨ����start transaction��begin������ʱ��������֮���DML��䶼��������ִ�У�ֱ������������ύ��ع��Ž�������

BEGIN;
INSERT INTO student VALUES(NULL,'001','aaa');
INSERT INTO student VALUES(NULL,'002','bbb');
INSERT INTO student VALUES(NULL,'003','ccc');
SELECT * FROM student;                ��
ROLLBACK;
SELECT * FROM student;                ��
������ѯ�Ľ���а�����������ݣ��������ʱ�ڱ�������д�����ִ�и���䣬Ҳ���ῴ�����ϵ��������ݣ�����������ĸ����ԣ�������������ʵ��û��д���������ݿ⣻
��ִ���˻ع��������ڢڵĲ�ѯ���Ľ���п�����begin֮�������������

INSERT INTO student VALUES(NULL,'001','aaa');
INSERT INTO student VALUES(NULL,'002','bbb');
SAVEPOINT p;
INSERT INTO student VALUES(NULL,'003','ccc');
SELECT * FROM student;                ��
ROLLBACK TO p;
SELECT * FROM student;                ��
MySQL���ṩ�ؼ���SAVEPOINT�����м�㣬�������ûع���λ�ã��ٴ��Ĳ�ѯ������а��������������ݵĽ�������ڴ��Ĳ�ѯ����в������м��p֮���������ݡ���Ҫע����ǣ��ص��м��Ļع������������