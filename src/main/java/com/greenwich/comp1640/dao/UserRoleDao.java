package com.greenwich.comp1640.dao;

import com.greenwich.comp1640.model.UserRole;
import com.greenwich.comp1640.repository.readonly.UserRoleRORepository;
import com.greenwich.comp1640.repository.readwrite.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDao extends BaseDao<UserRole, Long> {

    private final UserRoleRepository userRoleRepository;
    private final UserRoleRORepository userRoleRORepository;

    @Autowired
    public UserRoleDao(UserRoleRepository userRoleRepository, UserRoleRORepository userRoleRORepository) {
        super(userRoleRepository, userRoleRORepository);
        this.userRoleRepository = userRoleRepository;
        this.userRoleRORepository = userRoleRORepository;
    }

    public UserRole saveUserRole(UserRole userRole) {
        return this.userRoleRepository.save(userRole);
    }

}
