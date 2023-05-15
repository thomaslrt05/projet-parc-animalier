INSERT INTO species (id, label)
VALUES ('s2', 'lion');
INSERT INTO species (id, label)
VALUES ('s3', 'girafe');
INSERT INTO species (id, label)
VALUES ('s4', 'zèbre');
INSERT INTO species (id, label)
VALUES ('s5', 'éléphant');
INSERT INTO species (id, label)
VALUES ('s6', 'singe');
INSERT INTO species (id, label)
VALUES ('s7', 'hippopotame');
INSERT INTO species (id, label)
VALUES ('s8', 'puma');
INSERT INTO species (id, label)
VALUES ('s9', 'rhinocéros');
INSERT INTO species (id, label)
VALUES ('s10', 'tigre');
INSERT INTO species (id, label)
VALUES ('s1', 'crocodile');



INSERT INTO breed (id, label, specification)
VALUES ('b1', 'américain', 's1');

INSERT INTO breed (id, label, specification)
VALUES ('b2', 'africain', 's2');

INSERT INTO breed (id, label, specification)
VALUES ('b3', 'masai', 's3');

INSERT INTO breed (id, label, specification)
VALUES ('b4', 'grevy', 's4');

INSERT INTO breed (id, label, specification)
VALUES ('b5', 'sri lanka', 's5');

INSERT INTO breed (id, label, specification)
VALUES ('b6', 'capucin', 's6');

INSERT INTO breed (id, label, specification)
VALUES ('b7', 'nain', 's7');

INSERT INTO breed (id, label, specification)
VALUES ('b8', 'puma concolor', 's8');

INSERT INTO breed (id, label, specification)
VALUES ('b9', 'indien', 's9');

INSERT INTO breed (id, label, specification)
VALUES ('b10', 'de sibérie', 's10');



INSERT INTO animal (code, name, sex, isDangerous, weight, arrivalDate, nickName, breed) 
VALUES 
('a', 'Thomas', 'M', '1', '69', '2010-10-21', NULL, 'b2'),
('b', 'Musa', 'F', '0', '12', '2022-05-10', NULL, 'b3'),
('c', 'Sophie', 'F', '0', '45', '2012-05-18', 'Foxy', 'b8'),
('d', 'Rocky', 'M', '1', '85', '2014-02-23', 'Tank', 'b7'),
('e', 'Bella', 'F', '0', '24', '2015-09-12', 'Bee', 'b6'),
('f', 'Max', 'M', '1', '110', '2017-11-01', 'Bruiser', 'b5'),
('g', 'Fluffy', 'F', '0', '25', '2022-05-13', NULL, 'b10'),
('h', 'Rex', 'M', '1', '60', '2022-05-13', NULL, 'b8'),
('i', 'Daisy', 'F', '0', '40', '2022-05-13', NULL, 'b9'),
('j', 'Nemo', 'M', '0', '0.5', '2022-05-13', NULL, 'b7'),
('k', 'Simba', 'M', '1', '150', '2022-05-13', NULL, 'b2'),
('l', 'Coco', 'F', '0', '3', '2022-05-13', NULL, 'b1'),
('m', 'Pinky', 'F', '0', '0.2', '2022-05-13', NULL, 'b3'),
('n', 'Jaws', 'M', '1', '500', '2022-05-13', NULL, 'b10'),
('o', 'Mufasa', 'M', '1', '180', '2022-05-13', NULL, 'b10'),
('p', 'Dumbo', 'M', '0', '150', '2022-05-13', NULL, 'b5');




INSERT INTO medicine (name, unitOfMesurement, instruction )
VALUES ('paracetamol', 'gr', 'Un comprimé par repas');

INSERT INTO medicine (name, unitOfMesurement, instruction)
VALUES ('ibuprofen', 'mg', 'Prendre un comprimé toutes les 6 heures avec de la nourriture.');

INSERT INTO medicine (name, unitOfMesurement, instruction)
VALUES ('loratadine', 'mg', 'Prendre un comprimé une fois par jour pour les allergies.');

INSERT INTO medicine (name, unitOfMesurement, instruction)
VALUES ('insulin', 'IU', 'Prendre une dose en fonction des niveaux de glucose sanguin.');

INSERT INTO medicine (name, unitOfMesurement, instruction)
VALUES ('amoxicillin', 'mg', 'Prendre un comprimé toutes les 8 heures avec de la nourriture.');

