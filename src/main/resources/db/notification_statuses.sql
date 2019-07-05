-- -----------------------------------------------------
-- Data for table `lanit`.`notification_statuses`
-- -----------------------------------------------------
START TRANSACTION;
USE `lanit`;
INSERT INTO `lanit`.`notification_statuses` (`id`, `name`)
VALUES (1, 'Новое');
INSERT INTO `lanit`.`notification_statuses` (`id`, `name`)
VALUES (2, 'Отправлено в обработку');
INSERT INTO `lanit`.`notification_statuses` (`id`, `name`)
VALUES (3, 'Обработано');
COMMIT;
