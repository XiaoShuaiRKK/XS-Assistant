/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : xs

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 26/02/2024 09:43:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area`  (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `AreaName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES (1, 'China');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `LastName` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Password` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Birth` date NULL DEFAULT NULL,
  `IdNumber` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `AreaId` int(11) NOT NULL,
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `Area`(`AreaId`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'Xiao', 'Shuai', '1298372143@qq.com', 'XS123456', '2012-06-28', 'XS0000000001', 1);
INSERT INTO `customer` VALUES (2, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280000', 1);
INSERT INTO `customer` VALUES (3, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280000', 1);
INSERT INTO `customer` VALUES (4, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280000', 1);
INSERT INTO `customer` VALUES (5, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280000', 1);
INSERT INTO `customer` VALUES (6, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280000', 1);
INSERT INTO `customer` VALUES (7, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280000', 1);
INSERT INTO `customer` VALUES (8, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280000', 1);
INSERT INTO `customer` VALUES (9, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280000', 1);
INSERT INTO `customer` VALUES (10, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280001', 1);
INSERT INTO `customer` VALUES (11, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280001', 1);
INSERT INTO `customer` VALUES (12, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280001', 1);
INSERT INTO `customer` VALUES (13, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280003', 1);

-- ----------------------------
-- Table structure for icon
-- ----------------------------
DROP TABLE IF EXISTS `icon`;
CREATE TABLE `icon`  (
  `Id` int(11) NOT NULL,
  `Name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Acrpnym` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of icon
-- ----------------------------
INSERT INTO `icon` VALUES (1, 'XS-Account', 'XS-Assistant the account', NULL);

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note`  (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Background` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Image` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `AuthorId` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `ArticleId` varchar(14) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `AuthorId`(`AuthorId`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of note
-- ----------------------------
INSERT INTO `note` VALUES (1, 'Background 5', 'Illustration 1', 'Logo 2', '1', '');
INSERT INTO `note` VALUES (2, 'Background 5', 'Illustration 1', 'Logo 2', 'XS0000000001', 'XSA00000000004');
INSERT INTO `note` VALUES (3, 'Background 5', 'Illustration 1', 'Logo 2', 'XS0000000001', 'XSA00000000003');
INSERT INTO `note` VALUES (4, 'Background 5', 'Illustration 1', 'Logo 2', 'XS0000000001', 'XSA00000000002');
INSERT INTO `note` VALUES (5, 'Background 5', 'Illustration 1', 'Logo 2', 'XS0000000001', 'XSA00000000001');
INSERT INTO `note` VALUES (6, 'Background 5', 'Illustration 1', 'Logo 2', 'XS0000000001', 'XSA00000000000');

SET FOREIGN_KEY_CHECKS = 1;
