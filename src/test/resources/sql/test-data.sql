/*
	Pay attention:
	1. Titles and names;
	2. Dates;
	3. Bids, proposals, items, measurements should be logically connected to right tables ;
	4. Few units per tender, few bids per unit, few proposal per tender etc;

	PS: All new records must be written in the bottom of insert block otherwise it will cause mistakes!!!
*/

USE test_tenders;

INSERT INTO checked_status (name) VALUES
  ('Unchecked'),
  ('In progress'),
  ('Checked'),
  ('Denied');

INSERT INTO tender_status (name, active) VALUES
  ('Open',        true),
  ('In progress', true),
  ('Close',       false);

INSERT INTO conflict_status (name) VALUE
  ('Open'),
  ('In progress'),
  ('resolved');

INSERT INTO role (name) VALUES
  ('ADMIN'),
  ('MODERATOR'),
  ('SELLER'),
  ('CUSTOMER');

INSERT INTO deal_status (name) VALUES
  ('open'),
  ('in progress'),
  ('close');

INSERT INTO measurement (name) VALUES
  ('Kg'),
  ('Km'),
  ('Liter'),
  ('Box'),
  ('Unit'),
  ('Ton'),
  ('Sm'),
  ('Humen/Hour'),
  ('Hours'),
  ('Days');

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

INSERT INTO address (city, street, building_number, postcode) VALUES
  ('Vilnohirsk',     'Stepova',      1,  27500),
  ('Drohobych',      'Khmelnitsky',  5,  29000),
  ('Kiev',           'Moskovska',    21, 01015),
  ('Nizhyn',         'Vosstaniya',   8,  16500),
  ('Dnipropetrovsk', 'Yuzhnaya',     12, 22300),
  ('Donetsk',        'Dzerzhinsky',  14, 12500),
  ('Kiev',           'Solomenskaya', 21, 26600),
  ('Odessa',         'Bunin',        32, 24500),
  ('Simferopol',     'Timoshenko',   55, 23300),
  ('Kiev',           'Puluja',       7,  07500),
  ('Kiev',           'Karla Marksa', 9,  12500),
  ('Alchevsk',       'Moskovskaya',  34, 32500),
  ('Novoukrayinka',  'Dovga',        55, 21500),
  ('Lviv',           'Kravchenko',   2,  26600),
  ('Odessa',         'Bugaevskaya',  3,  25600);

INSERT INTO company (name, srn, email, logo, address_id) VALUES
  ('Vilnohirsk Mining Metallurgical Plant', 121345, 'Vilnohirsk@gmail.com',        null, 1),
  ('Podolsky Smak',                         122345, 'PodolskySmak@gmail.com',      null, 2),
  ('Econom-servis Gp CffInfo',              123345, 'EconomservisGp@gmail.com',    null, 3),
  ('Epcf Syaivo LtdInfo',                   124345, 'EpcfSyaivo@gmail.com',        null, 4),
  ('Planet Service',                        125345, 'PlanetService@gmail.com',     null, 5),
  ('Global Titan Distribution Inc',         126345, 'GlobalTitan@gmail.com',       null, 6),
  ('Sea Electronics',                       127345, 'SeaElectronics@gmail.com',    null, 7),
  ('Kaalbye Logistics Ltd',                 128345, 'KaalbyeLogistics@gmail.com',  null, 8),
  ('Energy Xxii',                           129345, 'EnergyXxii@gmail.com',        null, 9),
  ('Eurocom Components',                    120345, 'EurocomComponents@gmail.com', null, 10),
  ('GoP',                                   102345, 'GodsOfProgramming@gmail.com', null, 11),
  ('Cory Sro',                              112345, 'CorySro@gmail.com',           null, 12),
  ('Polymercontainer Plant',                122345, 'PolymerPlant@gmail.com',      null, 13),
  ('Xado Corp',                             132345, 'XadoCorp@gmail.com',          null, 14),
  ('Garbuz Studio',                         142345, 'GarbuzStudio@gmail.com',      null, 15);

