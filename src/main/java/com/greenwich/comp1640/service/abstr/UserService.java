package com.greenwich.comp1640.service.abstr;

import com.greenwich.comp1640.dto.request.user.AuthRequestDto;
import com.greenwich.comp1640.dto.request.user.SignupRequestDto;
import com.greenwich.comp1640.response.GeneralResponse;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<GeneralResponse<Object>> login(AuthRequestDto authRequestDto);

    ResponseEntity<GeneralResponse<Object>> refreshToken(Claims claims);

    ResponseEntity<GeneralResponse<Object>> loadUser();

    ResponseEntity<GeneralResponse<Object>> signup(SignupRequestDto signupRequestDto);

    ResponseEntity<GeneralResponse<Object>> getUserByUsername(String username);

}
