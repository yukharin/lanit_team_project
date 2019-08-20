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
-- -----------------------------------------------------
-- Schema new_schema1
-- -----------------------------------------------------
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
-- Table `lanit`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanit`.`users`
(
    `id`                    INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `id_org`                INT UNSIGNED NOT NULL,
    `first_name`            VARCHAR(45)  NOT NULL,
    `last_name`             VARCHAR(45)  NOT NULL,
    `username`              VARCHAR(45)  NOT NULL,
    `password`              VARCHAR(60)  NOT NULL,
    `registration_date`     DATE         NOT NULL,
    `enabled`               TINYINT      NOT NULL,
    `accountNonExpired`     TINYINT      NOT NULL,
    `accountNonLocked`      TINYINT      NOT NULL,
    `credentialsNonExpired` TINYINT      NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `id_org` (`id_org` ASC) VISIBLE,
    INDEX `last_name` (`last_name` ASC) INVISIBLE,
    UNIQUE INDEX `login_UNIQUE` (`username` ASC) VISIBLE,
    CONSTRAINT `id_org`
        FOREIGN KEY (`id_org`)
            REFERENCES `lanit`.`organizations` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
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
    `notification_status`         INT UNSIGNED NOT NULL,
    `date_received`               DATE         NOT NULL,
    `date_response`               DATE         NOT NULL,
    `letter_number`               VARCHAR(12)  NOT NULL,
    `id_user_notification_author` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `responsible_implementor_idx` (`id_user_notification_author` ASC) VISIBLE,
    INDEX `date_received` (`date_received` ASC) VISIBLE,
    INDEX `date_response` (`date_response` ASC) VISIBLE,
    CONSTRAINT `client`
        FOREIGN KEY (`id_org`)
            REFERENCES `lanit`.`organizations` (`id`)
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
-- Table `lanit`.`actions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanit`.`actions`
(
    `id`                  INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `id_notification`     INT UNSIGNED NOT NULL,
    `action_type`         INT UNSIGNED NOT NULL,
    `content`             VARCHAR(300) NULL,
    `date`                DATETIME     NOT NULL,
    `id_implementor`      INT UNSIGNED NOT NULL,
    `notification_status` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`, `date`),
    INDEX `id_implementor_idx` (`id_implementor` ASC) VISIBLE,
    INDEX `id_notification_idx` (`id_notification` ASC) VISIBLE,
    INDEX `idx_datetime` (`date` ASC) VISIBLE,
    CONSTRAINT `id_implementor`
        FOREIGN KEY (`id_implementor`)
            REFERENCES `lanit`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `id_notification`
        FOREIGN KEY (`id_notification`)
            REFERENCES `lanit`.`notifications` (`id`)
            ON DELETE CASCADE
            ON UPDATE RESTRICT
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanit`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanit`.`roles`
(
    `id`   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `role` VARCHAR(50)  NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `role_UNIQUE` (`role` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanit`.`users_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanit`.`users_roles`
(
    `id_user` INT UNSIGNED NOT NULL,
    `id_role` INT UNSIGNED NOT NULL,
    INDEX `id_user_idx` (`id_user` ASC) VISIBLE,
    INDEX `id_role_idx` (`id_role` ASC) VISIBLE,
    CONSTRAINT `id_user`
        FOREIGN KEY (`id_user`)
            REFERENCES `lanit`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `id_role`
        FOREIGN KEY (`id_role`)
            REFERENCES `lanit`.`roles` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

USE `lanit`;

DELIMITER $$
USE `lanit`$$
CREATE DEFINER = CURRENT_USER TRIGGER `lanit`.`notifications_AFTER_INSERT`
    AFTER INSERT
    ON `notifications`
    FOR EACH ROW
BEGIN
    INSERT INTO actions (id_notification, action_type, content, date, id_implementor, notification_status)
    values (new.id, 3, 'Уведомление создано', now(), new.id_user_notification_author, 0);
END$$


DELIMITER ;

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
-- Data for table `lanit`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `lanit`;
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`, `username`, `password`, `registration_date`,
                             `enabled`, `accountNonExpired`, `accountNonLocked`, `credentialsNonExpired`)
VALUES (1, 1, 'Vlad', 'Yukharin', 'yukharin', '$2a$10$zXPjDMCKkU16WS0bD1ZuCOgGxE3.GjL0rYswij5KLwHx7J7bkFXdS',
        '2015-07-12', 1, 1, 1, 1);
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`, `username`, `password`, `registration_date`,
                             `enabled`, `accountNonExpired`, `accountNonLocked`, `credentialsNonExpired`)
VALUES (2, 2, 'Slava', 'Satonin', 'satonin', '$2a$10$zXPjDMCKkU16WS0bD1ZuCOgGxE3.GjL0rYswij5KLwHx7J7bkFXdS',
        '2016-06-21', 1, 1, 1, 1);
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`, `username`, `password`, `registration_date`,
                             `enabled`, `accountNonExpired`, `accountNonLocked`, `credentialsNonExpired`)
VALUES (3, 3, 'Коля', 'Иванов', 'login1', '$2a$10$zXPjDMCKkU16WS0bD1ZuCOgGxE3.GjL0rYswij5KLwHx7J7bkFXdS', '2017-03-15',
        1, 1, 1, 1);
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`, `username`, `password`, `registration_date`,
                             `enabled`, `accountNonExpired`, `accountNonLocked`, `credentialsNonExpired`)
VALUES (4, 3, 'Вася ', 'Пупкин', 'login2', '$2a$10$zXPjDMCKkU16WS0bD1ZuCOgGxE3.GjL0rYswij5KLwHx7J7bkFXdS', '2017-04-29',
        1, 1, 1, 1);
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`, `username`, `password`, `registration_date`,
                             `enabled`, `accountNonExpired`, `accountNonLocked`, `credentialsNonExpired`)
