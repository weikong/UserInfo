-- auto-generated definition
create table goods_collect
(
	id int not null
		primary key,
	account_id int NULL,
	product_id varchar(50) null,
	product_name varchar(100) null,
	product_desc varchar(255) null,
	price float null,
	address varchar(255) null,
	time timestamp default CURRENT_TIMESTAMP not null
)
;

