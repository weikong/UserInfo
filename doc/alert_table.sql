-- auto-generated definition
# ALTER TABLE Address CHANGE account_id account_name VARCHAR(100) NULL;
#
# ALTER TABLE Address ADD COLUMN account_id INT NULL AFTER id;

# drop index product_id on Product;
# alter table Product drop primary key;
# ALTER TABLE Product ADD COLUMN id INT NOT NULL auto_increment primary key;
# alter table Product add primary key(id);
# alter table Product change id id int primary key auto_increment;

# ALTER TABLE orders ADD COLUMN status INT NULL AFTER order_id;
# ALTER TABLE Address ADD COLUMN state INT NULL AFTER update_time;
# ALTER TABLE Address CHANGE create_time create_time timestamp default CURRENT_TIMESTAMP not null;
# ALTER TABLE Address CHANGE update_time update_time timestamp default CURRENT_TIMESTAMP not null;

# ALTER TABLE order_item DROP COLUMN state;

ALTER TABLE order_item ADD COLUMN state INT NULL AFTER time;