package com.greenwich.comp1640.repository.readwrite;

import com.greenwich.comp1640.model.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends GenericRepository<UserRole, Long> {
}
