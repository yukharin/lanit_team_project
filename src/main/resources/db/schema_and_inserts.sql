-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema lanit
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lanit
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lanit` DEFAULT CHARACTER SET utf8;
USE `lanit`;

-- -----------------------------------------------------
-- Table `lanit`.`organizations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanit`.`organizations`
(
    `id`         INT UNSIGNED        NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(200)        NOT NULL,
    `org_type`   TINYINT(1) UNSIGNED NOT NULL,
    `id_gos_org` INT UNSIGNED        NULL,
    PRIMARY KEY (`id`),
    INDEX `id_gos_org_idx` (`id_gos_org` ASC) VISIBLE,
    INDEX `name` (`name` ASC) VISIBLE,
    CONSTRAINT `id_gos_org`
        FOREIGN KEY (`id_gos_org`)
            REFERENCES `lanit`.`organizations` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanit`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanit`.`roles`
(
    `id`   INT UNSIGNED                     NOT NULL AUTO_INCREMENT,
    `role` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `rolescol_UNIQUE` (`role` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanit`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanit`.`users`
(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `id_org`            INT UNSIGNED NOT NULL,
    `first_name`        VARCHAR(45)  NOT NULL,
    `last_name`         VARCHAR(45)  NOT NULL,
    `login`             VARCHAR(45)  NOT NULL,
    `password`          VARCHAR(45)  NOT NULL,
    `registration_date` DATE         NOT NULL,
    `id_role`           INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `id_org` (`id_org` ASC) VISIBLE,
    INDEX `last_name` (`last_name` ASC) INVISIBLE,
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
    INDEX `id_role_idx` (`id_role` ASC) VISIBLE,
    CONSTRAINT `id_org`
        FOREIGN KEY (`id_org`)
            REFERENCES `lanit`.`organizations` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `id_role`
        FOREIGN KEY (`id_role`)
            REFERENCES `lanit`.`roles` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanit`.`notification_statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanit`.`notification_statuses`
(
    `id`   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45)  NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `name` (`name` ASC) VISIBLE,
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanit`.`notifications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanit`.`notifications`
(
    `id`                          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `id_org`                      INT UNSIGNED NOT NULL,
    `notification_type`           VARCHAR(150) NULL,
    `id_notification_status`      INT UNSIGNED NOT NULL,
    `date_received`               DATE         NOT NULL,
    `date_response`               DATE         NOT NULL,
    `letter_number`               VARCHAR(12)  NULL,
    `id_user_notification_author` INT UNSIGNED NULL,
    PRIMARY KEY (`id`),
    INDEX `notification_status_idx` (`id_notification_status` ASC) VISIBLE,
    INDEX `responsible_implementor_idx` (`id_user_notification_author` ASC) VISIBLE,
    INDEX `date_received` (`date_received` ASC) VISIBLE,
    INDEX `date_response` (`date_response` ASC) VISIBLE,
    CONSTRAINT `client`
        FOREIGN KEY (`id_org`)
            REFERENCES `lanit`.`organizations` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `notification_status`
        FOREIGN KEY (`id_notification_status`)
            REFERENCES `lanit`.`notification_statuses` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `id_user_notification_creator`
        FOREIGN KEY (`id_user_notification_author`)
            REFERENCES `lanit`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanit`.`action_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanit`.`action_types`
(
    `id`   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45)  NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `idx_name` USING BTREE (`name`) VISIBLE,
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanit`.`actions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanit`.`actions`
(
    `id`                     INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `id_notification`        INT UNSIGNED NOT NULL,
    `id_action_type`         INT UNSIGNED NOT NULL,
    `content`                VARCHAR(300) NULL,
    `date`                   DATETIME     NOT NULL,
    `id_implementor`         INT UNSIGNED NOT NULL,
    `id_notification_status` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`, `date`),
    INDEX `id_implementor_idx` (`id_implementor` ASC) VISIBLE,
    INDEX `id_notification_status_after_processing_idx` (`id_notification_status` ASC) VISIBLE,
    INDEX `id_notification_idx` (`id_notification` ASC) VISIBLE,
    INDEX `idx_datetime` (`date` ASC) VISIBLE,
    INDEX `id_action_type_idx` (`id_action_type` ASC) VISIBLE,
    CONSTRAINT `id_implementor`
        FOREIGN KEY (`id_implementor`)
            REFERENCES `lanit`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `id_notification_status_after_processing`
        FOREIGN KEY (`id_notification_status`)
            REFERENCES `lanit`.`notification_statuses` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `id_notification`
        FOREIGN KEY (`id_notification`)
            REFERENCES `lanit`.`notifications` (`id`)
            ON DELETE CASCADE
            ON UPDATE RESTRICT,
    CONSTRAINT `id_action_type`
        FOREIGN KEY (`id_action_type`)
            REFERENCES `lanit`.`action_types` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `lanit`.`organizations`
