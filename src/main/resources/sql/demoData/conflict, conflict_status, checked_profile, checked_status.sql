USE tender;

INSERT INTO checked_status (name) VALUES
('Open'),
('In progress'),
('Checked'),
('denied');

INSERT INTO checked_profile (profile_id, moderator_id, status_id) VALUES
(1,1,1),
(2,2,2),
(3,2,1),
(4,1,3),
(5,3,3),
(6,1,4),
(7,2,3),
(8,2,3),
(9,2,2);

INSERT INTO conflict (moderator_id, bid_id, description, status_id) VALUE
(1,1,'text1',1),
(2,2,'text2',2),
(3,3,'text3',2),
(2,4,'text4',2),
(2,5,'text5',3),
(1,6,'text6',1),
(1,7,'text7',1),
(3,8,'text8',3),
(2,9,'text9',1),
(2,10,'text10',2),
(1,11,'text11',3);

INSERT INTO conflict_status (name) VALUE
('Open'),
('In progress'),
('resolved');
