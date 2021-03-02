package com.greenwich.comp1640.dao;

import com.greenwich.comp1640.model.Faculty;
import com.greenwich.comp1640.repository.readonly.FacultyRORepository;
import com.greenwich.comp1640.repository.readwrite.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FacultyDao extends BaseDao<Faculty, String> {

    private final FacultyRepository facultyRepository;
    private final FacultyRORepository facultyRORepository;

    @Autowired
    public FacultyDao(FacultyRepository facultyRepository, FacultyRORepository facultyRORepository) {
        super(facultyRepository, facultyRORepository);
        this.facultyRepository = facultyRepository;
        this.facultyRORepository = facultyRORepository;
    }

    public Faculty findByCode(String code) {
        return this.facultyRORepository.findByCode(code);
    }

    public Faculty findByCoordinatorUsername(String username) {
        return this.facultyRORepository.findByCoordinatorManagerUsername(username);
    }

    public List<Faculty> findAllFaculty() {
        return this.facultyRORepository.findAll();
    }

    public Faculty saveFaculty(Faculty faculty) {
        return this.facultyRepository.save(faculty);
    }
    
}
