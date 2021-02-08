package com.greenwich.comp1640.repository.readwrite;

import com.greenwich.comp1640.model.Demo;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends GenericRepository<Demo, Long> {
}