INSERT INTO user (login, password, create_date, enabled) VALUES
/* Sellers */
  ('odin_ogame@ukr.net',    'arlekin',   		CURDATE(), TRUE),
  ('mail4quest@mail.ru',    'quest',     		CURDATE(), TRUE),
  ('vasapupkin@gmail.com',  'pupkin',    		CURDATE(), TRUE),
  ('vasahopnik@rambler.ru', 'dver88',    		CURDATE(), TRUE),
  ('dota2@gmail.com',       'noob23',    		CURDATE(), TRUE),
  ('olgamoros@ukr.net',     'ole4ka',    		CURDATE(), TRUE),
  ('sanja_ivanow@ukr.net',  '123qwerty', 		CURDATE(), TRUE),
  ('kolja234@qmail.com',    'vasa567',   		CURDATE(), TRUE),
  ('GUSbYa@gmail.com',      'maideniron987',  CURDATE(), TRUE),
  ('romawar@gmail.com',     'deuslovult',     CURDATE(), TRUE),

/* Customers */
  ('ramstein@ukr.net',      'duriechstsogut', CURDATE(), TRUE),
  ('maidenFan@ukr.net',     'holysmoke',      CURDATE(), TRUE),
  ('TSRules@qmail.com',     'iwonarock',      CURDATE(), TRUE),
  ('tenderCustomer@gmail.com',   '1111',      CURDATE(), TRUE),
  ('egoManiac@qmail.com',   'iamtheking',     CURDATE(), TRUE),

/* Admin, moderators */
  ('TenderAdmin@gmail.com', 'thebestadmever', '2014-04-07', TRUE),
  ('modjunior@gmail.com',   'holysmoke',      '2014-04-07', TRUE),
  ('modSenior@gmail.com',   'iwonarock',      '2014-04-07', TRUE),
  ('modIntermid@gmail.com', 'iamtheking',     '2014-04-07', TRUE);

INSERT INTO user_role (user_id, role_id) VALUES
/* Sellers */
  (1,  3),
  (2,  3),
  (3,  3),
  (4,  3),
  (5,  3),
  (7,  3),
  (6,  3),
  (8,  3),
  (9,  3),
  (10, 3),

/* Customers */
  (11, 4),
  (12, 4),
  (13, 4),
  (14, 4),
  (15, 4),

/* Admin, moderators */
  (16, 1),
  (17, 2),
  (18, 2),
  (19, 2);

INSERT INTO profile (icon, first_name, last_name, birthday, telephone, checked, person, user_id, company_id) VALUES
/* Seller */
  (null, 'Ivan',     'Nesamay',  '1986-05-28', '55-559-5', false, 'L', 1,  1),
  (null, 'Vasja',    'Pupkin',   '1966-02-18', '55-559-5', false, 'L', 2,  2),
  (null, 'Kolja',    'Qwerty',   '1996-01-02', '55-559-5', false, 'L', 3,  3),
  (null, 'Olja',     'Semenova', '1984-07-09', '55-559-5', false, 'L', 4,  4),
  (null, 'Katja',    'Moros',    '1985-07-08', '55-559-5', false, 'L', 5,  5),
  (null, 'Nasar',    'Havruliv', '1976-06-08', '55-559-5', false, 'L', 6,  6),
  (null, 'Vasul',    'Sobol',    '1989-02-02', '55-559-5', false, 'L', 7,  7),
  (null, 'Oksana',   'Sobol',    '1983-08-01', '55-559-5', false, 'L', 8,  8),
  (null, 'Vasul',    'Sobol',    '1989-02-02', '55-559-5', false, 'L', 9,  9),
  (null, 'Oksana',   'Sobol',    '1983-08-01', '55-559-5', false, 'L', 10, 10),

/* Customer */
  (null, 'Bronemir', 'Yavorsky', '1984-07-09', '55-559-5', false, 'L', 11, 11),
  (null, 'Oleg',     'Bulba',    '1985-07-08', '55-559-5', false, 'L', 12, 12),
  (null, 'Vitaly',   'Koval',    '1976-06-08', '55-559-5', false, 'L', 13, 13),
  (null, 'Andrij',   'Vovk',     '1989-02-02', '55-559-5', false, 'P', 14, NULL),
  (null, 'Roman',    'Bereza',   '1983-08-01', '55-559-5', false, 'P', 15, NULL);

