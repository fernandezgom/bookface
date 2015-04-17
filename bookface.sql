CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


insert into `users`(`username`,`password`,`enabled`,`email`) values ('alex','123456',1,'fasffasd@hotmail.com');
insert into `users`(`username`,`password`,`enabled`,`email`) values ('garamond','123456',1,'fasdf@hotmail.com');
insert into `users`(`username`,`password`,`enabled`,`email`) values ('pedrulo','12345',1,'xvcbc@hotmail.com');
insert into `users`(`username`,`password`,`enabled`,`email`) values ('tludmetal','12345',1,'rrrr@hotmail.com');
insert into `users`(`username`,`password`,`enabled`,`email`) values ('tludmetal2','12345',1,'sdgn@hotmail.com' );

CREATE TABLE `user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `ROLE` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`ROLE`,`username`),
  KEY `fk_username_idx` (`username`),
  CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;


insert into `user_roles`(`user_role_id`,`username`,`ROLE`) values (2,'mkyong','ROLE_ADMIN');
insert into `user_roles`(`user_role_id`,`username`,`ROLE`) values (7,'tludmetal','ROLE_ADMIN');
insert into `user_roles`(`user_role_id`,`username`,`ROLE`) values (3,'alex','ROLE_USER');
insert into `user_roles`(`user_role_id`,`username`,`ROLE`) values (1,'mkyong','ROLE_USER');
insert into `user_roles`(`user_role_id`,`username`,`ROLE`) values (6,'pedrulo','ROLE_USER');
insert into `user_roles`(`user_role_id`,`username`,`ROLE`) values (10,'tludmetal2','ROLE_USER');

CREATE TABLE `author` (
  `id_author` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `bio` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`id_author`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


insert into `author`(`id_author`,`name`,`bio`) values (1,'adsfasdfasdf','asfd		asdfasdf
afsdf

fasdfasdfasdfasdfasdfasdfasdfasdfasfd
asdfasdfasdfasd

afsdfassssssssssssssssssssssssssssssssssssssssssssssssssss		');
insert into `author`(`id_author`,`name`,`bio`) values (2,'joseluis','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.		');

CREATE TABLE `book` (
  `id_book` int(11) NOT NULL AUTO_INCREMENT,
  `id_author` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `synopsis` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id_book`),
  KEY `fk_author` (`id_author`),
  CONSTRAINT `fk_author` FOREIGN KEY (`id_author`) REFERENCES `author` (`id_author`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


insert into `book`(`id_book`,`id_author`,`name`,`synopsis`) values (1,2,'Atila','Buen libro');
insert into `book`(`id_book`,`id_author`,`name`,`synopsis`) values (2,2,'huno','Mal libro');
insert into `book`(`id_book`,`id_author`,`name`,`synopsis`) values (3,2,'malo','muy malo');
insert into `book`(`id_book`,`id_author`,`name`,`synopsis`) values (4,2,'book2','laksjdfasdfjasldfjasjdlfjasldfjasdlfljjfjasldfjlasjdf						');
insert into `book`(`id_book`,`id_author`,`name`,`synopsis`) values (5,2,'sadfasdfasd','fasdfasdfasdfasdf	');

CREATE TABLE `comment` (
  `id_comment` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `id_book` int(11) DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comment` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`id_comment`),
  KEY `fk_book` (`id_book`),
  KEY `fk_user` (`username`),
  CONSTRAINT `fk_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`),
  CONSTRAINT `fk_user` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `likes` (
  `username` varchar(45) DEFAULT NULL,
  `id_book` int(11) DEFAULT NULL,
  `id_likes` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_likes`),
  KEY `fk_book1` (`id_book`),
  KEY `fk_user1` (`username`),
  CONSTRAINT `fk_book1` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`),
  CONSTRAINT `fk_user1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


insert into `likes`(`username`,`id_book`,`id_likes`) values ('tludmetal2',2,1);
insert into `likes`(`username`,`id_book`,`id_likes`) values ('tludmetal2',1,2);
insert into `likes`(`username`,`id_book`,`id_likes`) values ('tludmetal2',3,3);


