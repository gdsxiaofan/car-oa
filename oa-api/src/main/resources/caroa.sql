/*
Navicat MySQL Data Transfer

Source Server         : gdscar
Source Server Version : 50537
Source Host           : 47.93.20.244:3306
Source Database       : caroa_db

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2017-10-29 23:37:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_arrange_info
-- ----------------------------
DROP TABLE IF EXISTS `t_arrange_info`;
CREATE TABLE `t_arrange_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `morning_start` varchar(20) NOT NULL DEFAULT '00:00' COMMENT '早班开始时间：时分',
  `morning_end` varchar(20) NOT NULL DEFAULT '00:00' COMMENT '早班结束时间：时分',
  `noon_start` varchar(20) NOT NULL DEFAULT '00:00' COMMENT '晚班开始时间：时分',
  `noon_end` varchar(20) NOT NULL DEFAULT '00:00' COMMENT '晚班结束时间：时分',
  `is_effective` tinyint(4) NOT NULL DEFAULT '2' COMMENT '是否启用：1,启用 2：停用',
  `delete_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否有效：1，删除状态',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(10) NOT NULL DEFAULT '0' COMMENT '创建人时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_arrange_info
-- ----------------------------
INSERT INTO `t_arrange_info` VALUES ('1', '09:41:00', '12:00:00', '09:41:00', '12:04:00', '2', '1', '2017-10-26 21:45:03', '2017-10-29 17:53:22', '0');
INSERT INTO `t_arrange_info` VALUES ('2', '09:41:00', '12:00:00', '09:41:00', '12:00:04', '2', '1', '2017-10-26 21:45:13', '2017-10-29 17:53:25', '0');
INSERT INTO `t_arrange_info` VALUES ('3', '09:41:00', '12:00:00', '09:41:00', '12:03:00', '1', '1', '2017-10-26 21:45:22', '2017-10-29 17:53:25', '0');
INSERT INTO `t_arrange_info` VALUES ('4', '06:00:00', '11:00:00', '13:00:00', '17:00:00', '2', '1', '2017-10-26 21:46:08', '0000-00-00 00:00:00', '0');

-- ----------------------------
-- Table structure for t_attachment_info
-- ----------------------------
DROP TABLE IF EXISTS `t_attachment_info`;
CREATE TABLE `t_attachment_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `biz_id` int(10) NOT NULL DEFAULT '0' COMMENT '业务id',
  `biz_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '业务类型：1：工单，2：工单明细，3：设备，4：检查项',
  `attachment_name` varchar(50) NOT NULL DEFAULT '' COMMENT '附件名称',
  `attachment_url` varchar(300) NOT NULL DEFAULT '' COMMENT '附件url',
  `attachment_key` varchar(50) NOT NULL DEFAULT '' COMMENT '附件key',
  `attachment_ext` varchar(10) NOT NULL DEFAULT '' COMMENT '附件后缀',
  `delete_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '有效状态：1，有效；0无效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_attachment_info
-- ----------------------------
INSERT INTO `t_attachment_info` VALUES ('1', '0', '0', 'file', '\\e1b38e59-b6ac-469e-9011-126a998f803b\\232\\14\\e1b38e59-b6ac-469e-9011-126a998f803b20171028204231501976.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('2', '0', '0', 'file', '\\73c9f967-7abf-4e93-822b-f97b12bc6f14\\173\\10\\73c9f967-7abf-4e93-822b-f97b12bc6f1420171028204255393743.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('3', '0', '0', 'file', '\\f413094c-c171-4686-ba2c-e43c26d0634c\\21\\1\\f413094c-c171-4686-ba2c-e43c26d0634c20171028204403419070.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('4', '0', '0', 'file', '\\5ce3f7bc-9db5-4066-a5af-991cd053eef6\\221\\13\\5ce3f7bc-9db5-4066-a5af-991cd053eef620171028210453285099.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('5', '0', '0', 'file', '\\ded656c2-2306-4af5-a816-34c0c156f75a\\181\\11\\ded656c2-2306-4af5-a816-34c0c156f75a20171028210621321771.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('6', '0', '0', 'file', '\\4d581984-ae47-4d48-9915-9521a9ebee6e\\40\\2\\4d581984-ae47-4d48-9915-9521a9ebee6e20171028210738667922.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('7', '0', '0', 'file', '\\06056ccb-d041-4b6b-aa5b-9463323a2edc\\176\\11\\06056ccb-d041-4b6b-aa5b-9463323a2edc20171028210930218578.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('8', '0', '0', 'file', '\\4f267dc3-546d-477f-9fb6-7a45efd90e18\\185\\11\\4f267dc3-546d-477f-9fb6-7a45efd90e1820171028211157690423.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('9', '0', '0', 'file', '\\c8fba2f7-fba7-4524-a56f-446706c02c54\\132\\8\\c8fba2f7-fba7-4524-a56f-446706c02c5420171028211339373491.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('10', '0', '0', 'file', '\\5e8cdda7-29fc-4dd4-96c1-5f25119c79ac\\59\\3\\5e8cdda7-29fc-4dd4-96c1-5f25119c79ac20171028211549914461.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('11', '0', '0', 'file', '\\a1631a48-7b44-480c-bccc-a84e838d25a8\\17\\1\\a1631a48-7b44-480c-bccc-a84e838d25a820171028211710456980.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('12', '0', '0', 'file', '\\131271b3-adde-4cd9-b141-44194f651e98\\207\\12\\131271b3-adde-4cd9-b141-44194f651e9820171028214459487401.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('13', '0', '0', 'file', '\\36bbb1e2-c6e1-4e78-a15e-b2b227b23d72\\18\\1\\36bbb1e2-c6e1-4e78-a15e-b2b227b23d7220171028214513558108.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('14', '0', '0', 'file', '\\9c42b2b8-979c-47d2-910a-cd31eda9105f\\20\\1\\9c42b2b8-979c-47d2-910a-cd31eda9105f20171028214518847657.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('15', '0', '0', 'file', '\\219674a4-e7d5-4982-9c61-1f39506f270e\\95\\5\\219674a4-e7d5-4982-9c61-1f39506f270e20171028215239689753.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('16', '0', '0', 'file', '\\f4dadca1-e2a1-4abf-9aab-096bf5626f0c\\128\\8\\f4dadca1-e2a1-4abf-9aab-096bf5626f0c20171028215658898385.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('17', '0', '0', 'file', '\\13f8f5d6-e4ae-43dc-b4a8-b6970a8b9881\\60\\3\\13f8f5d6-e4ae-43dc-b4a8-b6970a8b988120171028215706796496.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('18', '0', '0', 'file', '\\0f77bee1-032a-42fe-841f-fe1f5578f7fb\\218\\13\\0f77bee1-032a-42fe-841f-fe1f5578f7fb20171029115126825403.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('19', '0', '0', 'file', '\\b9bf7eb4-763f-4006-9c8a-725c164f236e\\93\\5\\b9bf7eb4-763f-4006-9c8a-725c164f236e20171029115129901186.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('20', '0', '0', 'file', '\\baa19f89-ebcf-4283-8a65-2a081bebce48\\116\\7\\baa19f89-ebcf-4283-8a65-2a081bebce4820171029115159589268.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('21', '0', '0', 'file', '\\7cb3f8ab-52c5-4dbf-ada9-24e38facec98\\77\\4\\7cb3f8ab-52c5-4dbf-ada9-24e38facec9820171029115202809836.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('22', '1', '5', 'file', '\\f6120791-9c86-49a3-81fd-3b684cedbf15\\166\\10\\f6120791-9c86-49a3-81fd-3b684cedbf1520171029120131009256.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('23', '1', '5', 'file', '\\82664e7a-12df-43a2-8dc5-485c86333112\\171\\10\\82664e7a-12df-43a2-8dc5-485c8633311220171029120133729623.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('24', '0', '0', 'file', '\\d9c77e49-2049-4beb-9d67-b0d3f5c977c7\\146\\9\\d9c77e49-2049-4beb-9d67-b0d3f5c977c720171029122616303355.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('25', '0', '0', 'file', '\\fd884978-e22e-45c6-ba58-97940d6af369\\202\\12\\fd884978-e22e-45c6-ba58-97940d6af36920171029122619268666.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('26', '1', '5', 'file', '\\1de8bdcc-be16-4d8e-867b-5e7f1998090c\\118\\7\\1de8bdcc-be16-4d8e-867b-5e7f1998090c20171029122742810347.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('27', '1', '5', 'file', '\\d8c0a5e0-34ac-446c-8faf-21a3c244368e\\175\\10\\d8c0a5e0-34ac-446c-8faf-21a3c244368e20171029122746461585.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('28', '1', '6', 'file', '\\88c4c60a-3f82-4f63-87b9-983057f89f7a\\99\\6\\88c4c60a-3f82-4f63-87b9-983057f89f7a20171029123358369264.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('29', '0', '0', 'file', '\\f241a4ea-9ee0-47df-997e-4afb24b679e5\\123\\7\\f241a4ea-9ee0-47df-997e-4afb24b679e520171029130839075498.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('30', '1', '6', 'file', '\\182ae509-aeec-4449-8589-133a22ea90b0\\14\\0\\182ae509-aeec-4449-8589-133a22ea90b020171029152349377654.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('31', '1', '5', 'file', '\\b994e96a-f986-4645-b055-b38992fcef36\\226\\14\\b994e96a-f986-4645-b055-b38992fcef3620171029155155770400.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('32', '1', '5', 'file', '\\7c1b6adc-3d72-44a3-93a0-77122c097352\\80\\5\\7c1b6adc-3d72-44a3-93a0-77122c09735220171029155158616311.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('33', '0', '0', 'file', '/22bdff60-ab60-4540-b864-5c38339bb87d/251/15/22bdff60-ab60-4540-b864-5c38339bb87d20171029175456460519.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('34', '0', '0', 'file', '/008e9a96-84f7-4772-936e-99040006608f/0/0/008e9a96-84f7-4772-936e-99040006608f20171029184519385942.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('35', '0', '0', 'file', '\\69a67667-cad2-4d48-8e18-b790187cddc6\\218\\13\\69a67667-cad2-4d48-8e18-b790187cddc620171029185551787299.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('36', '0', '0', 'file', '/8325a568-58e1-472a-8dc6-faf9fc50db8c/254/15/8325a568-58e1-472a-8dc6-faf9fc50db8c20171029192809326912.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('37', '1', '5', 'file', '\\adad01f2-611c-493d-81b5-ca8cf195380d\\68\\4\\adad01f2-611c-493d-81b5-ca8cf195380d20171029193732333655.jpg', '', 'jpg', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_attachment_info` VALUES ('38', '1', '6', 'file', '\\31add476-3695-4491-9800-cba0791443a6\\62\\3\\31add476-3695-4491-9800-cba0791443a620171029194108979138.png', '', 'png', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for t_bacth_test
-- ----------------------------
DROP TABLE IF EXISTS `t_bacth_test`;
CREATE TABLE `t_bacth_test` (
  `id` bigint(20) NOT NULL,
  `device_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bacth_test
-- ----------------------------

-- ----------------------------
-- Table structure for t_check_info
-- ----------------------------
DROP TABLE IF EXISTS `t_check_info`;
CREATE TABLE `t_check_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `service_name` varchar(50) NOT NULL DEFAULT '' COMMENT '服务名称',
  `device_id` int(10) NOT NULL DEFAULT '0' COMMENT '设备id',
  `device_name` varchar(20) NOT NULL DEFAULT '' COMMENT '设备名称',
  `system_id` int(10) NOT NULL DEFAULT '0' COMMENT '系统id',
  `system_name` varchar(50) NOT NULL DEFAULT '' COMMENT '系统名称',
  `check_cycle` int(10) NOT NULL DEFAULT '0' COMMENT '巡检周期',
  `check_time` varchar(20) NOT NULL DEFAULT '9:00' COMMENT '检查时间，格式为：hh:ss',
  `check_point` varchar(50) NOT NULL DEFAULT '' COMMENT '检查点',
  `check_comment` varchar(50) NOT NULL DEFAULT '' COMMENT '检查/维护内容',
  `set_value` varchar(50) NOT NULL DEFAULT '' COMMENT '设定值',
  `shifts_no` tinyint(4) NOT NULL DEFAULT '1' COMMENT '班次：1，早班；2：中班',
  `create_user_id` int(10) NOT NULL DEFAULT '0' COMMENT '创建人id',
  `service_describe` varchar(50) NOT NULL DEFAULT '' COMMENT '任务描述',
  `first_check_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '首次巡检日期',
  `last_check_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后一次巡检的日期',
  `delete_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '有效状态：0：无效，1：有效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `next_check_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '下一次巡检的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_check_info
-- ----------------------------
INSERT INTO `t_check_info` VALUES ('1', '', '3', '', '0', '', '0', '9', 'asd4444', '2', '3', '2', '0', '', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0', '2017-10-16 21:21:35', '2017-10-28 14:57:00', '2017-10-28 14:56:58');
INSERT INTO `t_check_info` VALUES ('2', '', '3', '', '0', '', '0', '9', 'adsa', 'asdad', 'asdad', '2', '0', '', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '1', '2017-10-16 21:22:20', '2017-10-28 14:57:06', '2017-10-28 14:57:02');
INSERT INTO `t_check_info` VALUES ('3', '', '1', '', '0', '', '0', '9', '1', '2', '3', '1', '0', '', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0', '2017-10-17 19:58:15', '2017-10-28 14:57:11', '2017-10-28 14:57:08');
INSERT INTO `t_check_info` VALUES ('4', '', '1', '', '0', '', '0', '9:00', '', '', '', '1', '0', '', '2017-10-28 00:00:00', '2017-10-28 00:00:00', '0', '2017-10-28 18:42:58', '2017-10-28 19:13:13', '2017-10-28 08:00:00');
INSERT INTO `t_check_info` VALUES ('5', '', '1', '', '0', '', '30', '9:00', '', '', '', '1', '0', '', '2017-10-28 00:00:00', '2017-10-28 00:00:00', '0', '2017-10-28 18:49:44', '2017-10-28 18:58:51', '2017-10-28 08:00:00');
INSERT INTO `t_check_info` VALUES ('6', '', '1', '', '0', '', '30', '9:00', '阿达的', 'asdad', 'asdad', '1', '0', 'asdad', '2017-10-28 00:00:00', '2017-10-28 00:00:00', '0', '2017-10-28 18:55:46', '2017-10-28 18:58:54', '2017-10-28 08:00:00');
INSERT INTO `t_check_info` VALUES ('7', '', '1', '', '0', '', '30', '9:00', 'aa', 'aa', '', '1', '0', '', '2017-10-28 00:00:00', '2017-10-28 00:00:00', '0', '2017-10-28 18:56:36', '2017-10-28 19:13:15', '2017-10-28 08:00:00');
INSERT INTO `t_check_info` VALUES ('8', '', '1', '', '0', '', '30', '9:00', 'a', '', '', '1', '0', '', '2017-10-28 00:00:00', '2017-10-28 00:00:00', '0', '2017-10-28 18:56:57', '2017-10-28 19:13:17', '2017-10-28 08:00:00');
INSERT INTO `t_check_info` VALUES ('9', '', '1', '', '0', '', '30', '9:00', 'aaa', '', '', '1', '0', 'aaa', '2017-10-28 00:00:00', '2017-10-28 00:00:00', '0', '2017-10-28 18:57:37', '2017-10-28 19:13:18', '2017-10-28 08:00:00');
INSERT INTO `t_check_info` VALUES ('10', 'aaaa', '1', '阿斯达撒大声地', '0', '', '30', '9:00', '', '', '', '1', '0', 'adsasdasdasd\nasdasdads', '2017-10-28 00:00:00', '2017-10-28 00:00:00', '1', '2017-10-28 19:09:10', '2017-10-28 19:37:54', '2017-10-28 08:00:00');
INSERT INTO `t_check_info` VALUES ('11', '', '1', '', '0', '', '30', '9:00', '', '', '', '1', '0', '', '2017-10-28 00:00:00', '2017-10-28 00:00:00', '1', '2017-10-28 19:11:20', '0000-00-00 00:00:00', '2017-10-28 08:00:00');
INSERT INTO `t_check_info` VALUES ('12', '', '1', '', '0', '', '30', '9:00', 'aasdsad', 'asdasd', 'asd', '1', '0', 'asd', '2017-10-28 00:00:00', '2017-10-28 00:00:00', '1', '2017-10-28 19:12:09', '0000-00-00 00:00:00', '2017-10-28 08:00:00');
INSERT INTO `t_check_info` VALUES ('13', '', '1', '阿斯达撒大声地', '0', '', '30', '9:00', 'asd', '', '', '1', '0', 'asd', '2017-10-28 00:00:00', '2017-10-28 00:00:00', '1', '2017-10-28 19:13:25', '0000-00-00 00:00:00', '2017-10-28 08:00:00');
INSERT INTO `t_check_info` VALUES ('14', '机油', '5', '刹车液BK随行单元1/2/3	', '0', '', '1', '9:00', '', '', '', '1', '0', '1.查询机油是否足够\n2.检查机油的量\n3.测试发动机是否正常', '2017-10-29 00:00:00', '2017-10-29 00:00:00', '1', '2017-10-29 18:59:25', '0000-00-00 00:00:00', '2017-10-29 08:00:00');
INSERT INTO `t_check_info` VALUES ('15', '是否可以生成对应工单', '5', '刹车液BK随行单元1/2/3	', '0', '', '1', '9:00', '', '', '', '1', '0', '属性二', '2017-10-29 00:00:00', '2017-10-29 00:00:00', '1', '2017-10-29 19:18:57', '0000-00-00 00:00:00', '2017-10-29 08:00:00');
INSERT INTO `t_check_info` VALUES ('16', '测试2', '5', '刹车液BK随行单元1/2/3	', '0', '', '1', '9:00', '', '', '', '1', '0', '测试二的任务描述', '2017-10-29 00:00:00', '2017-10-29 00:00:00', '0', '2017-10-29 19:22:54', '2017-10-29 19:34:56', '2017-10-29 08:00:00');
INSERT INTO `t_check_info` VALUES ('17', '测试三', '5', '刹车液BK随行单元1/2/3	', '0', '', '30', '9:00', '', '', '', '1', '0', '服务', '2017-10-29 00:00:00', '2017-10-29 00:00:00', '0', '2017-10-29 19:25:53', '2017-10-29 19:34:53', '2017-10-29 08:00:00');
INSERT INTO `t_check_info` VALUES ('18', '新增服务', '5', '刹车液BK随行单元1/2/3	', '0', '', '1', '9:00', '', '', '', '1', '0', '属性', '2017-10-29 00:00:00', '2017-10-29 00:00:00', '0', '2017-10-29 19:30:06', '2017-10-29 19:34:51', '2017-10-29 08:00:00');
INSERT INTO `t_check_info` VALUES ('19', '工单1', '5', '刹车液BK随行单元1/2/3	', '0', '', '1', '9:00', '', '', '', '1', '0', '师傅的说法是', '2017-10-29 00:00:00', '2017-10-29 00:00:00', '0', '2017-10-29 19:31:31', '2017-10-29 19:34:48', '2017-10-29 08:00:00');
INSERT INTO `t_check_info` VALUES ('20', '生成工单2', '5', '刹车液BK随行单元1/2/3	', '0', '', '30', '9:00', '', '', '', '1', '0', '谁发的撒地方撒地方', '2017-10-29 00:00:00', '2017-10-29 00:00:00', '0', '2017-10-29 19:32:56', '2017-10-29 19:34:46', '2017-10-29 08:00:00');
INSERT INTO `t_check_info` VALUES ('21', '工单1212', '5', '刹车液BK随行单元1/2/3	', '0', '', '1', '9:00', '', '', '', '1', '0', '士大夫撒发斯蒂芬', '2017-10-29 00:00:00', '2017-10-29 00:00:00', '1', '2017-10-29 19:35:06', '0000-00-00 00:00:00', '2017-10-29 08:00:00');

-- ----------------------------
-- Table structure for t_device_checkInfo
-- ----------------------------
DROP TABLE IF EXISTS `t_device_checkInfo`;
CREATE TABLE `t_device_checkInfo` (
  `id` int(11) NOT NULL,
  `device_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_device_checkInfo
-- ----------------------------

-- ----------------------------
-- Table structure for t_device_info
-- ----------------------------
DROP TABLE IF EXISTS `t_device_info`;
CREATE TABLE `t_device_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `routing_days` int(10) NOT NULL DEFAULT '0' COMMENT '巡检天数',
  `device_name` varchar(50) NOT NULL DEFAULT '' COMMENT '设备名称',
  `area` varchar(50) NOT NULL DEFAULT '' COMMENT '区域',
  `location` varchar(50) NOT NULL DEFAULT '' COMMENT '位置',
  `from_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开始时间',
  `to_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  `delete_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否有效：1：是，0：无效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_use_id` int(10) NOT NULL DEFAULT '0' COMMENT '创建人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_device_info
-- ----------------------------
INSERT INTO `t_device_info` VALUES ('1', '0', '阿斯达撒大声地', '111', '2', '2017-10-14 00:00:00', '2017-10-14 00:00:00', '0', '0000-00-00 00:00:00', '2017-10-29 21:03:19', '0');
INSERT INTO `t_device_info` VALUES ('2', '1', 'asdads', 'asdad', 'asdsadad', '2017-10-09 11:00:00', '2017-10-15 11:00:00', '1', '2017-10-16 06:21:12', '0000-00-00 00:00:00', '0');
INSERT INTO `t_device_info` VALUES ('3', '1', '123', '111', '111', '2017-10-15 11:00:00', '2017-10-15 11:00:00', '0', '2017-10-16 06:22:11', '2017-10-29 21:05:32', '0');
INSERT INTO `t_device_info` VALUES ('4', '1', 'asd', 'asd', 'asd', '2017-10-15 11:00:00', '2017-10-15 11:00:00', '0', '2017-10-16 06:23:21', '2017-10-16 19:25:34', '0');
INSERT INTO `t_device_info` VALUES ('5', '1', '刹车液BK随行单元1/2/3	', 'Chassis2', '129L/R	', '2017-10-16 00:00:00', '2018-10-16 00:00:00', '0', '2017-10-16 21:05:59', '2017-10-29 21:05:30', '0');
INSERT INTO `t_device_info` VALUES ('6', '444', '11', '11', '11', '2017-10-17 00:00:00', '2017-10-17 00:00:00', '0', '2017-10-17 19:58:34', '2017-10-17 19:58:37', '0');

-- ----------------------------
-- Table structure for t_employee
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `employee_name` varchar(50) NOT NULL DEFAULT '' COMMENT '员工姓名',
  `employee_password` varchar(50) NOT NULL DEFAULT '' COMMENT '员工密码',
  `employee_mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '员工手机号码',
  `employee_no` varchar(50) NOT NULL DEFAULT '' COMMENT '员工号',
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色id',
  `induction_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '入职时间',
  `employee_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '员工状态：1：在职，2休假，3：离职',
  `shifts_no` tinyint(4) NOT NULL DEFAULT '0' COMMENT '排班设置:1早班，2：晚班 0：无排班设置',
  `create_Time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_active` tinyint(1) DEFAULT '1' COMMENT '是否可用0停用1可用',
  `delete_Status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '删除状态：1：正常状态，0：删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_no_password` (`employee_password`,`employee_no`) USING BTREE COMMENT '工号，密码唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8 COMMENT='员工信息';

-- ----------------------------
-- Records of t_employee
-- ----------------------------
INSERT INTO `t_employee` VALUES ('64', '王林', '2d8eae191f17ca6b97cf22ea258040bd', '13128949887', 'admin', '1', '2017-08-25 23:02:01', '1', '1', '2017-08-25 23:02:06', '2017-10-28 22:38:24', '1', '1');
INSERT INTO `t_employee` VALUES ('65', '123', '2b5ceb5f4f4933456082030a2787d67f', '123', '123', '1', '0000-00-00 00:00:00', '1', '2', '0000-00-00 00:00:00', '2017-10-29 16:20:14', '1', '0');
INSERT INTO `t_employee` VALUES ('66', '', 'c5ff0d7e432dfcc5f5f421f39cc8790f', '', '', '0', '0000-00-00 00:00:00', '1', '0', '0000-00-00 00:00:00', '2017-09-02 19:18:26', '1', '0');
INSERT INTO `t_employee` VALUES ('67', 'asdasd', '74055c3dc5b3bc9d8928a2702276f7e1', '123123', '123123', '1', '0000-00-00 00:00:00', '1', '0', '0000-00-00 00:00:00', '2017-10-29 16:20:16', '1', '0');
INSERT INTO `t_employee` VALUES ('68', '1', 'c781b06dc08c1f7fe542280121816d77', '1', '111', '9', '0000-00-00 00:00:00', '1', '0', '0000-00-00 00:00:00', '2017-09-17 13:22:58', '1', '0');
INSERT INTO `t_employee` VALUES ('71', 'test', 'c781b06dc08c1f7fe542280121816d77', '1111', '1111', '17', '0000-00-00 00:00:00', '1', '0', '0000-00-00 00:00:00', '2017-10-29 16:20:19', '1', '0');
INSERT INTO `t_employee` VALUES ('72', '张三', '2d8eae191f17ca6b97cf22ea258040bd', '13189897890', 'S001', '1', '0000-00-00 00:00:00', '1', '0', '0000-00-00 00:00:00', '2017-10-29 21:04:26', '1', '1');

-- ----------------------------
-- Table structure for t_ledger_info
-- ----------------------------
DROP TABLE IF EXISTS `t_ledger_info`;
CREATE TABLE `t_ledger_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `device_No` varchar(50) NOT NULL DEFAULT '' COMMENT '设备编号',
  `create_User` varchar(50) NOT NULL DEFAULT '' COMMENT '录入人员',
  `create_Time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_Status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '删除状态：1：正常状态，0：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 COMMENT='台账信息';

-- ----------------------------
-- Records of t_ledger_info
-- ----------------------------
INSERT INTO `t_ledger_info` VALUES ('1', 'jasdlfjaldfja', '张三', '2017-08-05 00:00:00', '0000-00-00 00:00:00', '1');
INSERT INTO `t_ledger_info` VALUES ('2', 'b729e654-9c6b-453a-b375-cf911c320b46', 'β他爹', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '1');
INSERT INTO `t_ledger_info` VALUES ('3', '631c791d-baf1-4c87-b6ce-fdee71357e99', 'β他爹', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '1');
INSERT INTO `t_ledger_info` VALUES ('4', '2e141c5b-99db-4d12-a8ed-013079213ae0', 'β他爹', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '1');
INSERT INTO `t_ledger_info` VALUES ('5', '16704076-e60f-470d-8fa3-b1bf88ae93b2', 'β他爹', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '1');
INSERT INTO `t_ledger_info` VALUES ('6', '84e2422e-f06a-491a-9854-7513123ca237', 'β他爹', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '1');
INSERT INTO `t_ledger_info` VALUES ('7', '17d9a08f-837b-4b3c-a97d-f387ed168579', 'β他爹', '2017-08-05 12:20:55', '0000-00-00 00:00:00', '1');
INSERT INTO `t_ledger_info` VALUES ('8', '78864e9e-a078-441b-938b-bcacccfe475b', 'β他爹', '2017-08-05 12:29:27', '0000-00-00 00:00:00', '1');
INSERT INTO `t_ledger_info` VALUES ('9', 'a49da547-f8ee-4822-bc19-181ed48f1550', 'β他爹', '2017-08-05 12:30:23', '0000-00-00 00:00:00', '1');
INSERT INTO `t_ledger_info` VALUES ('10', '1bccbf2c-48c7-48c6-b020-4c67c0c89e3d', 'β他爹', '2017-08-05 12:57:32', '0000-00-00 00:00:00', '1');
INSERT INTO `t_ledger_info` VALUES ('11', 'adfasd', '张三', '2017-08-05 13:05:22', '0000-00-00 00:00:00', '1');
INSERT INTO `t_ledger_info` VALUES ('12', 'adfasd', '张三', '2017-08-05 13:15:04', '0000-00-00 00:00:00', '1');
INSERT INTO `t_ledger_info` VALUES ('13', '484c86c1-6fc9-4e94-a987-6c5ec6e5f11e', 'β他爹', '2017-08-05 21:57:35', '0000-00-00 00:00:00', '1');
INSERT INTO `t_ledger_info` VALUES ('14', 'dc0a278b-57a6-40ad-a7b1-0b346d3fc8b5', 'β他爹', '2017-08-05 22:01:42', '0000-00-00 00:00:00', '1');
INSERT INTO `t_ledger_info` VALUES ('15', '设备编号', '小凡', '2017-08-05 22:11:25', '0000-00-00 00:00:00', '1');
INSERT INTO `t_ledger_info` VALUES ('16', '设备编号1', '小凡', '2017-08-05 22:17:37', '0000-00-00 00:00:00', '1');
INSERT INTO `t_ledger_info` VALUES ('17', 'asdasd', 'bean', '2017-08-05 22:27:31', '0000-00-00 00:00:00', '1');
INSERT INTO `t_ledger_info` VALUES ('18', 'asdasd', 'bean', '2017-08-05 22:27:56', '0000-00-00 00:00:00', '1');
INSERT INTO `t_ledger_info` VALUES ('19', '49f37df6-dd5f-4059-97ad-e75e4d4d4467', 'β他爹', '2017-08-05 22:39:56', '2017-08-06 11:39:06', '1');
INSERT INTO `t_ledger_info` VALUES ('20', '设备编号2', '小凡', '2017-08-05 22:40:43', '2017-08-06 11:40:44', '1');
INSERT INTO `t_ledger_info` VALUES ('21', '设备编号3', 'stabber', '2017-08-06 11:40:43', '2017-08-06 11:41:46', '1');
INSERT INTO `t_ledger_info` VALUES ('22', 'b16e2254-c0fc-4af2-b410-4b59fcb369ed', 'β他爹', '2017-08-05 22:45:23', '2017-08-06 11:45:23', '1');
INSERT INTO `t_ledger_info` VALUES ('23', '04920cbb-de0b-485f-aa3d-1b24692b289f', 'β他爹', '2017-08-05 22:47:39', '2017-08-06 11:47:40', '1');
INSERT INTO `t_ledger_info` VALUES ('24', 'bf8bb835-9a2d-40c5-9a9c-2857b3ea0b1b', 'β他爹', '2017-08-05 23:06:58', '2017-08-06 12:06:07', '1');
INSERT INTO `t_ledger_info` VALUES ('25', 'asdasd', 'bean', '2017-08-05 23:12:05', '2017-08-06 12:11:15', '1');
INSERT INTO `t_ledger_info` VALUES ('26', '设备编号2', '小凡', '2017-08-05 23:37:18', '2017-08-06 12:37:19', '1');
INSERT INTO `t_ledger_info` VALUES ('27', '50e484f5-8386-4507-9177-3db26ba2263d', 'β他爹', '2017-08-06 00:06:09', '2017-08-06 13:06:10', '1');
INSERT INTO `t_ledger_info` VALUES ('28', '设备编号4', '小凡', '2017-08-06 00:07:13', '2017-08-06 13:07:14', '1');
INSERT INTO `t_ledger_info` VALUES ('29', '设备编号2', '小凡', '2017-08-06 00:11:36', '2017-08-06 13:11:36', '1');
INSERT INTO `t_ledger_info` VALUES ('30', '设备编号2', '小凡', '2017-08-06 00:11:39', '2017-08-06 13:11:39', '1');
INSERT INTO `t_ledger_info` VALUES ('31', '设备编号2', '小凡', '2017-08-06 01:50:49', '2017-08-06 14:50:50', '1');
INSERT INTO `t_ledger_info` VALUES ('32', '设备编号2', '小凡', '2017-08-06 01:51:06', '2017-08-06 14:51:06', '1');
INSERT INTO `t_ledger_info` VALUES ('33', '设备编号2', '小凡', '2017-08-06 01:51:10', '2017-08-06 14:51:10', '1');
INSERT INTO `t_ledger_info` VALUES ('34', '设备编号2', '小凡', '2017-08-06 01:57:02', '2017-08-06 14:57:02', '1');
INSERT INTO `t_ledger_info` VALUES ('35', '设备编号2', '小凡', '2017-08-06 02:04:28', '2017-08-06 15:04:29', '1');
INSERT INTO `t_ledger_info` VALUES ('36', 'asdasd', 'bean', '2017-08-06 04:29:03', '2017-08-06 17:28:12', '1');
INSERT INTO `t_ledger_info` VALUES ('39', '设备编号2', '小凡', '2017-08-06 06:09:55', '2017-08-06 19:11:11', '1');
INSERT INTO `t_ledger_info` VALUES ('40', '设备编号2', '小凡', '2017-08-06 06:13:50', '2017-08-06 19:15:05', '1');
INSERT INTO `t_ledger_info` VALUES ('41', '设备编号2', '小凡', '2017-08-06 06:13:50', '2017-08-06 19:15:05', '1');
INSERT INTO `t_ledger_info` VALUES ('42', '设备编号2', '小凡', '2017-08-06 06:13:50', '2017-08-06 19:15:05', '1');
INSERT INTO `t_ledger_info` VALUES ('43', '设备编号2', '小凡', '2017-08-06 06:16:26', '2017-08-06 19:17:43', '1');
INSERT INTO `t_ledger_info` VALUES ('44', '设备编号2', '小凡', '2017-08-06 06:18:31', '2017-08-06 19:19:47', '1');
INSERT INTO `t_ledger_info` VALUES ('45', '设备编号2', '小凡', '2017-08-06 06:18:33', '2017-08-06 19:19:49', '1');
INSERT INTO `t_ledger_info` VALUES ('46', '', '', '2017-08-06 07:01:12', '2017-08-06 20:01:12', '1');
INSERT INTO `t_ledger_info` VALUES ('47', '', '', '2017-08-06 07:02:37', '2017-08-06 20:02:37', '1');

-- ----------------------------
-- Table structure for t_memu
-- ----------------------------
DROP TABLE IF EXISTS `t_memu`;
CREATE TABLE `t_memu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `href` varchar(255) NOT NULL DEFAULT '' COMMENT '菜单url',
  `icon` varchar(50) NOT NULL DEFAULT '' COMMENT '待定',
  `order_top` tinyint(4) NOT NULL DEFAULT '1' COMMENT '菜单层级：1最高',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父菜单id',
  `delete_status` tinyint(4) NOT NULL DEFAULT '1',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_memu
-- ----------------------------
INSERT INTO `t_memu` VALUES ('106', '权限管理', '', '', '1', '0', '1', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_memu` VALUES ('107', '工单管理', '', '', '1', '0', '1', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_memu` VALUES ('108', '员工管理', '', '', '1', '0', '1', '0000-00-00 00:00:00', '2017-10-26 15:01:33');
INSERT INTO `t_memu` VALUES ('109', '设备管理', '', '', '1', '0', '1', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_memu` VALUES ('110', '角色管理', '/role', '', '2', '106', '1', '0000-00-00 00:00:00', '2017-10-26 15:07:53');
INSERT INTO `t_memu` VALUES ('111', '排班管理', '/plan', '', '2', '106', '1', '0000-00-00 00:00:00', '2017-10-23 15:10:39');
INSERT INTO `t_memu` VALUES ('112', '用户管理', '/userRole', '', '2', '108', '1', '0000-00-00 00:00:00', '2017-10-26 15:08:00');
INSERT INTO `t_memu` VALUES ('113', '巡检工单', '/lookOrder', '', '2', '107', '1', '0000-00-00 00:00:00', '2017-10-02 11:05:55');
INSERT INTO `t_memu` VALUES ('114', '维修工单', '/fixOrder', '', '2', '107', '1', '0000-00-00 00:00:00', '2017-10-02 11:06:22');
INSERT INTO `t_memu` VALUES ('115', '审核工单', '/verifyOrder', '', '2', '107', '1', '0000-00-00 00:00:00', '2017-10-02 11:07:03');
INSERT INTO `t_memu` VALUES ('116', '配置工单', '/configOrder', '', '2', '107', '0', '0000-00-00 00:00:00', '2017-10-29 22:58:12');
INSERT INTO `t_memu` VALUES ('117', '所有工单', '/allOrder', '', '2', '107', '1', '0000-00-00 00:00:00', '2017-09-28 19:44:50');
INSERT INTO `t_memu` VALUES ('118', '员工信息管理', '/employeeInfo', '', '2', '108', '0', '0000-00-00 00:00:00', '2017-10-26 15:08:25');
INSERT INTO `t_memu` VALUES ('119', '新增员工', '/employeeAdd', '', '2', '108', '0', '0000-00-00 00:00:00', '2017-10-26 15:08:24');
INSERT INTO `t_memu` VALUES ('120', '设备信息管理', '/deviceInfo', '', '2', '109', '1', '0000-00-00 00:00:00', '2017-09-15 16:54:59');
INSERT INTO `t_memu` VALUES ('121', '服务管理', '/service', '', '2', '109', '0', '0000-00-00 00:00:00', '2017-10-11 20:57:19');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色id',
  `memu_id` int(11) NOT NULL DEFAULT '0' COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('68', '5', '112');
INSERT INTO `t_permission` VALUES ('69', '5', '106');
INSERT INTO `t_permission` VALUES ('70', '8', '112');
INSERT INTO `t_permission` VALUES ('71', '8', '110');
INSERT INTO `t_permission` VALUES ('72', '8', '114');
INSERT INTO `t_permission` VALUES ('73', '8', '115');
INSERT INTO `t_permission` VALUES ('74', '8', '106');
INSERT INTO `t_permission` VALUES ('75', '8', '107');
INSERT INTO `t_permission` VALUES ('76', '9', '110');
INSERT INTO `t_permission` VALUES ('77', '9', '112');
INSERT INTO `t_permission` VALUES ('78', '9', '106');
INSERT INTO `t_permission` VALUES ('81', '17', '107');
INSERT INTO `t_permission` VALUES ('82', '17', '117');
INSERT INTO `t_permission` VALUES ('83', '1', '106');
INSERT INTO `t_permission` VALUES ('84', '1', '107');
INSERT INTO `t_permission` VALUES ('85', '1', '108');
INSERT INTO `t_permission` VALUES ('86', '1', '109');
INSERT INTO `t_permission` VALUES ('87', '1', '110');
INSERT INTO `t_permission` VALUES ('88', '1', '112');
INSERT INTO `t_permission` VALUES ('89', '1', '113');
INSERT INTO `t_permission` VALUES ('90', '1', '114');
INSERT INTO `t_permission` VALUES ('91', '1', '115');
INSERT INTO `t_permission` VALUES ('92', '1', '116');
INSERT INTO `t_permission` VALUES ('93', '1', '117');
INSERT INTO `t_permission` VALUES ('94', '1', '118');
INSERT INTO `t_permission` VALUES ('95', '1', '119');
INSERT INTO `t_permission` VALUES ('96', '1', '120');
INSERT INTO `t_permission` VALUES ('97', '1', '121');
INSERT INTO `t_permission` VALUES ('98', '1', '111');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL DEFAULT '' COMMENT '角色名称',
  `parent_role_id` int(10) NOT NULL DEFAULT '0' COMMENT '父节点权限id',
  `role_code` varchar(50) NOT NULL DEFAULT '' COMMENT '角色code',
  `delete_staus` tinyint(4) NOT NULL DEFAULT '1' COMMENT '有效状态：1：有效 0：无效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '系统管理员', '0', 'admin', '1', '0000-00-00 00:00:00', '2017-08-31 19:55:29');
INSERT INTO `t_role` VALUES ('2', 'asdasd', '0', '', '0', '0000-00-00 00:00:00', '2017-08-31 20:06:44');
INSERT INTO `t_role` VALUES ('5', 'asdadsa', '0', '', '0', '0000-00-00 00:00:00', '2017-08-31 20:06:46');
INSERT INTO `t_role` VALUES ('8', 'asdadas', '0', '', '1', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_role` VALUES ('9', '1312', '0', '', '0', '0000-00-00 00:00:00', '2017-10-17 20:00:33');
INSERT INTO `t_role` VALUES ('15', 'asdada', '0', '', '1', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `t_role` VALUES ('16', 'dads', '0', '', '0', '0000-00-00 00:00:00', '2017-10-29 21:11:35');
INSERT INTO `t_role` VALUES ('17', 'test1', '0', '', '0', '0000-00-00 00:00:00', '2017-10-25 21:54:02');

-- ----------------------------
-- Table structure for t_service_info
-- ----------------------------
DROP TABLE IF EXISTS `t_service_info`;
CREATE TABLE `t_service_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `service_name` varchar(50) NOT NULL DEFAULT '' COMMENT '服务名称',
  `device_id` int(10) NOT NULL DEFAULT '0' COMMENT '关联的设备id',
  `service_description` text NOT NULL COMMENT '任务描述',
  `system_id` varchar(50) NOT NULL DEFAULT '' COMMENT '属性一',
  `system_name` varchar(50) NOT NULL DEFAULT '' COMMENT '属性二',
  `property3` varchar(50) NOT NULL DEFAULT '' COMMENT '属性三',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `property4` varchar(50) NOT NULL DEFAULT '' COMMENT '属性四',
  `comment` varchar(500) NOT NULL DEFAULT '' COMMENT '任务描述',
  `check_time` tinyint(4) NOT NULL DEFAULT '1' COMMENT '检查时间：1：早班，2：中班',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_service_info
-- ----------------------------
INSERT INTO `t_service_info` VALUES ('2', '修改过后的测试1', '1', '备注', '修改过后的属性1', '属性2', '属性3', '0000-00-00 00:00:00', '2017-10-12 13:23:01', '属性4', '', '1');

-- ----------------------------
-- Table structure for t_tpm_bill
-- ----------------------------
DROP TABLE IF EXISTS `t_tpm_bill`;
CREATE TABLE `t_tpm_bill` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `check_id` int(10) NOT NULL DEFAULT '0' COMMENT '价差项id',
  `tpm_name` varchar(100) NOT NULL DEFAULT '' COMMENT '工单名称',
  `tpm_no` varchar(50) NOT NULL DEFAULT '' COMMENT '工单号',
  `device_id` int(10) NOT NULL DEFAULT '0' COMMENT '设备id',
  `check_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '巡检时间',
  `device_name` varchar(50) NOT NULL DEFAULT '' COMMENT '设备名称',
  `device_location` varchar(50) NOT NULL DEFAULT '' COMMENT '设备位置',
  `device_area` varchar(50) NOT NULL DEFAULT '' COMMENT '设备区域',
  `finish_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '工单完成时间',
  `audit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '工单审核时间',
  `reject_reason` varchar(50) NOT NULL DEFAULT '' COMMENT '审核驳回原因',
  `tpm_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '工单状态：1，待处理，2：待审核，3：故障维修，4：修复完成，5处理完成',
  `tpm_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '工单类型：1：巡检工单，2：维修工单',
  `shifts_no` tinyint(4) NOT NULL DEFAULT '1' COMMENT '班次：1，早班；2，中班',
  `last_tpm_id` int(10) NOT NULL DEFAULT '0' COMMENT '发起维修的巡检工单id',
  `service_describe` varchar(500) NOT NULL DEFAULT '' COMMENT '服务描述',
  `delete_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '有效状态：0，无效；1：有效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '工单创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tpm_bill
-- ----------------------------
INSERT INTO `t_tpm_bill` VALUES ('1', '0', 'ASDA', '12313', '0', '2017-10-29 11:12:55', 'aaa', '', '', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '', '5', '1', '1', '0', '描述', '1', '0000-00-00 00:00:00', '2017-10-29 19:41:41');
INSERT INTO `t_tpm_bill` VALUES ('2', '0', '工单1212', '', '5', '2017-10-29 08:00:00', '刹车液BK随行单元1/2/3	', '', '', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '', '5', '1', '1', '0', '士大夫撒发斯蒂芬', '1', '2017-10-29 19:35:06', '2017-10-29 19:37:11');

-- ----------------------------
-- Table structure for t_tpm_bill_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_tpm_bill_detail`;
CREATE TABLE `t_tpm_bill_detail` (
  `id` int(10) NOT NULL,
  `tpm_id` int(10) NOT NULL DEFAULT '0' COMMENT '工单id',
  `check_id` int(10) NOT NULL DEFAULT '0' COMMENT '检查信息id',
  `system_name` varchar(10) NOT NULL DEFAULT '' COMMENT '系统名称',
  `check_point` varchar(50) NOT NULL DEFAULT '' COMMENT '检查点',
  `check_comment` varchar(100) NOT NULL DEFAULT '' COMMENT '检查/维护工作',
  `set_value` varchar(50) NOT NULL DEFAULT '' COMMENT '设定值',
  `check_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '检查标记：0，待确认，1，正常，2，有故障，3，修复',
  `delete_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '有效状态：1，有效；0，无效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tpm_bill_detail
-- ----------------------------

-- ----------------------------
-- Table structure for t_tpm_problem_log
-- ----------------------------
DROP TABLE IF EXISTS `t_tpm_problem_log`;
CREATE TABLE `t_tpm_problem_log` (
  `id` int(10) NOT NULL,
  `device_id` int(10) NOT NULL DEFAULT '0' COMMENT '设备id',
  `device_name` varchar(50) NOT NULL DEFAULT '' COMMENT '设备名称',
  `find_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '问题发现时间',
  `problem_des` varchar(100) NOT NULL DEFAULT '' COMMENT '问题描述',
  `measures` varchar(100) NOT NULL DEFAULT '' COMMENT '措施',
  `responsible_use_id` int(10) NOT NULL DEFAULT '0' COMMENT '责任人id',
  `responsible_use_name` varchar(50) NOT NULL DEFAULT '' COMMENT '责任人姓名',
  `plan_finsh_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '计划完成时间',
  `real_finsh_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '实际完成时间',
  `comment` varchar(250) NOT NULL DEFAULT '' COMMENT '备注',
  `delete_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '有效状态：1：有效，0无效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tpm_problem_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_vip
-- ----------------------------
DROP TABLE IF EXISTS `t_vip`;
CREATE TABLE `t_vip` (
  `id` int(10) NOT NULL,
  `vip_no` varchar(50) NOT NULL DEFAULT '' COMMENT 'vip账号',
  `vip_level_id` int(10) NOT NULL DEFAULT '0' COMMENT 'vip级别id',
  `delete_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '有效状态：1：有效，0：无效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_vip
-- ----------------------------

-- ----------------------------
-- Table structure for t_vip_level
-- ----------------------------
DROP TABLE IF EXISTS `t_vip_level`;
CREATE TABLE `t_vip_level` (
  `id` int(10) NOT NULL,
  `vip_name` varchar(50) NOT NULL DEFAULT '' COMMENT '会员名称',
  `discount_rate` varchar(50) NOT NULL DEFAULT '0' COMMENT '折扣费率',
  `delete_status` tinyint(4) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_vip_level
-- ----------------------------
