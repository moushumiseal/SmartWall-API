DROP TABLE IF EXISTS `Booking`;

CREATE TABLE `Booking` (
  `id` int(11) NOT NULL,
  `date` date,
  `startTime` varchar(45),
  `endTime` varchar(45),
  `room` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`room`) references `Room`(`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `smartwall_test_schema`.`Booking` (`id`,`date`,`startTime`,`endTime`,`room`) VALUES ('1','2018-01-17','2018-01-17 11:15:00','2018-01-17 13:15:00','3');
INSERT INTO `smartwall_test_schema`.`Booking` (`id`,`date`,`startTime`,`endTime`,`room`) VALUES ('1','2018-01-18','2018-01-18 11:15:00','2018-01-18 13:15:00','2');