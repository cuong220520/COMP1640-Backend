package com.greenwich.comp1640.dao;

import com.greenwich.comp1640.model.Role;
import com.greenwich.comp1640.repository.readonly.RoleRORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class RoleDao extends BaseDao<Role, Long> {

    private final RoleRORepository roleRORepository;

    @Autowired
    public RoleDao(RoleRORepository roleRORepository) {
        super(null, roleRORepository);
        this.roleRORepository = roleRORepository;
    }

    public Optional<Role> findById(Long id) {
        return this.roleRORepository.findById(id);
    }

    public List<String> getRolesNameByUserId(Long userId) {
        return this.roleRORepository.getRolesByUserId(userId);
    }

}