/*
 Navicat Premium Data Transfer

 Source Server         : MY_Connection
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : db_myblog

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 14/04/2019 11:00:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_message
-- ----------------------------
DROP TABLE IF EXISTS `user_message`;
CREATE TABLE `user_message`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user_id` bigint(20) NULL DEFAULT NULL COMMENT '发送消息的用户ID',
  `to_user_id` bigint(20) NULL DEFAULT NULL COMMENT '接收消息的用户ID',
  `post_id` bigint(20) NULL DEFAULT NULL COMMENT '消息可能关联的帖子',
  `comment_id` bigint(20) NULL DEFAULT NULL COMMENT '消息可能关联的评论',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` tinyint(2) NULL DEFAULT NULL COMMENT '消息类型',
  `created` datetime(0) NOT NULL,
  `modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
