/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 18/02/2021 17:46:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` date NOT NULL,
  `login_time` datetime(0) NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'Admin', '$2a$10$tpyCrIdNNvYx7bjvZzAaT.opVnDdjqFwllA2Jmqk7XQG2nqj9GIQm', '2020-09-08', '2020-09-08 08:02:58', NULL, NULL);
INSERT INTO `admin` VALUES (2, 'test', 'Test', '$2a$10$tpyCrIdNNvYx7bjvZzAaT.opVnDdjqFwllA2Jmqk7XQG2nqj9GIQm', '2020-09-08', '2020-09-08 09:20:48', NULL, NULL);

-- ----------------------------
-- Table structure for admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_relation`;
CREATE TABLE `admin_role_relation`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `admin_id` bigint NULL DEFAULT NULL,
  `role_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户和角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role_relation
-- ----------------------------
INSERT INTO `admin_role_relation` VALUES (1, 1, 1);
INSERT INTO `admin_role_relation` VALUES (2, 1, 2);

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author_id` int NOT NULL,
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '摘要',
  `title_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `content_type` int NULL DEFAULT NULL COMMENT '文章内容类型 0：md, 1:html',
  `cid` int NULL DEFAULT NULL COMMENT '文章类别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, 'test', 1, '无', 'dd', '## Blog\r\n\r\n#### 用户管理\r\n\r\n用户基本信息\r\n\r\n| 名称         | 数据库字段名 |\r\n| ------------ | ------------ |\r\n| ID           | id           |\r\n| 账号         | code         |\r\n| 用户名       | name         |\r\n| 密码         | password     |\r\n| 头像         | icon         |\r\n| 邮箱         | email        |\r\n| 账号创建时间 | create_time  |\r\n| 最后登录时间 | login_time   |\r\n\r\n粉丝管理\r\n\r\n| 名称     | 数据库字段名 |\r\n| -------- | ------------ |\r\n| ID       | id           |\r\n| 用户ID   | uid          |\r\n| 关注人ID | oid          |\r\n\r\n#### 文章管理\r\n\r\n文章标签管理\r\n\r\n| 名称     | 数据库字段名 |\r\n| -------- | ------------ |\r\n| ID       | id           |\r\n| 创建人ID | uid          |\r\n| 标签名称 | label        |\r\n| 标签分类 | lclass       |\r\n| 创建时间 | create_time  |\r\n\r\n文章内容管理\r\n\r\n\r\n\r\n文章评论管理\r\n\r\n文章扩展信息\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n#### ArticleDto 各标签含义\r\n\r\n```\r\nid,\r\nAS atitle,\r\naid,\r\naname,\r\nacontent,\r\n            a.`describe` AS `describe`,\r\n            a.title_img AS titleimg,\r\n            ai.id AS aiid,\r\n            ai.likes AS likes,\r\n            ai.comments AS comments,\r\n            ai.collections AS collections,\r\n            ai.readings AS readings,\r\n            c.content AS ccontent,\r\n            c.article_score AS articleScore,\r\n            c.id AS cid,\r\n            c.create_time AS ctime,\r\n            c.likes AS clikes,\r\n            c.pid AS cpid,\r\n            c.uid AS cuid,\r\n            l.createtime AS ltime,\r\n            l.id AS lid,\r\n            l.label_name AS lname,\r\n            l.pid AS lpid,\r\n            l.label_class AS lclass,\r\n            u2.`name` AS cuname\r\n```\r\n\r\n', '2020-09-09 11:10:17', 0, 1);
INSERT INTO `article` VALUES (2, 'test2', 1, 'teasst', 'dd', '12121212', '2020-09-09 11:10:56', 0, 1);
INSERT INTO `article` VALUES (3, 'hello', 1, 'hello', 'hhh', '### 东方饭店的\n色大幅发多少', '2020-09-12 15:51:25', 0, 1);
INSERT INTO `article` VALUES (4, 'hello', 1, 'hello', 'hhh', '#### 你好\nhello\ndemo\n', '2020-09-12 15:51:28', 0, 1);
INSERT INTO `article` VALUES (5, 'hello', 1, 'hello', 'hhh', ' # img\n![hdImg_cb0e33e253702e1056495487f5013d3415941142186.jpg](http://gregorio-vblog.oss-cn-beijing.aliyuncs.com/img/57074632-2ab0-4355-83e0-ccd9a1686b16.jpg)\n\n\n### hello', '2020-09-12 15:51:31', 0, 1);
INSERT INTO `article` VALUES (6, 'hello', 1, 'hello', 'hhh', '##  第三次\n<img src=\"http://gregorio-vblog.oss-cn-beijing.aliyuncs.com/img/869233f1-a76d-4555-a494-95366443913a.jpg\" style=\"zoom:33%;\" />', '2020-09-12 15:51:33', 0, 1);
INSERT INTO `article` VALUES (7, 'hello', 1, 'hello', 'hhh', '##  第三次\n<img src=\"http://gregorio-vblog.oss-cn-beijing.aliyuncs.com/img/869233f1-a76d-4555-a494-95366443913a.jpg\" style=\"zoom:33%;\" />', '2020-09-12 15:51:37', 0, 2);
INSERT INTO `article` VALUES (8, 'hello', 1, 'hello', 'hhh', '##  第三次\n<img src=\"http://gregorio-vblog.oss-cn-beijing.aliyuncs.com/img/869233f1-a76d-4555-a494-95366443913a.jpg\" style=\"zoom:33%;\" />', '2020-09-12 15:51:40', 0, 2);
INSERT INTO `article` VALUES (9, 'hello', 1, 'hello', 'hhh', '##  第三次\n<img src=\"http://gregorio-vblog.oss-cn-beijing.aliyuncs.com/img/869233f1-a76d-4555-a494-95366443913a.jpg\" style=\"zoom:33%;\" />', '2020-09-12 15:51:43', 0, 2);
INSERT INTO `article` VALUES (10, 'hello', 1, 'hello', 'hhh', '##  第三次\n<img src=\"http://gregorio-vblog.oss-cn-beijing.aliyuncs.com/img/869233f1-a76d-4555-a494-95366443913a.jpg\" style=\"zoom:33%;\" />', '2020-09-12 15:51:47', 0, 2);
INSERT INTO `article` VALUES (11, 'hello', 1, 'hello', 'hhh', '##  第三次\n<img src=\"http://gregorio-vblog.oss-cn-beijing.aliyuncs.com/img/869233f1-a76d-4555-a494-95366443913a.jpg\" style=\"zoom:33%;\" />', '2020-09-12 15:51:50', 0, 1);
INSERT INTO `article` VALUES (12, 'hello', 1, 'hello', 'hhh', '##  第三次\n<img src=\"http://gregorio-vblog.oss-cn-beijing.aliyuncs.com/img/869233f1-a76d-4555-a494-95366443913a.jpg\" style=\"zoom:33%;\" />', '2020-09-12 15:51:53', 0, 1);
INSERT INTO `article` VALUES (13, 'hello', 1, 'hello', 'hhh', '##  第三次\n<img src=\"http://gregorio-vblog.oss-cn-beijing.aliyuncs.com/img/869233f1-a76d-4555-a494-95366443913a.jpg\" style=\"zoom:33%;\" />', '2020-09-12 15:51:56', 0, 1);
INSERT INTO `article` VALUES (14, 'hello', 1, 'hello', 'hhh', '##  第三次\n<img src=\"http://gregorio-vblog.oss-cn-beijing.aliyuncs.com/img/869233f1-a76d-4555-a494-95366443913a.jpg\" style=\"zoom:33%;\" />', '2020-09-12 15:52:00', 0, 1);
INSERT INTO `article` VALUES (15, '阿达', 1, '阿萨德阿萨德都是按说das', '', '阿萨德按说sad按说', '2020-09-13 11:52:33', 0, NULL);
INSERT INTO `article` VALUES (18, '撒', 1, '是', '', ' 是', '2020-09-13 11:52:29', 0, NULL);
INSERT INTO `article` VALUES (19, '阿萨德按说sad', 1, ' 手打  ', '', '是d 手打大声道撒阿萨德 ', '2020-09-13 12:14:19', 0, NULL);
INSERT INTO `article` VALUES (20, '阿萨德按说sad', 1, ' 手打  ', '', '是d 手打大声道撒阿萨德 ', '2020-09-13 12:14:16', 0, NULL);
INSERT INTO `article` VALUES (21, '阿萨德', 1, '大赛的', '', ' 阿萨德', '2020-09-13 12:14:12', 0, NULL);
INSERT INTO `article` VALUES (22, '阿萨德', 1, 'sad按说', '', '手打d', '2020-09-13 12:12:45', 0, 1);
INSERT INTO `article` VALUES (23, '阿萨德', 1, '按说打的', '', '阿萨德', '2020-09-13 12:14:01', 0, 1);
INSERT INTO `article` VALUES (24, '手打按说', 1, '啊手打', '', '阿萨德sad', '2020-09-13 12:15:42', 0, 2);

-- ----------------------------
-- Table structure for article_label
-- ----------------------------
DROP TABLE IF EXISTS `article_label`;
CREATE TABLE `article_label`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `aid` int NULL DEFAULT NULL COMMENT '文章id',
  `lid` int NULL DEFAULT NULL COMMENT '标签ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_label
-- ----------------------------
INSERT INTO `article_label` VALUES (1, 1, 2);
INSERT INTO `article_label` VALUES (2, 1, 1);

-- ----------------------------
-- Table structure for articleinfo
-- ----------------------------
DROP TABLE IF EXISTS `articleinfo`;
CREATE TABLE `articleinfo`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL COMMENT '文章id',
  `likes` int NULL DEFAULT NULL COMMENT '点赞数',
  `readings` int NULL DEFAULT NULL COMMENT '浏览数',
  `collections` int NULL DEFAULT NULL COMMENT '收藏数',
  `comments` int NULL DEFAULT NULL COMMENT '评论数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of articleinfo
-- ----------------------------
INSERT INTO `articleinfo` VALUES (1, 1, 32, 2, 4, 33);
INSERT INTO `articleinfo` VALUES (2, 2, 334, 4444, 334, 343);
INSERT INTO `articleinfo` VALUES (3, 3, 0, 0, 0, 0);
INSERT INTO `articleinfo` VALUES (4, 4, 0, 0, 0, 0);
INSERT INTO `articleinfo` VALUES (5, 5, 0, 0, 0, 0);
INSERT INTO `articleinfo` VALUES (6, 7, 0, 0, 0, 0);
INSERT INTO `articleinfo` VALUES (7, 8, 0, 0, 0, 0);
INSERT INTO `articleinfo` VALUES (8, 9, 0, 0, 0, 0);
INSERT INTO `articleinfo` VALUES (9, 10, 0, 0, 0, 0);
INSERT INTO `articleinfo` VALUES (10, 11, 0, 0, 0, 0);
INSERT INTO `articleinfo` VALUES (11, 12, 0, 0, 0, 0);
INSERT INTO `articleinfo` VALUES (12, 13, 0, 0, 0, 0);
INSERT INTO `articleinfo` VALUES (13, 14, 0, 0, 0, 0);

-- ----------------------------
-- Table structure for blog_class
-- ----------------------------
DROP TABLE IF EXISTS `blog_class`;
CREATE TABLE `blog_class`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_class
-- ----------------------------
INSERT INTO `blog_class` VALUES (1, '后端');
INSERT INTO `blog_class` VALUES (2, 'AI');
INSERT INTO `blog_class` VALUES (3, '0_0');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int NULL DEFAULT NULL COMMENT '评论人ID',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `article_score` decimal(10, 0) NULL DEFAULT NULL COMMENT '文章评分',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `likes` int NULL DEFAULT NULL COMMENT '点赞数',
  `aid` int NULL DEFAULT NULL COMMENT '文章id',
  `pid` int NULL DEFAULT NULL COMMENT '父评论',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (25, 1, 'df ', 0, '2020-09-12 20:30:36', 0, 4, 0);
INSERT INTO `comment` VALUES (26, 1, 'dsa ', 0, '2020-09-12 20:30:40', 0, 4, 25);
INSERT INTO `comment` VALUES (27, 1, '', 0, '2020-09-12 20:32:00', 0, 4, 0);
INSERT INTO `comment` VALUES (28, 1, '', 0, '2020-09-12 20:32:01', 0, 4, 0);
INSERT INTO `comment` VALUES (29, 1, '', 0, '2020-09-12 20:32:01', 0, 4, 0);
INSERT INTO `comment` VALUES (30, 1, '', 0, '2020-09-12 20:32:01', 0, 4, 0);
INSERT INTO `comment` VALUES (31, 1, '', 0, '2020-09-12 20:32:01', 0, 4, 0);
INSERT INTO `comment` VALUES (32, 1, '', 0, '2020-09-12 20:32:10', 0, 4, 0);
INSERT INTO `comment` VALUES (33, 1, '', 0, '2020-09-12 20:32:10', 0, 4, 0);
INSERT INTO `comment` VALUES (34, 1, '', 0, '2020-09-12 20:32:10', 0, 4, 0);
INSERT INTO `comment` VALUES (35, 1, '', 0, '2020-09-12 20:32:11', 0, 4, 0);
INSERT INTO `comment` VALUES (36, 1, '', 0, '2020-09-12 20:32:11', 0, 4, 0);
INSERT INTO `comment` VALUES (37, 1, '22', 0, '2020-09-13 08:21:45', 0, 9, 0);
INSERT INTO `comment` VALUES (38, 1, ' 手打', 0, '2020-09-13 11:56:57', 0, 21, 0);
INSERT INTO `comment` VALUES (42, 1, 'cxzv b ds ', 0, '2020-09-14 19:29:04', 0, 1, 0);
INSERT INTO `comment` VALUES (44, 1, 'das d  ', 0, '2020-09-14 19:29:09', 0, 1, 0);
INSERT INTO `comment` VALUES (46, 1, 'asd ', 0, '2020-09-14 19:30:27', 0, 1, 42);
INSERT INTO `comment` VALUES (47, 1, 'asd ', 0, '2020-09-14 19:30:29', 0, 1, 0);
INSERT INTO `comment` VALUES (48, 1, 'das ', 0, '2020-09-14 19:30:47', 0, 1, 47);
INSERT INTO `comment` VALUES (52, 1, 'sd s', 0, '2020-09-14 20:28:57', 0, 2, 0);
INSERT INTO `comment` VALUES (53, 1, 'fd', 0, '2020-09-14 20:28:58', 0, 2, 0);
INSERT INTO `comment` VALUES (54, 1, '', 0, '2020-09-14 20:28:59', 0, 2, 0);
INSERT INTO `comment` VALUES (55, 1, '', 0, '2020-09-14 20:29:01', 0, 2, 0);
INSERT INTO `comment` VALUES (56, 1, '', 0, '2020-09-14 20:29:01', 0, 2, 0);
INSERT INTO `comment` VALUES (57, 1, '', 0, '2020-09-14 20:29:01', 0, 2, 0);
INSERT INTO `comment` VALUES (58, 1, '', 0, '2020-09-14 20:29:02', 0, 2, 0);
INSERT INTO `comment` VALUES (59, 1, 'fdsfs', 0, '2020-09-14 20:29:04', 0, 2, 0);
INSERT INTO `comment` VALUES (60, 1, 'fdsf', 0, '2020-09-14 20:29:07', 0, 2, 0);
INSERT INTO `comment` VALUES (61, 1, '', 0, '2020-09-14 20:30:52', 0, 2, 0);
INSERT INTO `comment` VALUES (62, 1, 'asdas ', 0, '2020-09-14 20:31:46', 0, 2, 0);

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `label_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createtime` datetime(0) NULL DEFAULT NULL,
  `label_class` int NULL DEFAULT NULL,
  `pid` int NULL DEFAULT NULL COMMENT '父标签id\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of label
-- ----------------------------
INSERT INTO `label` VALUES (1, 'Java', '2020-09-09 11:09:02', 1, 0);
INSERT INTO `label` VALUES (2, 'Python', '2020-09-09 11:09:05', 1, 0);

-- ----------------------------
-- Table structure for labelclass
-- ----------------------------
DROP TABLE IF EXISTS `labelclass`;
CREATE TABLE `labelclass`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签类别名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of labelclass
-- ----------------------------
INSERT INTO `labelclass` VALUES (1, '计算机');
INSERT INTO `labelclass` VALUES (2, '金融');
INSERT INTO `labelclass` VALUES (5, '美术');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pid` bigint NULL DEFAULT NULL COMMENT '父级权限id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限值',
  `icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `type` int NULL DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端资源路径',
  `status` int NULL DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 0, '后台管理', 'ADMIN', NULL, 1, '/admin', 1, '2018-09-29 16:17:01', 0);
INSERT INTO `permission` VALUES (2, 0, '普通用户', 'USER', NULL, 1, '/user', 1, '2020-09-08 08:07:48', 0);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `admin_count` int NULL DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` int NULL DEFAULT 1 COMMENT '启用状态：0->禁用；1->启用',
  `sort` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', '管理后台', 0, '2020-02-03 16:50:37', 1, 0);
