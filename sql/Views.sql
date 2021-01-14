use policlinica;
drop view if exists datepersonale; 
CREATE VIEW datepersonale AS Select contract.nrContract,contract.nume,contract.prenume,contract.salariu,contract.nrOre,contract.functie,contract.nrUnitate,
username,userdata.pwd,userdata.tip,dateangajat.angajatCNP,dateangajat.adresa,dateangajat.nrTelefon,dateangajat.email,dateangajat.iban,dateangajat.DataAngajarii 
from dateangajat INNER JOIN contract INNER JOIN userdata ON contract.nrcontract=dateangajat.nrcontract and contract.nrcontract=userdata.nrcontract;
drop view if exists orar;
CREATE VIEW orar AS Select contract.nrcontract,contract.nume,contract.prenume,contract.salariu,contract.nrOre,contract.functie,
contract.nrUnitate,ogID,ziSaptamana,intervalOrar,unitatemedicala.nume AS numeUM,unitatemedicala.adresa from unitatemedicala INNER JOIN contract INNER JOIN 
orargeneric ON contract.nrcontract=orargeneric.nrcontract  and orargeneric.nrUnitate=unitatemedicala.nrUnitate;
drop view if exists exceptiiorarmedic;
CREATE VIEW exceptiiorarmedic AS Select osID,ziCalendaristica,intervalOrar,unitatemedicala.nrunitate,unitatemedicala.nume AS numeUM,adresa,nrProgram,
contract.nrContract,contract.nume,contract.prenume,contract.salariu,contract.nrOre,contract.functie from unitatemedicala INNER JOIN contract 
INNER JOIN orarspecific ON contract.nrContract=orarspecific.nrContract and unitatemedicala.nrUnitate=orarspecific.nrUnitate and 
contract.nrUnitate=unitatemedicala.nrUnitate;
drop view if exists vizualizareistoric;
CREATE VIEW vizualizareistoric AS Select nume,prenume,pacient.nrpacient,programare.nrprogramare,dataP,ora,nrCMedic,nrCabinet,nrraport,medicRecomandare,
asistentContract,simptome,diagnostic,recomandari from raport INNER JOIN pacient INNER JOIN programare ON pacient.nrpacient=programare.nrpacient and 
programare.nrProgramare=raport.nrProgramare;
drop view if exists platimedic;
Create VIEW platimedic AS Select  Programare.nrProgramare,suma,ziPlata,nrCMedic,comision,salariu from Plata INNER JOIN Programare ON 
Plata.nrProgramare = Programare.nrProgramare INNER JOIN Medic ON Programare.nrCMedic = Medic.nrContract INNER JOIN Contract ON 
Medic.nrContract =Contract.nrContract ;
drop view if exists serviciilecabinetului;
CREATE VIEW serviciilecabinetului AS Select cabinet.nrCabinet,nrUnitate,ID,serviciu.nrServiciu,nume,nrSpecialitate,needsCompetenta,pret,durata 
from cabinet INNER JOIN serviciu INNER JOIN serviciupercabinet ON cabinet.nrCabinet=serviciupercabinet.nrCabinet and 
serviciu.nrServiciu=serviciupercabinet.nrServiciu;
drop view if exists realizarebon;
CREATE VIEW realizarebon AS Select serviciu.nrserviciu,nume,nrspecialitate,needscompetenta,pret,durata,serviciuperprogramare.id,rezultat,
programare.nrprogramare,dataP,ora,nrcabinet,nrpacient,programare.nrcmedic,codparafa,competente,titlustiintific,postdidactic,comision,
serviciucustom.id AS idservcustom,newpret,newdurata from medic INNER JOIN serviciucustom INNER JOIN programare INNER JOIN serviciu 
INNER JOIN serviciuperprogramare ON serviciu.nrserviciu=serviciuperprogramare.nrserviciu and programare.nrprogramare=serviciuperprogramare.nrprogramare 
and serviciu.nrserviciu=serviciucustom.nrserviciu and medic.nrcontract=serviciucustom.nrcontract and programare.nrcmedic=medic.nrcontract;
drop view if exists serviciilemedicului;
CREATE VIEW serviciilemedicului AS Select medic.nrcontract,codparafa,competente,titlustiintific,postdidactic,specialitatemedic.id,grad,specialitate.nrspecialitate,
specialitate.nume AS numespecialitate,serviciu.nrserviciu,serviciu.nume AS numeserviciu,needscompetenta,pret,durata,serviciucustom.id AS idserviciucustom,newpret,
newdurata from medic INNER JOIN specialitatemedic INNER JOIN specialitate INNER JOIN serviciu INNER JOIN serviciucustom 
ON medic.nrcontract=specialitatemedic.nrcontract and specialitate.nrspecialitate=specialitatemedic.nrspecialitate and medic.nrcontract=serviciucustom.nrcontract 
and serviciu.nrserviciu=serviciucustom.nrserviciu and serviciu.nrspecialitate=specialitate.nrspecialitate;
drop view if exists raportcomplet; 
CREATE VIEW raportcompletmediccontract AS Select distinct contract.nrcontract,contract.nume,contract.prenume,salariu,nrore,functie,nrunitate,tip as tipasistent,grad,pacient.nrpacient,
pacient.nume as numepacient,pacient.prenume as prenumepacient,codparafa as codparafamedic,competente,titlustiintific,postdidactic,programare.nrprogramare,datap,
ora,nrcabinet,serviciu.nrserviciu,serviciu.nume as numeserviciu,nrspecialitate,needscompetenta,pret,durata,rezultat,raport.nrraport,simptome,
diagnostic,recomandari,parafat from raport INNER JOIN programare INNER JOIN pacient INNER JOIN medic INNER JOIN tipasistentmedical INNER JOIN contract 
INNER JOIN serviciu INNER JOIN serviciuperprogramare ON pacient.nrpacient=programare.nrpacient and programare.nrcmedic=medic.nrcontract 
and programare.nrprogramare=raport.nrprogramare and serviciu.nrserviciu=serviciuperprogramare.nrserviciu and serviciuperprogramare.nrprogramare=programare.nrprogramare and medic.nrcontract=contract.nrcontract;

CREATE VIEW raportcompletasistentcontract AS Select distinct contract.nrcontract,contract.nume,contract.prenume,salariu,nrore,functie,nrunitate,tip as tipasistent,grad,pacient.nrpacient,
pacient.nume as numepacient,pacient.prenume as prenumepacient,codparafa as codparafamedic,competente,titlustiintific,postdidactic,programare.nrprogramare,datap,
ora,nrcabinet,serviciu.nrserviciu,serviciu.nume as numeserviciu,nrspecialitate,needscompetenta,pret,durata,rezultat,raport.nrraport,simptome,
diagnostic,recomandari,parafat from raport INNER JOIN programare INNER JOIN pacient INNER JOIN medic INNER JOIN tipasistentmedical INNER JOIN contract 
INNER JOIN serviciu INNER JOIN serviciuperprogramare ON pacient.nrpacient=programare.nrpacient and programare.nrcmedic=medic.nrcontract 
and programare.nrprogramare=raport.nrprogramare and serviciu.nrserviciu=serviciuperprogramare.nrserviciu and serviciuperprogramare.nrprogramare=programare.nrprogramare and tipasistentmedical.nrcontract=contract.nrcontract;
