USE tender;

INSERT INTO checked_status (name) VALUES
('Open'),
('In progress'),
('Checked'),
('Denied');


INSERT INTO tender_status (name) VALUES
('Open'),
('In progress'),
('Close');


INSERT INTO conflict_status (name) VALUE
('Open'),
('In progress'),
('resolved');


INSERT INTO role (name) VALUES
('admin'),
('moderator'),
('seller'),
('customer');


INSERT INTO measurement (name) VALUES
('Kg'),
('Km'),
('Liter'),
('Box'),
('Unit'),
('Ton'),
('Sm'),
('Humen/Hour');


INSERT INTO address (city, street, building_number, postcode) VALUES 
('Vilnohirsk', 'Stepova', 1, 27500),
('Drohobych', 'Khmelnitsky', 5, 29000),
('Kiev', 'Moskovska', 21, 01015),
('Nizhyn', 'Vosstaniya', 8, 16500),
('Dnipropetrovsk', 'Yuzhnaya', 12, 22300),
('Donetsk', 'Dzerzhinsky', 14, 12500),
('Kiev', 'Solomenskaya', 21, 26600),
('Odessa', 'Bunin', 32, 24500),
('Simferopol', 'Timoshenko', 55, 23300),
('Kiev', 'Narodnogo Opolcheniy', 7, 07500),
('Kiev', 'Karla Marksa', 9, 12500),
('Alchevsk', 'Moskovskaya', 34, 32500),
('Novoukrayinka', 'Pershoi Mayivki', 55, 21500),
('Lviv', 'Kravchenko', 2, 26600),
('Odessa', 'Bugaevskaya', 3, 25600);


INSERT INTO company (name, srn, email, logo, address_id) VALUES 
('Vilnohirsk Mining Metallurgical Plant', 121345, 'Vilnohirsk@gmail.com', null, 1),
('Podolsky Smak', 122345, 'PodolskySmak@gmail.com', null, 2),
('Econom-servis Gp CffInfo', 123345, 'EconomservisGp@gmail.com', null, 3),
('Epcf Syaivo LtdInfo', 124345, 'EpcfSyaivo@gmail.com', null, 4),
('Planet Service', 125345, 'PlanetService@gmail.com', null, 5),
('Global Titan Distribution Inc', 126345, 'GlobalTitan@gmail.com', null, 6),
('Sea Electronics', 127345, 'SeaElectronics@gmail.com', null, 7),
('Kaalbye Logistics Ltd', 128345, 'KaalbyeLogistics@gmail.com', null, 8),
('Energy Xxii', 129345, 'EnergyXxii@gmail.com', null, 9),
('Eurocom Components', 120345, 'EurocomComponents@gmail.com', null, 10),
('Natural Foods', 102345, 'NaturalFoods@gmail.com', null, 11),
('Cory Sro', 112345, 'CorySro@gmail.com', null, 12),
('Polymercontainer Plant', 122345, 'PolymerPlant@gmail.com', null, 13),
('Xado Corp', 132345, 'XadoCorp@gmail.com', null, 14),
('Garbuz Studio', 142345, 'GarbuzStudio@gmail.com', null, 15);


INSERT INTO user (login, password, create_date) VALUES
('odin_ogame@ukr.net','arlekin',CURDATE()),
('mail4quest@mail.ru','quest',CURDATE()),
('vasapupkin@gmail.com','pupkin',CURDATE()),
('vasahopnik@rambler.ru','dver88',CURDATE()),
('dota2@gmail.com','noob23',CURDATE()),
('olgamoros@ukr.net','ole4ka',CURDATE()),
('sanja_ivanow@ukr.net','123qwerty',CURDATE()),
('kolja234@qmail.com','vasa567',CURDATE());


INSERT INTO profile (icon, first_name, last_name, birthday, telephone, checked, person, user_id, company_id) VALUES 
(null, 'Ivan', 'Nesamay', '1986-05-28', '55-559-5', false, 'L', 1, 1),
(null, 'Vasja', 'Pupkin', '1966-02-18', '55-559-5', false, 'L', 2, 2),
(null, 'Kolja', 'Qwerty', '1996-01-02', '55-559-5', false, 'L', 3, 3),
(null, 'Olja', 'Semenova', '1984-07-09', '55-559-5', false, 'L', 4, 4),
(null, 'Katja', 'Moros', '1985-07-08', '55-559-5', false, 'L', 5, 5),
(null, 'Nasar', 'Havruliv', '1976-06-08', '55-559-5', false, 'L', 6, 6),
(null, 'Vasul', 'Sobol', '1989-02-02', '55-559-5', false, 'L', 7, 7),
(null, 'Oksana', 'Sobol', '1983-08-01', '55-559-5', false, 'L', 8, 8);


