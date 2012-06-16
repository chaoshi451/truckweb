/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.22 : Database - truckdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`truckdb` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `truckdb`;

/*Table structure for table `t_article` */

DROP TABLE IF EXISTS `t_article`;

CREATE TABLE `t_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `lastUpdateDate` date DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `content` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=gbk;

/*Data for the table `t_article` */

insert  into `t_article`(`id`,`title`,`lastUpdateDate`,`author`,`content`) values (1,'南昌驾校教练车上路有新规定','2012-04-16','湖北力威教练车','<p style=\"widows: 2; text-transform: none; background-color: rgb(255,255,255); text-indent: 0px; font: 12px/20px Verdana, Arial, Helvetica, sans-serif; white-space: normal; orphans: 2; letter-spacing: normal; color: rgb(85,85,85); word-spacing: 0px; -webkit-text-size-adjust: auto; -webkit-text-stroke-width: 0px\">\n	　　本报南昌讯（记者蔡颖辉）12月26日，记者获悉，驾校在每月20日前须上交培训计划至南昌市交管局，由该局视道路通行条件，合理安排培训路线和时间，且每辆教练车需持专用通行证，只允许在全市11条道路上进行培训。</p>\n<p style=\"widows: 2; text-transform: none; background-color: rgb(255,255,255); text-indent: 0px; font: 12px/20px Verdana, Arial, Helvetica, sans-serif; white-space: normal; orphans: 2; letter-spacing: normal; color: rgb(85,85,85); word-spacing: 0px; -webkit-text-size-adjust: auto; -webkit-text-stroke-width: 0px\">\n	　　为规范教练车培训时间和路线，南昌市交管局秩序处将逐月核准驾校上交的培训计划，每天8时至22时为训练时间，早晚上下班高峰（8时至9时30分、17时至19时）除外。按照要求，教练车必须在规定路段教学，超出规定路段的，将追究教练或驾校相关负责人的责任。</p>\n<p style=\"widows: 2; text-transform: none; background-color: rgb(255,255,255); text-indent: 0px; font: 12px/20px Verdana, Arial, Helvetica, sans-serif; white-space: normal; orphans: 2; letter-spacing: normal; color: rgb(85,85,85); word-spacing: 0px; -webkit-text-size-adjust: auto; -webkit-text-stroke-width: 0px\">\n	　　南昌市交管局要求，每月20日前由申请训练的驾驶培训学校提出申请，领取并填写好《指定学习机动车道路驾驶时间和路线申请表》，同时将下个月训练的时间、学员数、车辆数上交至该局秩序处。对于材料齐全的，交管部门在综合考虑道路交通状况、通行能力的基础上，予以核准训练安排。同时，按照规定申办的《指定学习机动车道路驾驶通行证》的车辆只允许一车一证，且必须持证上路培训。对于道路交通现状发生改变，不宜作为训练道路的，市交管局将予以调整，对无通行证的教练车，按照规定进行处罚。</p>\n'),(2,'南昌驾校教练车上路有新规定','2012-04-16','湖北力威教练车','<p style=\"widows: 2; text-transform: none; background-color: rgb(255,255,255); text-indent: 0px; font: 12px/20px Verdana, Arial, Helvetica, sans-serif; white-space: normal; orphans: 2; letter-spacing: normal; color: rgb(85,85,85); word-spacing: 0px; -webkit-text-size-adjust: auto; -webkit-text-stroke-width: 0px\">\n	本报南昌讯（记者蔡颖辉）12月26日，记者获悉，驾校在每月20日前须上交培训计划至南昌市交管局，由该局视道路通行条件，合理安排培训路线和时间，且每辆教练车需持专用通行证，只允许在全市11条道路上进行培训。</p>\n<p style=\"widows: 2; text-transform: none; background-color: rgb(255,255,255); text-indent: 0px; font: 12px/20px Verdana, Arial, Helvetica, sans-serif; white-space: normal; orphans: 2; letter-spacing: normal; color: rgb(85,85,85); word-spacing: 0px; -webkit-text-size-adjust: auto; -webkit-text-stroke-width: 0px\">\n	　　为规范教练车培训时间和路线，南昌市交管局秩序处将逐月核准驾校上交的培训计划，每天8时至22时为训练时间，早晚上下班高峰（8时至9时30分、17时至19时）除外。按照要求，教练车必须在规定路段教学，超出规定路段的，将追究教练或驾校相关负责人的责任。</p>\n<p style=\"widows: 2; text-transform: none; background-color: rgb(255,255,255); text-indent: 0px; font: 12px/20px Verdana, Arial, Helvetica, sans-serif; white-space: normal; orphans: 2; letter-spacing: normal; color: rgb(85,85,85); word-spacing: 0px; -webkit-text-size-adjust: auto; -webkit-text-stroke-width: 0px\">\n	　　南昌市交管局要求，每月20日前由申请训练的驾驶培训学校提出申请，领取并填写好《指定学习机动车道路驾驶时间和路线申请表》，同时将下个月训练的时间、学员数、车辆数上交至该局秩序处。对于材料齐全的，交管部门在综合考虑道路交通状况、通行能力的基础上，予以核准训练安排。同时，按照规定申办的《指定学习机动车道路驾驶通行证》的车辆只允许一车一证，且必须持证上路培训。对于道路交通现状发生改变，不宜作为训练道路的，市交管局将予以调整，对无通行证的教练车，按照规定进行处罚。</p>\n'),(7,'34','2012-04-16','234','<p>\n	3241</p>\n'),(8,'4314','2012-04-16','2134','<p>\n	132412</p>\n');

/*Table structure for table `t_links` */

DROP TABLE IF EXISTS `t_links`;

CREATE TABLE `t_links` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `linkName` varchar(20) DEFAULT NULL COMMENT '链接名称',
  `linkUrl` varchar(100) DEFAULT NULL COMMENT '连接地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `t_links` */

/*Table structure for table `t_message` */

DROP TABLE IF EXISTS `t_message`;

CREATE TABLE `t_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL COMMENT '提交时间',
  `clientName` varchar(20) DEFAULT NULL COMMENT '客户名称',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `concent` varchar(500) DEFAULT NULL COMMENT '留言内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `t_message` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
