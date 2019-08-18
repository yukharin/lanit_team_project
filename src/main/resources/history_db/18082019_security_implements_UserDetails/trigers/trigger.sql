-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema lanit
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lanit
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lanit` DEFAULT CHARACTER SET utf8 ;
USE `lanit` ;

-- -----------------------------------------------------
-- Table `lanit`.`organizations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanit`.`organizations` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `org_type` TINYINT(1) UNSIGNED NOT NULL,
  `id_gos_org` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `id_gos_org`
    FOREIGN KEY (`id_gos_org`)
    REFERENCES `lanit`.`organizations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `id_gos_org_idx` ON `lanit`.`organizations` (`id_gos_org` ASC) INVISIBLE;

CREATE INDEX `name` ON `lanit`.`organizations` (`name` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `lanit`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanit`.`users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_org` INT UNSIGNED NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `org`
    FOREIGN KEY (`id_org`)
    REFERENCES `lanit`.`organizations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `id_org` ON `lanit`.`users` (`id_org` ASC) VISIBLE;

CREATE INDEX `last_name` ON `lanit`.`users` (`last_name` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `lanit`.`notifications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanit`.`notifications` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_org` INT UNSIGNED NOT NULL,
  `notification_type` VARCHAR(150) NULL,
  `id_notification_status` INT UNSIGNED NOT NULL,
  `date_received` DATE NOT NULL,
  `date_response` DATE NOT NULL,
  `letter_number` VARCHAR(12) NULL,
  `id_user_curator_gos` INT UNSIGNED NOT NULL,
  `id_user_implementor` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `client`
    FOREIGN KEY (`id_org`)
    REFERENCES `lanit`.`organizations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `responsible_implementor`
    FOREIGN KEY (`id_user_implementor`)
    REFERENCES `lanit`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `curator_gos`
    FOREIGN KEY (`id_user_curator_gos`)
    REFERENCES `lanit`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `responsible_implementor_idx` ON `lanit`.`notifications` (`id_user_implementor` ASC) VISIBLE;

CREATE INDEX `curator_gos_idx` ON `lanit`.`notifications` (`id_user_curator_gos` ASC) VISIBLE;

CREATE INDEX `date_received` ON `lanit`.`notifications` (`date_received` ASC) VISIBLE;

CREATE INDEX `date_response` ON `lanit`.`notifications` (`date_response` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `lanit`.`actions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanit`.`actions` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_notification` INT UNSIGNED NOT NULL,
  `id_action_type` INT UNSIGNED NOT NULL,
  `content` VARCHAR(300) NULL,
  `date` DATETIME NOT NULL,
  `id_implementor` INT UNSIGNED NOT NULL,
  `id_notification_status` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `date`),
  CONSTRAINT `id_implementor`
    FOREIGN KEY (`id_implementor`)
    REFERENCES `lanit`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_notification`
    FOREIGN KEY (`id_notification`)
    REFERENCES `lanit`.`notifications` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;

CREATE INDEX `id_implementor_idx` ON `lanit`.`actions` (`id_implementor` ASC) VISIBLE;

CREATE INDEX `id_notification_idx` ON `lanit`.`actions` (`id_notification` ASC) VISIBLE;

CREATE INDEX `idx_datetime` ON `lanit`.`actions` (`date` ASC) VISIBLE;

USE `lanit`;

DELIMITER $$
USE `lanit`$$
CREATE DEFINER = CURRENT_USER TRIGGER `lanit`.`notifications_AFTER_INSERT` AFTER INSERT ON `notifications` FOR EACH ROW
BEGIN
INSERT INTO actions (id_notification, id_action_type, content, date, id_implementor, id_notification_status)
             values (new.id, 0, 'Начало работы с уведомлением', now(), new.id_user_curator_gos, 0);
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `lanit`.`organizations`
-- -----------------------------------------------------
START TRANSACTION;
USE `lanit`;
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`) VALUES (1, 'medicine', 1, null);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`) VALUES (2, 'construction', 1, null);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`) VALUES (3, 'lanit', 0, 2);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`) VALUES (4, 'naumen', 0, 2);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`) VALUES (5, 'google', 0, 2);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`) VALUES (6, 'amazon', 0, 2);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`) VALUES (7, 'microsoft', 0, 1);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`) VALUES (8, 'hackerrank', 0, 1);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`) VALUES (9, 'OtherOrg', 0, 1);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`) VALUES (10, 'Организация Орган Власти1', 1, null);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`) VALUES (11, 'Организация Заказчик1', 0, 10);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`) VALUES (12, 'Организация Заказчик2', 0, 10);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`) VALUES (13, 'Организация Заказчик3', 0, 10);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`) VALUES (20, 'Организация Орган Власти2', 1, null);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`) VALUES (21, 'Организация Заказчик4', 0, 20);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`) VALUES (22, 'Организация Заказчик5', 0, 20);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`) VALUES (23, 'Организация Заказчик6', 0, 20);

COMMIT;


-- -----------------------------------------------------
-- Data for table `lanit`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `lanit`;
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`) VALUES (1, 1, 'userName1', 'userName1');
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`) VALUES (2, 2, 'userName2', 'userName2');
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`) VALUES (3, 3, 'userName3', 'userName3');
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`) VALUES (4, 1, 'userName4', 'userName4');
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`) VALUES (5, 7, 'userName5', 'userName5');
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`) VALUES (6, 10, 'userName10', 'userName10');
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`) VALUES (7, 10, 'userName10', 'userName10');
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`) VALUES (8, 11, 'userName11', 'userName11');
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`) VALUES (9, 11, 'userName11', 'userName11');
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`) VALUES (10, 12, 'userName12', 'userName12');
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`) VALUES (11, 13, 'userName13', 'userName13');
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`) VALUES (12, 20, 'userName20', 'userName20');
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`) VALUES (13, 21, 'userName21', 'userName21');
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`) VALUES (14, 22, 'userName22', 'userName22');
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`) VALUES (15, 23, 'userName23', 'userName23');
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`) VALUES (16, 23, 'userName23', 'userName23');

COMMIT;


-- -----------------------------------------------------
-- Data for table `lanit`.`notifications`
-- -----------------------------------------------------
START TRANSACTION;
USE `lanit`;
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`, `date_response`, `letter_number`, `id_user_curator_gos`, `id_user_implementor`) VALUES (1, 7, 'Уведомление о непубликации сведений об оплате', 1, '2014-07-05', '2014-07-15', '0', 5, 1);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`, `date_response`, `letter_number`, `id_user_curator_gos`, `id_user_implementor`) VALUES (2, 11, 'Уведомление о незакрытии контракта', 2, '2015-07-05', '2015-07-05', '1', 8, 6);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`, `date_response`, `letter_number`, `id_user_curator_gos`, `id_user_implementor`) VALUES (3, 12, 'Уведомление о невключении поставщика в реестр недобросовестных поставщиков', 1, '2016-06-06', '2016-06-07', '2', 10, 6);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`, `date_response`, `letter_number`, `id_user_curator_gos`, `id_user_implementor`) VALUES (4, 11, 'Уведомление о заключении контракта с ед. поставщиком по п. 6 ч.1 ст.93', 1, '2014-07-05', '2014-07-06', '3', 9, 7);

COMMIT;


-- -----------------------------------------------------
-- Data for table `lanit`.`actions`
-- -----------------------------------------------------
START TRANSACTION;
USE `lanit`;
INSERT INTO `lanit`.`actions` (`id`, `id_notification`, `id_action_type`, `content`, `date`, `id_implementor`, `id_notification_status`) VALUES (1, 1, 1, 'content1', '2008-10-23 10:37:22', 4, 1);
INSERT INTO `lanit`.`actions` (`id`, `id_notification`, `id_action_type`, `content`, `date`, `id_implementor`, `id_notification_status`) VALUES (2, 2, 2, 'Куратор ГРБС: Захарова Александра Владимировна', '2012-01-01 01:01:01', 8, 1);
INSERT INTO `lanit`.`actions` (`id`, `id_notification`, `id_action_type`, `content`, `date`, `id_implementor`, `id_notification_status`) VALUES (3, 2, 1, 'Запрос в ГРБС: Прошу предоставить сведения о лимитах по статье бюджета. Сканированная копия ', '2012-01-01 02:01:01', 9, 1);

COMMIT;

