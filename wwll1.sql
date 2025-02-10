/*
 Navicat Premium Dump SQL

 Source Server         : mac
 Source Server Type    : MySQL
 Source Server Version : 90100 (9.1.0)
 Source Host           : localhost:3306
 Source Schema         : wwll

 Target Server Type    : MySQL
 Target Server Version : 90100 (9.1.0)
 File Encoding         : 65001

 Date: 07/02/2025 14:44:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ExtraCosts
-- ----------------------------
DROP TABLE IF EXISTS `ExtraCosts`;
CREATE TABLE `ExtraCosts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(50) NOT NULL,
  `cost` decimal(10,4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of ExtraCosts
-- ----------------------------
BEGIN;
INSERT INTO `ExtraCosts` (`id`, `description`, `cost`) VALUES (1, '员工费', 0.1000);
INSERT INTO `ExtraCosts` (`id`, `description`, `cost`) VALUES (2, '拉头', 0.1000);
COMMIT;

-- ----------------------------
-- Table structure for Materials
-- ----------------------------
DROP TABLE IF EXISTS `Materials`;
CREATE TABLE `Materials` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `type_id` int NOT NULL,
  `model_id` int NOT NULL,
  `roll_price` decimal(10,4) NOT NULL,
  `roll_length` int NOT NULL,
  `waste_length` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `type_id` (`type_id`),
  KEY `model_id` (`model_id`),
  CONSTRAINT `materials_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `MaterialTypes` (`id`),
  CONSTRAINT `materials_ibfk_2` FOREIGN KEY (`model_id`) REFERENCES `Models` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Materials
-- ----------------------------
BEGIN;
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (1, '白铝', 1, 1, 150.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (2, '仿铜', 1, 1, 170.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (3, '黄铜', 1, 1, 180.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (4, '青古铜', 1, 1, 185.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (5, '镀白铜', 1, 1, 190.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (6, '镀白金', 1, 1, 195.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (7, '白金', 1, 1, 200.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (8, '黑金', 1, 1, 210.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (9, '黄金', 1, 1, 220.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (10, '白铝', 1, 2, 160.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (11, '仿铜', 1, 2, 180.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (12, '黄铜', 1, 2, 190.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (13, '青古铜', 1, 2, 200.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (14, '镀白铜', 1, 2, 210.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (15, '镀白金', 1, 2, 220.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (16, '白金', 1, 2, 230.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (17, '黑金', 1, 2, 240.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (18, '黄金', 1, 2, 250.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (19, '白铝', 1, 3, 200.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (20, '仿铜', 1, 3, 220.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (21, '黄铜', 1, 3, 240.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (22, '青古铜', 1, 3, 250.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (23, '镀白铜', 1, 3, 260.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (24, '镀白金', 1, 3, 270.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (25, '白金', 1, 3, 280.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (26, '黑金', 1, 3, 290.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (27, '黄金', 1, 3, 300.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (28, '白铝', 1, 4, 250.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (29, '仿铜', 1, 4, 270.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (30, '黄铜', 1, 4, 290.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (31, '青古铜', 1, 4, 310.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (32, '镀白铜', 1, 4, 330.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (33, '镀白金', 1, 4, 350.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (34, '白金', 1, 4, 370.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (35, '黑金', 1, 4, 390.0000, 9000, 200);
INSERT INTO `Materials` (`id`, `name`, `type_id`, `model_id`, `roll_price`, `roll_length`, `waste_length`) VALUES (36, '黄金', 1, 4, 410.0000, 9000, 200);
COMMIT;

-- ----------------------------
-- Table structure for MaterialTypes
-- ----------------------------
DROP TABLE IF EXISTS `MaterialTypes`;
CREATE TABLE `MaterialTypes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of MaterialTypes
-- ----------------------------
BEGIN;
INSERT INTO `MaterialTypes` (`id`, `type_name`) VALUES (1, '金属');
INSERT INTO `MaterialTypes` (`id`, `type_name`) VALUES (2, '尼龙');
INSERT INTO `MaterialTypes` (`id`, `type_name`) VALUES (3, '树脂');
COMMIT;

-- ----------------------------
-- Table structure for Models
-- ----------------------------
DROP TABLE IF EXISTS `Models`;
CREATE TABLE `Models` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Models
-- ----------------------------
BEGIN;
INSERT INTO `Models` (`id`, `name`) VALUES (1, '3#');
INSERT INTO `Models` (`id`, `name`) VALUES (2, '4#');
INSERT INTO `Models` (`id`, `name`) VALUES (3, '5#');
INSERT INTO `Models` (`id`, `name`) VALUES (4, '8#');
COMMIT;

-- ----------------------------
-- Table structure for OpenCloseTypes
-- ----------------------------
DROP TABLE IF EXISTS `OpenCloseTypes`;
CREATE TABLE `OpenCloseTypes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  `length_adjust` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of OpenCloseTypes
-- ----------------------------
BEGIN;
INSERT INTO `OpenCloseTypes` (`id`, `type`, `length_adjust`) VALUES (1, '开口', 2);
INSERT INTO `OpenCloseTypes` (`id`, `type`, `length_adjust`) VALUES (2, '闭口', 4);
COMMIT;

-- ----------------------------
-- Table structure for Orders
-- ----------------------------
DROP TABLE IF EXISTS `Orders`;
CREATE TABLE `Orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `model_id` int NOT NULL,
  `material_id` int NOT NULL,
  `open_close_id` int NOT NULL,
  `length` int NOT NULL,
  `quantity` int NOT NULL,
  `profit_rate` decimal(5,4) NOT NULL,
  `total_price` decimal(10,4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `model_id` (`model_id`),
  KEY `material_id` (`material_id`),
  KEY `open_close_id` (`open_close_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`model_id`) REFERENCES `Models` (`id`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`material_id`) REFERENCES `Materials` (`id`),
  CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`open_close_id`) REFERENCES `OpenCloseTypes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Orders
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
