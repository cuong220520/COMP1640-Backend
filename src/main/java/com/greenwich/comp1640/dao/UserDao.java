package com.greenwich.comp1640.dao;

import com.greenwich.comp1640.model.User;
import com.greenwich.comp1640.repository.readonly.UserRORepository;
import com.greenwich.comp1640.repository.readwrite.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDao extends BaseDao<User, Long> {
    private final UserRepository userRepository;
    private final UserRORepository userRORepository;

    @Autowired
    public UserDao(UserRepository userRepository, UserRORepository userRORepository) {
        super(userRepository, userRORepository);
        this.userRepository = userRepository;
        this.userRORepository = userRORepository;
    }

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    public User findByUsername(String username) {
        return this.userRORepository.findByUsername(username);
    }

    public Optional<User> findById(Long id) {
        return this.userRORepository.findById(id);
    }

    public List<User> findByFacultyAndRole(Long roleId) {
        return this.userRORepository.findByFacultyAndRole(roleId);
    }
}