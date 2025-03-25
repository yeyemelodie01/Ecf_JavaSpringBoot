INSERT INTO Utilisateur (first_name, last_name, username, login, iban, email, password, role, balance, active, confirm) VALUES
('firstNameU1', 'LastNameU1', 'user', 'usertest', 'monIBAN', 'test1@test.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.', 'USER', 30.0, TRUE, FALSE),
('firstNameU2', 'LastNameU2', 'admin', 'usertest2', 'monIBAN', 'test2@test.com', '$2y$10$kp1V7UYDEWn17WSK16UcmOnFd1mPFVF6UkLrOOCGtf24HOYt8p1iC', 'ADMIN', 20.0, TRUE, FALSE),
('firstNameU3', 'LastNameU3', 'user3', 'usertest3', 'monIBAN', 'test3@test.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.', 'USER', 20.0, TRUE, FALSE),
('firstNameU3', 'LastNameU3', 'user4', 'usertest3', 'monIBAN', 'test3@test.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.', 'SUSPENDU', 20.0, TRUE, FALSE);


INSERT INTO Utilisateur_Contact (utilisateur_id, contact_id) VALUES (1, 2);

INSERT INTO Transaction (amount, message_content, date, sender_id, receiver_id, validate, reject, report) VALUES
(20.0, 'pour les courses', CURRENT_DATE, 1, 2, TRUE, FALSE, FALSE),
(30.0, 'Transaction 2', CURRENT_DATE, 2, 1, FALSE, FALSE, FALSE),
(20.0, 'Transaction 3', CURRENT_DATE, 1, 2, FALSE, FALSE, FALSE);


INSERT INTO Message (content, date, utilisateur_id) VALUES
('message 1', CURRENT_DATE, 1),
('message 3', CURRENT_DATE, 1),
('message 2', CURRENT_DATE,2);


INSERT INTO Transfert (amount, message_content, date, sender_id) VALUES
(10.0, 'transfert 1', CURRENT_DATE, 1),
(-10.0, 'transfert 2', CURRENT_DATE, 1),
(100.0, 'transfert 3', CURRENT_DATE, 2);