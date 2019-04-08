-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema final
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema final
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `final` DEFAULT CHARACTER SET utf8 ;
USE `final` ;

-- -----------------------------------------------------
-- Table `final`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` ENUM('admin', 'driver', 'manager') NOT NULL,
  `datareg` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `salary` FLOAT NOT NULL DEFAULT '0',
  `photo` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC))
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `final`.`cars`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final`.`cars` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(15) NOT NULL,
  `mark` VARCHAR(45) NOT NULL,
  `model` VARCHAR(80) NOT NULL,
  `photo` VARCHAR(45) NULL DEFAULT NULL,
  `type_bodywork` ENUM('tank', 'bulk', 'car', 'animal', 'container') NOT NULL,
  `max_weight` FLOAT NOT NULL,
  `max_volume` FLOAT NOT NULL,
  `id_driver` INT(11) NOT NULL,
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
CREATE TABLE IF NOT EXISTS `final`.`journeys` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` ENUM('new', 'on_process', 'old', 'canceled') NOT NULL,
  `price` FLOAT NOT NULL,
  `weight` FLOAT NOT NULL,
  `type` ENUM('bulk', 'liquid', 'animal', 'solid', 'car') NOT NULL,
  `volume` FLOAT NOT NULL COMMENT 'Volume in m^3',
  `id_manager` INT(11) NOT NULL,
  `cars_id` INT(11) NULL DEFAULT NULL,
  `from` VARCHAR(15) NOT NULL,
  `where` VARCHAR(15) NOT NULL,
  `date_finish` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_journeys_users1_idx` (`id_manager` ASC),
  INDEX `fk_journeys_cars1_idx` (`cars_id` ASC),
  CONSTRAINT `fk_journeys_cars1`
  FOREIGN KEY (`cars_id`)
  REFERENCES `final`.`cars` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_journeys_users1`
  FOREIGN KEY (`id_manager`)
  REFERENCES `final`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `final`.`potential_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final`.`potential_users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `role` ENUM('driver', 'manager') NOT NULL,
  `phone` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC))
  ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `final`.`potential_cars`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `final`.`potential_cars` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(15) NOT NULL,
  `photo` VARCHAR(255) NULL DEFAULT NULL,
  `max_weight` FLOAT NOT NULL,
  `users_id` INT(11) NULL DEFAULT NULL,
  `potential_users_id` INT(11) NULL DEFAULT NULL,
  `type_bodywork` ENUM('tank', 'bulk', 'animal', 'container', 'car') NOT NULL,
  `max_volume` FLOAT NOT NULL,
  `mark` VARCHAR(45) NOT NULL,
  `model` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC),
  INDEX `fk_potential_cars_users1_idx` (`users_id` ASC),
  INDEX `fk_potential_cars_potential_users1_idx` (`potential_users_id` ASC),
  CONSTRAINT `fk_potential_cars_potential_users1`
  FOREIGN KEY (`potential_users_id`)
  REFERENCES `final`.`potential_users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_potential_cars_users1`
  FOREIGN KEY (`users_id`)
  REFERENCES `final`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARACTER SET = utf8;

USE `final` ;

-- -----------------------------------------------------
-- function try_to_login
-- -----------------------------------------------------

DELIMITER $$
USE `final`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `try_to_login`(myemail VARCHAR(256), pass VARCHAR(45)) RETURNS int(11)
  BEGIN
    DECLARE var INT DEFAULT 0;
    SELECT count(*) from users WHERE users.email = myemail limit 1 into var;
    if(var = 0) then return 1; end if;
    SELECT count(*) from users WHERE users.email = myemail and users.password = pass limit 1 into var;
    if(var = 0) then return 2; end if;
    return 0;
  END$$

DELIMITER ;

DELIMITER $$
USE `final`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_portfolio`(IN user_id INT)
BEGIN
	DECLARE var VARCHAR(30);
    SELECT role from users where id = user_id into var;
    if(var = 'driver') then
    SELECT users.id, name, surname, email, phone, role, timestampdiff(DAY, datareg, now()) as days, sum(if(accept = 'yes', 1,0)) as journeys FROM users
	INNER JOIN cars ON users.id = id_driver
    LEFT OUTER JOIN journeys_has_cars ON cars.id = cars_id
	WHERE users.id =  user_id;
    else
    SELECT users.id, name, surname, email, phone, role, timestampdiff(DAY, datareg, now()) as days, count(id_manager) as journeys FROM users
	INNER JOIN journeys ON users.id = id_manager WHERE users.id = user_id ;
    end if;
END$$

   DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
