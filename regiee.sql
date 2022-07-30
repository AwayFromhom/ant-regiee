/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : regiee

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 30/07/2022 15:31:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address_book
-- ----------------------------
DROP TABLE IF EXISTS `address_book`;
CREATE TABLE `address_book`  (
  `id` bigint NOT NULL COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `consignee` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '收货人',
  `sex` tinyint NOT NULL COMMENT '性别 0 女 1 男',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `province_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省级区划编号',
  `province_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省级名称',
  `city_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '市级区划编号',
  `city_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '市级名称',
  `district_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区级区划编号',
  `district_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区级名称',
  `detail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签',
  `is_default` tinyint(1) NOT NULL DEFAULT 0 COMMENT '默认 0 否 1是',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '地址管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address_book
-- ----------------------------
INSERT INTO `address_book` VALUES (1417414526093082626, 1417012167126876162, '小明', 1, '13812345678', NULL, NULL, NULL, NULL, NULL, NULL, '昌平区金燕龙办公楼', '公司', 1, '2021-07-20 17:22:12', '2021-07-20 17:26:33', 1417012167126876162, 1417012167126876162, 0);
INSERT INTO `address_book` VALUES (1417414926166769666, 1417012167126876162, '小李', 1, '13512345678', NULL, NULL, NULL, NULL, NULL, NULL, '测试', '家', 0, '2021-07-20 17:23:47', '2021-07-20 17:23:47', 1417012167126876162, 1417012167126876162, 0);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int NULL DEFAULT NULL COMMENT '类型   1 菜品分类 2 套餐分类',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '分类名称',
  `sort` int NOT NULL DEFAULT 0 COMMENT '顺序',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` int NOT NULL COMMENT '创建人',
  `update_user` int NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_category_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜品及套餐分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 1, '湘菜', 1, '2021-05-27 09:16:58', '2021-07-15 20:25:23', 1, 1);
INSERT INTO `category` VALUES (2, 1, '主食', 10, '2022-07-25 11:45:23', '2022-07-30 14:24:16', 1, 1);
INSERT INTO `category` VALUES (3, 1, '饮料', 12, '2022-07-25 11:45:39', '2022-07-30 14:03:42', 1, 1);
INSERT INTO `category` VALUES (4, 1, '川菜', 5, '2022-07-25 11:45:47', '2022-07-25 11:45:47', 1, 1);
INSERT INTO `category` VALUES (5, 1, '徽菜', 5, '2022-07-25 11:45:54', '2022-07-25 11:45:54', 1, 1);
INSERT INTO `category` VALUES (6, 1, '豫菜', 5, '2022-07-25 11:46:02', '2022-07-25 11:46:02', 1, 1);
INSERT INTO `category` VALUES (7, 1, '鲁菜', 5, '2022-07-25 11:46:09', '2022-07-25 11:46:09', 1, 1);
INSERT INTO `category` VALUES (8, 1, '凉菜', 5, '2022-07-25 11:46:15', '2022-07-25 11:46:15', 1, 1);
INSERT INTO `category` VALUES (9, 2, '儿童套餐', 0, '2022-07-25 11:46:29', '2022-07-25 13:15:10', 1, 1);
INSERT INTO `category` VALUES (10, 2, '婚庆套餐', 0, '2022-07-25 11:46:39', '2022-07-25 13:15:14', 1, 1);
INSERT INTO `category` VALUES (21, 2, '成人套餐', 0, '2022-07-25 13:15:43', '2022-07-25 13:15:43', 1, 1);
INSERT INTO `category` VALUES (22, 1, '炒菜', 3, '2022-07-30 14:36:13', '2022-07-30 14:36:13', 1, 1);
INSERT INTO `category` VALUES (23, 1, '烧烤', 5, '2022-07-30 14:36:20', '2022-07-30 14:36:20', 1, 1);
INSERT INTO `category` VALUES (24, 1, '淮扬菜', 11, '2022-07-30 14:39:52', '2022-07-30 14:39:52', 1, 1);

