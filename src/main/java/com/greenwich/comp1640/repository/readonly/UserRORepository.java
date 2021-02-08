package com.greenwich.comp1640.repository.readonly;

import com.greenwich.comp1640.config.datasource.ReadOnlyRepository;
import com.greenwich.comp1640.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@ReadOnlyRepository
public interface UserRORepository extends GenericRORepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByUsername(String username);

}
