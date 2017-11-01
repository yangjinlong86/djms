/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1-mysql
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 127.0.0.1
 Source Database       : BMS

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : utf-8

 Date: 10/29/2017 22:44:55 PM
*/

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
--  Table structure for `ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `ROLE`;
CREATE TABLE `ROLE` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `ROLE_DESC` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `ROLE_RESOURCE`
-- ----------------------------
DROP TABLE IF EXISTS `ROLE_RESOURCE`;
CREATE TABLE `ROLE_RESOURCE` (
  `ROLE_ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `RESOURCE_ID` varchar(32) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `USER_ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `USER_ROLE`;
CREATE TABLE `USER_ROLE` (
  `USER_ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `ROLE_ID` varchar(32) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
