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
