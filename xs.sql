/*
 Navicat Premium Data Transfer

 Source Server         : LocalCon
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : xs

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 20/01/2024 06:35:00
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
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

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
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'Xiao', 'Shuai', '1298372143@qq.com', 'XS123', '2012-06-28', 'XS0000000001', 1);

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
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of icon
-- ----------------------------
INSERT INTO `icon` VALUES (1, 'XS-Account', 'XS-Assistant the account', NULL);

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note`  (
  `Id` int(11) NOT NULL,
  `Title` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `SubTitle` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Text` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Background` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Image` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `AuthorId` int(11) NOT NULL,
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `AuthorId`(`AuthorId`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of note
-- ----------------------------
INSERT INTO `note` VALUES (1, '欢迎加入XS-Assistant', '欢迎新用户加入XS-Assistant', '欢迎新用户加入XS-Assistant', 'Background 5', 'Illustration 1', 'Logo 2', 1);

SET FOREIGN_KEY_CHECKS = 1;
