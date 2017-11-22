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

 Date: 11/12/2017 14:39:29 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `RESOURCE`
-- ----------------------------
DROP TABLE IF EXISTS `RESOURCE`;
CREATE TABLE `RESOURCE` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `URL` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `PARENT_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `SORT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `RESOURCE`
-- ----------------------------
BEGIN;
INSERT INTO `RESOURCE` VALUES ('1', '资源管理', '/resource', '1', '0', '1'), ('2', '角色管理', '/role', '1', '0', '2'), ('3', '用户管理', '/user', '1', '0', '3'), ('4', '顾客管理', '/customer', '1', '0', '4');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
