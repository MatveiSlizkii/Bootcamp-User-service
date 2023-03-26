CREATE DATABASE  IF NOT EXISTS `user-service`
USE `user-service`;

CREATE TABLE `users` (
  `uuid` binary(16) NOT NULL,
  `name` varchar(20) NOT NULL,
  `surname` varchar(40) NOT NULL,
  `patronymic` varchar(40) NOT NULL,
  `email` varchar(45) NOT NULL,
  `role` enum('ADMINISTRATOR','SALE_USER','CUSTOMER_USER','SECURE_API_USER') NOT NULL,
  `dt_create` timestamp(3) NOT NULL,
  `dt_update` timestamp(3) NOT NULL,
  PRIMARY KEY (`uuid`))



