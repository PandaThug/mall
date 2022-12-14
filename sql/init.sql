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
  `type` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0-管理员; 1-买家; 2-卖家;',  # 用户类型
  `account` INT(11) NOT NULL,  # 用户账户余额
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_username`(`username`(20)) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,  # 商品编号
    `good_name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 商品名称
    `good_price` INT(11) NOT NULL,  # 商品价格
    `good_category` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT
        '0-女装; 1-内衣; 2-奢品; 3-女鞋; 4-男鞋; 5-箱包; 6-美妆; 7-饰品; 8-洗护; 9-男装; 10-运动; 11-百货; 12-手机; 13-数码;
         14-企业; 15-家装; 16-电器; 17-车品; 18-食品; 19-生鲜; 20-母婴; 21-医药; 22-保健; 23-进口;',  # 商品类别
    `good_introduction` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 商品详情
    `good_sales` INT(11) NOT NULL,  # 商品销量
    `good_options` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 商品选项
    `good_picture` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 商品图片
    `good_store` INT(11) NOT NULL,  # 所属店铺
    `good_comment_count` INT(11) NOT NULL,  # 商品评价数
    `real_inventory` INT(11) NOT NULL,  # 商品实际库存
    `virtual_inventory` INT(11) NOT NULL,  # 商品虚拟库存
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
    `store_id` INT(11) NOT NULL,  # 商家店铺编号
    `store_name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 店铺名称
    `total_sales` INT(11) DEFAULT NULL,  # 商家店铺总销量
    `instruction` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 店铺简介
    PRIMARY KEY (`store_id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
    `order_id` INT(11) NOT NULL AUTO_INCREMENT,  # 订单编号
    `user_id` INT(11) NOT NULL,  # 用户编号
    `shop_id` INT(11) NOT NULL,  # 店铺编号
    `good_id` INT(11) NOT NULL,  # 商品编号
    `purchase_quantity` INT(11) NOT NULL,  # 购买数量
    `total_price` INT(11) NOT NULL,  # 订单总金额
    `good_name` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 商品名称
    `good_option` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 商品选项
    `order_status` INT(11) NOT NULL DEFAULT -1 COMMENT '-1-未支付; 0-未发货; 1-待收货; 2-待评价; 3-已完成;',  # 订单状态
    `address` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 收货地址
    `tel_number` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 买方电话
    `buyer_name` VARCHAR(255)CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 买方名称
    PRIMARY KEY (`order_id`) USING BTREE
)ENGINE = INNODB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,  # 评价编号
    `comment_score` INT(5) NOT NULL,  # 评价分数
    `comment_content` VARCHAR(255)CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 评价内容
    `comment_store_id` INT(11) NOT NULL,  # 评价对应的店铺
    `comment_good_id` INT(11) NOT NULL,  # 评价对应店铺里的商品
    PRIMARY KEY (`id`) USING BTREE
)ENGINE = INNODB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;
