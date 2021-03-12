package com.greenwich.comp1640.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.greenwich.comp1640.dao.RoleDao;
import com.greenwich.comp1640.dao.UserDao;
import com.greenwich.comp1640.dao.UserRoleDao;
import com.greenwich.comp1640.dto.mapper.UserMapper;
import com.greenwich.comp1640.dto.request.user.AuthRequestDto;
import com.greenwich.comp1640.dto.request.user.SignupRequestDto;
import com.greenwich.comp1640.model.Role;
import com.greenwich.comp1640.model.UserRole;
import com.greenwich.comp1640.response.GeneralResponse;
import com.greenwich.comp1640.response.ResponseFactory;
import com.greenwich.comp1640.security.jwt.util.JWTUtil;
import com.greenwich.comp1640.service.abstr.UserService;
import com.greenwich.comp1640.util.constant.ResponseStatusCodeConst;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDao userRoleDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntity<GeneralResponse<Object>> login(AuthRequestDto authRequestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(),
                    authRequestDto.getPassword()));

            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestDto.getUsername());

            String accessToken = jwtUtil.generateToken(userDetails);

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode response = mapper.createObjectNode();
            response.put("access_token", accessToken);
            response.put("username", userDetails.getUsername());

            return responseFactory.success(response);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return responseFactory.fail(ex.getMessage(), ResponseStatusCodeConst.INTERNAL_SERVER_ERROR, null);
        }
    }

    public ResponseEntity<GeneralResponse<Object>> refreshToken(Claims claims) {
        try {
            Map<String, Object> map = jwtUtil.getMapFromJwtClaims(claims);

            String token = jwtUtil.createToken(map, map.get("sub").toString());

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode response = mapper.createObjectNode();
            response.put("access_token", token);
            response.put("username", jwtUtil.extractUsername(token));

            return responseFactory.success(response);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return responseFactory.fail(ex.getMessage(), ResponseStatusCodeConst.INTERNAL_SERVER_ERROR, null);
        }
    }

    public ResponseEntity<GeneralResponse<Object>> loadUser() {
        try {
            org.springframework.security.core.userdetails.User principal =
                    (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode response = mapper.createObjectNode();

            ArrayNode authorities = mapper.valueToTree(principal.getAuthorities());

            com.greenwich.comp1640.model.User loadedUser = userDao.findByUsername(principal.getUsername());

            response.put("username", principal.getUsername());
            response.putPOJO("details", UserMapper.toDto(loadedUser));
            response.putArray("authorities").addAll(authorities);

            return responseFactory.success(response);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return responseFactory.fail(ex.getMessage(), ResponseStatusCodeConst.INTERNAL_SERVER_ERROR, null);
        }
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> signup(SignupRequestDto signupRequestDto) {
        try {
            com.greenwich.comp1640.model.User existedUser = userDao.findByUsername(signupRequestDto.getUsername());

            if(existedUser != null) {
                return responseFactory.fail("Username has already existed", ResponseStatusCodeConst.DUPLICATE_ERROR, null);
            }

            for (Long rqRoleId : signupRequestDto.getRoles()) {
                Optional<Role> optRole = roleDao.findById(rqRoleId);

                if (!optRole.isPresent()) {
                    return responseFactory.fail("Invalid role id", ResponseStatusCodeConst.INVALID_ROLE_ID, null);
                }
            }

            com.greenwich.comp1640.model.User newUser = new com.greenwich.comp1640.model.User();
            newUser.setUsername(signupRequestDto.getUsername());
            newUser.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));

            newUser = userDao.saveUser(newUser);

            for (Long roleId : signupRequestDto.getRoles()) {
                UserRole userRole = new UserRole();
                userRole.setRole(roleDao.findById(roleId).get());
                userRole.setUser(newUser);

                userRoleDao.saveUserRole(userRole);
            }

            return responseFactory.success("Signup successfully");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return responseFactory.fail(ex.getMessage(), ResponseStatusCodeConst.INTERNAL_SERVER_ERROR, null);
        }
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> getUserByUsername(String username) {
        com.greenwich.comp1640.model.User user = userDao.findByUsername(username);

        if (user == null) {
            log.error(String.format("Can not find user with username: %s", username));
            return responseFactory.fail(String.format("Can not find user with username: %s", username),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        return responseFactory.success(UserMapper.toDto(user));
    }
}
