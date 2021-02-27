DROP TABLE IF EXISTS comp_1640.`faculty`;

CREATE TABLE comp_1640.`faculty` (
	`code` VARCHAR(4),
    `name` VARCHAR(100),
    `description` VARCHAR(255),
    `coordinator_id` INT,
    PRIMARY KEY (`code`),
    FOREIGN KEY (`coordinator_id`) REFERENCES comp_1640.`user`(id)
);