INSERT INTO category (parent, name) VALUES
  (/* 1  */ NULL, 'Electronic devices'),
  (/* 2  */ 1, 'Computers'),
  (/* 3  */ 1, 'Musical instrument'),
  (/* 4  */ 2, 'PCs'),
  (/* 5  */ 2, 'Laptops'),
  (/* 6  */ 3, 'Guitars'),
  (/* 7  */ 3, 'Drums'),
  (/* 8  */ 3, 'Pianos'),

  (/* 9  */ NULL, 'Constructing'),
  (/* 10 */ 9, 'Workers'),
  (/* 11 */ 9, 'Machines'),
  (/* 12 */ 9, 'Materials'),

  (/* 13 */ NULL, 'Vehicles'),
  (/* 14 */ 13, 'cars'),
  (/* 15 */ 13, 'Trucks'),
  (/* 16 */ 13, 'Motorcycle');

INSERT INTO moderator_category (user_id, category_id) VALUES
/* One moderator / category*/
  (17, 1),
  (18, 9),
  (19, 13),
  (17, 2),
  (17, 3),
  (17, 4),
  (17, 5),
  (17, 6),
  (18, 7),
  (18, 8),
  (18, 10),
  (18, 11),
  (19, 12),
  (19, 14),
  (19, 15),
  (19, 16);

INSERT INTO item (type, category_id, name) VALUES
  (/* 1 */  'P', 6,  'Gibson'),
  (/* 2 */  'P', 6,  'Epiphone'),
  (/* 3 */  'P', 7,  'Fender'),
  (/* 4 */  'P', 4,  'AMD'),
  (/* 5 */  'P', 4,  'Pentium'),
  (/* 6 */  'P', 5,  'Lenovo'),
  (/* 7 */  'P', 5,  'Accer'),
  (/* 8 */  'P', 12, 'Brick'),
  (/* 9 */  'P', 12, 'Paint'),
  (/* 10 */ 'S', 10, 'Builders'),
  (/* 11 */ 'S', 10, 'Painters'),
  (/* 12 */ 'S', 11, 'Dozer'),
  (/* 13 */ 'S', 11, 'Truck'),
  (/* 14 */ 'S', 14, 'Limo'),
  (/* 15 */ 'P', 14, 'Audi'),
  (/* 16 */ 'P', 15, 'Renault Trucks'),
  (/* 17 */ 'P', 16, 'Toyota'),

  (/* 18 */ 'P', 5, 'Macbook Air'),
  (/* 19 */ 'P', 1, 'Optic cable'),
  (/* 19 */ 'P', 2, 'IBM'),
  (/* 20 */ 'P', 2, 'Titanium');

INSERT INTO tender (author_id, title, create_date, end_date, status_id, suitable_price, description) VALUES
/* Open tenders */
  (/* 1 */  11, 'Test Title',   CURDATE(), CURDATE(), 1, 1000.00,  NULL),
  (/* 2 */  12, 'Apartments building', CURDATE(),    CURDATE(), 1, 2000.00,  'I want free deliver'),
  (/* 3 */  13, 'Test Title',       CURDATE(), CURDATE(),    1, 3000.00,  NULL),
  (/* 4 */  13, 'Test Title',       CURDATE(),    CURDATE(), 1, 4000.00,  'Need free deliver'),
  (/* 5 */  15, 'Animals',             CURDATE(), CURDATE(), 1, 5000.00,  'I need fast deliver'),
  (/* 6 */  11, 'Tourism',             CURDATE(), CURDATE(),    1, 6000.00,  NULL),
  (/* 7 */  12, 'Industry',            CURDATE(), CURDATE(), 1, 7000.00,  NULL),
  (/* 8 */  13, 'Test Title',       CURDATE(), CURDATE(), 1, 8000.00,  'I want deliver to some specific place'),
  (/* 9 */  13, 'Test Title',            CURDATE(),    CURDATE(), 1, 9000.00,  NULL),
  (/* 10 */ 15, 'Products',            CURDATE(),    CURDATE(), 1, 10000.00, NULL),

