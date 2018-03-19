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
  `time` time(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `smartwall`.`bus` VALUES (1,'A2','07:00:00'),(2,'A2','07:15:00'),(3,'A2','07:30:00'),(4,'A2','07:45:00'),(5,'A2','08:00:00'),(6,'A2','08:15:00'),(7,'A2','08:30:00'),(8,'A2','08:45:00'),(9,'A2','09:00:00'),(10,'A2','09:15:00'),(11,'A2','09:30:00'),(12,'A2','09:45:00'),(13,'A2','10:00:00'),(14,'A2','10:15:00'),(15,'A2','10:30:00'),(16,'A2','10:45:00'),(17,'A2','11:00:00'),(18,'A2','11:15:00'),(19,'A2','11:30:00'),(20,'A2','11:45:00'),(21,'A2','12:00:00'),(22,'A2','12:15:00'),(23,'A2','12:30:00'),(24,'A2','12:45:00'),(25,'A2','13:00:00'),(26,'A2','13:15:00'),(27,'A2','13:30:00'),(28,'A2','13:45:00'),(29,'A2','14:00:00'),(30,'A2','14:15:00'),(31,'A2','14:30:00'),(32,'A2','14:45:00'),(33,'A2','15:00:00'),(34,'A2','15:15:00'),(35,'A2','15:30:00'),(36,'A2','15:45:00'),(37,'A2','16:00:00'),(38,'A2','16:15:00'),(39,'A2','16:30:00'),(40,'A2','16:45:00'),(41,'A2','17:00:00'),(42,'A2','17:15:00'),(43,'A2','17:30:00'),(44,'A2','17:45:00'),(45,'A2','18:00:00'),(46,'A2','18:15:00'),(47,'A2','18:30:00'),(48,'A2','18:45:00'),(49,'A2','19:00:00'),(50,'A2','19:15:00'),(51,'A2','19:30:00'),(52,'A2','19:45:00'),(53,'A2','20:00:00'),(54,'A2','20:15:00'),(55,'A2','20:30:00'),(56,'A2','20:45:00'),(57,'A2','21:00:00'),(58,'D1','09:00:00'),(59,'D1','09:15:00'),(60,'D1','09:30:00'),(61,'D1','09:45:00'),(62,'D1','10:00:00'),(63,'D1','10:15:00'),(64,'D1','10:30:00'),(65,'D1','10:45:00'),(66,'D1','11:00:00'),(67,'D1','11:15:00'),(68,'D1','11:30:00'),(69,'D1','11:45:00'),(70,'D1','12:00:00'),(71,'D1','12:15:00'),(72,'D1','12:30:00'),(73,'D1','12:45:00'),(74,'D1','13:00:00'),(75,'D1','13:15:00'),(76,'D1','13:30:00'),(77,'D1','13:45:00'),(78,'D1','14:00:00'),(79,'D1','14:15:00'),(80,'D1','14:30:00'),(81,'D1','14:45:00'),(82,'D1','15:00:00'),(83,'D1','15:15:00'),(84,'D1','15:30:00'),(85,'D1','15:45:00'),(86,'D1','16:00:00'),(87,'D1','16:15:00'),(88,'D1','16:30:00'),(89,'D1','16:45:00'),(90,'D1','17:00:00'),(91,'D1','17:15:00'),(92,'D1','17:30:00'),(93,'D1','17:45:00'),(94,'D1','18:00:00'),(95,'D1','18:15:00'),(96,'D1','18:30:00'),(97,'D1','18:45:00'),(98,'D1','19:00:00'),(99,'D1','19:15:00'),(100,'D1','19:30:00'),(101,'D1','19:45:00'),(102,'D1','20:00:00'),(103,'D1','20:15:00'),(104,'D1','20:30:00'),(105,'D1','20:45:00'),(106,'D1','21:00:00'),(107,'B1','09:00:00'),(108,'B1','09:15:00'),(109,'B1','09:30:00'),(110,'B1','09:45:00'),(111,'B1','10:00:00'),(112,'B1','10:15:00'),(113,'B1','10:30:00'),(114,'B1','10:45:00'),(115,'B1','11:00:00'),(116,'B1','11:15:00'),(117,'B1','11:30:00'),(118,'B1','11:45:00'),(119,'B1','12:00:00'),(120,'B1','12:15:00'),(121,'B1','12:30:00'),(122,'B1','12:45:00'),(123,'B1','13:00:00'),(124,'B1','13:15:00'),(125,'B1','13:30:00'),(126,'B1','13:45:00'),(127,'B1','14:00:00'),(128,'B1','14:15:00'),(129,'B1','14:30:00'),(130,'B1','14:45:00'),(131,'B1','15:00:00'),(132,'B1','15:15:00'),(133,'B1','15:30:00'),(134,'B1','15:45:00'),(135,'B1','16:00:00'),(136,'B1','16:15:00'),(137,'B1','16:30:00'),(138,'B1','16:45:00'),(139,'B1','17:00:00'),(140,'B1','17:15:00'),(141,'B1','17:30:00'),(142,'B1','17:45:00'),(143,'B1','18:00:00'),(144,'B1','18:15:00'),(145,'B1','18:30:00'),(146,'B1','18:45:00'),(147,'B1','19:00:00'),(148,'B1','19:15:00'),(149,'B1','19:30:00'),(150,'B1','19:45:00'),(151,'B1','20:00:00'),(152,'B1','20:15:00'),(153,'B1','20:30:00'),(154,'B1','20:45:00'),(155,'B1','21:00:00'),(156,'B1','07:00:00'),(157,'B1','07:15:00'),(158,'B1','07:30:00'),(159,'B1','07:45:00'),(160,'B1','08:00:00'),(161,'B1','08:15:00'),(162,'B1','08:30:00'),(163,'B1','08:45:00'),(164,'D1','08:00:00'),(165,'D1','08:15:00'),(166,'D1','08:30:00'),(167,'D1','08:45:00'),(168,'D1','07:00:00'),(169,'D1','07:15:00'),(170,'D1','07:30:00'),(171,'D1','07:45:00');

DROP TABLE IF EXISTS `smartwall`.`busstop`;

CREATE TABLE `smartwall`.`busstop` (
   `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `bus` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `smartwall`.`busstop` VALUES (1,'Kent Ridge','A2'),(2,'UTown','D1'),(3,'UTown','B1'),(4,'Computer Centre','D1'),(5,'Computer Centre','B1'),(6,'Computer Centre','A2');


DROP TABLE IF EXISTS `smartwall`.`busstopmapping`;

CREATE TABLE  `smartwall`.`busstopmapping` (

`id` int(11) NOT NULL,
  
`Bus` int not null,
  
`BusStop` int not null,
  
PRIMARY KEY (`id`),
  
foreign key(`Bus`) references `smartwall`.`bus`(`id`),
  
foreign key(`BusStop`) references `smartwall`.`busstop`(`id`),
  
UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `smartwall`.`busstopmapping` values (1,1,1),(2,1,3),(3,3,2),(4,3,3),(5,2,2),(6,2,3);



DROP TABLE IF EXISTS `smartwall`.`bustiming`;

CREATE TABLE `smartwall`.`bustiming` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bus` int(11) DEFAULT NULL,
  `time` time(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bus_idx` (`bus`),
  CONSTRAINT `bus` FOREIGN KEY (`bus`) REFERENCES `smartwall`.`bus` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8;


INSERT INTO `smartwall`.`bustiming` VALUES (1,1,'07:00:00.000000'),(2,1,'07:15:00.000000'),(3,1,'07:30:00.000000'),(4,1,'07:45:00.000000'),(5,1,'08:00:00.000000'),(6,1,'08:15:00.000000'),(7,1,'08:30:00.000000'),(8,1,'08:45:00.000000'),(9,1,'09:00:00.000000'),(10,1,'09:15:00.000000'),(11,1,'09:30:00.000000'),(12,1,'09:45:00.000000'),(13,1,'10:00:00.000000'),(14,1,'10:15:00.000000'),(15,1,'10:30:00.000000'),(16,1,'10:45:00.000000'),(17,1,'11:00:00.000000'),(18,1,'11:15:00.000000'),(19,1,'11:30:00.000000'),(20,1,'11:45:00.000000'),(21,1,'12:00:00.000000'),(22,1,'12:15:00.000000'),(23,1,'12:30:00.000000'),(24,1,'12:45:00.000000'),(25,1,'13:00:00.000000'),(26,1,'13:15:00.000000'),(27,1,'13:30:00.000000'),(28,1,'13:45:00.000000'),(29,1,'14:00:00.000000'),(30,1,'14:15:00.000000'),(31,1,'14:30:00.000000'),(32,1,'14:45:00.000000'),(33,1,'15:00:00.000000'),(34,1,'15:15:00.000000'),(35,1,'15:30:00.000000'),(36,1,'15:45:00.000000'),(37,1,'16:00:00.000000'),(38,1,'16:15:00.000000'),(39,1,'16:30:00.000000'),(40,1,'16:45:00.000000'),(41,1,'17:00:00.000000'),(42,1,'17:15:00.000000'),(43,1,'17:30:00.000000'),(44,1,'17:45:00.000000'),(45,1,'18:00:00.000000'),(46,1,'18:15:00.000000'),(47,1,'18:30:00.000000'),(48,1,'18:45:00.000000'),(49,1,'19:00:00.000000'),(50,1,'19:15:00.000000'),(51,1,'19:30:00.000000'),(52,1,'19:45:00.000000'),(53,1,'20:00:00.000000'),(54,1,'20:15:00.000000'),(55,1,'20:30:00.000000'),(56,1,'20:45:00.000000'),(57,1,'21:00:00.000000'),(58,2,'07:00:00.000000'),(59,2,'07:15:00.000000'),(60,2,'07:30:00.000000'),(61,2,'07:45:00.000000'),(62,2,'08:00:00.000000'),(63,2,'08:15:00.000000'),(64,2,'08:30:00.000000'),(65,2,'08:45:00.000000'),(66,2,'09:00:00.000000'),(67,2,'09:15:00.000000'),(68,2,'09:30:00.000000'),(69,2,'09:45:00.000000'),(70,2,'10:00:00.000000'),(71,2,'10:15:00.000000'),(72,2,'10:30:00.000000'),(73,2,'10:45:00.000000'),(74,2,'11:00:00.000000'),(75,2,'11:15:00.000000'),(76,2,'11:30:00.000000'),(77,2,'11:45:00.000000'),(78,2,'12:00:00.000000'),(79,2,'12:15:00.000000'),(80,2,'12:30:00.000000'),(81,2,'12:45:00.000000'),(82,2,'13:00:00.000000'),(83,2,'13:15:00.000000'),(84,2,'13:30:00.000000'),(85,2,'13:45:00.000000'),(86,2,'14:00:00.000000'),(87,2,'14:15:00.000000'),(88,2,'14:30:00.000000'),(89,2,'14:45:00.000000'),(90,2,'15:00:00.000000'),(91,2,'15:15:00.000000'),(92,2,'15:30:00.000000'),(93,2,'15:45:00.000000'),(94,2,'16:00:00.000000'),(95,2,'16:15:00.000000'),(96,2,'16:30:00.000000'),(97,2,'16:45:00.000000'),(98,2,'17:00:00.000000'),(99,2,'17:15:00.000000'),(100,2,'17:30:00.000000'),(101,2,'17:45:00.000000'),(102,2,'18:00:00.000000'),(103,2,'18:15:00.000000'),(104,2,'18:30:00.000000'),(105,2,'18:45:00.000000'),(106,2,'19:00:00.000000'),(107,2,'19:15:00.000000'),(108,2,'19:30:00.000000'),(109,2,'19:45:00.000000'),(110,2,'20:00:00.000000'),(111,2,'20:15:00.000000'),(112,2,'20:30:00.000000'),(113,2,'20:45:00.000000'),(114,2,'21:00:00.000000'),(115,3,'07:00:00.000000'),(116,3,'07:15:00.000000'),(117,3,'07:30:00.000000'),(118,3,'07:45:00.000000'),(119,3,'08:00:00.000000'),(120,3,'08:15:00.000000'),(121,3,'08:30:00.000000'),(122,3,'08:45:00.000000'),(123,3,'09:00:00.000000'),(124,3,'09:15:00.000000'),(125,3,'09:30:00.000000'),(126,3,'09:45:00.000000'),(127,3,'10:00:00.000000'),(128,3,'10:15:00.000000'),(129,3,'10:30:00.000000'),(130,3,'10:45:00.000000'),(131,3,'11:00:00.000000'),(132,3,'11:15:00.000000'),(133,3,'11:30:00.000000'),(134,3,'11:45:00.000000'),(135,3,'12:00:00.000000'),(136,3,'12:15:00.000000'),(137,3,'12:30:00.000000'),(138,3,'12:45:00.000000'),(139,3,'13:00:00.000000'),(140,3,'13:15:00.000000'),(141,3,'13:30:00.000000'),(142,3,'13:45:00.000000'),(143,3,'14:00:00.000000'),(144,3,'14:15:00.000000'),(145,3,'14:30:00.000000'),(146,3,'14:45:00.000000'),(147,3,'15:00:00.000000'),(148,3,'15:15:00.000000'),(149,3,'15:30:00.000000'),(150,3,'15:45:00.000000'),(151,3,'16:00:00.000000'),(152,3,'16:15:00.000000'),(153,3,'16:30:00.000000'),(154,3,'16:45:00.000000'),(155,3,'17:00:00.000000'),(156,3,'17:15:00.000000'),(157,3,'17:30:00.000000'),(158,3,'17:45:00.000000'),(159,3,'18:00:00.000000'),(160,3,'18:15:00.000000'),(161,3,'18:30:00.000000'),(162,3,'18:45:00.000000'),(163,3,'19:00:00.000000'),(164,3,'19:15:00.000000'),(165,3,'19:30:00.000000'),(166,3,'19:45:00.000000'),(167,3,'20:00:00.000000'),(168,3,'20:15:00.000000'),(169,3,'20:30:00.000000'),(170,3,'20:45:00.000000'),(171,3,'21:00:00.000000');


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



