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
--  Table structure for `顾客信息表`CUSTOMER
-- ----------------------------
DROP TABLE IF EXISTS `CUSTOMER`;
CREATE TABLE `CUSTOMER` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `CORP_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `NUM` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(128) DEFAULT NULL,
  `REGION` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ADDRESS` varchar(2048) DEFAULT NULL,
  `CUSTOMER_TYPE` varchar(64) DEFAULT NULL,
  `BIRTHDAY` DATE DEFAULT NULL,
  `TELEPHONE` varchar(16) DEFAULT NULL,
  `COMMENT` varchar(2048) DEFAULT NULL,
  `CREATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `CREATE_TIME` timestamp,
  `UPDATE_TIME` timestamp,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `DICT`
-- ----------------------------
DROP TABLE IF EXISTS `DICT`;
CREATE TABLE `DICT` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `PID` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `CODE` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `COMMENT` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `DICT`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `CORP_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `CAPACITY` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `METE` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `AMOUNT` DECIMAL(17,2) COLLATE utf8_bin DEFAULT NULL,
  `COMMENT` varchar(2048) DEFAULT NULL,
  `CREATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `CREATE_TIME` timestamp,
  `UPDATE_TIME` timestamp,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `formula`;
CREATE TABLE `formula` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `CORP_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `SCENE` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  `TYPE` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `AMOUNT` DECIMAL(17,2) COLLATE utf8_bin DEFAULT NULL,
  `COMMENT` varchar(2048) DEFAULT NULL,
  `CREATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `CREATE_TIME` timestamp,
  `UPDATE_TIME` timestamp,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



SET FOREIGN_KEY_CHECKS = 1;
