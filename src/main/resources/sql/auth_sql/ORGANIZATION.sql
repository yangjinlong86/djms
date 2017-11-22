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

 Date: 11/22/2017 22:38:25 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ORGANIZATION`
-- ----------------------------
DROP TABLE IF EXISTS `ORGANIZATION`;
CREATE TABLE `ORGANIZATION` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `P_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `TYPE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `COMMENT` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `ORGANIZATION`
-- ----------------------------
BEGIN;
INSERT INTO `ORGANIZATION` VALUES ('1', '0', '总部', '1', '2017-11-22 14:14:09', '2017-11-22 14:14:09', '0', '');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
