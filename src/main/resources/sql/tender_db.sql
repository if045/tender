DROP DATABASE IF EXISTS tender;
CREATE DATABASE tender;

USE tender;

CREATE TABLE measurement (

	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(15) UNIQUE NOT NULL,
	
	PRIMARY KEY (id)

);

CREATE TABLE location (

	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) UNIQUE NOT NULL,
	
	PRIMARY KEY (id)

);

CREATE TABLE conflict_status (

	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(15) UNIQUE NOT NULL,
	
	PRIMARY KEY (id)

);

CREATE TABLE checked_status (

	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(15) UNIQUE NOT NULL,

	PRIMARY KEY (id)

);

CREATE TABLE tender_status (

	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(15) UNIQUE NOT NULL,
	active BOOLEAN NOT NULL,

	PRIMARY KEY (id)
);

CREATE TABLE category (

	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) UNIQUE NOT NULL,
	parent INT,

	FOREIGN KEY (parent) REFERENCES category(id),
	PRIMARY KEY(id)

);

CREATE TABLE role (

	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(10) UNIQUE NOT NULL,

	PRIMARY KEY(id)

);

CREATE TABLE user (
	
	id INT NOT NULL AUTO_INCREMENT,
	login VARCHAR(30) UNIQUE NOT NULL,
	password VARCHAR(15) NOT NULL,
	create_date DATE NOT NULL,
	enabled TINYINT NOT NULL DEFAULT 1 ,

	PRIMARY KEY(id)
);

CREATE TABLE address (

	id INT NOT NULL AUTO_INCREMENT,
	city VARCHAR(30),
	street VARCHAR(30),
	building_number INT,
	postcode INT,
	
	PRIMARY KEY (id)

);

CREATE TABLE company (

	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(50),
	srn INT,
	email VARCHAR(30) NOT NULL,
	logo LONGBLOB,
	address_id INT NOT NULL,

	FOREIGN KEY (address_id) REFERENCES address(id),
	PRIMARY KEY (id)

);

CREATE TABLE profile (
	
	id INT NOT NULL AUTO_INCREMENT,
	icon LONGBLOB,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	birthday DATE,
	telephone VARCHAR(20) NOT NULL,
	checked BOOLEAN NOT NULL,
	person ENUM('L','P') NOT NULL,
	user_id INT NOT NULL,
	company_id INT,

	FOREIGN KEY (company_id) REFERENCES company(id),
	FOREIGN KEY (user_id) REFERENCES user(id),
	PRIMARY KEY(id)
);

CREATE TABLE tender (

	id INT UNIQUE NOT NULL AUTO_INCREMENT,
	author_id INT NOT NULL,
	title VARCHAR(30) NOT NULL,
	create_date DATE NOT NULL,
	end_date DATE NOT NULL,
	status_id INT NOT NULL,
	suitable_price DECIMAL (13,2) NOT NULL,
	description TEXT,
	
	FOREIGN KEY (status_id) REFERENCES tender_status(id),
	FOREIGN KEY (author_id) REFERENCES profile(id),
	PRIMARY KEY(id)

);

CREATE TABLE item (

	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(15) NOT NULL,
	type ENUM ('P' , 'S') NOT NULL,
	category_id INT NOT NULL,

	FOREIGN KEY (category_id) REFERENCES category(id),
	PRIMARY KEY (id)

);

CREATE TABLE unit (

	id INT NOT NULL AUTO_INCREMENT,
	quantity DOUBLE(10,2) NOT NULL,
	measurement_id INT NOT NULL,
	item_id INT NOT NULL,
	tender_id INT NOT NULL,

	FOREIGN KEY (item_id) REFERENCES item(id),
	FOREIGN KEY (measurement_id) REFERENCES measurement(id),
	FOREIGN KEY (tender_id) REFERENCES tender(id),
	PRIMARY KEY (id)

);

CREATE TABLE moderator_category (

	id INT NOT NULL AUTO_INCREMENT,
	user_id INT NOT NULL,
	category_id INT NOT NULL,

	FOREIGN KEY (user_id) REFERENCES user(id),
	FOREIGN KEY (category_id) REFERENCES  category(id),
	PRIMARY KEY (id)

);

CREATE TABLE checked_profile (

	id INT NOT NULL AUTO_INCREMENT,
	profile_id INT NOT NULL,
	moderator_id INT NOT NULL,
	status_id INT NOT NULL,

	FOREIGN KEY (profile_id) REFERENCES profile(id),
	FOREIGN KEY (moderator_id) REFERENCES user(id),
	FOREIGN KEY (status_id) REFERENCES checked_status(id),
	PRIMARY KEY (id)
);

