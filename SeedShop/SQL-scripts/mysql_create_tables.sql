use seedsdb;

/* info table of user genders */
/* DROP TABLE USER_GENDER; */

CREATE TABLE USER_GENDER(
 gender_id  INT NOT NULL,
 name VARCHAR(1),
 used BOOLEAN DEFAULT TRUE,
 PRIMARY KEY (gender_id)
);

/* table of users */
/* DROP TABLE APPUSER; */

CREATE TABLE APPUSER(
 user_id  BIGINT NOT NULL,
 sec_name VARCHAR(30),
 first_name VARCHAR(20),
 third_name VARCHAR(20),
 email VARCHAR(30),
 phones VARCHAR(40),
 discount DECIMAL(6,2) DEFAULT '0.0',
 birthday DATE,
 gender_id INT,
 country VARCHAR(20),
 region VARCHAR(30),
 area VARCHAR(30),
 city VARCHAR(30),
 login VARCHAR(25),
 passwd_hash VARCHAR(64),
 role VARCHAR(20) DEFAULT 'ROLE_USER',
 sess_id VARCHAR(32),
 temp BOOLEAN DEFAULT TRUE,
 used BOOLEAN DEFAULT TRUE,
 PRIMARY KEY (user_id),
 FOREIGN KEY (gender_id) REFERENCES USER_GENDER (gender_id)
);

/* info table of delivery services */
/* DROP TABLE DELIVERY_SERVICE; */

CREATE TABLE DELIVERY_SERVICE(
 delivery_id INT NOT NULL,
 name VARCHAR(30),
 collect_avail BOOLEAN DEFAULT FALSE,
 used BOOLEAN DEFAULT TRUE,
 PRIMARY KEY (delivery_id)
);

/* info table of delivery statuses */
/* DROP TABLE DELIVERY_STATUS; */

CREATE TABLE DELIVERY_STATUS(
 status_id INT NOT NULL,
 status VARCHAR(30),
 used BOOLEAN DEFAULT TRUE, 
 PRIMARY KEY (status_id)
);

/* info table of available product locations */
/* DROP TABLE PRODUCT_LOCATION; */

CREATE TABLE PRODUCT_LOCATION(
 location_id INT NOT NULL,
 name VARCHAR(15),
 used BOOLEAN DEFAULT TRUE,
 PRIMARY KEY (location_id)
);

/* table of invoices */
/* DROP TABLE INVOICE; */

CREATE TABLE INVOICE( 
 order_id BIGINT NOT NULL,
 user_id  BIGINT NOT NULL,  
 order_date DATE,
 paid_date DATE,
 sent_date DATE,
 discount DECIMAL(6,2),
 pay DECIMAL(6,2),
 status_id INT NOT NULL,
 sec_name VARCHAR(30),
 first_name VARCHAR(20),
 third_name VARCHAR(20),
 phone VARCHAR(40), 
 country VARCHAR(20),
 region VARCHAR(30),
 area VARCHAR(30),
 city VARCHAR(30),
 source_id INT,
 destination_id INT,
 current_loc_id INT,
 delivery_id INT,
 delivery_office INT,
 prepayment BOOLEAN,
 declaration VARCHAR(30),
 backorder_id BIGINT,
 add_info_u TEXT,
 add_info_m TEXT,
 PRIMARY KEY (order_id),
 FOREIGN KEY (user_id) REFERENCES APPUSER (user_id),
 FOREIGN KEY (delivery_id) REFERENCES DELIVERY_SERVICE (delivery_id),
 FOREIGN KEY (status_id) REFERENCES DELIVERY_STATUS (status_id),
 FOREIGN KEY (source_id) REFERENCES PRODUCT_LOCATION (location_id),
 FOREIGN KEY (destination_id) REFERENCES PRODUCT_LOCATION (location_id),
 FOREIGN KEY (current_loc_id) REFERENCES PRODUCT_LOCATION (location_id),
 FOREIGN KEY (backorder_id) REFERENCES INVOICE (order_id)
);

/* info table of manufactures */
/* DROP TABLE MANUFACTURE; */

CREATE TABLE MANUFACTURE(
 manufact_id INT NOT NULL,
 name VARCHAR(50),
 address VARCHAR(100),
 used BOOLEAN DEFAULT TRUE,
 PRIMARY KEY (manufact_id)
);

/* table describe one product */
/* DROP TABLE A_PRODUCT; */

CREATE TABLE A_PRODUCT(
 product_id INT NOT NULL,
 parent_id INT,
 name VARCHAR(30),
 used BOOLEAN DEFAULT TRUE,
 PRIMARY KEY (product_id),
 FOREIGN KEY (parent_id) REFERENCES A_PRODUCT (product_id)
);

/* info table of packages */
/* DROP TABLE PACK; */

CREATE TABLE PACK(
 pack_id INT NOT NULL,
 name VARCHAR(20),
 used BOOLEAN DEFAULT TRUE,
 PRIMARY KEY (pack_id)
);

/* info table of packing */
/* DROP TABLE PACKING; */

CREATE TABLE PACKING(
 packing_id INT NOT NULL,
 weight DECIMAL(6,2),
 amount INT,
 pack_id INT NOT NULL,
 used BOOLEAN DEFAULT TRUE,
 PRIMARY KEY (packing_id),
 FOREIGN KEY (pack_id) REFERENCES PACK (pack_id)
);

/* table of exist products */
/* DROP TABLE PRODUCT; */

CREATE TABLE PRODUCT(
 barcode VARCHAR(15) NOT NULL,
 manufact_id INT NOT NULL,
 product_id INT NOT NULL,
 packing_id INT NOT NULL,
 price DECIMAL(6,2),
 used BOOLEAN DEFAULT TRUE,
 PRIMARY KEY (barcode),
 FOREIGN KEY (manufact_id) REFERENCES MANUFACTURE (manufact_id),
 FOREIGN KEY (product_id) REFERENCES A_PRODUCT (product_id),
 FOREIGN KEY (packing_id) REFERENCES PACKING (packing_id)
);

/* table of orders */
/* DROP TABLE AN_ORDER; */

CREATE TABLE AN_ORDER(
 id BIGINT NOT NULL,
 order_id BIGINT NOT NULL,
 barcode VARCHAR(15),
 price DECIMAL(6,2),
 amount INT,
 PRIMARY KEY (id),
 FOREIGN KEY (barcode) REFERENCES PRODUCT (barcode),
 FOREIGN KEY (order_id) REFERENCES INVOICE (order_id)
);

/* info table of products availability on different locations */
/* DROP TABLE AVAILABILITY; */

CREATE TABLE AVAILABILITY(
 id INT NOT NULL,
 location_id INT NOT NULL,
 barcode VARCHAR(15) NOT NULL,
 available INT DEFAULT 0,
 reserv INT DEFAULT 0,
 PRIMARY KEY (id),
 FOREIGN KEY (location_id) REFERENCES PRODUCT_LOCATION (location_id),
 FOREIGN KEY (barcode) REFERENCES PRODUCT (barcode)
);







