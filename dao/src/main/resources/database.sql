create database nitesh;
use nitesh;
show tables;

select * from referral_user;
select * from referral_maping;

SET FOREIGN_KEY_CHECKS = 0;
drop table laptop;
drop table student;
drop table test;
drop table sample_table;
drop table referral_user;
drop table referral_maping;
drop table question;
drop table answer;
drop table question_answers;
drop table answer_question;
drop table employee;
drop table project;
drop table employee_project;
drop table project_employee;
SET FOREIGN_KEY_CHECKS = 1;
