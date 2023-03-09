-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb3 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Equipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Equipo` (
  `idEquipo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `puntos` INT NOT NULL,
  `logo` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`idEquipo`),
  UNIQUE INDEX `idEquipo_UNIQUE` (`idEquipo` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`Juego`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Juego` (
  `idJuego` INT NOT NULL AUTO_INCREMENT,
  `dificultad` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `instrucciones` VARCHAR(500) NOT NULL,
  `intentosMax` INT NOT NULL,
  PRIMARY KEY (`idJuego`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`Jugador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Jugador` (
  `idJugador` INT NOT NULL AUTO_INCREMENT,
  `admin` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `clave` VARCHAR(100) NOT NULL,
  `avatar` VARCHAR(100) NULL DEFAULT NULL,
  `puntos` INT NULL DEFAULT NULL,
  `Equipo_idEquipo` INT NULL DEFAULT NULL,
  PRIMARY KEY (`idJugador`),
  UNIQUE INDEX `idJugador_UNIQUE` (`idJugador` ASC) VISIBLE,
  INDEX `fk_Jugador_Equipo1_idx` (`Equipo_idEquipo` ASC) VISIBLE,
  CONSTRAINT `fk_Jugador_Equipo1`
    FOREIGN KEY (`Equipo_idEquipo`)
    REFERENCES `mydb`.`Equipo` (`idEquipo`)
    ON DELETE SET NULL)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`Partida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Partida` (
  `idPartida` INT NOT NULL AUTO_INCREMENT,
  `Jugador_idJugador` INT NOT NULL,
  `Juego_idJuego` INT NOT NULL,
  `datetime` DATETIME NOT NULL,
  `intentos` INT NOT NULL,
  `palabra` VARCHAR(150) NOT NULL,
  `puntos` INT NOT NULL,
  PRIMARY KEY (`idPartida`),
  INDEX `fk_Jugador_has_Juego_Juego1_idx` (`Juego_idJuego` ASC) VISIBLE,
  INDEX `fk_Jugador_has_Juego_Jugador_idx` (`Jugador_idJugador` ASC) VISIBLE,
  CONSTRAINT `fk_Jugador_has_Juego_Juego1`
    FOREIGN KEY (`Juego_idJuego`)
    REFERENCES `mydb`.`Juego` (`idJuego`),
  CONSTRAINT `fk_Jugador_has_Juego_Jugador`
    FOREIGN KEY (`Jugador_idJugador`)
    REFERENCES `mydb`.`Jugador` (`idJugador`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
