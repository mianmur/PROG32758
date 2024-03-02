
create table student (
  student_id       		LONG NOT NULL Primary Key AUTO_INCREMENT,
  number    	LONG NOT NULL UNIQUE,
  name   		VARCHAR(128) NOT NULL 
);

create table course (
  course_id       		LONG NOT NULL Primary Key AUTO_INCREMENT,
  student_id   	LONG NOT NULL,
  name     		VARCHAR(128) NOT NULL,
  grade			LONG NOT NULL
);

alter table course
  add constraint students_courses_fk foreign key (student_id)
  references student (student_id);

insert into student (number, name)
	values (1234, 'Charlie Brown');
 
insert into student (number, name)
	values (4321, 'Lucy');
 
insert into course (name, grade, student_id)
values ('Java 1', 83, 1);
 
insert into course (name, grade, student_id)
values ('Java 3', 92, 2);

insert into course (name, grade, student_id)
values ('Databases', 78, 2);

	