CREATE TABLE tender_location (

	id INT NOT NULL AUTO_INCREMENT,
	tender_id INT NOT NULL,
	location_id INT NOT NULL,

	FOREIGN KEY (tender_id) REFERENCES tender(id),
	FOREIGN KEY (location_id) REFERENCES location(id),
	PRIMARY KEY (id)
);

CREATE TABLE proposal (

	id INT NOT NULL AUTO_INCREMENT,
	discount_percentage	INT,
	discount_currency DECIMAL(13,2),
	description	TEXT,
	seller_id INT  NOT NULL,
	tender_id INT  NOT NULL,

	FOREIGN KEY (seller_id) REFERENCES user(id),
	FOREIGN KEY (tender_id) REFERENCES tender(id),
	PRIMARY KEY (id)

);

CREATE TABLE bid (

	id INT NOT NULL AUTO_INCREMENT,
	price DECIMAL(13,2)  NOT NULL,
	date DATE  NOT NULL,
	proposal_id INT,
	unit_id INT NOT NULL,

	FOREIGN KEY (unit_id) REFERENCES unit(id),
	FOREIGN KEY (proposal_id) REFERENCES proposal(id),
	PRIMARY KEY (id)

);

CREATE TABLE conflict (

	id INT NOT NULL AUTO_INCREMENT,
	moderator_id INT NOT NULL,
	bid_id INT NOT NULL,
	description TEXT NOT NULL,
	status_id INT NOT NULL,

	FOREIGN KEY (moderator_id) REFERENCES user(id),
	FOREIGN KEY (bid_id) REFERENCES bid(id),
	FOREIGN KEY (status_id) REFERENCES conflict_status(id),
	PRIMARY KEY (id)
);

CREATE TABLE user_role (

	id INT NOT NULL AUTO_INCREMENT,
	user_id INT NOT NULL,
	role_id INT NOT NULL,

	FOREIGN KEY (user_id) REFERENCES user(id),
	FOREIGN KEY (role_id) REFERENCES role(id),
	PRIMARY KEY (id)

);

CREATE TABLE feedback (

	id INT NOT NULL AUTO_INCREMENT,
	user_id INT NOT NULL,
	profile_id INT NOT NULL,
	communication INT,
	speed INT,
	logistic INT,
	comment TEXT,

	FOREIGN KEY (user_id) REFERENCES user(id),
	FOREIGN KEY (profile_id) REFERENCES profile(id),
	PRIMARY KEY (id)

);

CREATE TABLE deal_status (

	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(15) UNIQUE NOT NULL,

	PRIMARY KEY (id)
);

CREATE TABLE deal (

	id INT NOT NULL AUTO_INCREMENT,
	bid_id	INT NOT NULL,
	proposal_id INT NOT NULL,
	customer_id INT NOT NULL,
	seller_id INT NOT NULL,
	sum	DECIMAL(13,2) NOT NULL,
	date DATE NOT NULL,
	status_id INT NOT NULL,

	FOREIGN KEY (status_id) REFERENCES deal_status(id), 
	FOREIGN KEY (proposal_id) REFERENCES proposal(id), 
	FOREIGN KEY (bid_id) REFERENCES bid(id),
	FOREIGN KEY (customer_id) REFERENCES profile(id),
	FOREIGN KEY (seller_id) REFERENCES profile(id),
	PRIMARY KEY (id)

);

CREATE TABLE comment (

	id INT NOT NULL AUTO_INCREMENT,
	tender_id INT NOT NULL,
	user_id	INT NOT NULL,
	message	TEXT NOT NULL,
	date DATE NOT NULL,

	FOREIGN KEY (tender_id) REFERENCES tender(id),
	FOREIGN KEY (user_id) REFERENCES user(id),
	PRIMARY KEY (id)

);

CREATE TABLE seller_location (

	id INT NOT NULL AUTO_INCREMENT,
	seller_id INT NOT NULL,
	location_id INT NOT NULL,

	FOREIGN KEY (seller_id) REFERENCES user(id),
	FOREIGN KEY (location_id) REFERENCES location(id),
	PRIMARY KEY (id)

);

CREATE TABLE seller_category (

	id INT NOT NULL AUTO_INCREMENT,
	seller_id INT NOT NULL,
	category_id INT NOT NULL,

	FOREIGN KEY (seller_id) REFERENCES user(id),
	FOREIGN KEY (category_id) REFERENCES location(id),
	PRIMARY KEY (id)

);