/* Tenders in progress */
  (/* 11 */ 11, 'Test Title',         CURDATE(),    CURDATE(), 2, 11000.00, NULL),
  (/* 12 */ 12, 'Products',           CURDATE(), CURDATE(), 2, 12000.00, 'I want free deliver to some specific place'),
  (/* 13 */ 13, 'Test Title',           CURDATE(), CURDATE(), 2, 13000.00, NULL),
  (/* 14 */ 13, 'Test Title',    CURDATE(), CURDATE(),    2, 14000.00, NULL),
  (/* 15 */ 15, 'Cars',               CURDATE(),    CURDATE(), 2, 15000.00, 'Firm need fast deliver'),
  (/* 16 */ 11, 'Cars for my firm',   CURDATE(), CURDATE(), 2, 16000.00, NULL),
  (/* 17 */ 12, 'House Building',     CURDATE(), CURDATE(), 2, 17000.00, NULL),
  (/* 18 */ 13, 'Test Title',           CURDATE(), CURDATE(), 2, 18000.00, NULL),
  (/* 19 */ 13, 'Test Title',      CURDATE(), CURDATE(), 2, 19000.00, NULL),
  (/* 20 */ 15, 'Need products',      CURDATE(),    CURDATE(), 2, 20000.00, 'I want free deliver'),

