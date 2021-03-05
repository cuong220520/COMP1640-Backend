DROP TABLE IF EXISTS comp_1640.`article`;

CREATE TABLE comp_1640.`article`(
	`id` INT auto_increment NOT NULL,
	`name` VARCHAR(255),
	`message` VARCHAR (255),
    `status` ENUM('PENDING', 'ACCEPTED', 'DENIED') DEFAULT 'PENDING',
    `image_url` VARCHAR(255),
    `document_url` VARCHAR(255),
    `user_id` INT,
    `faculty_code` VARCHAR(255),
    `campaign_code` VARCHAR(255),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES comp_1640.`user`(`id`),
    FOREIGN KEY (`faculty_code`) REFERENCES comp_1640.`faculty`(`code`),
    FOREIGN KEY (`campaign_code`) REFERENCES comp_1640.`campaign`(`code`)
);