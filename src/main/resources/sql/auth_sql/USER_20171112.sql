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

 Date: 11/12/2017 14:40:08 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `USER`
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `LAST_LOGIN_IP` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `LAST_LOGIN_TIME` datetime DEFAULT NULL,
  `CORP_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `DEPT_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `REAL_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `USER`
-- ----------------------------
BEGIN;
INSERT INTO `USER` VALUES ('ed19c148b1dd400bbca926dac2945b04', 'admin', '3ef7164d1f6167cb9f2658c07d3c2f0a', null, null, '1', '1', '管理员', '2017-11-12 06:35:57', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
