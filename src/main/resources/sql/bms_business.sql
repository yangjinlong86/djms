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

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
--   Table structure for `顾客信息表`CUSTOMER
--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
DROP TABLE IF EXISTS `CUSTOMER`;
CREATE TABLE `CUSTOMER` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `CORP_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `NUM` varchar(64) COLLATE utf8_bin DEFAULT NULL,-- 编号
  `NAME` varchar(128) DEFAULT NULL,-- 姓名
  `REGION` varchar(64) COLLATE utf8_bin DEFAULT NULL,-- 区划
  `ADDRESS` varchar(2048) DEFAULT NULL,-- 地址
  `CUSTOMER_TYPE` varchar(64) DEFAULT NULL,-- 顾客类型
  `BIRTHDAY` DATE DEFAULT NULL,-- 生日
  `TELEPHONE` varchar(16) DEFAULT NULL,-- 电话
  `COMMENT` varchar(2048) DEFAULT NULL,
  `CREATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `CREATE_TIME` timestamp,
  `UPDATE_TIME` timestamp,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
--   Table structure for `DICT` 字典
--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
DROP TABLE IF EXISTS `DICT`;
CREATE TABLE `DICT` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,-- 
  `PID` varchar(32) COLLATE utf8_bin DEFAULT NULL,-- 父节点
  `NAME` varchar(512) COLLATE utf8_bin DEFAULT NULL,-- 名称
  `CODE` varchar(64) COLLATE utf8_bin DEFAULT NULL,-- 代码
  `COMMENT` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
--   Table structure for `variety` 库存信息variety
--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
DROP TABLE IF EXISTS `variety`;
CREATE TABLE `variety` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `CORP_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(512) COLLATE utf8_bin DEFAULT NULL,-- 名称
  `BRAND` varchar(64) COLLATE utf8_bin DEFAULT NULL,-- 品牌
  `TYPE` varchar(64) COLLATE utf8_bin DEFAULT NULL,-- 类型
  `COMMENT` varchar(2048) DEFAULT NULL,
  `CREATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `CREATE_TIME` timestamp,
  `UPDATE_TIME` timestamp,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
--   Table structure for `product` 库存信息
--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `CORP_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(512) COLLATE utf8_bin DEFAULT NULL,-- 名称
  `CAPACITY` varchar(64) COLLATE utf8_bin DEFAULT NULL,-- 容量
  `METE` varchar(64) COLLATE utf8_bin DEFAULT NULL,-- 计量
  `TYPE` varchar(64) COLLATE utf8_bin DEFAULT NULL,-- 类型
  `AMOUNT` DECIMAL(17,2) COLLATE utf8_bin DEFAULT NULL,-- 单价
  `STATUS` varchar(64) DEFAULT NULL,-- 状态  入库  出库
  `IN_DATE` DATE DEFAULT NULL,-- 入库日期
  `COUNT` DECIMAL(17,0) DEFAULT 0,-- 入库数量
  `REMAIN_COUNT` DECIMAL(17,0) DEFAULT 0,-- 剩余数量
  `END_VALIDITY` DATE DEFAULT NULL,-- 保质期
  `COMMENT` varchar(2048) DEFAULT NULL,
  `CREATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `CREATE_TIME` timestamp,
  `UPDATE_TIME` timestamp,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
--   Table structure for `product` 出库记录
--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
DROP TABLE IF EXISTS `stock_use`;
CREATE TABLE `stock_use` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `STOCK_ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `CORP_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `OUT_USER_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL,-- 使用人ID
  `OUT_COUNT` DECIMAL(17,0) DEFAULT NULL,-- 出库数量
  `OUT_DATE` DATE DEFAULT NULL,-- 出库日期
  `COMMENT` varchar(2048) DEFAULT NULL,
  `CREATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `CREATE_TIME` timestamp,
  `UPDATE_TIME` timestamp,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
--   Table structure for `formula` 美容方案
--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
DROP TABLE IF EXISTS `formula`;
CREATE TABLE `formula` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `CORP_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL, -- 单位主键
  `NAME` varchar(512) COLLATE utf8_bin DEFAULT NULL, -- 名称
  `SCENE` varchar(1024) COLLATE utf8_bin DEFAULT NULL,-- 适用人群
  `TYPE` varchar(64) COLLATE utf8_bin DEFAULT NULL,-- 类型
  `AMOUNT` DECIMAL(17,2) COLLATE utf8_bin DEFAULT NULL,-- 金额
  `COMMENT` varchar(2048) DEFAULT NULL,
  `CREATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `CREATE_TIME` timestamp,
  `UPDATE_TIME` timestamp,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
--   Table structure for `formula_details` 美容法案细节
--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
DROP TABLE IF EXISTS `formula_details`;
CREATE TABLE `formula_details` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `FORMULA_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,-- 方案主键
  `PRODUCT_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,-- 产品主键
  `STEP` varchar(64) COLLATE utf8_bin DEFAULT NULL,-- 步骤
  `CAPACITY` varchar(64) COLLATE utf8_bin DEFAULT NULL,-- 容量
  `METE` varchar(64) COLLATE utf8_bin DEFAULT NULL,-- 计量
  `AMOUNT` DECIMAL(17,2) COLLATE utf8_bin DEFAULT NULL,-- 金额
  `SORT` 	DECIMAL(3,0) COLLATE utf8_bin DEFAULT NULL,-- 排序
  `COMMENT` varchar(2048) DEFAULT NULL,
  `CREATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `CREATE_TIME` timestamp,
  `UPDATE_TIME` timestamp,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
--   Table structure for `order` 订单/销售记录
--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `CORP_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,-- 单位主键
  `CUSTOMER_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,-- 顾客主键
  `USER_ID` varchar(1024) COLLATE utf8_bin DEFAULT NULL,-- 销售人员
  `TYPE` varchar(64) COLLATE utf8_bin DEFAULT NULL,-- 订单类型
  `USE_TYPE` varchar(64) COLLATE utf8_bin DEFAULT NULL,-- 使用类型
  `END_VALIDITY` date COLLATE utf8_bin DEFAULT NULL,-- 有效期
  `order_serive` varchar(64) COLLATE utf8_bin DEFAULT NULL,-- 服务类型
  `AMOUNT` DECIMAL(17,2) COLLATE utf8_bin DEFAULT NULL,-- 金额
  `COMMENT` varchar(2048) DEFAULT NULL,
  `CREATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `CREATE_TIME` timestamp,
  `UPDATE_TIME` timestamp,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
--   Table structure for `consume` 消费记录
--  -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
DROP TABLE IF EXISTS `consume`;
CREATE TABLE `consume` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `FORMULA_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,-- 方案主键
  `CUSTOMER_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,-- 顾客主键
  `CORP_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,-- 单位主键
  `USER_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL,-- 美容师ID
  `CONSUME_TYPE` varchar(64) COLLATE utf8_bin DEFAULT NULL,-- 消费类型
  `AMOUNT` DECIMAL(17,2) COLLATE utf8_bin DEFAULT NULL,-- 金额
  `COMMENT` varchar(2048) DEFAULT NULL,
  `CREATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `CREATE_TIME` timestamp,
  `UPDATE_TIME` timestamp,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
