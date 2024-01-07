

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP DATABASE IF EXISTS `bs_xk`;
CREATE DATABASE `bs_xk`;
use `bs_xk`;

-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `age` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '年龄',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `level` int(10) NULL DEFAULT 1 COMMENT '权限等级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理员信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_info
-- ----------------------------
INSERT INTO `admin_info` VALUES (1, 'admin', '123456', '男', '29', '18888888888', 1);
INSERT INTO `admin_info` VALUES (15, 'admin2', '123456', '男', '23', '18877778888', 1);
INSERT INTO `admin_info` VALUES (16, 'admin3', '123456', '女', '23', '18877778888', 1);
INSERT INTO `admin_info` VALUES (17, 'admin4', '123456', '女', '23', '18877778888', 1);
INSERT INTO `admin_info` VALUES (22, 'admin5', '123456', '女', '23', '18877778888', 1);
INSERT INTO `admin_info` VALUES (23, 'admin6', '123456', '女', '23', '18877778888', 1);
INSERT INTO `admin_info` VALUES (24, 'admin7', '123456', '女', '23', '18877778888', 1);
INSERT INTO `admin_info` VALUES (25, 'admin8', '123456', '女', '23', '18877778888', 1);

-- ----------------------------
-- Table structure for class_info
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程介绍',
  `zhuanyeId` bigint(20) NULL DEFAULT NULL COMMENT '专业ID',
  `score` int(10) NULL DEFAULT NULL COMMENT '课程学分',
  `teacherId` bigint(20) NULL DEFAULT NULL COMMENT '教师ID',
  `kaiban` int(10) NULL DEFAULT 0 COMMENT '开班人数',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上课时段',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上课地点',
  `yixuan` int(10) NULL DEFAULT 0 COMMENT '已选人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '课程信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class_info
-- ----------------------------
INSERT INTO `class_info` VALUES (13, 'Java基础', '学会Java基础', 8, 3, 26, 20, '周一第一大节', '3A101', 2);
INSERT INTO `class_info` VALUES (14, 'Python基础', '学会Python基础', 8, 3, 27, 20, '周二第一大节', '3A102', 2);

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `age` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '年龄',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学号',
  `level` int(10) NULL DEFAULT 3 COMMENT '权限等级',
  `xueyuanId` bigint(20) NULL DEFAULT NULL COMMENT '学院ID',
  `score` int(10) NULL DEFAULT 0 COMMENT '总学分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学生信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student_info
-- ----------------------------
INSERT INTO `student_info` VALUES (27, '张三', '123456', '男', '24', '20000', 3, 1, 3);
INSERT INTO `student_info` VALUES (28, '李四', '123456', '男', '23', '20001', 3, 2, 2);
INSERT INTO `student_info` VALUES (29, '王五', '123456', '男', '23', '20002', 3, 3, 2);
INSERT INTO `student_info` VALUES (30, '赵六', '123456', '男', '23', '20003', 3, 4, 3);
INSERT INTO `student_info` VALUES (31, '李七', '123456', '女', '24', '20005', 3, 7, 2);

-- ----------------------------
-- Table structure for teacher_info
-- ----------------------------
DROP TABLE IF EXISTS `teacher_info`;
CREATE TABLE `teacher_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `age` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '年龄',
  `zhicheng` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '职称',
  `level` int(10) NULL DEFAULT 2 COMMENT '权限等级',
  `zhuanyeId` bigint(20) NULL DEFAULT NULL COMMENT '专业ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '教师信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teacher_info
-- ----------------------------
INSERT INTO `teacher_info` VALUES (26, '张老师', '123456', '女', '35', '教授', 2, 8);
INSERT INTO `teacher_info` VALUES (27, '赵老师', '123456', '男', '29', '教授', 2, 8);
INSERT INTO `teacher_info` VALUES (28, '王老师', '123456', '女', '28', '副教授', 2, 9);
INSERT INTO `teacher_info` VALUES (31, '李老师', '123456', '女', '28', '副教授', 2, 9);
INSERT INTO `teacher_info` VALUES (32, '夏老张', '123456', '女', '28', '副教授', 2, 12);
INSERT INTO `teacher_info` VALUES (33, '倪老师', '123456', '女', '28', '副教授', 2, 9);

-- ----------------------------
-- Table structure for xuanke_info
-- ----------------------------
DROP TABLE IF EXISTS `xuanke_info`;
CREATE TABLE `xuanke_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程介绍',
  `zhuanyeId` bigint(20) NULL DEFAULT NULL COMMENT '专业ID',
  `score` int(10) NULL DEFAULT NULL COMMENT '课程学分',
  `teacherId` bigint(20) NULL DEFAULT NULL COMMENT '教师ID',
  `kaiban` int(10) NULL DEFAULT 0 COMMENT '开班人数',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上课时段',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上课地点',
  `studentId` bigint(20) NULL DEFAULT NULL COMMENT '学生ID',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '选课信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xuanke_info
-- ----------------------------
INSERT INTO `xuanke_info` VALUES (23, 'Java基础', '学会Java基础', 8, 3, 26, 20, '周一第一大节', '3A101', 28, '已开课');
INSERT INTO `xuanke_info` VALUES (24, 'Python基础', '学会Python基础', 8, 3, 27, 20, '周二第一大节', '3A102', 28, '待开课');
INSERT INTO `xuanke_info` VALUES (29, 'Java基础', '学会Java基础', 8, 3, 26, 20, '周一第一大节', '3A101', 27, '已开课');
INSERT INTO `xuanke_info` VALUES (32, 'Python基础', '学会Python基础', 8, 3, 27, 20, '周二第一大节', '3A102', 27, '待开课');

-- ----------------------------
-- Table structure for xueyuan_info
-- ----------------------------
DROP TABLE IF EXISTS `xueyuan_info`;
CREATE TABLE `xueyuan_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学院名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学院介绍',
  `score` int(10) NULL DEFAULT NULL COMMENT '学分限制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学院信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xueyuan_info
-- ----------------------------
INSERT INTO `xueyuan_info` VALUES (1, '信息工程学院', '巴拉巴拉巴拉', 20);
INSERT INTO `xueyuan_info` VALUES (2, '计算机学院', '巴拉巴拉巴拉', 25);
INSERT INTO `xueyuan_info` VALUES (3, '软件学院', '巴拉巴拉巴拉', 30);
INSERT INTO `xueyuan_info` VALUES (4, '外国语学院', '美女很多', 25);
INSERT INTO `xueyuan_info` VALUES (7, '文学院', '美女贼多', 30);

-- ----------------------------
-- Table structure for zhuanye_info
-- ----------------------------
DROP TABLE IF EXISTS `zhuanye_info`;
CREATE TABLE `zhuanye_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '专业名称',
  `department` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '系名',
  `xueyuanId` bigint(20) NULL DEFAULT NULL COMMENT '学院ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '专业信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zhuanye_info
-- ----------------------------
INSERT INTO `zhuanye_info` VALUES (8, '电子信息工程', '电子系', 1);
INSERT INTO `zhuanye_info` VALUES (9, '软件工程', '软件系', 3);
INSERT INTO `zhuanye_info` VALUES (12, '古汉语信息', '文学系', 7);

SET FOREIGN_KEY_CHECKS = 1;
