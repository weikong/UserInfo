-- auto-generated definition
create table goods_collect
(
	id int not null
		primary key,
	product_id varchar(50) null,
	name varchar(100) null,
	`desc` varchar(255) null,
	price float null,
	address varchar(255) null,
	time timestamp default CURRENT_TIMESTAMP not null
)
;

