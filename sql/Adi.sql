-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema policlinica
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema policlinica
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS  `policlinica`;
CREATE SCHEMA IF NOT EXISTS `policlinica` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `policlinica` ;

-- -----------------------------------------------------
-- Table `policlinica`.`departament`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`departament` (
  `iddepartament` INT NOT NULL AUTO_INCREMENT,
  `nume_departament` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`iddepartament`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`utilizator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`utilizator` (
  `idutilizator` INT NOT NULL AUTO_INCREMENT,
  `CNP` INT NULL DEFAULT NULL,
  `Nume` VARCHAR(45) NULL DEFAULT NULL,
  `Prenume` VARCHAR(45) NULL DEFAULT NULL,
  `Numar_telefon` INT NULL DEFAULT NULL,
  `Email` VARCHAR(45) NULL DEFAULT NULL,
  `IBAN` VARCHAR(45) NULL DEFAULT NULL,
  `nr_contract` INT NULL DEFAULT NULL,
  `data_angajarii` DATE NULL DEFAULT NULL,
  `functie` VARCHAR(45) NULL DEFAULT NULL,
  `salar_neg` FLOAT(5,2) NULL DEFAULT NULL,
  `nr_ore` INT NULL DEFAULT NULL,
  `nume_utilizator` VARCHAR(45) NULL DEFAULT NULL,
  `iddepartament` INT NULL DEFAULT NULL,
  `parola` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idutilizator`),
  INDEX `iddepartament_idx` (`iddepartament` ASC) VISIBLE,
  CONSTRAINT `iddepartament`
    FOREIGN KEY (`iddepartament`)
    REFERENCES `policlinica`.`departament` (`iddepartament`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`asistent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`asistent` (
  `idasistent` INT NOT NULL,
  `tip` VARCHAR(45) NULL DEFAULT NULL,
  `grad` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idasistent`),
  CONSTRAINT `asistent`
    FOREIGN KEY (`idasistent`)
    REFERENCES `policlinica`.`utilizator` (`idutilizator`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`concediu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`concediu` (
  `idconcediu` INT NOT NULL,
  `iduser` INT NULL DEFAULT NULL,
  `inceput_concediu` DATE NULL DEFAULT NULL,
  `sfarsit_concediu` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`idconcediu`),
  INDEX `user2_idx` (`iduser` ASC) VISIBLE,
  CONSTRAINT `user2`
    FOREIGN KEY (`iduser`)
    REFERENCES `policlinica`.`utilizator` (`idutilizator`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`medic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`medic` (
  `idmedic` INT NOT NULL,
  `cod_parafa` INT NULL DEFAULT NULL,
  `post_didactic` VARCHAR(45) NULL DEFAULT NULL,
  `titlu_stiintific` VARCHAR(45) NULL DEFAULT NULL,
  `procent` FLOAT(5,2) NULL DEFAULT NULL,
  `grad` VARCHAR(45) NULL DEFAULT NULL,
  `competente` TEXT NULL DEFAULT NULL,
  `specialitatea` VARCHAR(45) NULL DEFAULT NULL,
  `disponibil` BOOLEAN DEFAULT TRUE,
  PRIMARY KEY (`idmedic`),
  CONSTRAINT `medic`
    FOREIGN KEY (`idmedic`)
    REFERENCES `policlinica`.`utilizator` (`idutilizator`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`programare`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`programare` (
  `idprogramare` INT NOT NULL,
  `id_medic` INT NULL DEFAULT NULL,
  `nume_pacient` VARCHAR(45) NULL DEFAULT NULL,
  `prenume_pacient` VARCHAR(45) NULL DEFAULT NULL,
  `ora` TIME NULL DEFAULT NULL,
  `zi` DATE NULL DEFAULT NULL,
  `durata` INT NULL DEFAULT NULL,
  PRIMARY KEY (`idprogramare`),
  INDEX `medic3_idx` (`id_medic` ASC) VISIBLE,
  CONSTRAINT `medic3`
    FOREIGN KEY (`id_medic`)
    REFERENCES `policlinica`.`medic` (`idmedic`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`raport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`raport` (
  `idraport` INT NOT NULL,
  `nume_pacient` VARCHAR(45) NULL DEFAULT NULL,
  `prenume_pacient` VARCHAR(45) NULL DEFAULT NULL,
  `id_asistent` INT NULL DEFAULT NULL,
  `id_medic_consultatie` INT NULL DEFAULT NULL,
  `data_consultatiei` DATE NOT NULL,
  `simptome` TEXT NULL DEFAULT NULL,
  `diagnostic` TEXT NULL DEFAULT NULL,
  `recomandari` TEXT NULL DEFAULT NULL,
  `idprogramare` INT NULL DEFAULT NULL,
   `parafat` TINYINT DEFAULT 0,
  PRIMARY KEY (`idraport`, `data_consultatiei`),
  INDEX `medic_consult_idx` (`id_medic_consultatie` ASC) VISIBLE,
  INDEX `asistent_idx` (`id_asistent` ASC) VISIBLE,
  INDEX `asistent2_idx` (`id_asistent` ASC) VISIBLE,
  INDEX `programare_idx` (`idprogramare` ASC) VISIBLE,
  INDEX `data_consult_idx` (`data_consultatiei` ASC) VISIBLE,
  CONSTRAINT `asistent2`
    FOREIGN KEY (`id_asistent`)
    REFERENCES `policlinica`.`asistent` (`idasistent`),
  CONSTRAINT `medic_consult`
    FOREIGN KEY (`id_medic_consultatie`)
    REFERENCES `policlinica`.`medic` (`idmedic`),
  CONSTRAINT `programare`
    FOREIGN KEY (`idprogramare`)
    REFERENCES `policlinica`.`programare` (`idprogramare`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`investigatii`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`investigatii` (
  `idinvestigatii` INT NOT NULL,
  `id_raport` INT NULL DEFAULT NULL,
  `ziua` DATE NULL DEFAULT NULL,
  `serviciu_medical` VARCHAR(45) NULL DEFAULT NULL,
  `descriere` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`idinvestigatii`),
  INDEX `raport_idx` (`id_raport` ASC) VISIBLE,
  CONSTRAINT `raport`
    FOREIGN KEY (`id_raport`)
    REFERENCES `policlinica`.`raport` (`idraport`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`istoric_medical`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`istoric_medical` (
  `data` DATE NOT NULL,
  `nume_pacient` VARCHAR(45) NULL DEFAULT NULL,
  `prenume_pacient` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`data`),
  CONSTRAINT `data_raport`
    FOREIGN KEY (`data`)
    REFERENCES `policlinica`.`raport` (`data_consultatiei`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`program`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`program` (
  `nrProgram` INT NOT NULL AUTO_INCREMENT,
  `luni_s` TIME NULL DEFAULT NULL,
  `luni_f` TIME NULL DEFAULT NULL,
  `marti_s` TIME NULL DEFAULT NULL,
  `marti_f` TIME NULL DEFAULT NULL,
  `miercuri_s` TIME NULL DEFAULT NULL,
  `miercuri_f` TIME NULL DEFAULT NULL,
  `joi_s` TIME NULL DEFAULT NULL,
  `joi_f` TIME NULL DEFAULT NULL,
  `vineri_s` TIME NULL DEFAULT NULL,
  `vineri_f` TIME NULL DEFAULT NULL,
  `sambata_s` TIME NULL DEFAULT NULL,
  `sambata_f` TIME NULL DEFAULT NULL,
  `duminica_s` TIME NULL DEFAULT NULL,
  `duminica_f` TIME NULL DEFAULT NULL,
  PRIMARY KEY (`nrProgram`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`unitate_medicala`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`unitate_medicala` (
  `idunitate_medicala` INT NOT NULL AUTO_INCREMENT,
  `denumire` VARCHAR(45) NULL DEFAULT NULL,
  `adresa` VARCHAR(45) NULL DEFAULT NULL,
  `descriere_servicii` TEXT NULL DEFAULT NULL,
  `nrProgram` INT NOT NULL,
  `idAngajat` INT NULL DEFAULT NULL,
  PRIMARY KEY (`idunitate_medicala`),
  INDEX `program_idx` (`nrProgram` ASC) VISIBLE,
  INDEX `angajatId_idx` (`idAngajat` ASC) VISIBLE,
  CONSTRAINT `angajatId`
    FOREIGN KEY (`idAngajat`)
    REFERENCES `policlinica`.`utilizator` (`idutilizator`),
  CONSTRAINT `program`
    FOREIGN KEY (`nrProgram`)
    REFERENCES `policlinica`.`program` (`nrProgram`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`orar_generic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`orar_generic` (
  `idorar_generic` INT NOT NULL,
  `idUnitate` INT NULL DEFAULT NULL,
  `ziua` DATE NULL DEFAULT NULL,
  `ora_s` TIME NULL DEFAULT NULL,
  `ora_f` TIME NULL DEFAULT NULL,
  `idAngajat` INT NULL DEFAULT NULL,
  PRIMARY KEY (`idorar_generic`),
  INDEX `orar_unitate_idx` (`idUnitate` ASC) VISIBLE,
  INDEX `orar_angajat_idx` (`idAngajat` ASC) VISIBLE,
  CONSTRAINT `orar_angajat`
    FOREIGN KEY (`idAngajat`)
    REFERENCES `policlinica`.`utilizator` (`idutilizator`),
  CONSTRAINT `orar_unitate`
    FOREIGN KEY (`idUnitate`)
    REFERENCES `policlinica`.`unitate_medicala` (`idunitate_medicala`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`orar_specific`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`orar_specific` (
  `idorar_specific` INT NOT NULL,
  `iduser` INT NULL DEFAULT NULL,
  `zi` VARCHAR(45) NULL DEFAULT NULL,
  `ora_s` TIME NULL DEFAULT NULL,
  `ora_f` TIME NULL DEFAULT NULL,
  `unitate_med` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idorar_specific`),
  INDEX `user_idx` (`iduser` ASC) VISIBLE,
  CONSTRAINT `user`
    FOREIGN KEY (`iduser`)
    REFERENCES `policlinica`.`utilizator` (`idutilizator`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`servicii_medicale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`servicii_medicale` (
  `id_servicii` INT NOT NULL AUTO_INCREMENT,
  `id_medic` INT NULL DEFAULT NULL,
  `denumire` VARCHAR(45) NULL DEFAULT NULL,
  `pret` INT NULL DEFAULT NULL,
  `durata` TIME NULL DEFAULT NULL,
  `competenta` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id_servicii`),
  INDEX `medic2_idx` (`id_medic` ASC) VISIBLE,
  CONSTRAINT `medic2`
    FOREIGN KEY (`id_medic`)
    REFERENCES `policlinica`.`medic` (`idmedic`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
