/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : fruitlove

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-08-25 18:26:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `account` varchar(100) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `type` int(2) NOT NULL DEFAULT '1',
  `status` int(2) DEFAULT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'weikong555', 'weikong555@gmail.com', '$2a$10$33c/agV/tgxCppLTsLE4au1TLfNwVBBiEgnFtjXfDEm3XF8ELGG62', '1', null, '2017-08-22 14:22:04');
INSERT INTO `account` VALUES ('2', '18054780372', '18054780372', '$2a$10$fxNOcDr0SudxQ4HjZOCHY.XyFr1TBMGfTX.sI0MU1dWWYAal7CJY.', '2', null, '2017-08-22 14:32:13');

-- ----------------------------
-- Table structure for carts
-- ----------------------------
DROP TABLE IF EXISTS `carts`;
CREATE TABLE `carts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) DEFAULT NULL,
  `product_id` varchar(50) NOT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `product_desc` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of carts
-- ----------------------------
INSERT INTO `carts` VALUES ('1', '2', '20170824101918', '茵红李', '好吃，脆甜，半边红', '24', '2', '宜宾', '2017-08-24 10:21:03');

-- ----------------------------
-- Table structure for goods_collect
-- ----------------------------
DROP TABLE IF EXISTS `goods_collect`;
CREATE TABLE `goods_collect` (
  `id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `product_id` varchar(50) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `product_desc` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_collect
-- ----------------------------

-- ----------------------------
-- Table structure for goods_record
-- ----------------------------
DROP TABLE IF EXISTS `goods_record`;
CREATE TABLE `goods_record` (
  `id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `product_id` varchar(50) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `product_desc` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_record
-- ----------------------------
INSERT INTO `goods_record` VALUES ('1', '2', 'a0001', 'aaa', 'aaaaaaaaaaa', '21', '宜宾', '2017-08-25 11:05:56');
INSERT INTO `goods_record` VALUES ('2', '2', 'a0002', 'bbb', 'bbbbbbbbbb', '33', '西昌', '2017-08-25 11:15:31');
INSERT INTO `goods_record` VALUES ('3', '1', 'a0003', 'ccc', 'ccccccccccc', '50', '龙泉', '2017-08-25 11:06:44');
INSERT INTO `goods_record` VALUES ('4', '2', 'a0004', 'ddd', 'dddddddddddd', '12', '宜宾', '2017-08-25 11:07:28');

-- ----------------------------
-- Table structure for kuaidi
-- ----------------------------
DROP TABLE IF EXISTS `kuaidi`;
CREATE TABLE `kuaidi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kuaidi
-- ----------------------------
INSERT INTO `kuaidi` VALUES ('1', '申通', 'shentong');
INSERT INTO `kuaidi` VALUES ('2', 'EMS', 'ems');
INSERT INTO `kuaidi` VALUES ('3', '顺丰', 'shunfeng');
INSERT INTO `kuaidi` VALUES ('4', '圆通', 'yuantong');
INSERT INTO `kuaidi` VALUES ('5', '中通', 'zhongtong');
INSERT INTO `kuaidi` VALUES ('6', '韵达', 'yunda');
INSERT INTO `kuaidi` VALUES ('7', '天天', 'tiantian');
INSERT INTO `kuaidi` VALUES ('8', '汇通', 'huitongkuaidi');
INSERT INTO `kuaidi` VALUES ('9', '全峰', 'quanfengkuaidi');
INSERT INTO `kuaidi` VALUES ('10', '德邦', 'debangwuliu');
INSERT INTO `kuaidi` VALUES ('11', '宅急送', 'zhaijisong');

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) DEFAULT NULL,
  `order_id` varchar(50) NOT NULL,
  `product_id` varchar(50) NOT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `product_desc` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` int(11) DEFAULT NULL COMMENT '待付款：-1；待发货：1；待收货：2；已完成：9',
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES ('3', '2', '20170824113040', 'a0003', '巨峰葡萄', '甘甜可口，水分足，微酸', '20', '1', '龙泉', '2017-08-24 11:31:29', '2');
INSERT INTO `order_item` VALUES ('6', '2', '117-7-4-14-19-17', 'a0005', '水蜜桃', '香甜顺滑', '35', '3', '龙泉', '2017-08-24 14:19:18', '9');
INSERT INTO `order_item` VALUES ('7', '2', '117-7-4-14-46-23', 'a0005', '蟠桃', '香甜顺滑', '35', '3', '龙泉', '2017-08-24 14:46:24', '-1');
INSERT INTO `order_item` VALUES ('8', '2', '2 2017-8-24 14:50:23', 'a0005', '蟠桃', '香甜顺滑', '35', '3', '龙泉', '2017-08-24 14:50:23', '-1');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) DEFAULT NULL,
  `order_id` varchar(50) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('2', '2', '20170824113040', '2017-08-24 11:33:30');
INSERT INTO `orders` VALUES ('13', '2', '117-7-4-14-13-57', '2017-08-24 14:13:57');
INSERT INTO `orders` VALUES ('14', '2', '117-7-4-14-19-17', '2017-08-24 14:19:18');
INSERT INTO `orders` VALUES ('15', '2', '117-7-4-14-46-23', '2017-08-24 14:46:24');
INSERT INTO `orders` VALUES ('16', '2', '2 2017-8-24 14:50:23', '2017-08-24 14:50:23');
