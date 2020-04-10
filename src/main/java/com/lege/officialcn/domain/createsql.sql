
# user 表
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(1024) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `address` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


# topicEntity 表

CREATE TABLE `topicEntity` (
  `topicId` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'topicId',
  `title` VARCHAR(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'title',
  `summary` VARCHAR(500) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'summary',
  `picUrl` VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'picUrl',

  `topicType` INT(11) UNSIGNED NOT NULL COMMENT '来源ID:各个爬虫来源的以及运营给的',
  `recommend` INT(11) UNSIGNED NOT NULL COMMENT '文章分类id',
  `status` INT(11) UNSIGNED NOT NULL DEFAULT '100' COMMENT '文章权重',

  `statusCode` INT(11) UNSIGNED NOT NULL COMMENT '来源ID:各个爬虫来源的以及运营给的',
  `source` INT(11) UNSIGNED NOT NULL COMMENT '文章分类id',

  `addTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '原文章创建时间',
  `modifyTime` DATETIME DEFAULT NULL COMMENT '修改时间',
  `updateTime` DATETIME DEFAULT NULL COMMENT '修改时间',

  PRIMARY KEY (`topicId`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;