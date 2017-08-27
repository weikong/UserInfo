-- auto-generated definition
# ALTER TABLE Address CHANGE account_id account_name VARCHAR(100) NULL

# ALTER TABLE Address ADD COLUMN account_id INT NULL AFTER id;

-- ALTER TABLE Address ADD COLUMN account_id INT NULL AFTER id;

# drop index product_id on Product;

# alter table Product drop primary key;

ALTER TABLE Product ADD COLUMN id INT NOT NULL auto_increment primary key;

# alter table Product add primary key(id);

# alter table Product change id id int primary key auto_increment;