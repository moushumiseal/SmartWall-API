DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `date` date,
  `startTime` varchar(45),
  `endTime` varchar(45),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `smartwall_test_schema`.`event` (`id`, `name`, `location`,`date`,`startTime`,`endTime`) VALUES ('5', 'Cryptocurrency', 'UTown','2018-01-17','2018-01-17 11:15:00','2018-01-17 13:15:00');