CREATE TABLE `restaurant` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `stall` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `smartwall_test_schema`.`restaurant` (`id`, `name`, `stall`) VALUES ('5', 'Flavours', 'Western');