VALUES (5, 10, 'Артем', 'Гринев', 'login3', '$2a$10$zXPjDMCKkU16WS0bD1ZuCOgGxE3.GjL0rYswij5KLwHx7J7bkFXdS',
        '2017-05-05', 1, 1, 1, 1);
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`, `username`, `password`, `registration_date`,
                             `enabled`, `accountNonExpired`, `accountNonLocked`, `credentialsNonExpired`)
VALUES (6, 10, 'Паша', 'Гриневич', 'login4', '$2a$10$zXPjDMCKkU16WS0bD1ZuCOgGxE3.GjL0rYswij5KLwHx7J7bkFXdS',
        '2017-05-12', 1, 1, 1, 1);
INSERT INTO `lanit`.`users` (`id`, `id_org`, `first_name`, `last_name`, `username`, `password`, `registration_date`,
                             `enabled`, `accountNonExpired`, `accountNonLocked`, `credentialsNonExpired`)
VALUES (7, 10, 'Саша', 'Вербицкий', 'login5', '$2a$10$zXPjDMCKkU16WS0bD1ZuCOgGxE3.GjL0rYswij5KLwHx7J7bkFXdS',
        '2017-05-21', 1, 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `lanit`.`notifications`
-- -----------------------------------------------------
START TRANSACTION;
USE `lanit`;
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (1, 1, 'Уведомление о получении денег', 0, '2018-07-21', '2019-09-11', '11-12-1593/2', 1);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (2, 1, 'Уведомление о получении кредита', 0, '2018-07-24', '2019-09-14', '16-65-1863/5', 1);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (3, 1, 'Уведомление о завершении тендера', 0, '2018-07-27', '2019-09-21', '85-60-9482/9', 1);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (4, 1, 'Уведомление о премии', 0, '2018-07-29', '2019-09-03', '96-49-1836/5', 1);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (5, 1, 'Уведомление об увольнении с работы', 0, '2018-08-01', '2019-09-07', '38-97-9738/3', 1);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (6, 1, 'Уведомление о выигрыше тендера', 0, '2018-08-04', '2019-09-11', '28-75-2374/2', 1);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (7, 1, 'Уведомление о закрытии счета', 0, '2018-08-06', '2019-09-14', '11-42-5426/4', 1);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (8, 1, 'Уведомление о завершении работы', 0, '2018-08-09', '2019-09-17', '25-96-2643/1', 1);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (9, 1, 'Уведомление о перечислении денег', 0, '2018-08-14', '2019-10-21', '79-75-9848/9', 1);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (10, 1, 'Уведомление о получении премии', 0, '2018-08-19', '2019-10-24', '23-89-1378/5', 1);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (11, 1, 'Уведомление о начислении бонусов', 0, '2018-08-23', '2019-10-27', '32-43-2368/3', 1);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (12, 1, 'Уведомление о начислении средств', 0, '2018-08-27', '2019-10-02', '87-54-0958/3', 1);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (13, 1, 'Уведомление о получении прибыли', 0, '2018-09-03', '2019-10-07', '19-65-9854/1', 1);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (14, 1, 'Уведомление о получении повышения', 0, '2018-09-12', '2019-10-15', '89-96-9847/2', 1);
INSERT INTO `lanit`.`notifications` (`id`, `id_org`, `notification_type`, `notification_status`, `date_received`,
                                     `date_response`, `letter_number`, `id_user_notification_author`)
VALUES (15, 1, 'Уведомление о выигрыше конкурса', 0, '2018-09-15', '2019-10-17', '78-39-3479/8', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `lanit`.`roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `lanit`;
INSERT INTO `lanit`.`roles` (`id`, `role`)
VALUES (1, 'EMPLOYEE');
INSERT INTO `lanit`.`roles` (`id`, `role`)
VALUES (2, 'AUTHORITY');
INSERT INTO `lanit`.`roles` (`id`, `role`)
VALUES (3, 'ADMIN');

COMMIT;


-- -----------------------------------------------------
-- Data for table `lanit`.`users_roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `lanit`;
INSERT INTO `lanit`.`users_roles` (`id_user`, `id_role`)
VALUES (1, 2);
INSERT INTO `lanit`.`users_roles` (`id_user`, `id_role`)
VALUES (2, 2);
INSERT INTO `lanit`.`users_roles` (`id_user`, `id_role`)
VALUES (3, 1);
INSERT INTO `lanit`.`users_roles` (`id_user`, `id_role`)
VALUES (4, 1);
INSERT INTO `lanit`.`users_roles` (`id_user`, `id_role`)
VALUES (5, 1);
INSERT INTO `lanit`.`users_roles` (`id_user`, `id_role`)
VALUES (6, 1);
INSERT INTO `lanit`.`users_roles` (`id_user`, `id_role`)
VALUES (7, 1);
INSERT INTO `lanit`.`users_roles` (`id_user`, `id_role`)
VALUES (1, 3);
INSERT INTO `lanit`.`users_roles` (`id_user`, `id_role`)
VALUES (1, 1);

COMMIT;

