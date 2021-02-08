# COMP1640-Backend
This project is COMP1640 coursework of group: Cuong, Son, Tu, Tuan Anh.

# Migration
Open project folder, go to the migration folder and run the ```comp_1640.sql``` file in MySQL Workbench.

# Config
Open file config.yaml and setting spring.datasource.username & password & port according to your MySQL setting.
Open edit run/debug configurations (InteliJ) and set:
- ```Main Class = com.greenwich.comp1640.GraduationprojectApplication```
- ```VM options = -DSpring.config.location=config.yaml```
- ```Use classpath of module = graduationproject.main```
- ```JDK = 1.8```
