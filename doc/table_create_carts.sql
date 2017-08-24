-- auto-generated definition
create table carts
(
	id int not null auto_increment
		primary key,
	account_id int NULL,
	product_id varchar(50) not null,
	name varchar(100) null,
	`desc` varchar(255) null,
	price float null,
	count int null,
	address varchar(255) null,
	time timestamp DEFAULT current_timestamp
)
;

create index product_id
  on carts (product_id)
;


