CREATE TABLE `files` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` longblob DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
 CREATE TABLE `themes` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `text` varchar(255) NOT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
CREATE TABLE `flashcards` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image` longblob DEFAULT NULL,
  `word` varchar(255) NOT NULL,
  `theme_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfn1ehpu5hcyis1qgc0wq6kv45` (`theme_id`),
  CONSTRAINT `FKfn1ehpu5hcyis1qgc0wq6kv45` FOREIGN KEY (`theme_id`) REFERENCES `themes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 CREATE TABLE `tests` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `title` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
CREATE TABLE `questions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) DEFAULT NULL,
  `test_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoc6xkgj16nhyyes4ath9dyxxw` (`test_id`),
  CONSTRAINT `FKoc6xkgj16nhyyes4ath9dyxxw` FOREIGN KEY (`test_id`) REFERENCES `tests` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
CREATE TABLE `test_options` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `correct` bit(1) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `question_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK76jy43giumehhyc3dxfwphvcp` (`question_id`),
  CONSTRAINT `FK76jy43giumehhyc3dxfwphvcp` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;