INSERT INTO medicine (name, unitOfMesurement, instruction)
VALUES ('prednisone', 'mg', null);



INSERT INTO fonction (id, position, label) VALUES ('f1', 'CHIEF', 'Véterinaire en chef');
INSERT INTO fonction (id, position, label) VALUES ('f2', 'JUNIOR', 'Véterinaire junior');
INSERT INTO fonction (id, position, label) VALUES ('f3', 'SENIOR', 'Soigneur');
INSERT INTO fonction (id, position, label) VALUES ('f4', 'JUNIOR', 'Soigneur junior');
INSERT INTO fonction (id, position, label) VALUES ('f5', 'SENIOR', 'Pharmacien');
INSERT INTO fonction (id, position, label) VALUES ('f6', 'JUNIOR', 'Pharmacien assistant');



INSERT INTO typeOfCare(id, label) VALUES('ty1', 'soin de base');
INSERT INTO typeOfCare(id, label) VALUES('ty2', 'soin avancé');


INSERT INTO employee (matricule, lastName, firstName, supervisor, position)
VALUES ('emp1', 'Donnateli','Géraldine', null, 'f1');

INSERT INTO employee (matricule, lastName, firstName, supervisor, position)
VALUES ('emp2', 'Smith', 'John', 'emp1', 'f2');

INSERT INTO employee (matricule, lastName, firstName, supervisor, position)
VALUES ('emp3', 'Lee', 'Yun', null, 'f3');

INSERT INTO employee (matricule, lastName, firstName, supervisor, position)
VALUES ('emp4', 'Dubois', 'Jean', 'emp3', 'f4');

INSERT INTO employee (matricule, lastName, firstName, supervisor, position)
VALUES ('emp5', 'Velkoz', 'Johnny', null, 'f5');

INSERT INTO employee (matricule, lastName, firstName, supervisor, position)
VALUES ('emp6', 'Nguyen', 'Thi', 'emp5', 'f6');

INSERT INTO employee (matricule, lastName, firstName, supervisor, position)
VALUES ('emp7', 'Martin', 'Sophie', null, 'f1');

INSERT INTO employee (matricule, lastName, firstName, supervisor, position)
VALUES ('emp8', 'Garcia', 'Maria', 'emp1', 'f2');

INSERT INTO employee (matricule, lastName, firstName, supervisor, position)
VALUES ('emp9', 'Lee', 'Jin', null, 'f3');

INSERT INTO employee (matricule, lastName, firstName, supervisor, position)
VALUES ('emp10', 'Moreau', 'Julie', 'emp3', 'f4');

INSERT INTO employee (matricule, lastName, firstName, supervisor, position)
VALUES ('emp11', 'Zhang', 'Huan', 'emp5', 'f5');



INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidient', '2022-10-5', 'a');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2021-10-05', 'a');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2023-10-06', 'b');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2022-10-07', 'c');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2023-10-11', 'd');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2021-10-09', 'e');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2020-10-10', 'f');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2022-11-11', 'b');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2022-9-12', 'c');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2022-7-13', 'e');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2022-8-14', 'd');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidient', '2022-7-6', 'a');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2021-8-29', 'a');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2023-03-15', 'g');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2023-04-18', 'h');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2022-02-01', 'i');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2022-01-12', 'j');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2023-02-10', 'k');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2022-12-25', 'l');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2022-11-05', 'm');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2021-10-28', 'n');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2023-01-19', 'o');
INSERT INTO caresheet (label, date, animal) VALUES ('Soin quotidien', '2022-06-30', 'p');



INSERT INTO treatment (idCare, date, animal, isBasic, description, classification)
VALUES (1, '2021-10-05', 'a', true, 'Nourrissage', 'ty1');

INSERT INTO treatment (idCare, date, animal, isBasic, description, classification) 
VALUES (2, '2022-10-05', 'b', true, 'Nettoyage de la cage', 'ty1');

INSERT INTO treatment (idCare, date, animal, isBasic, description, classification) 
VALUES (3, '2022-11-11', 'c', false, 'Consultation vétérinaire', 'ty2');

INSERT INTO treatment (idCare, date, animal, isBasic, description, classification) 
VALUES (4, '2021-10-9', 'd', true, 'Administration médicament', 'ty2');

