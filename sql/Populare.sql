-- ---------------------------------------------Prima coloana
use policlinica;

delete from AparatMedical;
Insert into aparatmedical values
(1,"Stestoscop"),
(2,"Concentrator de oxigen"),
(3,"Oximetru"),
(4,"Electrocardiograf"),
(5,"Ecograf"),
(6,"Monitor functii vitale"),
(7,"Aspirator chirurgical"),
(8,"Aparat electrostimulare");

delete from AparatNecesar;
Insert into aparatnecesar  values
(1,1,1),
(2,1,2),
(3,1,5),
(4,1,8),
(5,2,5),
(6,2,7),
(7,3,6),
(8,4,4),
(9,5,1),
(10,5,2),
(11,5,4),
(12,5,6);

delete from cabinet;
Insert into cabinet values
(1,1),
(2,1),
(3,1),
(4,1),
(5,1),
(6,1),
(7,1),
(8,1),
(9,1),
(10,1),
(11,2),
(12,2),
(13,2),
(14,2),
(15,2),
(16,2),
(17,2),
(18,2),
(19,2),
(20,2),
(21,3),
(22,3),
(23,3),
(24,3),
(25,3),
(26,3),
(27,3),
(28,3),
(29,3),
(30,3),
(31,4),
(32,4),
(33,4),
(34,4),
(35,4),
(36,4),
(37,4),
(38,4),
(39,4),
(40,4);

delete from aparatpercabinet;
Insert into aparatpercabinet values
(1,1,1),
(2,3,1),
(3,4,2),
(4,6,2),
(5,7,3),
(6,8,3),
(7,2,4),
(8,3,4),
(9,4,5),
(10,5,5),
(11,1,6),
(12,3,6),
(13,2,7),
(14,1,7),
(15,6,8),
(16,5,8),
(17,7,9),
(18,8,9),
(19,2,10),
(20,1,10),
(21,5,11),
(22,1,11),
(23,5,12),
(24,6,12),
(25,1,13),
(26,6,13),
(27,1,14),
(28,4,14),
(29,2,15),
(30,7,15),
(31,3,16),
(32,6,16),
(33,5,17),
(34,4,17),
(35,1,18),
(36,8,18),
(37,2,19),
(38,4,19),
(39,6,20),
(40,8,20),
(41,4,21),
(42,5,21),
(43,3,22),
(44,2,22),
(45,5,23),
(46,1,23),
(47,8,24),
(48,7,24),
(49,7,25),
(50,6,25),
(51,4,26),
(52,2,26),
(53,3,27),
(54,5,27),
(55,1,28),
(56,5,28),
(57,2,29),
(58,5,29),
(59,3,30),
(60,4,30),
(61,6,31),
(62,1,31),
(63,8,32),
(64,1,32),
(65,2,33),
(66,1,33),
(67,2,34),
(68,7,34),
(69,7,35),
(70,6,35),
(71,5,36),
(72,4,36),
(73,4,37),
(74,7,37),
(75,4,38),
(76,8,38),
(77,8,39),
(78,3,39),
(79,3,40),
(80,6,40);

delete from Unitatemedicala;
Insert into Unitatemedicala values
(1,"Regina Maria","Strada Observatorului nr.3, Cluj",1),
(2,"Polimed","Strada Gabriel Popescu nr.4, Targoviste ",2),
(3,"Medica","Calea Dumbrăvii nr.107, Sibiu",3),
(4,"Petros","Strada Alesd nr.16, Cluj",4);

delete from Program;
Insert into Program values
(1,"08:00-12:00 13:00-18:00","08:00-12:00 13:00-19:00","08:00-12:00 13:00-15:00 15:30-20:00","08:00-11:00 11:30-15:00 15:30-20:00","08:00-12:30 13:00-17:00"),
(2,"07:00-12:00 13:00-18:30","09:00-12:00 13:00-19:00","08:00-12:00 13:00-15:00 15:30-20:00","07:00-11:00 11:30-15:00 15:30-19:30","08:00-12:30 13:00-17:30","09:00-14:00 14:30-17:00"),
(3,"08:00-13:00 14:00-17:00","08:00-13:00 14:00-18:00","08:00-12:00 13:00-15:00 15:30-20:00","08:00-11:00 11:30-15:00 15:30-20:00","09:00-12:30 13:30-17:30"),
(4,"09:00-12:00 13:00-18:00","09:00-12:00 13:00-19:00","09:00-12:00 13:00-15:00 15:30-20:00","09:00-11:00 11:30-15:00 15:30-20:00","07:00-12:30 13:00-16:00","07:00-10:00 11:00-15:00");

delete from UserData;
Insert into UserData (username,pwd,nrContract,tip) values
("ana30","1234",1,'admin');

-- --------------------------------------------A doua Coloana
delete from DateAngajat;
Insert into DateAngajat (angajatCNP,adresa,nrTelefon,email,iban,nrContract,DataAngajarii) values
(123456789,"nr Magic,Str Baba Vanga,Pocreaca din Deal","696770107","ana@yahoo.com","IBANanei",1,CURDATE());

-- -----------------------------------------------A treia Cloana
delete from Contract;
Insert into Contract (nume,prenume,salariu,dataConcediere,nrOre,functie,nrUnitate) values
("Ana","Maria",5.23,null,10,'m',1);