INSERT INTO `role` VALUES (2, '用户', '无', 0, '2020-09-08 09:22:57', 1, 0);

-- ----------------------------
-- Table structure for role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_relation`;
CREATE TABLE `role_permission_relation`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NULL DEFAULT NULL,
  `permission_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户角色和权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission_relation
-- ----------------------------
INSERT INTO `role_permission_relation` VALUES (1, 1, 1);
INSERT INTO `role_permission_relation` VALUES (2, 1, 2);
INSERT INTO `role_permission_relation` VALUES (3, 2, 2);

-- ----------------------------
-- Table structure for subscribe
-- ----------------------------
DROP TABLE IF EXISTS `subscribe`;
CREATE TABLE `subscribe`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int NOT NULL COMMENT '用户ID',
  `pid` int NOT NULL COMMENT '关注人ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subscribe
-- ----------------------------
INSERT INTO `subscribe` VALUES (1, 1, 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `create_time` date NOT NULL COMMENT '注册时间',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$tpyCrIdNNvYx7bjvZzAaT.opVnDdjqFwllA2Jmqk7XQG2nqj9GIQm', 'Tom地方', '2020-07-14', '2020-07-14 11:09:33', 'http://gregorio-vblog.oss-cn-beijing.aliyuncs.com/img/d1b09541-3aab-4311-a644-9f221127ac76.jpg', '132@163.com');
INSERT INTO `user` VALUES (2, '111112', '123456', 'Toms', '2020-07-19', '2020-09-08 19:28:27', 'http://gregorio-vblog.oss-cn-beijing.aliyuncs.com/img/869233f1-a76d-4555-a494-95366443913a.jpg', 'as seDf ');
INSERT INTO `user` VALUES (4, '111114', '123456', '的发生', '2020-09-08', '2020-09-08 19:29:48', ' http://gregorio-vblog.oss-cn-beijing.aliyuncs.com/img/869233f1-a76d-4555-a494-95366443913a.jpg', 'as da');
INSERT INTO `user` VALUES (5, '111115', '123456', '速度', '2020-09-09', '2020-09-09 19:30:08', 'http://gregorio-vblog.oss-cn-beijing.aliyuncs.com/img/869233f1-a76d-4555-a494-95366443913a.jpg', NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 1, 2);

SET FOREIGN_KEY_CHECKS = 1;
