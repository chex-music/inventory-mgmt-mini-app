CREATE DATABASE inventory_mgmt;
USE inventory_mgmt;

CREATE TABLE CATEGORY_MASTER (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL UNIQUE,
    category_desc TEXT
);
CREATE TABLE SUPPLIER_MASTER (
    supplier_id INT AUTO_INCREMENT PRIMARY KEY,
    supplier_name VARCHAR(100) NOT NULL,
    contact_no VARCHAR(15),
    email VARCHAR(100) UNIQUE
);
CREATE TABLE PRODUCT_INVENTORY (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(150) NOT NULL,
    category_id INT,
    supplier_id INT,
    quantity INT DEFAULT 0,
    price DECIMAL(10,2),

    FOREIGN KEY (category_id) REFERENCES CATEGORY_MASTER(category_id),
    FOREIGN KEY (supplier_id) REFERENCES SUPPLIER_MASTER(supplier_id)
);

select * from CATEGORY_MASTER;
select * from SUPPLIER_MASTER;
select * from PRODUCT_INVENTORY;

SELECT category_name,product_name
FROM PRODUCT_INVENTORY as p
LEFT JOIN CATEGORY_MASTER as c
ON p.category_id = c.category_id;

SELECT s.supplier_id
FROM SUPPLIER_MASTER as s
LEFT JOIN CATEGORY_MASTER as c
ON s.supplier_id = c.category_id;

drop table PRODUCT_INVENTORY;
truncate table CATEGORY_MASTER;
truncate table SUPPLIER_MASTER;

INSERT INTO SUPPLIER_MASTER (supplier_name, contact_no, email) VALUES
('TechWorld Suppliers', '9876543210', 'techworld@gmail.com'),
('UrbanStyle Pvt Ltd', '9123456780', 'urbanstyle@gmail.com'),
('FreshMart Distributors', '9988776655', 'freshmart@gmail.com'),
('BookHub Traders', '9011223344', 'bookhub@gmail.com'),
('FitLife Sports', '9090909090', 'fitlife@gmail.com'),
('HomeEssentials', '9345678123', 'homeessentials@gmail.com');
INSERT INTO CATEGORY_MASTER (category_name, category_desc) VALUES
('Electronics', 'Electronic devices and accessories'),
('Clothing', 'Men and women clothing'),
('Groceries', 'Daily grocery items'),
('Books', 'Educational and story books'),
('Footwear', 'Shoes, slippers, sandals'),
('Furniture', 'Home and office furniture'),
('Beauty', 'Beauty and personal care'),
('Sports', 'Sports and fitness items'),
('Toys', 'Kids toys and games'),
('Stationery', 'Office and school supplies');

SELECT category_name FROM CATEGORY_MASTER WHERE category_id = 2;
SELECT supplier_name FROM SUPPLIER_MASTER WHERE supplier_id = 1;
UPDATE CATEGORY_MASTER SET category_name = "chex", category_desc="lmnop" WHERE category_id =1;
UPDATE CATEGORY_MASTER SET category_name =?, category_desc = ? WHERE category_id = ?;
SELECT supplier_id, supplier_name, contact_no, email FROM SUPPLIER_MASTER;