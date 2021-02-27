package com.greenwich.comp1640.repository.readwrite;

import com.greenwich.comp1640.model.Faculty;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends GenericRepository<Faculty, String> {
}
