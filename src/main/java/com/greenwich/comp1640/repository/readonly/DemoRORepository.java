package com.greenwich.comp1640.repository.readonly;

import com.greenwich.comp1640.config.datasource.ReadOnlyRepository;
import com.greenwich.comp1640.model.Demo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@ReadOnlyRepository
public interface DemoRORepository extends GenericRORepository<Demo, Long>, JpaSpecificationExecutor<Demo> {
    Demo findByEmail(String email);
}
