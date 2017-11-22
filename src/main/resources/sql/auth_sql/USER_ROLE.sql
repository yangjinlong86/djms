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

 Date: 11/12/2017 14:40:22 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `USER_ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `USER_ROLE`;
CREATE TABLE `USER_ROLE` (
  `USER_ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `ROLE_ID` varchar(32) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `USER_ROLE`
-- ----------------------------
BEGIN;
INSERT INTO `USER_ROLE` VALUES ('ed19c148b1dd400bbca926dac2945b04', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