INSERT INTO location (name) VALUES 
('Autonomous Republic of Crimea'),
('Cherkasy Region'),
('Chernihiv Region'), 
('Chernivtski Region'), 
('Dnipropetrovsk Region'), 
('Donetsk Region'),
('Ivano-Frankivsk Region'),
('Kharkiv Region'),
('Kherson Region'), 
('Khmelnytsky Region'),
('Kirovograd Region'),
('Kyiv Region'),
('Luhansk Region'),
('Lviv Region'),
('Mykolayiv Region'),
('Odesa Region'),
('Poltava Region'),
('Rivne Region'),
('Sumy Region'),
('Ternopil Region'),
('Vinnytsya Region'),
('Volyn region'),
('Zakarpattya Region'),
('Zaporizhzhya Region'),
('Zhytomyr Region');


INSERT INTO deal_status (name) VALUES 
('open'),
('in progress'),
('close');


INSERT INTO category (name, parent) VALUES
('Building', NULL),
('Animals', NULL),
('Pet', 2),
('Exotic', 2),
('Dogs', 3),
('Birds', 3),
('Product', NULL),
('Meat', 7),
('Fish', 7),
('Milk', 7),
('Cars', NULL);


INSERT INTO tender (author_id, title, create_date, end_date, status_id, suitable_price, description) VALUES
(1, 'Meal', '2014-06-10', '2014-07-10', 1, 1000000.00, NULL),
(2, 'Materials', CURDATE(), '2014-06-03', 2, 500000.00, NULL),
(3, 'Haus Building', '2014-04-10', CURDATE(), 3, 1600000.00, NULL),
(4, 'Animals', CURDATE(), '2014-06-04', 2, 9900000.00, NULL),
(5, 'Animals', '2014-06-08', '2014-06-18', 1, 19000000.00, NULL),
(6, 'Tourism', '2014-04-12', CURDATE(), 3, 23000000.00, NULL),
(7, 'Industry', '2014-06-07', '2014-06-28', 1, 450000.00, NULL),
(8, 'Products', '2014-06-08', '2014-06-17', 1, 113000000.00, NULL),
(4, 'Products', CURDATE(),'2014-06-15', 2, 14000000.00, NULL),
(2, 'Products', CURDATE(), '2014-06-18', 2, 76000000.00, NULL),
(3, 'All in 0ne', CURDATE(), '2014-06-22', 2, 59000000.00, NULL),

(1, 'Products', '2014-06-10', '2014-06-25', 1, 1000000.00, NULL),
(2, 'Need workers', '2014-05-11', '2014-06-16', 2, 500000.00, NULL),
(3, 'Haus Building', '2014-04-25', CURDATE(), 3, 1600000.00, NULL),
(4, 'Cars', CURDATE(), '2014-06-22', 2, 9900000.00, NULL),
(5, 'Cars', '2014-06-09', '2014-06-19', 1, 19000000.00, NULL),
(6, 'Tourism', '2014-04-12', '2014-06-10', 2, 23000000.00, NULL),
(7, 'Industry', '2014-06-07', '2014-06-28', 1, 450000.00, NULL),
(8, 'Products', '2014-06-08', '2014-06-17', 1, 113000000.00, NULL),
(4, 'Products', CURDATE(),'2014-06-15', 2, 14000000.00, NULL),
(2, 'Products', CURDATE(), '2014-06-18', 2, 76000000.00, NULL);



INSERT INTO proposal (discount_percentage, discount_currency, description, seller_id, tender_id) VALUES
(10, null, 'If you wil take all', 1, 10),
(20, null, null, 1, 9),
(30, null, 'and free delivery', 2, 8),
(50, null, null, 3, 7),
(null, 10000.00, null, 4, 6),
(null, 5000.00, null, 5, 5),
(null, 8000.00, null, 6, 4),
(null, 1400.00, null, 7, 3),
(null, 1500.00, null, 8, 2),
(null, 500.00, null, 4, 1),
(null, 5000.00, null, 1, 7);


INSERT INTO item (name, type, category_id) VALUES
('Rocks', 'P', 1),
('Dogs', 'P', 2),
('Cats', 'P', 3),
('Puffin', 'P', 4),
('Ovcharka', 'P', 5),
('Penguins', 'P', 6),
('Juises', 'P', 7),
('Salami', 'P', 8),
('Gold fish', 'P', 9),
('jogurt', 'P', 10),
('Build team', 'S', 1),
('Excavator', 'S', 1),
('Hrejder', 'S', 1),
('Audi-7', 'P', 11),
('BMW', 'P', 11),
('Kia', 'P', 11);


