# 创建mybatis数据库
CREATE DATABASE mybatis DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
# 创建一个名为country 的表并插入一些简单的数据
# 表示使用mybatis数据库
CREATE TABLE `country`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`countryname` VARCHAR(255) NULL,
	`countrycode` VARCHAR(255) NULL,
	PRIMARY KEY(`id`)
);
INSERT country(`countryname`,`countrycode`)
VALUES('中国','CN'),('美国','US'),('俄罗斯','RU'),('英国','GB'),('法国','FR');