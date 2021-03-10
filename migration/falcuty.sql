DROP TABLE IF EXISTS comp_1640.`faculty`;

CREATE TABLE comp_1640.`faculty` (
	`code` VARCHAR(4),
    `name` VARCHAR(100),
    `description` VARCHAR(255),
    `coordinator_id` INT,
    PRIMARY KEY (`code`),
    FOREIGN KEY (`coordinator_id`) REFERENCES comp_1640.`user`(id)
);

INSERT INTO comp_1640.`faculty`
VALUES
('COMP', 'Computer Science and Software', 'Computer Science and Software', 1),
('BUSI', 'Business', 'Business', 1),
('DESI', 'Design', 'Design', 1);