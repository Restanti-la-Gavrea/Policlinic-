drop database if exists policlinica;
create database policlinica;
use policlinica;
-- -------------------------------------------------------------------------coloana 1 

create table AparatMedical (
	nrAparat int primary key auto_increment not null,
    nume varchar(32) not null
);

create table AparatPerCabinet (
	ID int primary key auto_increment not null,
    nrAparat int not null,
    nrCabinet int not null
);


create table Cabinet (
	nrCabinet int primary key auto_increment not null,
    nrUnitate int not null
);


create table OrarGeneric (
	ogID int primary key auto_increment not null,
    ziSaptamana varchar(10) not null,
    intervalOrar varchar(36) not null,
    nrContract int not null,
    nrUnitate int not null
);


create table UserData (
	nrcontract int unique primary key not null,
    username varchar(32) not null,
    pwd varchar(64) not null,
    tip enum("admin","Sadmin","user")  not null
);
-- --------------------------------------------------------------------coloana 2


create table AparatNecesar (
	ID int primary key auto_increment not null,
    nrServiciu int not null,
    nrAparat int not null
);


create table UnitateMedicala (
	nrUnitate int primary key auto_increment not null,
    nume varchar(64) not null,
    adresa varchar(128) not null,
    nrProgram int unique not null
);


create table OrarSpecific (
	osID int primary key auto_increment not null,
    ziCalendaristica varchar(10) not null,
    intervalOrar varchar(36) not null,
    nrContract int not null,
    nrUnitate int not null
);


create table DateAngajat (
	angajatCNP BIGINT primary key auto_increment not null,
    adresa varchar(128) not null,
    nrTelefon varchar(11) not null,
    email varchar(32) not null,
    iban varchar(35) not null,-- e de ajuns ?
    nrContract int unique not null unique,
    DataAngajarii date not null
);


create table Concediu (
	nrContract int unique primary key not null,
    dataIncepere date,
    dataTerminare date
);
-- ------------------------------------------------------------Coloana 3

create table ServiciuPerCabinet (
	ID int primary key auto_increment not null,
    nrCabinet int not null,
    nrServiciu int not null
);


create table Program(
	nrProgram int primary key auto_increment not null,
    luni varchar(24),
    marti varchar(24),
    miercuri varchar(24),
    joi varchar(24),
    vineri varchar(24),
    sambata varchar(24),
    duminica varchar(24)
);


create table Contract (
	nrContract int primary key auto_increment not null,
    nume varchar(20) not null,
    prenume varchar(20) not null,
    salariu numeric(10,2) not null,
    dataConcediere date,
    nrOre int not null,
    functie enum("hr","eco","rec","as","m") not null,
    nrUnitate int not null
);


create table TipAsistentMedical (
	nrContract int primary key not null,
    tip enum("generalist","laborator","radiologie") not null,
    grad boolean
);


create table Pacient (
	nrPacient int primary key auto_increment not null,
    nume varchar(20) ,
    prenume varchar(20)
);
-- -----------------------------------------------------------coloana 4


create table Serviciu (
	nrServiciu int primary key auto_increment not null,
    nume varchar(32) not null,
    nrSpecialitate int,
    needsCompetenta boolean,
    pret numeric(10,2)not null,
    durata time not null
);


create table ServiciuCustom (
	ID int primary key auto_increment not null,
	nrServiciu int not null unique,
    nrContract int not null,
    newPret numeric(10,2) ,
    newDurata time 
);


create table Medic(
	nrContract int primary key unique,
    codParafa int not null,
    competente varchar(128),
    titluStiintific varchar(128),
    postDidactic varchar(128),
    comision numeric(4,2) not null
    );


create table Raport(
	nrRaport int primary key auto_increment,
    nrProgramare int unique not null,
    medicRecomandare int,
    asistentContract int,
    simptome varchar(256),
    -- investigatii din ServiciuPerProgramare
    diagnostic varchar(256),
    recomandari varchar(256),
    parafat boolean not null
);

-- --------------------------------------------------------Coloana 5


create table Specialitate(
	nrSpecialitate int primary key auto_increment,
    nume varchar(64) not null
);
    

create table SpecialitateMedic(
	ID int primary key auto_increment,
    nrContract int not null,
    nrSpecialitate int not null,
    grad enum("primar","specialist","profesor") not null
);
    

create table Plata(
	nrPlata int primary key auto_increment,
    suma numeric(10,2) not null,
    ziPlata date not null,
    nrProgramare int not null unique
);
    

create table Programare(
	nrProgramare int primary key not null auto_increment,
    dataP date not null,
    ora time not null,
    nrCMedic int not null,
    nrCabinet int,
    nrPacient int not null
);
    

create table ServiciuPerProgramare(	
	ID int primary key auto_increment,
    nrServiciu int not null,
    nrProgramare int not null,
    rezultat varchar(64)
    -- trebuie tinut cont de statusul raportului pentru modificarea campului rezultat
);