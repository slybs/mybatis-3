/* === user === */
CREATE TABLE `jwxa_user_0` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_user_1` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_user_2` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_user_3` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_user_4` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_user_5` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_user_6` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_user_7` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_user_8` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_user_9` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MRG_MyISAM DEFAULT CHARSET=utf8 UNION=(`jwxa_user_0`,`jwxa_user_1`,`jwxa_user_2`,`jwxa_user_3`,`jwxa_user_4`,`jwxa_user_5`,`jwxa_user_6`,`jwxa_user_7`,`jwxa_user_8`,`jwxa_user_9`);

/* --------------------------------------------- */

/* === device === */
CREATE TABLE `jwxa_device_0` (
  `imei` char(15) NOT NULL DEFAULT '',
  PRIMARY KEY (`imei`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_device_1` (
  `imei` char(15) NOT NULL DEFAULT '',
  PRIMARY KEY (`imei`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_device_2` (
  `imei` char(15) NOT NULL DEFAULT '',
  PRIMARY KEY (`imei`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_device_3` (
  `imei` char(15) NOT NULL DEFAULT '',
  PRIMARY KEY (`imei`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_device_4` (
  `imei` char(15) NOT NULL DEFAULT '',
  PRIMARY KEY (`imei`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_device_5` (
  `imei` char(15) NOT NULL DEFAULT '',
  PRIMARY KEY (`imei`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_device_6` (
  `imei` char(15) NOT NULL DEFAULT '',
  PRIMARY KEY (`imei`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_device_7` (
  `imei` char(15) NOT NULL DEFAULT '',
  PRIMARY KEY (`imei`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_device_8` (
  `imei` char(15) NOT NULL DEFAULT '',
  PRIMARY KEY (`imei`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_device_9` (
  `imei` char(15) NOT NULL DEFAULT '',
  PRIMARY KEY (`imei`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `jwxa_device` (
  `imei` char(15) NOT NULL DEFAULT '',
  PRIMARY KEY (`imei`)
) ENGINE=MRG_MyISAM DEFAULT CHARSET=utf8 UNION=(`jwxa_device_0`,`jwxa_device_1`,`jwxa_device_2`,`jwxa_device_3`,`jwxa_device_4`,`jwxa_device_5`,`jwxa_device_6`,`jwxa_device_7`,`jwxa_device_8`,`jwxa_device_9`);

/* --------------------------------------------- */

/* === sequence === */
CREATE TABLE `jwxa_sequence` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `stub` char(1) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `stub` (`stub`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
