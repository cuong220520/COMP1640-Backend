package com.greenwich.comp1640.repository.readonly;

import com.greenwich.comp1640.config.datasource.ReadOnlyRepository;
import com.greenwich.comp1640.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ReadOnlyRepository
public interface RoleRORepository extends GenericRORepository<Role, Long> {

    @Query(value = "SELECT ur.role.name FROM UserRole ur WHERE ur.user.id = :userId")
    List<String> getRolesByUserId(@Param("userId") Long userId);

}
