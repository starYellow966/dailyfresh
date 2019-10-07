CREATE DATABASE dailyfresh;
USE dailyfresh;

CREATE TABLE
IF
	NOT EXISTS `df_user` (
	`user_id` INT NOT NULL auto_increment,
	`user_name` VARCHAR ( 128 ),
	`pwd` VARCHAR ( 128 ),
	`email` VARCHAR ( 128 ),
	`is_active` INT ( 2 ) DEFAULT '0' COMMENT '0:未激活 1：激活',
	`user_status` INT DEFAULT '0' COMMENT '0:普通用户，1：超级管理员',
	PRIMARY KEY ( `user_id` )
	) ENGINE = INNODB auto_increment = 1 DEFAULT charset = utf8;

CREATE TABLE
IF
	NOT EXISTS `df_address` (
	`addr_id` INT NOT NULL auto_increment,
	`addressee` VARCHAR ( 128 ) COMMENT '收件人',
	`address` VARCHAR ( 1024 ) COMMENT '收件地址',
	`phone` VARCHAR ( 128 ) COMMENT '联系方式',
	`is_default` INT ( 2 ) DEFAULT '0' COMMENT '0：非默认地址，1：默认地址',
	`user_id` INT NOT NULL,
	PRIMARY KEY ( `addr_id` ),
	CONSTRAINT FOREIGN KEY ( `user_id` ) REFERENCES `df_user` ( `user_id` )
	) ENGINE = INNODB auto_increment = 1 DEFAULT charset = utf8;

CREATE TABLE
IF
	NOT EXISTS `df_goods_type` (
	`type_id` INT NOT NULL auto_increment,
	`type_name` VARCHAR ( 128 ),
	`priority` INT DEFAULT '0' COMMENT '显示优先级，值越大，优先级越高',
	`is_delete` int default '0',
	PRIMARY KEY ( `type_id` )
	) ENGINE = INNODB auto_increment = 1 DEFAULT charset = utf8;

CREATE TABLE
IF
	NOT EXISTS `df_good_spu` (
	`spu_id` INT auto_increment,
	`spu_name` VARCHAR ( 1024 ),
	`spu_detail` VARCHAR ( 2048 ),
	`type_id` INT,
	`create_time` datetime,
	`last_edit_time` datetime,
	`is_delete` int default '0',
	PRIMARY KEY ( `spu_id` ),
	CONSTRAINT FOREIGN KEY ( `type_id` ) REFERENCES `df_goods_type` ( `type_id` )
	) ENGINE = INNODB auto_increment = 1 DEFAULT charset = utf8;

CREATE TABLE
IF
	NOT EXISTS `df_headline` (
	`line_id` INT auto_increment,
	`spu_id` INT NOT NULL,
	`img_addr` VARCHAR ( 1024 ) NOT NULL,
	`priority` INT DEFAULT '0',
	PRIMARY KEY ( `line_id` ),
	CONSTRAINT FOREIGN KEY ( `spu_id` ) REFERENCES df_good_spu ( `spu_id` )
	) ENGINE = INNODB auto_increment = 1 DEFAULT charset = utf8;

CREATE TABLE
IF
	NOT EXISTS `df_good_sku` (
	`sku_id` INT ( 64 ) auto_increment,
	`sku_name` VARCHAR ( 1024 ) NOT NULL,
	`sock` INT COMMENT '库存',
	`price` DECIMAL ( 10, 2 ),
	`priority` INT DEFAULT '0',
	`spec` VARCHAR ( 1024 ) COMMENT '以json格式存储的属性，如{"内存":"2G","颜色":"红色"}',
	`sku_img_addr` VARCHAR ( 1024 ),
	`sku_brief_info` VARCHAR ( 1024 ),
	`spu_id` INT NOT NULL,
	`create_time` datetime,
	`last_edit_time` datetime,
	PRIMARY KEY ( `sku_id` ),
	CONSTRAINT FOREIGN KEY ( `spu_id` ) REFERENCES `df_good_spu` ( `spu_id` )
	) ENGINE = INNODB auto_increment = 1 DEFAULT charset = utf8;

CREATE TABLE
IF
	NOT EXISTS `df_order_detail` (
	`id` INT auto_increment,
	`order_id` VARCHAR ( 128 ),
	`addr_id` INT,
	`user_id` INT,
	`pay_method` INT DEFAULT '0' COMMENT '0：支付宝，1：微信，其他：未知',
	`pay_status` int default '0' comment '0:待支付，1：已支付，2：取消，其他：未知',
	`total_amount` DECIMAL(10,2),
	`total_count` int,
	`freight` decimal(10,2),
	`create_time` datetime,
	PRIMARY key(`id`),
	unique(`order_id`),
	FOREIGN key(`addr_id`) references `df_address`(`addr_id`),
	FOREIGN key(`user_id`) references `df_user`(`user_id`)
	)ENGINE = INNODB auto_increment = 1 DEFAULT charset = utf8;

CREATE TABLE
IF
	NOT EXISTS `df_order_good` (
	`og_id` INT auto_increment,
	`order_id` VARCHAR ( 128 ),
	`sku_id` INT,
	`num` INT,
	`price` DECIMAL ( 10, 2 ),
	`comment` VARCHAR ( 1024 ),
	`create_time` datetime,
	`last_edit_time` datetime,
	PRIMARY KEY ( `og_id` ),
	FOREIGN KEY ( `order_id` ) REFERENCES `df_order_detail` ( `order_id` ),
	FOREIGN KEY ( `sku_id` ) REFERENCES `df_good_sku` ( `sku_id` )
	) ENGINE = INNODB auto_increment = 1 DEFAULT charset = utf8;

CREATE TABLE
IF
	NOT EXISTS `df_comment` (
	`comment_id` INT auto_increment,
	`order_id` varchar(128),
	`user_id` INT,
	`sku_id` INT,
	`comment` VARCHAR ( 2048 ),
	`create_time` datetime,
	PRIMARY KEY ( `comment_id` ),
	FOREIGN KEY ( `order_id` ) REFERENCES `df_order_detail` ( `order_id` ),
	FOREIGN KEY ( `user_id` ) REFERENCES `df_user` ( `user_id` ),
	FOREIGN KEY ( `sku_id` ) REFERENCES `df_good_sku` ( `sku_id` )
	) ENGINE = INNODB auto_increment = 1 DEFAULT charset = utf8;