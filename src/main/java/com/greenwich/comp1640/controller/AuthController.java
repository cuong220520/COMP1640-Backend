package com.greenwich.comp1640.controller;

import com.greenwich.comp1640.dto.request.user.AuthRequestDto;
import com.greenwich.comp1640.dto.request.user.SignupRequestDto;
import com.greenwich.comp1640.response.GeneralResponse;
import com.greenwich.comp1640.service.abstr.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<GeneralResponse<Object>> login(@Valid @RequestBody AuthRequestDto authRequestDto) {
        return userService.login(authRequestDto);
    }

    @GetMapping(value = "/refresh-token")
    public ResponseEntity<GeneralResponse<Object>> refreshToken(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");

        return userService.refreshToken(claims);
    }

    @GetMapping(value = "/load-user")
    public ResponseEntity<GeneralResponse<Object>> loadUser() {
        return userService.loadUser();
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<GeneralResponse<Object>> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    @GetMapping(value = "/get-user")
    public ResponseEntity<GeneralResponse<Object>> getUser(@RequestParam("id") Long id) {
        return userService.getUser(id);
    }
}