INSERT INTO unit (quantity, measurement_id, item_id, tender_id) VALUES
(500, 1, 10, 9),
(100, 5, 6, 5),
(5300, 5, 11, 5),
(1200, 6, 1, 3),
(2300, 5, 1, 2),
(1000, 1, 8, 1),
(666, 5, 1, 3),
(999, 5, 3, 4),
(1023, 3, 7, 10),
(512, 5, 2, 5),

(500, 8, 12, 11),

(500, 1, 10, 12),
(100, 8, 11, 13),
(5300, 8, 12, 14),
(1200, 5, 14, 15),
(2300, 5, 16, 16),

(1000, 1, 8, 17),
(666, 5, 1, 18),
(999, 5, 3, 19),
(1023, 3, 7, 20),
(512, 5, 2, 21);


INSERT INTO checked_profile (profile_id, moderator_id, status_id) VALUES
(1,1,1),
(2,2,2),
(3,2,1),
(4,1,3),
(5,3,3),
(6,1,4),
(7,2,3),
(8,2,3),
(3,2,2);


INSERT INTO bid (price, date, proposal_id, unit_id) VALUES
(1000.00, CURDATE(), 1, 1),
(60000.00, CURDATE(), 2, 2),
(1200.00, CURDATE(), 3, 3),
(56000.00, CURDATE(), 4, 4),
(56000.00, CURDATE(), 5, 5),
(56000.00, CURDATE(), 6, 6),
(56000.00, CURDATE(), 7, 7),
(56000.00, CURDATE(), 8, 8),
(56000.00, CURDATE(), 9, 9),
(56000.00, CURDATE(), 10, 10);


INSERT INTO conflict (moderator_id, bid_id, description, status_id) VALUE
(1,1,'text1',1),
(2,2,'text2',2),
(3,3,'text3',2),
(2,4,'text4',2),
(2,5,'text5',3),
(1,6,'text6',1),
(1,7,'text7',1),
(3,8,'text8',3),
(2,9,'text9',1);


INSERT INTO comment (tender_id, user_id, message, date) VALUES 
(1, 1, 'This price is too high', CURDATE()), 
(1, 2, 'This is my opinion', CURDATE()),
(2, 1, 'hello', CURDATE()),
(2, 1, 'This price is too high', CURDATE()),
(2, 3, 'Some text', '2014-07-10'), 
(1, 1, 'You can buy it much cheaper', CURDATE()),
(1, 2, 'Ok, i will think about it.', CURDATE()), 
(2, 1, 'This is too low price.', CURDATE()), 
(2, 2, 'I do not think so.', CURDATE());


INSERT INTO deal (bid_id, proposal_id, customer_id, seller_id, sum, date, status_id) VALUES 
(1, 1, 1, 1, 100000.00, CURDATE(), 1),
(3, 1, 2, 1, 55000.00, CURDATE(), 1),
(2, 3, 5, 2, 40000.00, CURDATE(), 1),
(5, 4, 4, 5, 400000.00, CURDATE(), 2),
(10, 6, 2, 6, 25000.00, CURDATE(), 2),
(4, 1, 5, 3, 1000.00, CURDATE(), 2),
(4, 8, 4, 7, 240000.00, CURDATE(), 1),
(1, 4, 5, 2, 1250500.00, CURDATE(), 1),
(9, 9, 6, 3, 56000.00, CURDATE(), 3),
(6, 6, 6, 7, 95600.00, CURDATE(), 3);

INSERT INTO moderator_category (user_id, category_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(5, 7),
(5, 8);

INSERT INTO seller_category (seller_id, category_id) VALUES
(2, 1),
(2, 2),
(3, 3),
(4, 4),
(7, 7),
(7, 8);

INSERT INTO seller_location (seller_id, location_id) VALUES
(2, 1),
(2, 2),
(3, 13),
(4, 24),
(7, 4),
(7, 5),
(8, 17),
(8, 18);

INSERT INTO tender_location (tender_id, location_id) VALUES
(1, 8),
(2, 1),
(3, 2),
(4, 13),
(5, 24),
(6, 7),
(7, 15),
(8, 22),
(9, 4),
(10, 5),
(11, 17),
(12, 18),
(13, 3),
(14, 25),
(15, 6);

INSERT INTO user_role (user_id, role_id) VALUES
(1, 2),
(2, 3),
(3, 3),
(4, 3),
(5, 2),
(7, 3),
(6, 4),
(8, 3);