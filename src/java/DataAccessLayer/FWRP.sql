DROP DATABASE IF EXISTS FWRP;
CREATE DATABASE FWRP;
USE FWRP;

-- SQL statements for table creation

-- Users Table
CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    address VARCHAR(50),
    phone_num VARCHAR(50),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    charity_name VARCHAR(50),
    retailer_name VARCHAR(50),
    Users ENUM('retailer', 'consumer', 'charitable_org.')  NOT NULL --Users should be userType
);

-- Inventory Table
CREATE TABLE Inventory (
    inventory_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    item_name VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    expiration_date DATE NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- Subscriptions Table
CREATE TABLE Subscriptions (
    subscription_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    location VARCHAR(50) NOT NULL,
    charity_name VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
DELIMITER $$

CREATE TRIGGER before_subscription_insert
BEFORE INSERT ON Subscriptions
FOR EACH ROW
BEGIN
    SELECT address, charity_name INTO @user_address, @charity_name FROM Users WHERE user_id = NEW.user_id;
    SET NEW.location = @user_address;
    SET NEW.charity_name = @charity_name;  
END$$

DELIMITER ;

select * from users;
select * from subscriptions;
select * from inventory;



-- 2. MODIFIED THE DATABSE - by Rustom 01-04-2024

DROP DATABASE IF EXISTS FWRP;
CREATE DATABASE FWRP;
USE FWRP;

-- SQL statements for table creation

-- Users Table
CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    address VARCHAR(50),
    phone_num VARCHAR(50),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    charity_name VARCHAR(50),
    retailer_name VARCHAR(50),
    Users ENUM('retailer', 'consumer', 'charitable_org.')  NOT NULL
);

-- Inventory Table
CREATE TABLE Inventory (
    inventory_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    item_name VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
-- expiration_date DATE NOT NULL,
-- Subscriptions Table
CREATE TABLE Subscriptions (
    subscription_id INT PRIMARY KEY AUTO_INCREMENT,
    subscriber_id INT,
    retailer_id INT,
    location VARCHAR(50),
    retailer_name VARCHAR(50),
    FOREIGN KEY (subscriber_id) REFERENCES Users(user_id),
    FOREIGN KEY (retailer_id) REFERENCES Users(user_id)
);


DELIMITER $$

CREATE TRIGGER before_subscription_insert
BEFORE INSERT ON Subscriptions
FOR EACH ROW
BEGIN
    SELECT address, retailer_name INTO @retailer_address, @retailer_name FROM Users WHERE user_id = NEW.retailer_id;
    SET NEW.location = @retailer_address;
    SET NEW.retailer_name = @retailer_name;
END$$

DELIMITER ;


select * from users;
select * from subscriptions;
select * from inventory;

-- added two columns in inventory table by Vaishali
use fwrp;
alter table inventory add column for_consumer tinyint(1) default 0, add column for_charity tinyint(1) default 0;