INSERT INTO treatment (idCare, date, animal, isBasic, description, classification) 
VALUES (5, '2020-10-10', 'e', false, 'Radiographie', 'ty2');

INSERT INTO treatment (idCare, date, animal, isBasic, description, classification) 
VALUES (6, '2022-10-07', 'a', false, 'Opération chirurgicale', 'ty2');



INSERT INTO remark (number, animal, date, description, isMadeByVeterinarian, author)
VALUES (1, 'a', '2021-10-05', 'L\'animal à mangé sa nourriture', false, 'emp3');

INSERT INTO remark (number, animal, date, description, isMadeByVeterinarian, author)
VALUES (2, 'a', '2022-10-05', 'Nettoyage accompli', false, 'emp4');

INSERT INTO remark (number, animal, date, description, isMadeByVeterinarian, author)
VALUES (3, 'b', '2022-11-11', 'Nécéssite un régime spécifique', true, 'emp1');

INSERT INTO remark (number, animal, date, description, isMadeByVeterinarian, author)
VALUES (4, 'e', '2021-10-09', 'Vermifugation réalisée', true, 'emp2');

INSERT INTO remark (number, animal, date, description, isMadeByVeterinarian, author)
VALUES (5, 'f', '2020-10-10', 'Foulure de la patte arrière gauche', true, 'emp2');

INSERT INTO remark (number, animal, date, description, isMadeByVeterinarian, author)
VALUES (6, 'c', '2022-10-07', 'Tumeur enlevée', true, 'emp1');

INSERT INTO remark (number, animal, date, description, isMadeByVeterinarian, author)
VALUES (7, 'd', '2023-10-11', 'Blessure à la patte guérie', true, 'emp3');

INSERT INTO remark (number, animal, date, description, isMadeByVeterinarian, author)
VALUES (8, 'f', '2020-10-10', 'A besoin d\'une visite vétérinaire', true, 'emp2');

INSERT INTO remark (number, animal, date, description, isMadeByVeterinarian, author)
VALUES (9, 'h', '2023-04-18', 'Nouvelle naissance dans l\'enclos', false, 'emp4');

INSERT INTO remark (number, animal, date, description, isMadeByVeterinarian, author)
VALUES (10, 'j', '2022-01-12', 'Refus de manger, surveiller la situation', true, 'emp3');

INSERT INTO remark (number, animal, date, description, isMadeByVeterinarian, author)
VALUES (11, 'k', '2023-02-10', 'Nouveau jouet installé', false, 'emp5');

INSERT INTO remark (number, animal, date, description, isMadeByVeterinarian, author)
VALUES (12, 'm', '2022-11-05', 'Fièvre légère, traitement prescrit', true, 'emp1');

INSERT INTO remark (number, animal, date, description, isMadeByVeterinarian, author)
VALUES (13, 'n', '2021-10-28', 'Besoin d\'un nettoyage de l\'enclos', false, 'emp6');

INSERT INTO remark (number, animal, date, description, isMadeByVeterinarian, author)
VALUES (14, 'o', '2023-01-19', 'Besoin d\'une nouvelle lampe chauffante', true, 'emp2');

INSERT INTO remark (number, animal, date, description, isMadeByVeterinarian, author)
VALUES (15, 'p', '2022-06-30', 'Nouvelle vitamine ajoutée à l\'alimentation', true, 'emp1');



INSERT INTO modification (date, animal, employee)
VALUES  ('2021-10-05', 'a', 'emp3');

INSERT INTO modification (date, animal, employee)
VALUES ('2022-10-05', 'b', 'emp4');

INSERT INTO modification (date, animal, employee)
VALUES ('2022-11-11', 'c', 'emp1');

INSERT INTO modification (date, animal, employee)
VALUES ('2021-10-09', 'd', 'emp2');

INSERT INTO modification (date, animal, employee)
VALUES ('2020-10-10', 'e', 'emp2');

INSERT INTO modification (date, animal, employee)
VALUES ('2022-10-07', 'f', 'emp1');



INSERT INTO prescriptionsheet (number, date, prescription, author)
VALUES (1, '2022-5-10', 4, 'emp3');

INSERT INTO prescriptionsheet (number, date, prescription, author)
VALUES (2, '2022-10-8', 4, 'emp3');

