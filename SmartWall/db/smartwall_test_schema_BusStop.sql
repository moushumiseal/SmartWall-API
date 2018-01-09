DROP TABLE IF EXISTS `BusStop`;

CREATE TABLE `BusStop` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `bus` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into smartwall_test_schema.BusStop values (1,"Kent Ridge","A2"),(2,"UTown","D1"),(3,"Computer Centre","D1"),(4,"UTown","D1"),(5,"Computer Centre","D1");