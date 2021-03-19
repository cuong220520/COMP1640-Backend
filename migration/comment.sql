CREATE TABLE `comp_1640`.`comment`(
    `id` INT AUTO_INCREMENT NOT NULL,
    `content` VARCHAR(255),
    `user_id` INT,
    `article_id` INT,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`user_id`) REFERENCES `comp_1640`.`user`(id),
    FOREIGN KEY(`article_id`) REFERENCES `comp_1640`.`article`(id)
);