INSERT INTO prescriptionsheet (number, date, prescription, author)
VALUES (3, '2022-9-25', 4, 'emp3');

INSERT INTO prescriptionsheet (number, date, prescription, author)
VALUES (4, '2022-10-1', 4, 'emp3');

INSERT INTO prescriptionsheet (number, date, prescription, author)
VALUES (5, '2022-11-15', 3, 'emp4');

INSERT INTO prescriptionsheet (number, date, prescription, author)
VALUES (6, '2022-12-25', 2, 'emp5');

INSERT INTO prescriptionsheet (number, date, prescription, author)
VALUES (7, '2023-01-10', 1, 'emp4');

INSERT INTO prescriptionsheet (number, date, prescription, author)
VALUES (8, '2023-02-14', 5, 'emp6');

INSERT INTO prescriptionsheet (number, date, prescription, author)
VALUES (9, '2023-03-22', 6, 'emp4');

INSERT INTO prescriptionsheet (number, date, prescription, author)
VALUES (10, '2023-04-01', 3, 'emp5');



INSERT INTO preparationsheet (number, date, quantity, posology, creation, detail, preparation, attachment)
VALUES(1, '2022-05-10', 10.5, 'Un fois pas jour', null, 1, 'insulin', 'a');

INSERT INTO preparationsheet (number, date, quantity, posology, creation, detail, preparation, attachment)
VALUES(2, '2022-05-05', 1, 'Un comprimé 30 minutes avant le repas', null, 2, 'ibuprofen', 'b');

INSERT INTO preparationsheet (number, date, quantity, posology, creation, detail, preparation, attachment)
VALUES(3, '2022-05-08', 1, 'Un comprimé à écraser dans la nourriture', null, 5, 'paracetamol', 'f');

INSERT INTO preparationsheet (number, date, quantity, posology, creation, detail, preparation, attachment)
VALUES(4, '2022-05-11', 2, 'Une fois par jour après le repas', null, 2, 'amoxicillin', 'c');

INSERT INTO preparationsheet (number, date, quantity, posology, creation, detail, preparation, attachment)
VALUES(5, '2022-05-06', 1.5, 'Un fois par jour avant le repas', null, 5, 'loratadine', 'd');

INSERT INTO preparationsheet (number, date, quantity, posology, creation, detail, preparation, attachment)
VALUES(6, '2022-05-09', 1, 'Une fois par jour avec de l\'eau', null, 1, 'ibuprofen', 'a');

INSERT INTO preparationsheet (number, date, quantity, posology, creation, detail, preparation, attachment)
VALUES(7, '2022-05-12', 0.5, 'Un fois par jour avec de la nourriture', null, 1, 'prednisone', 'a');

INSERT INTO preparationsheet (number, date, quantity, posology, creation, detail, preparation, attachment)
VALUES(8, '2022-05-13', 1, 'Une fois par jour le matin', null, 2, 'paracetamol', 'b');

INSERT INTO preparationsheet (number, date, quantity, posology, creation, detail, preparation, attachment)
VALUES(9, '2022-05-14', 2.5, 'Un fois par jour avec un repas', null, 1, 'prednisone', 'a');

INSERT INTO preparationsheet (number, date, quantity, posology, creation, detail, preparation, attachment)
VALUES(10, '2022-05-15', 0.5, 'Deux fois par jour avant les repas', null, 2, 'insulin', 'b');

INSERT INTO preparationsheet (number, date, quantity, posology, creation, detail, preparation, attachment)
VALUES(11, '2022-05-16', 1, 'Une fois par jour après le repas', null, 3, 'amoxicillin', 'c');

INSERT INTO preparationsheet (number, date, quantity, posology, creation, detail, preparation, attachment)
VALUES(12, '2022-05-17', 2, 'Deux fois par jour avec de l\'eau', null, 4, 'loratadine', 'd');

INSERT INTO preparationsheet (number, date, quantity, posology, creation, detail, preparation, attachment)
VALUES(13, '2022-05-18', 1.5, 'Un fois par jour avant le coucher', null, 3, 'loratadine', 'c');

INSERT INTO preparationsheet (number, date, quantity, posology, creation, detail, preparation, attachment)
VALUES(14, '2022-05-19', 1, 'Une fois par jour avec de la nourriture', null, 3, 'prednisone', 'c');