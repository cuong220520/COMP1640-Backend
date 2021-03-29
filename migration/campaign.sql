DROP TABLE IF EXISTS comp_1640.`campaign`;
CREATE TABLE comp_1640.`campaign` (
    `code` VARCHAR(255),
    `submit_deadline` DATETIME,
    `edit_deadline` DATETIME,
    `start_date` DATETIME,
    `admin_id` INT,
    PRIMARY KEY(`code`),
    FOREIGN KEY(`admin_id`) REFERENCES comp_1640.`user`(id)
);

ALTER TABLE comp_1640.`campaign`
ADD COLUMN `status` ENUM('ACTIVE', 'DISABLE') DEFAULT 'DISABLE';