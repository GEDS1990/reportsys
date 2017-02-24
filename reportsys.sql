/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.6.26 : Database - reportsys
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`reportsys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `reportsys`;

/*Table structure for table `groups` */

DROP TABLE IF EXISTS `groups`;

CREATE TABLE `groups` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `leader` varchar(20) DEFAULT NULL,
  `member` varchar(255) DEFAULT NULL,
  `ids` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `groups` */

insert  into `groups`(`id`,`name`,`leader`,`member`,`ids`) values (1,'1','student','123,teacher','2,4,5'),(3,'第二小组','111','111,student,123',NULL),(4,'第三小组','222','111,222,123','7,6,7,5'),(5,'222222','111','teacher','6,2');

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `id` varchar(40) NOT NULL DEFAULT '',
  `content` varchar(255) DEFAULT NULL,
  `createTime` varchar(30) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `notice` */

insert  into `notice`(`id`,`content`,`createTime`,`type`) values ('1ac2d47e-980d-41d2-837c-ec63143364c0','2017-02-26 提交周报表的截止时间是： 23:00:00','2017-02-24 14:30:18','weekly'),('272d19cc-f029-40b2-b970-0d1be749e52f','2017-02-24 提交日报表的截止时间是： 22:30:00','2017-02-24 15:23:06','daily'),('2e3fea14-e309-425f-b0b5-7caa83311d94','2017-02-24 提交日报表的截止时间是： 22:30:00','2017-02-24 15:25:02','daily'),('4b898c6e-95d8-4ed7-92b1-d031fa8924ce','2017-02-24 提交日报表的截止时间是： 22:30:00','2017-02-24 14:03:54','daily'),('7d65b7cf-d936-495f-8c41-0326ba476d36','2017-02-24 提交日报表的截止时间是： 22:30:00','2017-02-24 14:10:23','daily'),('859efc01-a3d2-4a74-a924-30d24af7673c','2017-02-24 提交日报表的截止时间是： 22:30:00','2017-02-24 14:04:54','daily'),('9e655378-4b63-49fd-a9c0-61bf5405e43f','2017-02-22 提交日报表的截止时间是： 22:30:00','2017-02-22 16:43:10','daily'),('a1598acd-9c54-4182-a5f9-2aa5dcb4bd53','2017-02-26 提交周报表的截止时间是： 23:00:00','2017-02-24 15:10:20','weekly'),('bce409e6-600b-4f8b-ac22-ecc0429e84ec','2017-02-26 提交周报表的截止时间是： 23:00:00','2017-02-24 15:25:02','weekly'),('bdda75b9-9efe-4adb-80e0-82aa10ba3f41','2017-02-24 提交日报表的截止时间是： 22:30:00','2017-02-24 14:30:00','daily'),('c87fb7dd-fb33-48ca-8b40-38d7f8ff7a2f','2017-02-24 提交日报表的截止时间是： 22:30:00','2017-02-24 15:10:20','daily'),('e3f90908-fee3-431e-a768-c8c1587708cf','2017-02-26 提交周报表的截止时间是： 23:00:00','2017-02-24 15:23:06','weekly');

/*Table structure for table `permission` */

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

/*Data for the table `permission` */

insert  into `permission`(`id`,`name`,`iconCls`,`url`,`pid`,`sort`) values (1,'系统公告','icon-blank','notice.jsp',0,1),(2,'用户管理','icon-sys',NULL,0,5),(3,'创建用户','icon-blank','addUser.jsp',2,6),(4,'查看用户','icon-blank','findUser.jsp',2,7),(5,'小组管理','icon-sys',NULL,0,8),(6,'创建小组','icon-blank','addGroup.jsp',5,9),(7,'查看小组','icon-blank','findGroup.jsp',5,10),(8,'报表管理','icon-sys',NULL,0,2),(9,'日报表','icon-blank','allDailyReport.jsp',8,3),(10,'周报表','icon-blank','allWeeklyReport.jsp',8,4),(11,'我的日报表','icon-blank','dailyReport.jsp',0,11),(12,'我的周报表','icon-blank','weeklyReport.jsp',0,12),(13,'我的小组','icon-blank','myGroup.jsp',0,13),(14,'统计分析','icon-blank','statistics.jsp',0,14);

/*Table structure for table `report` */

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

/*Data for the table `report` */

insert  into `report`(`id`,`name`,`type`,`url`,`createTime`,`userId`,`downloadUrl`) values ('032fd357-d246-4e2d-8c4d-dc0631f93dc1','2017-02-19_周报表_刘良.docx','weekly','cc1d2a50-48bd-4b82-ad28-34ef12e782be.docx','2017-02-17 15:35:08',5,'<a href=\"report/downloadReport/weekly?id=032fd357-d246-4e2d-8c4d-dc0631f93dc1\">下载</a>'),('05ba899a-bb33-4313-8326-8f6aaf1346c0','2017-02-20_日报表_student.docx','daily','30a10df3-bc3e-4b74-9043-9277bce3386a.docx','2017-02-20 13:44:28',4,'<a href=\"report/downloadReport/daily?id=05ba899a-bb33-4313-8326-8f6aaf1346c0\">下载</a>'),('0c3f3b01-0296-4749-aa61-d89372b9e316','2017-02-17_日报表_刘良.docx','daily','c026253d-b54e-45ec-95ff-e681f92df250.docx','2017-02-17 15:34:47',5,'<a href=\"report/downloadReport/daily?id=0c3f3b01-0296-4749-aa61-d89372b9e316\">下载</a>'),('36f255a6-6fe7-4aaf-8987-0ab036e14dc1','2017-02-17_日报表_刘良.docx','daily','748ccc99-6eee-4431-a508-7d6ede3a8229.docx','2017-02-17 14:31:16',5,'<a href=\"report/downloadReport/daily?id=36f255a6-6fe7-4aaf-8987-0ab036e14dc1\">下载</a>'),('4b385119-2617-4a43-bbec-4a26b32425a5','2017-02-20_日报表_student.docx','daily','58c99cbd-f0d4-4fc0-bef1-0b11f5619662.docx','2017-02-20 13:44:36',4,'<a href=\"report/downloadReport/daily?id=4b385119-2617-4a43-bbec-4a26b32425a5\">下载</a>'),('4b63d7f6-0792-4ca4-b9b9-858bd4a0d079','周报表模板.doc','template','e7de0b6e-5855-4c63-bb67-69c2e9b6c496.doc','2017-02-24 16:30:32',1,'<a href=\"report/downloadReport/template?id=4b63d7f6-0792-4ca4-b9b9-858bd4a0d079\">下载</a>'),('a4ca2073-bb71-4a28-a227-f4bc5f6bea8c','2017-02-26_周报表_student.doc','weekly','3b37433a-ec5d-498f-878f-e00264b45451.doc','2017-02-20 13:48:29',4,'<a href=\"report/downloadReport/weekly?id=a4ca2073-bb71-4a28-a227-f4bc5f6bea8c\">下载</a>'),('b9c68a6f-e7d7-481f-8e0a-2ba74a8021d3','日报表模板.docx','template','82173820-0afb-4f77-bb13-c7a7a14373f2.docx','2017-02-24 16:50:09',1,'<a href=\"report/downloadReport/template?id=b9c68a6f-e7d7-481f-8e0a-2ba74a8021d3\">下载</a>');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`) values (1,'管理员'),(2,'项目管理'),(3,'项目组长'),(4,'项目成员');

