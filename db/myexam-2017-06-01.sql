/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50539
Source Host           : 127.0.0.1:3306
Source Database       : myexam

Target Server Type    : MYSQL
Target Server Version : 50539
File Encoding         : 65001

Date: 2017-06-01 18:36:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ClassExamFile
-- ----------------------------
DROP TABLE IF EXISTS `ClassExamFile`;
CREATE TABLE `classexamfile` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `course_id` int(10) unsigned DEFAULT NULL COMMENT '考试课程的id',
  `classInfo_id` int(10) unsigned DEFAULT NULL COMMENT '考试班级的',
  `examFile_id` int(10) unsigned DEFAULT NULL COMMENT '考哪套题目',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ClassExamFile
-- ----------------------------
INSERT INTO `ClassExamFile` VALUES ('1', '2017-05-31 21:44:44', '1', '1', '1');
INSERT INTO `ClassExamFile` VALUES ('2', '2017-05-31 21:44:44', '1', '2', '2');

-- ----------------------------
-- Table structure for ClassInfo
-- ----------------------------
DROP TABLE IF EXISTS `ClassInfo`;
CREATE TABLE `classinfo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `className` varchar(20) NOT NULL COMMENT '班级名称',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ClassInfo
-- ----------------------------
INSERT INTO `ClassInfo` VALUES ('1', '信息A1521', '2017-05-31 21:06:09');
INSERT INTO `ClassInfo` VALUES ('2', '信息A1522', '2017-05-31 21:08:09');

-- ----------------------------
-- Table structure for classstartexam
-- ----------------------------
DROP TABLE IF EXISTS `classstartexam`;
CREATE TABLE `classstartexam` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `class_id` int(10) unsigned DEFAULT NULL COMMENT '考试的班级id',
  `course_id` int(10) unsigned DEFAULT NULL COMMENT '考试课程的id',
  `teacher_id` int(10) unsigned DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `startTime` datetime DEFAULT NULL COMMENT '开始考试的时间',
  `duration` bigint(20) DEFAULT NULL COMMENT '考试的时间长度，按照毫秒算',
  `notify` bigint(20) DEFAULT NULL COMMENT '还差多长时间时提示，毫秒算',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classstartexam
-- ----------------------------
INSERT INTO `classstartexam` VALUES ('2', '1', '1', '1', '2017-05-31 21:57:39', '2017-05-31 21:57:39', '6000000', '300000');
INSERT INTO `classstartexam` VALUES ('3', '2', '1', '1', '2017-05-31 21:59:06', '2017-05-31 21:59:06', '6000000', '300000');

-- ----------------------------
-- Table structure for Course
-- ----------------------------
DROP TABLE IF EXISTS `Course`;
CREATE TABLE `course` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `courseName` varchar(30) DEFAULT NULL COMMENT '课程名称',
  `createTime` datetime DEFAULT NULL,
  `examFilePath` varchar(100) DEFAULT NULL COMMENT '考题的目录',
  `stusExamFilePath` varchar(100) DEFAULT NULL COMMENT '学生提交答案的存储总目录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Course
-- ----------------------------
INSERT INTO `Course` VALUES ('1', 'Web数据库技术', '2017-05-31 21:23:15', '/Web数据库技术/考题试题集', '/Web数据库技术/学生答题集合');

-- ----------------------------
-- Table structure for CourseExamFile
-- ----------------------------
DROP TABLE IF EXISTS `CourseExamFile`;
CREATE TABLE `courseexamfile` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `course_id` int(10) unsigned DEFAULT NULL COMMENT '考试科目id',
  `examName` varchar(40) DEFAULT NULL COMMENT '考试题目的名称,如试题A、试题B等',
  `realPath` varchar(100) DEFAULT NULL COMMENT '题目的路径',
  `teacher_id` int(11) DEFAULT NULL COMMENT '上传教师的id',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CourseExamFile
-- ----------------------------
INSERT INTO `CourseExamFile` VALUES ('1', '1', 'Web数据库技术-项目-A', '/Web数据库技术/考题试题集/SSH1.rar', '1', '2017-05-31 21:43:11');
INSERT INTO `CourseExamFile` VALUES ('2', '1', 'Web数据库技术-项目-B', '/Web数据库技术/考题试题集/SSH2.rar', '1', '2017-05-31 21:43:11');

-- ----------------------------
-- Table structure for StudentExamRecord
-- ----------------------------
DROP TABLE IF EXISTS `StudentExamRecord`;
CREATE TABLE `studentexamrecord` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `stu_id` int(10) unsigned DEFAULT NULL,
  `course_id` int(10) unsigned DEFAULT NULL COMMENT '考试科目',
  `courseExamFile_id` int(10) unsigned DEFAULT NULL COMMENT '考试题目的题号',
  `startTime` datetime DEFAULT NULL COMMENT '开始考试时间',
  `endTime` datetime DEFAULT NULL COMMENT '考试结束的时间',
  `ip` varchar(18) DEFAULT NULL COMMENT '首次登陆的ip地址',
  `realPath` varchar(100) DEFAULT NULL COMMENT '上传的答案的路径',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of StudentExamRecord
-- ----------------------------
INSERT INTO `StudentExamRecord` VALUES ('1', '2', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('2', '3', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('3', '4', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('4', '5', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('5', '6', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('6', '7', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('7', '8', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('8', '9', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('9', '10', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('10', '11', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('11', '12', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('12', '13', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('13', '14', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('14', '15', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('15', '16', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('16', '17', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('17', '18', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('18', '19', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('19', '20', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('20', '21', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('21', '22', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('22', '23', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('23', '24', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('24', '25', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('25', '26', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('26', '27', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('27', '28', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('28', '29', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('29', '30', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('30', '31', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('31', '32', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('32', '33', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('33', '34', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('34', '35', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('35', '36', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('36', '37', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('37', '38', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('38', '39', '1', '1', null, null, null, null, '2017-05-31 21:57:40');
INSERT INTO `StudentExamRecord` VALUES ('39', '40', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('40', '41', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('41', '42', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('42', '43', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('43', '44', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('44', '45', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('45', '46', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('46', '47', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('47', '48', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('48', '49', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('49', '50', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('50', '51', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('51', '52', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('52', '53', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('53', '54', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('54', '55', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('55', '56', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('56', '57', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('57', '58', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('58', '59', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('59', '60', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('60', '61', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('61', '62', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('62', '63', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('63', '64', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('64', '65', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('65', '66', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('66', '67', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('67', '68', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('68', '69', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('69', '70', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('70', '71', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('71', '72', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('72', '73', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('73', '74', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('74', '75', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('75', '76', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('76', '77', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('77', '78', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('78', '79', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('79', '80', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('80', '81', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('81', '82', '1', '2', null, null, null, null, '2017-05-31 21:59:07');
INSERT INTO `StudentExamRecord` VALUES ('82', '83', '1', '2', null, null, null, null, '2017-05-31 21:59:07');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `loginName` varchar(20) NOT NULL COMMENT '登录名称，学生的学号',
  `no` varchar(20) DEFAULT NULL COMMENT '短学号或者职工号',
  `type` tinyint(4) NOT NULL COMMENT '用户类型，0学生，1教师',
  `pwd` varchar(20) NOT NULL COMMENT '密码',
  `class_id` int(11) DEFAULT NULL COMMENT '用户所属班级的id',
  `createTime` datetime DEFAULT NULL COMMENT '创建日期时间',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123123', '信息A152301', '1', '123123', null, '2017-05-23 08:28:49', '张海');
INSERT INTO `user` VALUES ('2', '20150200614', '信息A152101', '0', '20150200614', '1', '2017-05-31 21:11:55', '彭超群');
INSERT INTO `user` VALUES ('3', '20150200616', '信息A152102', '0', '20150200616', '1', '2017-05-31 21:11:55', '李月星');
INSERT INTO `user` VALUES ('4', '20150200617', '信息A152103', '0', '20150200617', '1', '2017-05-31 21:11:55', '王浩');
INSERT INTO `user` VALUES ('5', '20150200727', '信息A152104', '0', '20150200727', '1', '2017-05-31 21:11:55', '徐繁韵');
INSERT INTO `user` VALUES ('6', '20150200730', '信息A152105', '0', '20150200730', '1', '2017-05-31 21:11:55', '王彩云');
INSERT INTO `user` VALUES ('7', '20150200854', '信息A152106', '0', '20150200854', '1', '2017-05-31 21:11:55', '杨迎方');
INSERT INTO `user` VALUES ('8', '20150200857', '信息A152107', '0', '20150200857', '1', '2017-05-31 21:11:55', '冯凡');
INSERT INTO `user` VALUES ('9', '20150201048', '信息A152108', '0', '20150201048', '1', '2017-05-31 21:11:55', '周隽如');
INSERT INTO `user` VALUES ('10', '20150201086', '信息A152109', '0', '20150201086', '1', '2017-05-31 21:11:55', '罗子文');
INSERT INTO `user` VALUES ('11', '20150201343', '信息A152110', '0', '20150201343', '1', '2017-05-31 21:11:55', '胡露');
INSERT INTO `user` VALUES ('12', '20150201811', '信息A152111', '0', '20150201811', '1', '2017-05-31 21:11:55', '廖玉霞');
INSERT INTO `user` VALUES ('13', '20150201871', '信息A152112', '0', '20150201871', '1', '2017-05-31 21:11:55', '俞雅玲');
INSERT INTO `user` VALUES ('14', '20150201916', '信息A152113', '0', '20150201916', '1', '2017-05-31 21:11:55', '申玉霞');
INSERT INTO `user` VALUES ('15', '20150201922', '信息A152114', '0', '20150201922', '1', '2017-05-31 21:11:55', '黄明兰');
INSERT INTO `user` VALUES ('16', '20150201929', '信息A152115', '0', '20150201929', '1', '2017-05-31 21:11:55', '傅粤华');
INSERT INTO `user` VALUES ('17', '20150201948', '信息A152116', '0', '20150201948', '1', '2017-05-31 21:11:55', '吴涵');
INSERT INTO `user` VALUES ('18', '20150202044', '信息A152117', '0', '20150202044', '1', '2017-05-31 21:11:55', '何斌');
INSERT INTO `user` VALUES ('19', '20150202055', '信息A152118', '0', '20150202055', '1', '2017-05-31 21:11:55', '黄燕');
INSERT INTO `user` VALUES ('20', '20150202327', '信息A152119', '0', '20150202327', '1', '2017-05-31 21:11:55', '汤福平');
INSERT INTO `user` VALUES ('21', '20150202443', '信息A152120', '0', '20150202443', '1', '2017-05-31 21:11:55', '张翔');
INSERT INTO `user` VALUES ('22', '20150202532', '信息A152121', '0', '20150202532', '1', '2017-05-31 21:11:55', '杜敏志');
INSERT INTO `user` VALUES ('23', '20150202706', '信息A152122', '0', '20150202706', '1', '2017-05-31 21:11:55', '朱书林');
INSERT INTO `user` VALUES ('24', '20150202828', '信息A152123', '0', '20150202828', '1', '2017-05-31 21:11:55', '李晓琳');
INSERT INTO `user` VALUES ('25', '20150202899', '信息A152124', '0', '20150202899', '1', '2017-05-31 21:11:55', '曾晓琴');
INSERT INTO `user` VALUES ('26', '20150203122', '信息A152125', '0', '20150203122', '1', '2017-05-31 21:11:55', '梁亿');
INSERT INTO `user` VALUES ('27', '20150203165', '信息A152126', '0', '20150203165', '1', '2017-05-31 21:11:55', '黎凡');
INSERT INTO `user` VALUES ('28', '20150203408', '信息A152127', '0', '20150203408', '1', '2017-05-31 21:11:55', '蔡雅丽');
INSERT INTO `user` VALUES ('29', '20150203426', '信息A152128', '0', '20150203426', '1', '2017-05-31 21:11:55', '叶紫微');
INSERT INTO `user` VALUES ('30', '20150203932', '信息A152129', '0', '20150203932', '1', '2017-05-31 21:11:55', '李振坤');
INSERT INTO `user` VALUES ('31', '20150204382', '信息A152130', '0', '20150204382', '1', '2017-05-31 21:11:55', '谢鸿婷');
INSERT INTO `user` VALUES ('32', '20150204383', '信息A152131', '0', '20150204383', '1', '2017-05-31 21:11:55', '马爱香');
INSERT INTO `user` VALUES ('33', '20150204384', '信息A152132', '0', '20150204384', '1', '2017-05-31 21:11:55', '康丽娜');
INSERT INTO `user` VALUES ('34', '20150204385', '信息A152133', '0', '20150204385', '1', '2017-05-31 21:11:55', '李旭亮');
INSERT INTO `user` VALUES ('35', '20150204388', '信息A152134', '0', '20150204388', '1', '2017-05-31 21:11:55', '张奇奇');
INSERT INTO `user` VALUES ('36', '20150204389', '信息A152135', '0', '20150204389', '1', '2017-05-31 21:11:55', '毛蕴珩');
INSERT INTO `user` VALUES ('37', '20150204459', '信息A152136', '0', '20150204459', '1', '2017-05-31 21:11:55', '褚文清');
INSERT INTO `user` VALUES ('38', '20150204665', '信息A152137', '0', '20150204665', '1', '2017-05-31 21:11:55', '赵美琦');
INSERT INTO `user` VALUES ('39', '20150204879', '信息A152138', '0', '20150204879', '1', '2017-05-31 21:11:55', '刘惠中');
INSERT INTO `user` VALUES ('40', '20150200615', '信息A152201', '0', '20150200615', '2', '2017-05-31 21:20:04', '施重阳');
INSERT INTO `user` VALUES ('41', '20150200712', '信息A152202', '0', '20150200712', '2', '2017-05-31 21:20:04', '张佳浩');
INSERT INTO `user` VALUES ('42', '20150200714', '信息A152203', '0', '20150200714', '2', '2017-05-31 21:20:04', '郭猛');
INSERT INTO `user` VALUES ('43', '20150200735', '信息A152204', '0', '20150200735', '2', '2017-05-31 21:20:04', '梁霄');
INSERT INTO `user` VALUES ('44', '20150200853', '信息A152205', '0', '20150200853', '2', '2017-05-31 21:20:04', '李兴懋');
INSERT INTO `user` VALUES ('45', '20150200855', '信息A152206', '0', '20150200855', '2', '2017-05-31 21:20:04', '贾立顺');
INSERT INTO `user` VALUES ('46', '20150200856', '信息A152207', '0', '20150200856', '2', '2017-05-31 21:20:04', '柴森');
INSERT INTO `user` VALUES ('47', '20150201064', '信息A152208', '0', '20150201064', '2', '2017-05-31 21:20:04', '章俊');
INSERT INTO `user` VALUES ('48', '20150201071', '信息A152209', '0', '20150201071', '2', '2017-05-31 21:20:04', '余悦');
INSERT INTO `user` VALUES ('49', '20150201386', '信息A152210', '0', '20150201386', '2', '2017-05-31 21:20:04', '曾雨琪');
INSERT INTO `user` VALUES ('50', '20150201697', '信息A152211', '0', '20150201697', '2', '2017-05-31 21:20:04', '肖常盛');
INSERT INTO `user` VALUES ('51', '20150201876', '信息A152212', '0', '20150201876', '2', '2017-05-31 21:20:04', '李婷');
INSERT INTO `user` VALUES ('52', '20150202002', '信息A152213', '0', '20150202002', '2', '2017-05-31 21:20:04', '袁香港');
INSERT INTO `user` VALUES ('53', '20150202004', '信息A152214', '0', '20150202004', '2', '2017-05-31 21:20:04', '李路斌');
INSERT INTO `user` VALUES ('54', '20150202070', '信息A152215', '0', '20150202070', '2', '2017-05-31 21:20:04', '杜志诚');
INSERT INTO `user` VALUES ('55', '20150202092', '信息A152216', '0', '20150202092', '2', '2017-05-31 21:20:04', '赖志强');
INSERT INTO `user` VALUES ('56', '20150202105', '信息A152217', '0', '20150202105', '2', '2017-05-31 21:20:04', '宋晓梅');
INSERT INTO `user` VALUES ('57', '20150202165', '信息A152218', '0', '20150202165', '2', '2017-05-31 21:20:04', '邓丽萍');
INSERT INTO `user` VALUES ('58', '20150202193', '信息A152219', '0', '20150202193', '2', '2017-05-31 21:20:04', '刘萃花');
INSERT INTO `user` VALUES ('59', '20150202365', '信息A152220', '0', '20150202365', '2', '2017-05-31 21:20:04', '刘兴');
INSERT INTO `user` VALUES ('60', '20150202399', '信息A152221', '0', '20150202399', '2', '2017-05-31 21:20:04', '梁琦');
INSERT INTO `user` VALUES ('61', '20150202401', '信息A152222', '0', '20150202401', '2', '2017-05-31 21:20:04', '谢继');
INSERT INTO `user` VALUES ('62', '20150202479', '信息A152223', '0', '20150202479', '2', '2017-05-31 21:20:04', '刘倩');
INSERT INTO `user` VALUES ('63', '20150202492', '信息A152224', '0', '20150202492', '2', '2017-05-31 21:20:04', '陈艺丹');
INSERT INTO `user` VALUES ('64', '20150202821', '信息A152225', '0', '20150202821', '2', '2017-05-31 21:20:04', '聂海霞');
INSERT INTO `user` VALUES ('65', '20150202968', '信息A152226', '0', '20150202968', '2', '2017-05-31 21:20:04', '余海燕');
INSERT INTO `user` VALUES ('66', '20150203019', '信息A152227', '0', '20150203019', '2', '2017-05-31 21:20:04', '金利娜');
INSERT INTO `user` VALUES ('67', '20150203060', '信息A152228', '0', '20150203060', '2', '2017-05-31 21:20:04', '金明亮');
INSERT INTO `user` VALUES ('68', '20150203215', '信息A152229', '0', '20150203215', '2', '2017-05-31 21:20:04', '赖淑敏');
INSERT INTO `user` VALUES ('69', '20150203309', '信息A152230', '0', '20150203309', '2', '2017-05-31 21:20:04', '尧建建');
INSERT INTO `user` VALUES ('70', '20150203318', '信息A152231', '0', '20150203318', '2', '2017-05-31 21:20:04', '甘婷');
INSERT INTO `user` VALUES ('71', '20150203446', '信息A152232', '0', '20150203446', '2', '2017-05-31 21:20:04', '占玉芬');
INSERT INTO `user` VALUES ('72', '20150203455', '信息A152233', '0', '20150203455', '2', '2017-05-31 21:20:04', '毛乐');
INSERT INTO `user` VALUES ('73', '20150203652', '信息A152235', '0', '20150203652', '2', '2017-05-31 21:20:04', '程领');
INSERT INTO `user` VALUES ('74', '20150203663', '信息A152236', '0', '20150203663', '2', '2017-05-31 21:20:04', '邵文泽');
INSERT INTO `user` VALUES ('75', '20150203678', '信息A152237', '0', '20150203678', '2', '2017-05-31 21:20:04', '夏宝锋');
INSERT INTO `user` VALUES ('76', '20150203744', '信息A152238', '0', '20150203744', '2', '2017-05-31 21:20:04', '刘玲');
INSERT INTO `user` VALUES ('77', '20150203930', '信息A152239', '0', '20150203930', '2', '2017-05-31 21:20:04', '陈强');
INSERT INTO `user` VALUES ('78', '20150204125', '信息A152240', '0', '20150204125', '2', '2017-05-31 21:20:04', '洪燕');
INSERT INTO `user` VALUES ('79', '20150204386', '信息A152241', '0', '20150204386', '2', '2017-05-31 21:20:04', '李安琪');
INSERT INTO `user` VALUES ('80', '20150204390', '信息A152242', '0', '20150204390', '2', '2017-05-31 21:20:04', '路博婷');
INSERT INTO `user` VALUES ('81', '20150204458', '信息A152243', '0', '20150204458', '2', '2017-05-31 21:20:04', '李菲');
INSERT INTO `user` VALUES ('82', '20150204667', '信息A152244', '0', '20150204667', '2', '2017-05-31 21:20:04', '胡东');
INSERT INTO `user` VALUES ('83', '20150203473', '信息A152234', '0', '20150203473', '2', '2017-05-31 21:11:55', '刘建新');
