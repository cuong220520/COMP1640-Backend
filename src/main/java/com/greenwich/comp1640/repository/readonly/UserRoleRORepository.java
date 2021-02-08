package com.greenwich.comp1640.repository.readonly;

import com.greenwich.comp1640.config.datasource.ReadOnlyRepository;
import com.greenwich.comp1640.model.UserRole;
import org.springframework.stereotype.Repository;

@Repository
@ReadOnlyRepository
public interface UserRoleRORepository extends GenericRORepository<UserRole, Long> {
}
