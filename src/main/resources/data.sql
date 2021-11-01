## run this first
## create database dashboard;

## run the application the come back to initialize this values
insert into  faculty(id,code,name)
values (1,'IT', 'Information Technology');

insert into course (id, code, faculty_id, name)
values (1,'BSc IT',1,'Bachelor of Science in Information Technology'),
       (2,'Dip IT',1,'Diploma in Information Technology');

insert into class (id, name, year, course_id)
values (1,'BSC',1,1),(2,'BSC',2,1),(3,'BSC',3,1),
       (4,'DIT',1,2),(5,'DIT',2,2),(6,'DIT',3,2);

insert into staff (id,title,firstname,surname,gender,cellphone,email,designation,reg_date)
values (10000,'Mrs','Kelly','Johnson','Female','0785607730','kjohnson@hotmail.com','Admin','2017-02-01'),
       (10001,'Mr','Harold','Chen','Male','0785607730','harolc@hotmail.com','Teacher','2017-02-01'),
       (10002,'Mrs','Dana','Yelle','Female','0678523840','danay@hotmail.com','Teacher','2014-01-15'),
       (10003,'Mrs','Jennifer','Acosta','Female','0634197387','jeniffera@gmail.com','Teacher','2012-01-16'),
       (10004,'Mr','Harry','Miller','Male','0665777030','harrym@gmail.com','Teacher','2018-04-12'),
       (10005,'Mr','Eric','Varela','Male','0663824442','ericv@gmail.com','Teacher','2014-02-01'),
       (10006,'Mrs','Nancy','Cook','Female','0662483375','nancyc@gmail.com','Teacher','2010-02-01'),
       (10007,'Mrs','Mary','McMinn','Female','0786234583','marym@gmail.com','Teacher','2015-02-02'),
       (10008,'Mr','Angel','Flynn','Male','0782882306','angelf@gmail.com','Teacher','2015-02-02'),
       (10009,'Mrs','Valorie','Goforth','Female','0785844199','valorieg@gmail.com','Teacher','2015-02-04'),
       (10010,'Mr','Robert','Norris','Male','0783051366','robertn@gmail.com','Manager','2013-02-04'),
       (10011,'Mrs','Molly','Lowe','Female','0742039485','mollyl@gmail.com','Stakeholder','2015-02-04');

insert into subject(id, code, name, semester, classroom_id, staff_id)
values (1,'PRO511','Programming 511',1,1,10001),
       (2,'PRO512','Programming 512',2,1,10001),

       (3,'WBT511','Web Technology 511',1,1,10002),
       (4,'WBT512','Web Technology 512',2,1,10002),

       (5,'PRO621','Programming 621',1,2,10003),
       (6,'PRO622','Programming 622',2,2,10003),

       (7,'IPR621','Internet Programming 621',1,2,10004),
       (8,'IPR622','Internet Programming 622',2,2,10004),

       (9,'PRO731','Programming 731',1,3,10005),
       (10,'PRO732','Programming 732',2,3,10005),

       (11,'PRO741','Programming 741',1,3,10006),
       (12,'PRO742','Programming 742',2,3,10006);


delete from second_year_marks;