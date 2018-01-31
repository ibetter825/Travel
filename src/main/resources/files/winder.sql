/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50711
Source Host           : 127.0.0.1:3306
Source Database       : winder

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2018-01-31 23:08:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `art_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `art_title` varchar(100) NOT NULL COMMENT '文章标题',
  `art_desc` varchar(400) DEFAULT '' COMMENT '文章摘要',
  `author_id` int(11) NOT NULL COMMENT '文章作者FK',
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `edit_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后修改时间',
  `art_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '文章状态: 1正常 -1软删除',
  `art_cont` text NOT NULL COMMENT '文章内容',
  `art_imgs` varchar(4000) DEFAULT '' COMMENT '文章中的图片地址，每张图片之间用";"隔开',
  `art_cover` varchar(500) DEFAULT '' COMMENT '文章封面',
  `art_tags` varchar(250) DEFAULT '' COMMENT '标签',
  `art_song` varchar(200) DEFAULT '' COMMENT '背景音乐',
  `del_time` bigint(20) DEFAULT '0' COMMENT '软删除时间',
  PRIMARY KEY (`art_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '一次就好', '一次就好', '10000', '1517408293724', '0', '1', '<p>想看你笑</p><p>想和你闹</p><p>想拥你入我怀抱</p><p>上一秒红着脸在争吵</p><p>下一秒一转身就和好</p><p>一次就好</p><p>我带你去到天荒地老</p><p>在阳光灿烂的时光里</p>', '', '', '标签1,标签2', '', '0');
INSERT INTO `article` VALUES ('2', '一次就好', '一次就好', '10000', '1517408445688', '0', '1', '<p>想看你笑</p><p>想和你闹</p><p>想拥你入我怀抱</p><p>上一秒红着脸在争吵</p><p>下一秒一转身就和好</p><p>一次就好</p><p>我带你去到天荒地老</p><p>在阳光灿烂的时光里</p>', '', '', '标签1,标签2', '', '0');

-- ----------------------------
-- Table structure for article_count
-- ----------------------------
DROP TABLE IF EXISTS `article_count`;
CREATE TABLE `article_count` (
  `art_id` bigint(20) NOT NULL COMMENT '文章id',
  `like_count` int(11) DEFAULT '0' COMMENT '点赞次数',
  `comt_count` int(11) DEFAULT '0' COMMENT '评论数',
  `scan_count` int(11) DEFAULT '0' COMMENT '浏览数量',
  `share_count` int(11) DEFAULT '0' COMMENT '分享次数',
  PRIMARY KEY (`art_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章统计表\r\n点赞次数\r\n评论次数\r\n浏览次数\r\n分享次数\r\n等等';

-- ----------------------------
-- Records of article_count
-- ----------------------------

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `dict_no` varchar(50) NOT NULL DEFAULT '' COMMENT '字典编码',
  `dict_fno` varchar(50) DEFAULT '' COMMENT '父级字典',
  `dict_nm` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_desc` varchar(200) DEFAULT '',
  `dict_status` tinyint(4) DEFAULT '0' COMMENT '状态:-1软删 0隐藏 1正常',
  `dict_seq` int(11) DEFAULT '0' COMMENT '同级排序',
  `dict_icon` varchar(100) DEFAULT '' COMMENT '图标，或者类',
  `dict_par` varchar(1000) DEFAULT '' COMMENT '字典参数',
  PRIMARY KEY (`dict_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES ('D003', '', '音乐地址', '音乐API', '1', '0', '', '');
INSERT INTO `dict` VALUES ('D00301', 'D003', '音乐搜索', '直接拼上查询的字段即可使用', '1', '0', '', 'http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&from=webapp_music&method=baidu.ting.search.catalogSug&query=');
INSERT INTO `dict` VALUES ('D00302', 'D003', '音乐查询', '直接拼接查询音乐id即可获取歌曲信息', '1', '0', '', 'http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&from=webapp_music&method=baidu.ting.song.play&songid=');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tag_nm` varchar(50) NOT NULL COMMENT '标签名',
  `tag_num` int(11) DEFAULT '0' COMMENT '文章数',
  `add_time` bigint(20) DEFAULT '0' COMMENT '标签新增时间',
  PRIMARY KEY (`tag_nm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------

-- ----------------------------
-- Table structure for tag_art
-- ----------------------------
DROP TABLE IF EXISTS `tag_art`;
CREATE TABLE `tag_art` (
  `tag_nm` varchar(50) NOT NULL,
  `art_id` bigint(20) NOT NULL,
  PRIMARY KEY (`tag_nm`,`art_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签文章关联表';

-- ----------------------------
-- Records of tag_art
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT '' COMMENT '登录名',
  `user_pwd` varchar(32) NOT NULL COMMENT '用户密码',
  `user_salt` varchar(10) NOT NULL COMMENT '加密用字符串',
  `user_phone` bigint(11) DEFAULT '0' COMMENT '用户电话号码，可用于登录',
  `user_email` varchar(100) DEFAULT '' COMMENT '用户邮箱，可用于登录',
  `user_status` tinyint(4) DEFAULT '1' COMMENT '状态: -1：删除，0：限制登陆，1：正常',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `INX_TK_USER_USER_NAME` (`user_name`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10000', 'ibetter825', 'BB855C106B50CEA148E553154E3DF151', 'yang', '13408458790', 'y825y@qq.com', '1');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL,
  `user_avatar` varchar(200) DEFAULT '' COMMENT '用户头像',
  `user_intro` varchar(500) DEFAULT '' COMMENT '用户简介',
  `nick_name` varchar(100) DEFAULT '' COMMENT '用户昵称',
  `user_sex` tinyint(4) DEFAULT '1' COMMENT '用户性别: 1: 男  0:女',
  `login_time` bigint(20) DEFAULT '0' COMMENT '上次登录时间',
  `login_ip` varchar(50) DEFAULT '' COMMENT '上次登录ip,另外一张表使用触发器自动记录登录时间与ip',
  `reg_time` bigint(20) DEFAULT '0' COMMENT '注册时间',
  `err_count` tinyint(20) DEFAULT '0' COMMENT '连续登录错误次数',
  `stop_time` bigint(20) DEFAULT '0' COMMENT '限制登录开始时间，24小时候才能再次登录并清空'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户详情表';

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('10000', '', '第一个用户', 'TkTick', '1', '1504610020608', '127.0.0.1', '0', '0', '1495517899241');
