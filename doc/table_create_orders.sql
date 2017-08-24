-- auto-generated definition
create table orders
(
	id int not null auto_increment
		primary key,
	account_id int NULL,
	order_id varchar(50) not null,
	time timestamp DEFAULT current_timestamp
)
;

create index order_id
  on orders (order_id)
;

