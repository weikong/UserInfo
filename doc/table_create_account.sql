-- auto-generated definition
create table account
(
	id int(20) not null auto_increment
		primary key,
	name varchar(50) null,
	account varchar(100) null,
	password varchar(100) not null,
	type int(2) default '1' not null,
	status int(2) null,
	create_at timestamp DEFAULT current_timestamp
)
;

comment on column account.type is '邮箱：1；电话：2'
;

