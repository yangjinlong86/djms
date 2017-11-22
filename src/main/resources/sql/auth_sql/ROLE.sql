/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1-mysql
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 127.0.0.1
 Source Database       : bms

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : utf-8

 Date: 11/15/2017 00:00:27 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `ROLE`;
CREATE TABLE `ROLE` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `ROLE_DESC` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `ROLE_NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `ROLE`
-- ----------------------------
BEGIN;
INSERT INTO `ROLE` VALUES ('1', '管理员', '管理员'), ('2', '普通用户', '普通用户');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
