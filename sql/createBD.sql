-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema role
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `role` ;

-- -----------------------------------------------------
-- Schema final
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `final` ;

-- -----------------------------------------------------
-- Schema final
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `final` ;

USE `final` ;

-- -----------------------------------------------------
-- Table `final`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `final`.`users` ;

CREATE TABLE IF NOT EXISTS `final`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` ENUM('admin', 'driver', 'manager') NOT NULL,
  `datareg` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `salary` FLOAT NOT NULL DEFAULT 0,
  `photo` VARCHAR(45) NULL,
  `phone` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `final`.`cars`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `final`.`cars` ;

CREATE TABLE IF NOT EXISTS `final`.`cars` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(15) NOT NULL,
  `mark` VARCHAR(45) NOT NULL,
  `model` VARCHAR(80) NOT NULL,
  `photo` VARCHAR(45) NULL,
  `type_bodywork` ENUM('tank', 'bulk', 'car', 'animal', 'container') NOT NULL,
  `max_weight` FLOAT NOT NULL,
  `max_volume` FLOAT NOT NULL,
  `id_driver` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC),
  INDEX `fk_cars_users_idx` (`id_driver` ASC),
  CONSTRAINT `fk_cars_users`
    FOREIGN KEY (`id_driver`)
    REFERENCES `final`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `final`.`journeys`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `final`.`journeys` ;

CREATE TABLE IF NOT EXISTS `final`.`journeys` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` ENUM('new', 'on_process', 'old', 'canceled') NOT NULL,
  `price` FLOAT NOT NULL,
  `weight` FLOAT NOT NULL,
  `type` ENUM('bulk', 'liquid', 'animal', 'solid', 'car') NOT NULL,
  `volume` FLOAT NOT NULL COMMENT 'Volume in m^3',
  `id_manager` INT NOT NULL,
  `cars_id` INT NULL,
  `from` VARCHAR(15) NOT NULL,
  `where` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_journeys_users1_idx` (`id_manager` ASC),
  INDEX `fk_journeys_cars1_idx` (`cars_id` ASC),
  CONSTRAINT `fk_journeys_users1`
    FOREIGN KEY (`id_manager`)
    REFERENCES `final`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_journeys_cars1`
    FOREIGN KEY (`cars_id`)
    REFERENCES `final`.`cars` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `final`.`potential_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `final`.`potential_users` ;

CREATE TABLE IF NOT EXISTS `final`.`potential_users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `role` ENUM('driver', 'manager') NOT NULL,
  `phone` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final`.`potential_cars`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `final`.`potential_cars` ;

CREATE TABLE IF NOT EXISTS `final`.`potential_cars` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(15) NOT NULL,
  `photo` VARCHAR(255) NULL,
  `max_weight` FLOAT NOT NULL,
  `users_id` INT NULL,
  `potential_users_id` INT NULL,
  `type_bodywork` ENUM('tank', 'bulk', 'animal', 'container', 'car') NOT NULL,
  `max_volume` FLOAT NOT NULL,
  `mark` VARCHAR(45) NOT NULL,
  `model` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_potential_cars_users1_idx` (`users_id` ASC),
  INDEX `fk_potential_cars_potential_users1_idx` (`potential_users_id` ASC),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC),
  CONSTRAINT `fk_potential_cars_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `final`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_potential_cars_potential_users1`
    FOREIGN KEY (`potential_users_id`)
    REFERENCES `final`.`potential_users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
