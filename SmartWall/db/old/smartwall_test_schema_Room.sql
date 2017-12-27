DROP TABLE IF EXISTS `Room`;

CREATE TABLE `Room` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `smartwall_test_schema`.`Room` (`id`, `name`) VALUES ('3','3-01');
INSERT INTO `smartwall_test_schema`.`Room` (`id`, `name`) VALUES ('2','2-01');