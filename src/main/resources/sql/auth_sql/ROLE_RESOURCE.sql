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

 Date: 11/12/2017 14:39:56 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ROLE_RESOURCE`
-- ----------------------------
DROP TABLE IF EXISTS `ROLE_RESOURCE`;
CREATE TABLE `ROLE_RESOURCE` (
  `ROLE_ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `RESOURCE_ID` varchar(32) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `ROLE_RESOURCE`
-- ----------------------------
BEGIN;
INSERT INTO `ROLE_RESOURCE` VALUES ('1', '1'), ('1', '2'), ('1', '3'), ('1', '4'), ('2', '1'), ('2', '2'), ('2', '3');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
