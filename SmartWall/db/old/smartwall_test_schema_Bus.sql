DROP TABLE IF EXISTS `Bus`;

CREATE TABLE `Bus` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into smartwall_test_schema.Bus values (1,"A1"),(2,"D1"),(3,"B1");