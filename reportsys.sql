/*
Navicat MySQL Data Transfer

Source Server         : java_web
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : reportsys

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-02-17 15:52:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for groups
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `leader` varchar(20) DEFAULT NULL,
  `member` varchar(255) DEFAULT NULL,
  `ids` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of groups
-- ----------------------------
INSERT INTO `groups` VALUES ('1', '1', 'student', '123,teacher', '2,4,5');
INSERT INTO `groups` VALUES ('3', '第二小组', '111', '111,student,123', null);
INSERT INTO `groups` VALUES ('4', '第三小组', '222', '111,222,123', '7,6,7,5');
INSERT INTO `groups` VALUES ('5', '222222', '111', 'teacher', '6,2');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` varchar(40) NOT NULL DEFAULT '',
  `content` varchar(255) DEFAULT NULL,
  `createTime` varchar(30) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('0', '2017-02-17 提交日报表的截止时间是： 22:30:00', '2017-02-17 15:20:10', 'daily');
INSERT INTO `notice` VALUES ('4bc3d03b-c8cd-4892-9e69-b5c0715647ae', '2017-02-17 提交日报表的截止时间是： 22:30:00', '2017-02-17 15:34:10', 'daily');
INSERT INTO `notice` VALUES ('d56630ed-7dc7-427d-acaf-bd9575094210', '2017-02-19 提交周报表的截止时间是： 23:00:00', '2017-02-17 15:34:12', 'weekly');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `iconCls` varchar(10) DEFAULT NULL,
  `url` varchar(20) DEFAULT NULL,
  `pid` int(5) DEFAULT NULL,
  `sort` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '系统公告', 'icon-blank', 'notice.jsp', '0', '1');
INSERT INTO `permission` VALUES ('2', '用户管理', 'icon-sys', null, '0', '5');
INSERT INTO `permission` VALUES ('3', '创建用户', 'icon-blank', 'addUser.jsp', '2', '6');
INSERT INTO `permission` VALUES ('4', '查看用户', 'icon-blank', 'findUser.jsp', '2', '7');
INSERT INTO `permission` VALUES ('5', '小组管理', 'icon-sys', null, '0', '8');
INSERT INTO `permission` VALUES ('6', '创建小组', 'icon-blank', 'addGroup.jsp', '5', '9');
INSERT INTO `permission` VALUES ('7', '查看小组', 'icon-blank', 'findGroup.jsp', '5', '10');
INSERT INTO `permission` VALUES ('8', '报表管理', 'icon-sys', null, '0', '2');
INSERT INTO `permission` VALUES ('9', '日报表', 'icon-blank', 'allDailyReport.jsp', '8', '3');
INSERT INTO `permission` VALUES ('10', '周报表', 'icon-blank', 'allWeeklyReport.jsp', '8', '4');
INSERT INTO `permission` VALUES ('11', '我的日报表', 'icon-blank', 'dailyReport.jsp', '0', '11');
INSERT INTO `permission` VALUES ('12', '我的周报表', 'icon-blank', 'weeklyReport.jsp', '0', '12');
INSERT INTO `permission` VALUES ('13', '我的小组', 'icon-blank', 'myGroup.jsp', '0', '13');
INSERT INTO `permission` VALUES ('14', '统计分析', 'icon-blank', 'statistics.jsp', '0', '14');

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` varchar(200) NOT NULL DEFAULT '',
  `name` varchar(30) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `createTime` varchar(20) DEFAULT NULL,
  `userId` int(5) DEFAULT NULL,
  `downloadUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `report_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES ('032fd357-d246-4e2d-8c4d-dc0631f93dc1', '2017-02-19_周报表_刘良.docx', 'weekly', 'cc1d2a50-48bd-4b82-ad28-34ef12e782be.docx', '2017-02-17 15:35:08', '5', '<a href=\"report/downloadReport/weekly?id=032fd357-d246-4e2d-8c4d-dc0631f93dc1\">下载</a>');
INSERT INTO `report` VALUES ('0c3f3b01-0296-4749-aa61-d89372b9e316', '2017-02-17_日报表_刘良.docx', 'daily', 'c026253d-b54e-45ec-95ff-e681f92df250.docx', '2017-02-17 15:34:47', '5', '<a href=\"report/downloadReport/daily?id=0c3f3b01-0296-4749-aa61-d89372b9e316\">下载</a>');
INSERT INTO `report` VALUES ('36f255a6-6fe7-4aaf-8987-0ab036e14dc1', '2017-02-17_日报表_刘良.docx', 'daily', '748ccc99-6eee-4431-a508-7d6ede3a8229.docx', '2017-02-17 14:31:16', '5', '<a href=\"report/downloadReport/daily?id=36f255a6-6fe7-4aaf-8987-0ab036e14dc1\">下载</a>');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员');
INSERT INTO `role` VALUES ('2', '项目管理');
INSERT INTO `role` VALUES ('3', '项目组长');
INSERT INTO `role` VALUES ('4', '项目成员');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `roleId` int(5) DEFAULT NULL,
  `permissionId` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `roleId` (`roleId`),
  KEY `permissionId` (`permissionId`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permissionId`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '1');
INSERT INTO `role_permission` VALUES ('2', '1', '2');
INSERT INTO `role_permission` VALUES ('3', '1', '3');
INSERT INTO `role_permission` VALUES ('4', '1', '4');
INSERT INTO `role_permission` VALUES ('5', '1', '5');
INSERT INTO `role_permission` VALUES ('6', '1', '6');
INSERT INTO `role_permission` VALUES ('7', '1', '7');
INSERT INTO `role_permission` VALUES ('8', '1', '8');
INSERT INTO `role_permission` VALUES ('9', '1', '9');
INSERT INTO `role_permission` VALUES ('10', '1', '10');
INSERT INTO `role_permission` VALUES ('11', '1', '14');
INSERT INTO `role_permission` VALUES ('12', '2', '1');
INSERT INTO `role_permission` VALUES ('13', '2', '4');
INSERT INTO `role_permission` VALUES ('14', '2', '5');
INSERT INTO `role_permission` VALUES ('15', '2', '6');
INSERT INTO `role_permission` VALUES ('16', '2', '7');
INSERT INTO `role_permission` VALUES ('17', '2', '8');
INSERT INTO `role_permission` VALUES ('18', '2', '9');
INSERT INTO `role_permission` VALUES ('19', '2', '10');
INSERT INTO `role_permission` VALUES ('20', '2', '14');
INSERT INTO `role_permission` VALUES ('21', '3', '1');
INSERT INTO `role_permission` VALUES ('22', '3', '11');
INSERT INTO `role_permission` VALUES ('23', '3', '12');
INSERT INTO `role_permission` VALUES ('24', '3', '13');
INSERT INTO `role_permission` VALUES ('25', '3', '14');
INSERT INTO `role_permission` VALUES ('26', '4', '1');
INSERT INTO `role_permission` VALUES ('27', '4', '11');
INSERT INTO `role_permission` VALUES ('28', '4', '12');
INSERT INTO `role_permission` VALUES ('29', '4', '13');
INSERT INTO `role_permission` VALUES ('30', '2', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', 'admin', '1');
INSERT INTO `user` VALUES ('2', '2', 'teacher', '2');
INSERT INTO `user` VALUES ('3', '3', 'leader', '3');
INSERT INTO `user` VALUES ('4', '4', 'student', '4');
INSERT INTO `user` VALUES ('5', '123', '刘良', '123');
INSERT INTO `user` VALUES ('7', '222', '中文', '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `userId` int(5) DEFAULT NULL,
  `roleId` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '3', '3');
INSERT INTO `user_role` VALUES ('3', '3', '4');
INSERT INTO `user_role` VALUES ('4', '5', '4');
INSERT INTO `user_role` VALUES ('5', '6', '4');
INSERT INTO `user_role` VALUES ('6', '7', '4');
INSERT INTO `user_role` VALUES ('7', '2', '2');
INSERT INTO `user_role` VALUES ('8', '4', '4');
SET FOREIGN_KEY_CHECKS=1;
