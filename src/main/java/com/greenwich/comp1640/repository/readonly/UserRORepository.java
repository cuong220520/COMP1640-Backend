package com.greenwich.comp1640.repository.readonly;

import com.greenwich.comp1640.config.datasource.ReadOnlyRepository;
import com.greenwich.comp1640.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ReadOnlyRepository
public interface UserRORepository extends GenericRORepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByUsername(String username);

    @Query(value = "SELECT u FROM User u " +
            "INNER JOIN UserRole ur ON u.id = ur.user.id " +
            "WHERE ur.role.id = :role_id"
    )
    List<User> findByFacultyAndRole(@Param("role_id") Long roleId);

}
