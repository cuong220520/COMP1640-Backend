package com.greenwich.comp1640.repository.readonly;

import com.greenwich.comp1640.model.Faculty;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRORepository extends GenericRORepository<Faculty, String> {

    Faculty findByCode(String code);

    Faculty findByCoordinatorManagerId(Long coordinatorId);

}
