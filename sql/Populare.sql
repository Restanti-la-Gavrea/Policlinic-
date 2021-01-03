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

delete from contract;
Insert into contract values
(1,"Baragan","Andrei",35,210,"hr",1),
(2,"Bledea","Dragos",30,200,"rec",1),
(3,"Gavrila","Tiberiu",35,190,"m",1),
(4,"Tiplea","Ionut",25,205,"eco",1),
(5,"Varga","Robert",50,210,"m",1),
(6,"Ivan","Cosmina",50,200,"m",1),
(7,"Cornea","Georgiana",40,190,"as",1),
(8,"Gabor","Mihai",50,205,"m",2),
(9,"Ratiu","Vlad",45,180,"m",2),
(10,"Lazareanu","Sabina",35,190,"as",2),
(11,"Hustiuc","Teodor",35,200,"hr",2),
(12,"Manole","Gicu",25,195,"eco",2),
(13,"Todor","Robert",30,180,"rec",2),
(14,"Rusu","Mihai",45,190,"m",3),
(15,"Lunca","Eduard",35,190,"eco",3),
(16,"Coatu","Sorin",35,190,"hr",3),
(17,"Pop","Diana",35,190,"rec",3),
(18,"David","Valeriu",40,190,"as",3),
(19,"Gavrila","Tiberiu",35,190,"as",4),
(20,"Rusu","David",55,190,"m",4),
(21,"Berci","Roxana",30,190,"eco",4),
(22,"Trifoi","Mihaela",35,190,"res",4),
(23,"Ofrim","Adela",35,190,"hr",4);


delete from UserData;
Insert into UserData  values
(1,"0000","admin"),
(2,"0000","admin"),
(3,"0000","user"),
(4,"0000","user"),
(5,"0000","Sadmin"),
(6,"0000","Sadmin"),
(7,"1111","admin"),
(8,"1234","Sadmin"),
(9,"1357","admin"),
(10,"1111","user"),
(11,"2345","user"),
(12,"9999","admin"),
(13,"4456","Sadmin"),
(14,"9832","admin"),
(15,"6580","Sadmin"),
(16,"5684","admin"),
(17,"4550","user"),
(18,"0712","user"),
(19,"1234","user"),
(20,"1111","admin"),
(21,"1111","admin"),
(22,"0000","Sadmin"),
(23,"5555","user");

delete from dateangajat;
Insert into dateangajat values
(501492145,"In varful Carpatilor acolo unde se strang animalele","5442974402","steaua2@gmail.com","IBANRO6622",1,"01-01-2001"),
(123456789,"nr Magic,Str Baba Vanga,Pocreaca din Deal","6963770107","bri@yahoo.com","IBANanei",2,CURDATE()),
(674392843,"A treia strada la stanga la casa cu 2 etaje","8795123091","dia420@gmail.com","GBKK2139512151",3,"29-02-2000"),
(315918244,"Sub podul vechi de 100 de ani din capitala Angliei, Rusia","5329854115","restanta@utcluj.com","AFP141256124",4,"29-02 2010"),
(126125125,"Al patrulea copac de langa spitalul de urgente","7693520943","anideliceu@maimuta.uk","TYA12351612",5,"14-14-1914"),
(879540324,"A doua statie de bus de la aeroport","7592481042","boratsagdiev@europa.ro","POO35135113",6,"11-9-2001"),
(019249127,"In podul din casa lui Pinocchio","4583922687","mariansafi@gmail.ro","AF4412515125",7,"25-12-2019"),
(105910258,"In baia din metrou","9334096751","arianagrande@gmail.com","BD12940124",8,"01-10-2008"),
(012580129,"La ferma lui LeatherFace","7593302587","kimmygranger@gmail.com","AS21312321",9,"04-02-2020"),
(163523460,"In buncarul vechi de pe vremea lui bunicu' ","6550938471","florinpascu@yahoo.ro","TO123123124",10,"06-06-2006"),
(162623321,"In subteranul scolii unde a invatat","4033598576","johnnysins@gmail.com","RE123912741",11,"07-01-2004"),
(123909262,"In fundul lacului Malaia","5783390561","andersenamy@canada.ro","TRE2147092",12,"05-04-1994"),
(812034913,"In santul de langa carciuma din drum","5901587629","jensenpeta@usanda.us","POT1279081",13,"19-04-2000"),
(023851314,"In varful unui copac din Padurea Cozia","0958509632","johnwick@movies.com","HMG1248129",14,"30-01-2003"),
(156326239,"In mlastina de langa oras","8665043571","sparrowjack@caribbean.com","YTK1250712",15,"15-08-2007"),
(162632356,"Dupa al 40-lea stalp de electricitate la stanga pe strada cu namol","3024865491","erenjeager@titans.com","GA7433248712",16,"07-01-2005"),
(173477477,"La granita dintre Romania si Ungaria","56540382912","mikasaarmin@astre.ro","KHM214012",17,"02-02-2005"),
(182553255,"In mijlocul Campiei Romane","1208576023","levilight@note.jpn","IVS123850",18,"22-02-2001"),
(197236235,"Langa cosul de gunoi de la mall","5503211789","lmn@ripthem.com","GIER120842",19,"26-02-2002"),
(212412440,"In directia iesirii din oras, al patrulea stalp in sus","1029584732","reinerbertholdt@ziduri.ro","ZPC123971",20,"01-01-2001"),
(215325555,"In interiorul policlinicii","5758330256","attackontitan@superio.com","IGO12412412",21,"04-12-1999"),
(222344342,"Langa beciul din Castelul Peles","0754029841","nanatsunotaizai@captain.com","JCA2131212",22,"05-05-2005"),
(235235325,"La a 5-a aruncatura de bat inspre supermarket","5794209123","bokunoheroacademia@dago.com","GAI1293841",23,"01-11-2001");


-- --------------------------------------------A doua Coloana


-- -----------------------------------------------A treia Cloana
delete from Contract;
Insert into Contract (nume,prenume,salariu,dataConcediere,nrOre,functie,nrUnitate) values
("Ana","Maria",5.23,null,10,'m',1);




