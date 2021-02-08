package com.greenwich.comp1640.repository.readwrite;

import com.greenwich.comp1640.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User, Long> {
}
