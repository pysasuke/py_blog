-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(100) DEFAULT NULL COMMENT '系统url',
  `parentId` int(10) DEFAULT NULL COMMENT ' 父id 关联sys_menu.id',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除,0=未删除，1=已删除',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` TIMESTAMP NOT NULL COMMENT '修改时间',
  `rank` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `actions` varchar(500) DEFAULT '0' COMMENT '注册Action 按钮|分隔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `email` varchar(50) NOT NULL COMMENT '邮箱也是登录帐号',
  `pwd` varchar(50) DEFAULT NULL COMMENT '登录密码',
  `nickName` varchar(50) DEFAULT NULL COMMENT '昵称',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态 0=可用,1=禁用',
  `loginCount` int(11) DEFAULT NULL COMMENT '登录总次数',
  `loginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '删除状态 0=未删除,1=已删除',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` TIMESTAMP NOT NULL COMMENT '修改时间',
  `createBy` int(11) DEFAULT NULL COMMENT '创建人',
  `updateBy` int(11) DEFAULT NULL COMMENT '修改人',
  `superAdmin` int(1) NOT NULL DEFAULT '0' COMMENT '是否超级管理员 0= 不是，1=是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

DROP TABLE IF EXISTS `user_score_check`;
CREATE TABLE `user_score_check` (
  `check_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `record_count` tinyint(4) NOT NULL,
  `check_time` datetime NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `task_id` bigint(20) NOT NULL,
  `status` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`check_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for advert_position
-- ----------------------------
DROP TABLE IF EXISTS `advert_position`;
CREATE TABLE `advert_position` (
  `position_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `position_name` varchar(32) NOT NULL,
  `page_id` tinyint(1) NOT NULL COMMENT '广告所在的页面.',
  `position` tinyint(1) NOT NULL COMMENT '在页面的位置.',
  PRIMARY KEY (`position_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for im_group
-- ----------------------------
DROP TABLE IF EXISTS `im_group`;
CREATE TABLE `im_group` (
  `group_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(12) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '群名称',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '类型.1.同龄群,2.同城群.',
  `scope_id` int(11) DEFAULT NULL COMMENT '入群要求范围id, 与type相关.type=1.宝宝年龄(月数), type=2.城市id.',
  `admin_id` bigint(20) NOT NULL COMMENT '群主用户id',
  `announcement` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '群公告',
  `intro` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '群描述',
  `invite_msg` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邀请发送的文字',
  `invite_agree` tinyint(1) NOT NULL DEFAULT '0' COMMENT '管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。',
  `join_mode` tinyint(1) NOT NULL DEFAULT '1' COMMENT '群建好后，sdk操作时，0不用验证，1需要验证,2不允许任何人加入。',
  `icon` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '群头像',
  `beinvitemode` tinyint(1) NOT NULL DEFAULT '0' COMMENT '被邀请人同意方式，0-需要同意(默认),1-不需要同意。',
  `invitemode` tinyint(1) NOT NULL DEFAULT '0' COMMENT '谁可以邀请他人入群，0-管理员(默认),1-所有人。',
  `uptinfomode` tinyint(1) NOT NULL DEFAULT '0' COMMENT '谁可以修改群资料，0-管理员(默认),1-所有人。',
  `upcustommode` tinyint(1) NOT NULL DEFAULT '0' COMMENT '谁可以更新群自定义属性，0-管理员(默认),1-所有人。',
  `is_full` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否满员 ，0-未满(默认),1-满员。',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否解散，0-正常,1-已解散。',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for im_group_tp_relation
-- ----------------------------
DROP TABLE IF EXISTS `im_group_tp_relation`;
CREATE TABLE `im_group_tp_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL,
  `third_party_id` varchar(32) NOT NULL COMMENT '第三方群组id。',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for im_group_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `im_group_user_relation`;
CREATE TABLE `im_group_user_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `group_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `reason` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '审核状态.0.待审核, 1.审核通过, 2.审核拒绝.',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for im_room
-- ----------------------------
DROP TABLE IF EXISTS `im_room`;
CREATE TABLE `im_room` (
  `room_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` bigint(20) NOT NULL COMMENT '聊天室属主id',
  `name` varchar(12) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '聊天室名称',
  `announcement` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公告',
  `broadcasturl` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '直播地址',
  `is_deleted` tinyint(1) NOT NULL DEFAULT FALSE COMMENT '是否关闭聊天室.0.正常, 1.关闭',
  `img_url` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for im_room_tp_relation
-- ----------------------------
DROP TABLE IF EXISTS `im_room_tp_relation`;
CREATE TABLE `im_room_tp_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_id` bigint(20) NOT NULL,
  `third_party_id` varchar(32) NOT NULL COMMENT '第三方聊天室id。',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for im_user
-- ----------------------------
DROP TABLE IF EXISTS `im_user`;
CREATE TABLE `im_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `third_party_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '第三方用户id。',
  `token` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '第三方用户token。',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
