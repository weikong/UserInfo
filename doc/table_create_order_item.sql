-- auto-generated definition
create table order_item
(
	id int not null auto_increment
		primary key,
	account_id int NULL,
	order_id varchar(50) not null,
	product_id varchar(50) not null,
	name varchar(100) null,
	`desc` varchar(255) null,
	price float null,
	count int null,
	address varchar(255) null,
	time timestamp DEFAULT current_timestamp,
	state int null
)
;
create index order_id
  on order_item (order_id)
;
create index product_id
  on order_item (product_id)
;

comment on column order_item.state is '待发货：1；待收货：2；已完成：9'
;

