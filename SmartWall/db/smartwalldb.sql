CREATE SCHEMA `smartwall` ;

---------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS `smartwall`.`booking`;
DROP TABLE IF EXISTS `smartwall`.`room`;

CREATE TABLE `smartwall`.`room` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `smartwall`.`room` (`id`, `name`) VALUES ('1','3-01');
INSERT INTO `smartwall`.`room` (`id`, `name`) VALUES ('2','2-01');

CREATE TABLE `smartwall`.`booking` (
  `id` int(11) NOT NULL,
  `date` date,
  `startTime` varchar(45),
  `endTime` varchar(45),
  `room` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`room`) references `smartwall`.`room`(`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `smartwall`.`booking` (`id`,`date`,`startTime`,`endTime`,`room`) VALUES ('1','2018-01-17','2018-01-17 11:15:00','2018-01-17 13:15:00','1');
INSERT INTO `smartwall`.`booking` (`id`,`date`,`startTime`,`endTime`,`room`) VALUES ('2','2018-01-18','2018-01-18 11:15:00','2018-01-18 13:15:00','2');


---------------------------------------------------------------------------------------------------------------------------


DROP TABLE IF EXISTS `smartwall`.`restaurant`;

CREATE TABLE `smartwall`.`restaurant` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `stall` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `smartwall`.`restaurant` (`id`, `name`, `stall`) VALUES ('1', 'Flavours', 'Western');

---------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS `smartwall`.`bus`;

CREATE TABLE `smartwall`.`bus` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `smartwall`.`bus` values (1,"A1"),(2,"D1"),(3,"B1");

DROP TABLE IF EXISTS `smartwall`.`busstop`;

CREATE TABLE `smartwall`.`busstop` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `smartwall`.`busstop` values (1,"Kent Ridge"),(2,"UTown"),(3,"Library");

---------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS `smartwall`.`event`;

CREATE TABLE  `smartwall`.`event` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `date` date,
  `startTime` varchar(45),
  `endTime` varchar(45),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO  `smartwall`.`event` (`id`, `name`, `location`,`date`,`startTime`,`endTime`) VALUES ('1', 'Cryptocurrency', 'UTown','2018-01-17','2018-01-17 11:15:00','2018-01-17 13:15:00');



