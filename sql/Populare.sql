-- ---------------------------------------------Prima cloana
use policlinica;

delete from AparatMedical;
Insert into aparatmedical (nume) values
("Stestoscop"),
("Ceas cu cuc");

delete from AparatNecesar;
Insert into aparatnecesar (nrServiciu,nrAparat) values
(1,1),
(1,2);

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




