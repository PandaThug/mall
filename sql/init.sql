SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,  # 用户编号
  `username` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 用户名
  `password` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 用户密码
  `type` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0-管理员; 1-卖家; 2-买家;',  # 用户类型
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_username`(`username`(20)) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,  # 商品编号
    `good_name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 商品名称
    `good_price` INT(11) NOT NULL,  # 商品价格
    `good_category` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT
        '0-女装; 1-内衣; 2-奢品; 3-女鞋; 4-男鞋; 5-箱包; 6-美妆; 7-饰品; 8-洗护; 9-男装; 10-运动; 11-百货; 12-手机; 13-数码;
         14-企业; 15-家装; 16-电器; 17-车品; 18-食品; 19-生鲜; 20-母婴; 21-医药; 22-保健; 23-进口;',  # 商品类别
    `good_introduction` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 商品详情
    `good_sales` INT(11) NOT NULL,  # 商品销量
    `good_options` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 商品选项
    `good_picture` BLOB(64000),  # 商品图片
    `good_store` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 所属店铺
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
    `create_time` TIMESTAMP NULL DEFAULT NULL,  # 商家注册时间
    `total_sales` INT(11) DEFAULT NULL,  # 商家店铺总销量
    `instruction` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 店铺简介
    `store_name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 店铺名称
    `store_id` INT(11) NOT NULL AUTO_INCREMENT,  # 商家店铺编号
    `good_id` INT(11) NOT NULL,  # 商家店铺包含的商品编号
    PRIMARY KEY (`store_id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
    `order_id` INT(11) NOT NULL AUTO_INCREMENT,  # 订单编号
    `user_id` INT(11) NOT NULL,  # 用户编号
    `good_id` INT(11) NOT NULL,  # 商品编号
    `purchase_quantity` INT(11) NOT NULL,  # 购买数量
    `good_name` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 商品名称
    `order_time` TIMESTAMP NULL DEFAULT NULL,  # 下单时间
    `order_status` INT(11) NOT NULL DEFAULT 0 COMMENT '0-未完成; 1-已完成未评价; 2-已完成已评价;',  # 订单状态
    `address` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 收货地址
    `tel_number` INT(11) NOT NULL,  # 买方电话
    `buyer_name` VARCHAR(255)CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 买方名称
    PRIMARY KEY (`order_id`) USING BTREE
)ENGINE = INNODB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,  # 评价编号
    `comment_time` TIMESTAMP NULL DEFAULT NULL,  # 评价时间
    `comment_score` INT(5) NOT NULL,  # 评价分数
    `comment_content` VARCHAR(255)CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 评价内容
    PRIMARY KEY (`id`) USING BTREE
)ENGINE = INNODB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;