-- -----------------------------------------------------
START TRANSACTION;
USE `lanit`;
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`)
VALUES (1, 'Министерство здравоохранения', 1, NULL);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`)
VALUES (2, 'Министерство строительства', 1, NULL);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`)
VALUES (3, 'Министерство образования', 1, NULL);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`)
VALUES (4, 'Больница 1', 0, 1);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`)
VALUES (5, 'Больница 2', 0, 1);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`)
VALUES (6, 'Больница 3', 0, 1);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`)
VALUES (7, 'Строительная компания 1', 0, 2);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`)
VALUES (8, 'Строительная компания 2', 0, 2);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`)
VALUES (9, 'Строительная компания 3', 0, 2);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`)
VALUES (10, 'Школа 1', 0, 3);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`)
VALUES (11, 'Школа 2', 0, 3);
INSERT INTO `lanit`.`organizations` (`id`, `name`, `org_type`, `id_gos_org`)
VALUES (12, 'Школа 3', 0, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `lanit`.`roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `lanit`;
INSERT INTO `lanit`.`roles` (`id`, `role`)
VALUES (1, 'Чиновник');
INSERT INTO `lanit`.`roles` (`id`, `role`)
VALUES (2, 'Работник огранизации');

COMMIT;


-- -----------------------------------------------------
-- Data for table `lanit`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `lanit`;
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`, `login`, `password`, `registration_date`,
                             `id_role`)
VALUES (1, 1, 'Vlad', 'Yukharin', 'yukharin', 'password7788', '2015-07-12', 1);
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`, `login`, `password`, `registration_date`,
                             `id_role`)
VALUES (2, 2, 'Slava', 'Satonin', 'satonin', 'password7788', '2016-06-21', 1);
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`, `login`, `password`, `registration_date`,
                             `id_role`)
VALUES (3, 3, 'Коля', 'Иванов', 'login1', 'password7788', '2017-03-15', 2);
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`, `login`, `password`, `registration_date`,
                             `id_role`)
VALUES (4, 3, 'Вася ', 'Пупкин', 'login2', 'password7788', '2017-04-29', 2);
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`, `login`, `password`, `registration_date`,
                             `id_role`)
VALUES (5, 10, 'Артем', 'Гринев', 'login3', 'password7788', '2017-05-05', 2);
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`, `login`, `password`, `registration_date`,
                             `id_role`)
VALUES (6, 10, 'Паша', 'Гриневич', 'login4', 'password7788', '2017-05-12', 2);
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`, `login`, `password`, `registration_date`,
                             `id_role`)
VALUES (7, 10, 'Саша', 'Вербицкий', 'login5', 'password7788', '2017-05-21', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `lanit`.`notification_statuses`
-- -----------------------------------------------------
START TRANSACTION;
USE `lanit`;
INSERT INTO `lanit`.`notification_statuses` (`id`, `name`)
VALUES (1, 'Новое');
INSERT INTO `lanit`.`notification_statuses` (`id`, `name`)
VALUES (2, 'Отправлено_в_обработку');
INSERT INTO `lanit`.`notification_statuses` (`id`, `name`)
VALUES (3, 'Обработано');

COMMIT;


-- -----------------------------------------------------
-- Data for table `lanit`.`notifications`
-- -----------------------------------------------------
START TRANSACTION;
USE `lanit`;
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (1, 1, 'Уведомление о получении денег', 1, '2019-07-21', '2019-08-11', NULL, NULL);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (2, 1, 'Уведомление о получении кредита', 1, '2019-07-24', '2019-08-14', NULL, NULL);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (3, 1, 'Уведомление о завершении тендера', 1, '2019-07-27', '2019-08-21', NULL, NULL);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (4, 1, 'Уведомление о премии', 1, '2019-07-29', '2019-08-03', NULL, NULL);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (5, 1, 'Уведомление об увольнении с работы', 1, '2019-08-01', '2019-08-07', NULL, NULL);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (6, 1, 'Уведомление о выигрыше тендера', 1, '2019-08-04', '2019-08-11', NULL, NULL);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (7, 1, 'Уведомление о закрытии счета', 1, '2019-08-06', '2019-08-14', NULL, NULL);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (8, 1, 'Уведомление о завершении работы', 1, '2019-08-09', '2019-08-17', NULL, NULL);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (9, 1, 'Уведомление о перечислении денег', 1, '2019-08-14', '2019-08-21', NULL, NULL);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (10, 1, 'Уведомление о получении премии', 1, '2019-08-19', '2019-08-24', NULL, NULL);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (11, 1, 'Уведомление о начислении бонусов', 1, '2019-08-23', '2019-08-27', NULL, NULL);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (12, 1, 'Уведомление о начислении средств', 1, '2019-08-27', '2019-09-02', NULL, NULL);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (13, 1, 'Уведомление о получении прибыли', 1, '2019-09-03', '2019-09-07', NULL, NULL);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (14, 1, 'Уведомление о получении повышения', 1, '2019-09-12', '2019-09-15', NULL, NULL);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `id_notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (15, 1, 'Уведомление о выигрыше конкурса', 1, '2019-09-15', '2019-09-17', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `lanit`.`action_types`
-- -----------------------------------------------------
START TRANSACTION;
USE `lanit`;
INSERT INTO `lanit`.`action_types` (`id`, `name`)
VALUES (1, 'Отправить_в_обработку');
INSERT INTO `lanit`.`action_types` (`id`, `name`)
VALUES (2, 'Обработать');

COMMIT;


-- -----------------------------------------------------
-- Data for table `lanit`.`actions`
-- -----------------------------------------------------
START TRANSACTION;
USE `lanit`;
INSERT INTO `lanit`.`actions` (`id`, `id_notification`, `id_action_type`, `content`, `date`, `id_implementor`,
                               `id_notification_status`)
VALUES (1, 1, 1, 'тест', '2019-07-09', 1, 1);

COMMIT;

