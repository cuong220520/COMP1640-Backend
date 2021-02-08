package com.greenwich.comp1640.security.jwt;

import com.greenwich.comp1640.dao.RoleDao;
import com.greenwich.comp1640.dao.UserDao;
import com.greenwich.comp1640.exception.CustomExceptions;
import com.greenwich.comp1640.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new CustomExceptions.BadRequestException(String.format("Can not find user with username: %s", username));
        }

        List<String> roles = roleDao.getRolesNameByUserId(user.getId());

        if (roles.isEmpty()) {
            throw new CustomExceptions.BadRequestException(String.format("Can not find role of this user with userId: %d", user.getId()));
        }

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        for (String role : roles) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            grantedAuthorityList.add(authority);
        }

        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorityList);

        return userDetails;
    }
}
