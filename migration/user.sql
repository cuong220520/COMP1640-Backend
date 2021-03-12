ALTER TABLE comp_1640.`user`
ADD COLUMN `first_name` VARCHAR(100),
ADD COLUMN `last_name` VARCHAR(100),
ADD COLUMN `date_of_birth` DATETIME,
ADD COLUMN `phone_number` VARCHAR(100),
ADD COLUMN `faculty_code` VARCHAR(4),
ADD FOREIGN KEY(faculty_code) REFERENCES comp_1640.`faculty`(`code`);

UPDATE comp_1640.`user`
SET `first_name` = 'John',
    `last_name` = 'Doe',
    `date_of_birth` = '1970-01-01',
    `phone_number` = '0123456789',
    `faculty_code` = 'COMP'
WHERE `id` = 1;

ALTER TABLE comp_1640.`user`
ADD COLUMN `email` VARCHAR(100);

UPDATE comp_1640.`user`
SET `email` = 'phamthaison11@gmail.com'
WHERE `id` = 1;
