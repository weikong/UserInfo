-- auto-generated definition
create table verification_code
(
	id INT NOT NULL auto_increment
		PRIMARY KEY,
	order_id VARCHAR(50) NULL,
  verification_code VARCHAR(50) NULL,
  flag INT NULL,
	time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)
;