/*Table structure for table `role_permission` */

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

/*Data for the table `role_permission` */

insert  into `role_permission`(`id`,`roleId`,`permissionId`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,14),(12,2,1),(13,2,4),(14,2,5),(15,2,6),(16,2,7),(17,2,8),(18,2,9),(19,2,10),(20,2,14),(21,3,1),(22,3,11),(23,3,12),(24,3,13),(25,3,14),(26,4,1),(27,4,11),(28,4,12),(29,4,13),(30,2,2);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`account`,`username`,`password`) values (1,'111111','admin','e10adc3949ba59abbe56e057f20f883e'),(2,'5090115','惠孛','e10adc3949ba59abbe56e057f20f883e'),(3,'201522220213','张乘斐','e10adc3949ba59abbe56e057f20f883e'),(4,'201421220158','尚雪峰','e10adc3949ba59abbe56e057f20f883e'),(5,'123','刘良','e10adc3949ba59abbe56e057f20f883e'),(7,'222','测试用户','e10adc3949ba59abbe56e057f20f883e'),(8,'333','测试用户','e10adc3949ba59abbe56e057f20f883e');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `userId` int(5) DEFAULT NULL,
  `roleId` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`userId`,`roleId`) values (1,1,1),(2,3,3),(3,3,4),(4,5,4),(5,6,4),(6,7,4),(7,2,2),(8,4,3),(9,8,4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
