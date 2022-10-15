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
  `salt` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 密码加密
  `email` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 用户邮箱
  `type` INT(11) NULL DEFAULT NULL COMMENT '0-管理员; 1-卖家; 2-买家;',  # 用户类型
  `status` INT(11) NULL DEFAULT NULL COMMENT '0-未激活; 1-已激活;',  # 用户状态
  `activation_code` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 激活码
  `header_url` VARCHAR(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 头像
  `create_time` TIMESTAMP NULL DEFAULT NULL,  # 创建时间
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_username`(`username`(20)) USING BTREE,
  INDEX `index_email`(`email`(20)) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,  # 商品编号
    `good_name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 商品名称
    `good_price` INT(11) NOT NULL,  # 商品价格
    `good_category` INT(11) NULL DEFAULT NULL COMMENT '0-女装; 1-内衣; 2-奢品; 3-女鞋; 4-男鞋; 5-箱包; 6-美妆; 7-饰品; 8-洗护;
      9-男装; 10-运动; 11-百货; 12-手机; 13-数码; 14-企业; 15-家装; 16-电器; 17-车品; 18-食品; 19-生鲜; 20-母婴; 21-医药; 22-保健;
      23-进口;',
    `good_introduction` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  # 商品简介
    `good_sales` INT(11) NOT NULL,  # 商品销量
    `good_picture` BLOB(64000),  # 商品图片
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;