-- ----------------------------
-- Table structure for dish
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '菜品名称',
  `category_id` int NULL DEFAULT NULL COMMENT '菜品分类id',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '菜品价格',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '商品码',
  `image` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '图片',
  `description` varchar(400) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述信息',
  `status` int NOT NULL DEFAULT 1 COMMENT '0 停售 1 起售',
  `sort` int NOT NULL DEFAULT 0 COMMENT '顺序',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` int NOT NULL COMMENT '创建人',
  `update_user` int NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_dish_name`(`name`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜品管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dish
-- ----------------------------
INSERT INTO `dish` VALUES (43, '米饭', 2, 1.00, NULL, '0d259f54-1049-40ec-be2e-1a70287a2069.jpeg', '精选东北大米。', 1, 0, '2022-07-30 14:26:08', '2022-07-30 14:26:08', 1, 1, 0);
INSERT INTO `dish` VALUES (44, '可口可乐', 3, 7.00, NULL, 'f59324b7-86b2-4566-802c-d44eac5cf903.jpeg', '可口可乐非常好喝', 1, 0, '2022-07-30 14:27:15', '2022-07-30 14:27:15', 1, 1, 0);
INSERT INTO `dish` VALUES (45, '酸菜鱼', 4, 80.00, NULL, '0000f825-c2d1-4fa8-a1f2-8cf3d287e256.jpeg', '酸菜鱼真的好吃', 1, 0, '2022-07-30 14:28:38', '2022-07-30 14:28:38', 1, 1, 0);
INSERT INTO `dish` VALUES (46, '水煮肉片', 4, 75.00, NULL, '29c9e30c-67c7-4dfa-882e-682bfcac7659.jpeg', '水煮肉片，你吃过没', 1, 0, '2022-07-30 14:29:48', '2022-07-30 14:29:48', 1, 1, 0);
INSERT INTO `dish` VALUES (47, '回锅肉', 4, 45.00, NULL, 'c7df9755-46fe-4c15-a221-901c6b7fd9a9.jpeg', '正宗四川火锅肉', 1, 0, '2022-07-30 14:31:29', '2022-07-30 14:31:29', 1, 1, 0);
INSERT INTO `dish` VALUES (48, '椰汁', 3, 10.00, NULL, '37686309-848d-44a0-824b-f28d00ea2b00.jpeg', '椰羊奶好喝', 1, 0, '2022-07-30 14:33:36', '2022-07-30 14:33:36', 1, 1, 0);
INSERT INTO `dish` VALUES (49, '皮蛋豆腐', 8, 18.00, NULL, 'b3ca6238-af7d-4deb-bdd1-0c7d62cf386e.jpeg', '真的不想尝尝招牌凉菜皮蛋豆腐吗', 1, 0, '2022-07-30 14:35:50', '2022-07-30 14:35:50', 1, 1, 0);
INSERT INTO `dish` VALUES (50, '北京烤鸭', 23, 120.00, NULL, 'c597c021-5858-4cca-a202-2ab07c3fbf98.jpeg', '就是这个味儿，特地道。', 1, 0, '2022-07-30 14:37:47', '2022-07-30 14:37:47', 1, 1, 0);
INSERT INTO `dish` VALUES (51, '青椒炒蛋', 22, 15.00, NULL, '0757eb7e-344f-4c9a-8735-5b0818aae447.jpeg', '家常小炒菜', 1, 0, '2022-07-30 14:38:51', '2022-07-30 14:38:51', 1, 1, 0);
INSERT INTO `dish` VALUES (52, '红烧狮子头', 24, 30.00, NULL, '43160957-57ad-4c1f-a066-3a9ae349b93b.jpeg', '正宗淮扬菜', 1, 0, '2022-07-30 14:40:56', '2022-07-30 14:40:56', 1, 1, 0);
INSERT INTO `dish` VALUES (53, '麻婆豆腐', 4, 25.00, NULL, '2e373890-5cda-462f-b60b-f2020d46b03c.jpeg', '正宗麻婆豆腐', 1, 0, '2022-07-30 14:41:59', '2022-07-30 14:41:59', 1, 1, 0);

-- ----------------------------
-- Table structure for dish_flavor
-- ----------------------------
DROP TABLE IF EXISTS `dish_flavor`;
CREATE TABLE `dish_flavor`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dish_id` int NOT NULL COMMENT '菜品',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '口味名称',
  `value` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '口味数据list',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` int NOT NULL COMMENT '创建人',
  `update_user` int NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dish_id`(`dish_id`, `name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜品口味关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dish_flavor
-- ----------------------------
INSERT INTO `dish_flavor` VALUES (32, 44, '温度', '[\"正常冰\",\"少冰\",\"常温\"]', '2022-07-30 14:27:15', '2022-07-30 14:27:15', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (33, 45, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"特辣\"]', '2022-07-30 14:28:38', '2022-07-30 14:28:38', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (34, 46, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"特辣\"]', '2022-07-30 14:29:48', '2022-07-30 14:29:48', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (35, 47, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"特辣\"]', '2022-07-30 14:31:29', '2022-07-30 14:31:29', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (36, 47, '忌口', '[\"不要蒜\",\"不要香菜\",\"不要葱\",\"不吃辣\"]', '2022-07-30 14:31:29', '2022-07-30 14:31:29', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (37, 48, '温度', '[\"正常冰\",\"少冰\",\"常温\",\"热\"]', '2022-07-30 14:33:36', '2022-07-30 14:33:36', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (38, 49, '忌口', '[\"不要蒜\",\"不要香菜\",\"不要葱\",\"不吃辣\"]', '2022-07-30 14:35:50', '2022-07-30 14:35:50', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (39, 50, '辣度', '[\"不辣\",\"微辣\",\"中辣\"]', '2022-07-30 14:37:47', '2022-07-30 14:37:47', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (40, 53, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"特辣\"]', '2022-07-30 14:41:59', '2022-07-30 14:41:59', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (41, 53, '忌口', '[\"不要蒜\",\"不要香菜\",\"不要葱\",\"不吃辣\"]', '2022-07-30 14:41:59', '2022-07-30 14:41:59', 1, 1, 0);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '性别',
  `id_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '身份证号',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态 0:禁用，1:正常',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` int NOT NULL COMMENT '创建人',
  `update_user` int NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '员工信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '管理员', 'user', '8914de686ab28dc22f30d3d8e107ff6c', '13605678648', '1', '111111111111111111', 1, '2022-07-23 18:48:35', '2022-07-25 13:51:50', 1, 1);

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名字',
  `image` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图片',
  `order_id` bigint NOT NULL COMMENT '订单id',
  `dish_id` bigint NULL DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint NULL DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '口味',
  `number` int NOT NULL DEFAULT 1 COMMENT '数量',
  `amount` decimal(10, 2) NOT NULL COMMENT '金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '订单明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint NOT NULL COMMENT '主键',
  `number` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '订单号',
  `status` int NOT NULL DEFAULT 1 COMMENT '订单状态 1待付款，2待派送，3已派送，4已完成，5已取消',
  `user_id` bigint NOT NULL COMMENT '下单用户',
  `address_book_id` bigint NOT NULL COMMENT '地址id',
  `order_time` datetime(0) NOT NULL COMMENT '下单时间',
  `checkout_time` datetime(0) NOT NULL COMMENT '结账时间',
  `pay_method` int NOT NULL DEFAULT 1 COMMENT '支付方式 1微信,2支付宝',
  `amount` decimal(10, 2) NOT NULL COMMENT '实收金额',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `consignee` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for setmeal
-- ----------------------------
DROP TABLE IF EXISTS `setmeal`;
CREATE TABLE `setmeal`  (
  `id` bigint NOT NULL COMMENT '主键',
  `category_id` bigint NULL DEFAULT NULL COMMENT '菜品分类id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '套餐名称',
  `price` decimal(10, 2) NOT NULL COMMENT '套餐价格',
  `status` int NULL DEFAULT NULL COMMENT '状态 0:停用 1:启用',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编码',
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述信息',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图片',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_setmeal_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '套餐' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of setmeal
-- ----------------------------
INSERT INTO `setmeal` VALUES (1, NULL, '儿童套餐A计划', 4000.00, 1, '', '', '61d20592-b37f-4d72-a864-07ad5bb8f3bb.jpg', '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1, 1, 0);

-- ----------------------------
-- Table structure for setmeal_dish
-- ----------------------------
DROP TABLE IF EXISTS `setmeal_dish`;
CREATE TABLE `setmeal_dish`  (
  `id` bigint NOT NULL COMMENT '主键',
  `setmeal_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '套餐id ',
  `dish_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '菜品id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜品名称 （冗余字段）',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '菜品原价（冗余字段）',
  `copies` int NOT NULL COMMENT '份数',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '套餐菜品关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of setmeal_dish
-- ----------------------------
INSERT INTO `setmeal_dish` VALUES (1415580119052894209, '1415580119015145474', '1397862198033297410', '老火靓汤', 49800.00, 1, 0, '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);
INSERT INTO `setmeal_dish` VALUES (1415580119061282817, '1415580119015145474', '1413342036832100354', '北冰洋', 500.00, 1, 0, '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);
INSERT INTO `setmeal_dish` VALUES (1415580119069671426, '1415580119015145474', '1413385247889891330', '米饭', 200.00, 1, 0, '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `image` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图片',
  `user_id` bigint NOT NULL COMMENT '主键',
  `dish_id` bigint NULL DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint NULL DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '口味',
  `number` int NOT NULL DEFAULT 1 COMMENT '数量',
  `amount` decimal(10, 2) NOT NULL COMMENT '金额',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '购物车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '性别',
  `id_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证号',
  `avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像',
  `status` int NULL DEFAULT 0 COMMENT '状态 0:禁用，1:正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
