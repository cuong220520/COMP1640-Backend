ALTER TABLE comp_1640.`user`
ADD COLUMN `first_name` VARCHAR(100),
ADD COLUMN `last_name` VARCHAR(100),
ADD COLUMN `date_of_birth` DATETIME,
ADD COLUMN `phone_number` VARCHAR(100),
ADD COLUMN `faculty_code` VARCHAR(4),
ADD FOREIGN KEY(faculty_code) REFERENCES comp_1640.`faculty`(`code`);