/* Closed tenders */
  (/* 21 */ 11, 'Products',           CURDATE(),    CURDATE(), 3, 21000.00, NULL),
  (/* 22 */ 12, 'Products',           CURDATE(), CURDATE(), 3, 22000.00, NULL),
  (/* 23 */ 13, 'Need workers',       CURDATE(), CURDATE(), 3, 23000.00, NULL),
  (/* 24 */ 15, 'House building',     CURDATE(), CURDATE(),    3, 24000.00, NULL),
  (/* 25 */ 15, 'I want find cars',   CURDATE(),    CURDATE(), 3, 25000.00, 'I want deliver to some specific place'),
  (/* 26 */ 11, 'Cars',               CURDATE(), CURDATE(), 3, 26000.00, NULL),
  (/* 27 */ 12, 'House Building',     CURDATE(), CURDATE(), 3, 27000.00, NULL),
  (/* 28 */ 13, 'Industry',           CURDATE(), CURDATE(), 3, 28000.00, NULL),
  (/* 29 */ 15, 'Products',           CURDATE(), CURDATE(), 3, 29000.00, 'I want free deliver to some specific
  place'),
  (/* 30 */ 15, 'Products for me',    CURDATE(),    CURDATE(), 3, 30000.00, NULL),
  
  (/* 31 */ 11, 'Need computers',     CURDATE(),    CURDATE(), 1, 60000.00, NULL),
  (/* 32 */ 12, 'Servers',    	      CURDATE(),    CURDATE(), 2, 70000.00, NULL),
  (/* 33 */ 13, 'Laptops in office',  CURDATE(),    CURDATE(), 1, 85000.00, NULL),
  (/* 34 */ 14, 'Computer network',   CURDATE(),    CURDATE(), 1, 90000.00, NULL);

INSERT INTO unit (quantity, measurement_id, item_id, tender_id) VALUES
  (5, 1, 1,  1),
  (1, 5, 2,  2),
  (5, 5, 3,  3),
  (1, 6, 4,  4),
  (2, 5, 5,  5),
  (1, 1, 6,  6),
  (6, 5, 7,  7),
  (9, 5, 8,  8),
  (1, 3, 9,  9),
  (5, 5, 10, 10),
  (5, 8, 11, 11),
  (5, 1, 12, 12),
  (1, 8, 13, 13),
  (5, 8, 14, 14),
  (1, 5, 15, 15),
  (2, 5, 16, 16),
  (1, 8, 17, 17),
  (6, 8, 1,  18),
  (9, 3, 2,  19),
  (1, 1, 3,  20),
  (5, 1, 4,  21),
  (8, 1, 5,  22),
  (1, 8, 6,  23),
  (2, 8, 7,  24),
  (5, 5, 8,  25),
  (4, 5, 9,  26),
  (8, 8, 10, 27),
  (7, 8, 11, 28),
  (8, 3, 12, 29),
  (2, 1, 13, 30),

  (5,  5, 19, 31),
  (10, 5, 20, 32),
  (15, 5, 18, 33),
  (20, 5, 19, 34);

INSERT INTO proposal (discount_percentage, discount_currency, seller_id, tender_id, description) VALUES
/* Proposals for tenders that in progress*/
  (10,   NULL,   1,  11, NULL),
  (20,   NULL,   2,  12, NULL),
  (30,   NULL,   3,  13, NULL),
  (50,   NULL,   4,  14, NULL),
  (NULL, 100.00, 5,  15, NULL),
  (NULL, 200.00, 6,  16, NULL),
  (NULL, 300.00, 7,  17, NULL),
  (NULL, 400.00, 8,  18, NULL),
  (NULL, 500.00, 9,  19, NULL),
  (NULL, 600.00, 10, 20, NULL),

  (10,   NULL,   1,  21, NULL),
  (20,   NULL,   2,  22, NULL),
  (30,   NULL,   3,  23, NULL),
  (50,   NULL,   4,  24, NULL),
  (NULL, 100.00, 5,  25, NULL),
  (NULL, 200.00, 6,  26, NULL),
  (NULL, 300.00, 7,  27, NULL),
  (NULL, 400.00, 8,  28, NULL),
  (NULL, 500.00, 9,  29, NULL),
  (NULL, 600.00, 10, 30, NULL),

/* Proposals for closed tenders*/
  (10,   NULL,   1,  20, NULL),
  (20,   NULL,   2,  19, NULL),
  (30,   NULL,   3,  18, NULL),
  (50,   NULL,   4,  17, NULL),
  (NULL, 100.00, 5,  16, NULL),
  (NULL, 200.00, 6,  15, NULL),
  (NULL, 300.00, 7,  14, NULL),
  (NULL, 400.00, 8,  13, NULL),
  (NULL, 500.00, 9,  12, NULL),
  (NULL, 600.00, 10, 11, NULL);

INSERT INTO bid (price, date, proposal_id, unit_id) VALUES
/* Bids for tenders units that are in progress*/
  (900.00, CURDATE(), 1,  11),
  (600.00, CURDATE(), 2,  12),
  (120.00, CURDATE(), 3,  13),
  (560.00, CURDATE(), 4,  14),
  (460.00, CURDATE(), 5,  15),
  (360.00, CURDATE(), 6,  16),
  (160.00, CURDATE(), 7,  17),
  (560.00, CURDATE(), 8,  18),
  (860.00, CURDATE(), 9,  19),
  (760.00, CURDATE(), 10, 20),

  (900.00, CURDATE(), 1,  11),
  (600.00, CURDATE(), 2,  12),
  (120.00, CURDATE(), 3,  13),
  (560.00, CURDATE(), 4,  14),
  (460.00, CURDATE(), 5,  15),
  (360.00, CURDATE(), 6,  16),
  (160.00, CURDATE(), 7,  17),
  (560.00, CURDATE(), 8,  18),
  (860.00, CURDATE(), 9,  19),
  (760.00, CURDATE(), 10, 20),

/* Bids for tenders units that are closed*/
  (900.00, CURDATE(), 1,  21),
  (600.00, CURDATE(), 2,  22),
  (120.00, CURDATE(), 3,  23),
  (560.00, CURDATE(), 4,  24),
  (460.00, CURDATE(), 5,  25),
  (360.00, CURDATE(), 6,  26),
  (160.00, CURDATE(), 7,  27),
  (560.00, CURDATE(), 8,  28),
  (860.00, CURDATE(), 9,  29),
  (760.00, CURDATE(), 10, 30);

INSERT INTO deal (bid_id, proposal_id, customer_id, seller_id, sum, date, status_id) VALUES
  (3, 1, 2, 1, 55000.00, CURRENT_TIMESTAMP(), 1),
  (2, 3, 5, 2, 40000.00, CURRENT_TIMESTAMP(), 1),
  (5, 4, 4, 5, 400000.00, CURRENT_TIMESTAMP(), 2),
  (10, 6, 2, 6, 25000.00, CURRENT_TIMESTAMP(), 2),
  (4, 2, 5, 3, 1000.00, CURRENT_TIMESTAMP(), 2),
  (4, 8, 4, 7, 240000.00, CURRENT_TIMESTAMP(), 1),
  (1, 4, 5, 2, 1250500.00, CURRENT_TIMESTAMP(), 1),
  (9, 9, 6, 3, 56000.00, CURRENT_TIMESTAMP(), 3),
  (6, 6, 6, 7, 95600.00, CURRENT_TIMESTAMP(), 3);

INSERT INTO conflict (moderator_id, bid_id, description, status_id) VALUE
  (17, 1, 'text1', 1),
  (17, 2, 'text2', 2),
  (17, 3, 'text3', 2),
  (17, 4, 'text4', 2),
  (17, 5, 'text5', 3),
  (17, 6, 'text6', 1),
  (17, 7, 'text7', 1),
  (17, 8, 'text8', 3),
  (17, 8, 'text8', 3),
  (17, 8, 'text8', 3),
  (17, 8, 'text8', 3),
  (17, 8, 'text8', 3),
  (18, 8, 'text8', 3),
  (18, 8, 'text8', 3),
  (18, 8, 'text8', 3),
  (18, 9, 'text9', 1);

INSERT INTO comment (tender_id, user_id, date, message) VALUES
  (1, 1, CURDATE(), 'This price is too high'),
  (1, 2, CURDATE(), 'This is my opinion'),
  (2, 1, CURDATE(), 'hello'),
  (2, 1, CURDATE(), 'This price is too high'),
  (2, 3, CURDATE(), 'Some text'),
  (1, 1, CURDATE(), 'You can buy it much cheaper'),
  (1, 2, CURDATE(), 'Ok, i will think about it.'),
  (2, 1, CURDATE(), 'This is too low price.'),
  (2, 2, CURDATE(), 'I do not think so.');

INSERT INTO seller_category (seller_id, category_id) VALUES
  (1,  1),
  (2,  2),
  (3,  3),
  (4,  4),
  (5,  7),
  (6,  8),
  (7,  2),
  (8,  3),
  (9,  4),
  (10, 7);

INSERT INTO seller_location (seller_id, location_id) VALUES
  (1,  1),
  (2,  2),
  (3,  3),
  (4,  4),
  (5,  5),
  (6,  6),
  (7,  7),
  (8,  8),
  (9,  9),
  (10, 10);

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
  (15, 6),
  (16, 5),
  (17, 17),
  (18, 18),
  (19, 3),
  (20, 3),
  (21, 5),
  (22, 17),
  (23, 18),
  (24, 3),
  (25, 25),
  (26, 6),
  (27, 5),
  (28, 17),
  (29, 18),
  (30, 3),

  (31, 5),
  (32, 17),
  (33, 18),
  (34, 3);

INSERT INTO checked_profile (profile_id, moderator_id, status_id) VALUES
  (1,  17, 3),
  (2,  17, 3),
  (3,  17, 3),
  (4,  17, 3),
  (5,  17, 3),
  (6,  18, 3),
  (7,  18, 3),
  (8,  18, 3),
  (9,  18, 3),
  (10, 18, 3),
  (11, 19, 3),
  (12, 19, 3),
  (13, 19, 3),
  (14, 19, 3),
  (15, 19, 3);

INSERT INTO feedback (user_id, profile_id, communication, speed, logistic, comment) VALUES
  (5,  1, 3, 4, 5, ''),
  (6,  1, 5, 4, 3, ''),
  (7,  1, 1, 2, 3, ''),
  (8,  1, 4, 5, 2, ''),
  (9,  1, 4, 3, 2, ''),
  (10, 1, 3, 3, 3, ''),
  (11, 1, 2, 4, 5, ''),
  (12, 1, 3, 4, 5, ''),
  (13, 1, 1, 1, 1, '');
