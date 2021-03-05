CREATE DATABASE  IF NOT EXISTS `comp_1640_demo`;
USE `comp_1640_demo`;

DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `translation`;
CREATE TABLE `translation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `key` varchar(100) NOT NULL,
  `value` varchar(200) NOT NULL,
  `locale` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Translation_id_uindex` (`id`)
);

LOCK TABLES `translation` WRITE;
INSERT INTO `translation` VALUES (1,'error.00','Thành công','vi'),(2,'error.00','Success','en'),(3,'error.0001','Lỗi nghiệp vụ','vi'),(4,'error.0001','Business error','en'),(5,'error.0002','Lỗi xác thực đầu vào','vi'),(6,'error.0002','Validation error','en'),(7,'error.0003','Lỗi máy chủ','vi'),(8,'error.0003','Server error','en'),(9,'error.0004','Lỗi trùng lặp','vi'),(10,'error.0004','Duplicate error','en'),(11,'error.0005','Lỗi không tìm thấy dữ liệu','vi'),(12,'error.0005','Data not found error','en'),(13,'error.0006','Lỗi xác thực người dùng','vi'),(14,'error.0006','Unauthorized error','en'),(15,'error.0007','Lỗi thiếu mã người dùng','vi'),(16,'error.0007','Missing client code error','en'),(17,'error.0008','Lỗi thiếu chữ kí dữ liệu ','vi'),(18,'error.0008','Missing data signature error','en'),(19,'error.0009','Lỗi thiếu ngày yêu cầu','vi'),(20,'error.0009','Missing request date error','en'),(21,'error.0010','Lỗi mã vai trò người dùng không hợp lệ','vi'),(22,'error.0010','Invalid role id error','en'),(23,'error.0011','Lỗi thiếu khóa công khai','vi'),(24,'error.0011','Missing public key error','en'),(25,'error.0012','Lỗi chữ kí không hợp lệ','vi'),(26,'error.0012','Invalid signature error','en');
UNLOCK TABLES;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
);

LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES (1,'admin','$2y$10$ygne8NZRPeImnNJflEGX9.dd7MkRtvbriPBvgRWWkMNBh4Csa4wkG');
UNLOCK TABLES;

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

LOCK TABLES `role` WRITE;
INSERT INTO `role` VALUES (1,'ROLE_GUEST');
INSERT INTO `role` VALUES (2,'ROLE_STUDENT');
INSERT INTO `role` VALUES (3,'ROLE_MARKETING_COORDINATOR');
INSERT INTO `role` VALUES (4,'ROLE_MARKETING_MANAGER');
UNLOCK TABLES;

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user`(id),
  FOREIGN KEY (`role_id`) REFERENCES `role`(id)
);

LOCK TABLES `user_role` WRITE;
INSERT INTO `user_role` VALUES (1,1,1);
INSERT INTO `user_role` VALUES (2,1,2);
INSERT INTO `user_role` VALUES (3,1,3);
INSERT INTO `user_role` VALUES (4,1,4);
UNLOCK TABLES;
