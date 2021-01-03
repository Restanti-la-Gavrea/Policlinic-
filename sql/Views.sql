use policlinica;
Drop view if exists datepersonale;
CREATE VIEW datepersonale AS Select contract.nrContract,contract.nume,contract.prenume,contract.salariu,contract.nrOre,contract.functie,contract.nrUnitate,userdata.username,
userdata.pwd,userdata.tip,dateangajat.angajatCNP,dateangajat.adresa,dateangajat.nrTelefon,dateangajat.email,dateangajat.iban,dateangajat.DataAngajarii 
from dateangajat INNER JOIN contract INNER JOIN userdata ON contract.nrcontract=dateangajat.nrcontract and contract.nrcontract=userdata.nrcontract;

CREATE VIEW orar AS Select concediu.nrContract,dataIncepere,dataTerminare,contract.nume,contract.prenume,contract.salariu,contract.nrOre,contract.functie,
contract.nrUnitate,ogID,ziSaptamana,intervalOrar,unitatemedicala.nume AS numeUM,unitatemedicala.adresa from unitatemedicala INNER JOIN contract INNER JOIN 
orargeneric INNER JOIN concediu ON contract.nrcontract=orargeneric.nrcontract and contract.nrcontract=concediu.nrcontract and orargeneric.nrUnitate=unitatemedicala.nrUnitate;

CREATE VIEW exceptiiorarmedic AS Select osID,ziCalendaristica,intervalOrar,unitatemedicala.nrunitate,unitatemedicala.nume AS numeUM,adresa,nrProgram,
contract.nrContract,contract.nume,contract.prenume,contract.salariu,contract.nrOre,contract.functie from unitatemedicala INNER JOIN contract 
INNER JOIN orarspecific ON contract.nrContract=orarspecific.nrContract and unitatemedicala.nrUnitate=orarspecific.nrUnitate and 
contract.nrUnitate=unitatemedicala.nrUnitate;

CREATE VIEW vizualizareistoric AS Select nume,prenume,pacient.nrpacient,programare.nrprogramare,dataP,ora,nrCMedic,nrCabinet,nrraport,medicRecomandare,
asistentContract,simptome,diagnostic,recomandari from raport INNER JOIN pacient INNER JOIN programare ON pacient.nrpacient=programare.nrpacient and 
programare.nrProgramare=raport.nrProgramare;

Create VIEW PlatiMedic AS Select  Programare.nrProgramare,suma,ziPlata,nrCMedic,comision,salariu from Plata INNER JOIN Programare ON 
Plata.nrProgramare = Programare.nrProgramare INNER JOIN Medic ON Programare.nrCMedic = Medic.nrContract INNER JOIN Contract ON 
Medic.nrContract =Contract.nrContract ;

CREATE VIEW serviciilecabinetului AS Select cabinet.nrCabinet,nrUnitate,ID,serviciu.nrServiciu,nume,nrSpecialitate,needsCompetenta,pret,durata 
from cabinet INNER JOIN serviciu INNER JOIN serviciupercabinet ON cabinet.nrCabinet=serviciupercabinet.nrCabinet and 
serviciu.nrServiciu=serviciupercabinet.nrServiciu;




CREATE VIEW realizarebon AS Select * from medic INNER JOIN serviciucustom INNER JOIN programare INNER JOIN serviciu INNER JOIN serviciuperprogramare;
CREATE VIEW serviciilemedicului AS Select * from medic INNER JOIN specialitatemedic INNER JOIN specialitate INNER JOIN serviciu INNER JOIN serviciucustom;
CREATE VIEW raportcomplet AS Select * from raport INNER JOIN programare INNER JOIN pacient INNER JOIN medic INNER JOIN tipasistent INNER JOIN contract INNER JOIN serviciu INNER JOIN serviciuperprogramare;
