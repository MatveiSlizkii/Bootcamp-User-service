


CREATE TABLE IF NOT EXISTS `user-service`.`users` (
  `uuid` BINARY(16) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `surname` VARCHAR(40) NOT NULL,
  `patronymic` VARCHAR(40) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `role` ENUM('ADMINISTRATOR', 'SALE_USER', 'CUSTOMER_USER', 'SECURE_API_USER') NOT NULL,
  `dt_create` TIMESTAMP(3) NOT NULL,
  `dt_update` TIMESTAMP(3) NOT NULL,
  PRIMARY KEY (`uuid`))
ENGINE = InnoDB;



