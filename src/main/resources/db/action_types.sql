-- -----------------------------------------------------
-- Data for table `lanit`.`action_types`
-- -----------------------------------------------------
START TRANSACTION;
USE `lanit`;
INSERT INTO `lanit`.`action_types` (`id`, `name`)
VALUES (1, 'Отправить в обработку');
INSERT INTO `lanit`.`action_types` (`id`, `name`)
VALUES (2, 'Обработать');

COMMIT;
