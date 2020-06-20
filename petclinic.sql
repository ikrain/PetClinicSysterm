/*
 Navicat Premium Data Transfer

 Source Server         : conn01
 Source Server Type    : MySQL
 Source Server Version : 50519
 Source Host           : localhost:3306
 Source Schema         : petclinic

 Target Server Type    : MySQL
 Target Server Version : 50519
 File Encoding         : 65001

 Date: 20/06/2020 21:36:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('admin', 'admin');
INSERT INTO `employee` VALUES ('huahua', '123456');

-- ----------------------------
-- Table structure for owners
-- ----------------------------
DROP TABLE IF EXISTS `owners`;
CREATE TABLE `owners`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of owners
-- ----------------------------
INSERT INTO `owners` VALUES (6, '王武武', '金水区', '郑州', '14879876355');
INSERT INTO `owners` VALUES (8, '李哥', '河南省', '开封市', '16529843764');
INSERT INTO `owners` VALUES (11, '李斯', '未央区', '西安', '888888880');
INSERT INTO `owners` VALUES (13, '龙儿', '朝阳区', '北京', '111111111');
INSERT INTO `owners` VALUES (14, '岳云鹏', '金水区', '郑州', '17648264737');
INSERT INTO `owners` VALUES (17, '凯凯', '禹州', '许昌', '18749302748');

-- ----------------------------
-- Table structure for pets
-- ----------------------------
DROP TABLE IF EXISTS `pets`;
CREATE TABLE `pets`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birth_date` date NULL DEFAULT NULL,
  `type_id` int(11) NOT NULL,
  `owner_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `type_id`(`type_id`) USING BTREE,
  INDEX `owner_id`(`owner_id`) USING BTREE,
  CONSTRAINT `pets_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `types` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `pets_ibfk_2` FOREIGN KEY (`owner_id`) REFERENCES `owners` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pets
-- ----------------------------
INSERT INTO `pets` VALUES (7, '二哈', '2020-06-01', 2, 6);
INSERT INTO `pets` VALUES (9, '小板凳', '2019-01-03', 5, 8);
INSERT INTO `pets` VALUES (10, '哮天犬', '2019-07-12', 3, 11);
INSERT INTO `pets` VALUES (20, '小毛子', '2019-06-20', 6, 8);
INSERT INTO `pets` VALUES (22, '豆豆', '2019-10-20', 1, 17);

-- ----------------------------
-- Table structure for specialties
-- ----------------------------
DROP TABLE IF EXISTS `specialties`;
CREATE TABLE `specialties`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of specialties
-- ----------------------------
INSERT INTO `specialties` VALUES (1, '外科');
INSERT INTO `specialties` VALUES (2, '内科');
INSERT INTO `specialties` VALUES (3, '牙科');
INSERT INTO `specialties` VALUES (4, '口腔咽喉科');
INSERT INTO `specialties` VALUES (5, '眼科');
INSERT INTO `specialties` VALUES (6, '放射科');

-- ----------------------------
-- Table structure for types
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of types
-- ----------------------------
INSERT INTO `types` VALUES (1, '橘猫');
INSERT INTO `types` VALUES (2, '哈士奇');
INSERT INTO `types` VALUES (3, '金毛');
INSERT INTO `types` VALUES (4, '泰迪');
INSERT INTO `types` VALUES (5, '柯基');
INSERT INTO `types` VALUES (6, '加菲猫');
INSERT INTO `types` VALUES (7, '仓鼠');
INSERT INTO `types` VALUES (8, '龙猫');

-- ----------------------------
-- Table structure for vet_specialties
-- ----------------------------
DROP TABLE IF EXISTS `vet_specialties`;
CREATE TABLE `vet_specialties`  (
  `vet_id` int(11) NOT NULL,
  `specialty_id` int(11) NOT NULL,
  INDEX `vet_id`(`vet_id`) USING BTREE,
  INDEX `specialty_id`(`specialty_id`) USING BTREE,
  CONSTRAINT `vet_specialties_ibfk_1` FOREIGN KEY (`vet_id`) REFERENCES `vets` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `vet_specialties_ibfk_2` FOREIGN KEY (`specialty_id`) REFERENCES `specialties` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of vet_specialties
-- ----------------------------
INSERT INTO `vet_specialties` VALUES (3, 5);
INSERT INTO `vet_specialties` VALUES (5, 6);

-- ----------------------------
-- Table structure for vets
-- ----------------------------
DROP TABLE IF EXISTS `vets`;
CREATE TABLE `vets`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of vets
-- ----------------------------
INSERT INTO `vets` VALUES (3, '黄秋生');
INSERT INTO `vets` VALUES (5, '王华');

-- ----------------------------
-- Table structure for visits
-- ----------------------------
DROP TABLE IF EXISTS `visits`;
CREATE TABLE `visits`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pet_id` int(11) NOT NULL,
  `visit_date` date NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pet_id`(`pet_id`) USING BTREE,
  CONSTRAINT `visits_ibfk_1` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of visits
-- ----------------------------
INSERT INTO `visits` VALUES (1, 7, '2020-04-01', '感冒');
INSERT INTO `visits` VALUES (9, 9, '2020-04-11', '新冠肺炎');
INSERT INTO `visits` VALUES (10, 9, '2020-05-12', '打疫苗');
INSERT INTO `visits` VALUES (15, 9, '2020-06-18', '食欲不振');
INSERT INTO `visits` VALUES (16, 9, '2020-02-11', '感冒');

SET FOREIGN_KEY_CHECKS = 1;
