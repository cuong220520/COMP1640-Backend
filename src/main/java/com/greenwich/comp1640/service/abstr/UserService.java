package com.greenwich.comp1640.service.abstr;

import com.greenwich.comp1640.dto.request.user.AuthRequestDto;
import com.greenwich.comp1640.dto.request.user.SignupRequestDto;
import com.greenwich.comp1640.response.GeneralResponse;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<GeneralResponse<Object>> login(AuthRequestDto authRequestDto);

    public ResponseEntity<GeneralResponse<Object>> refreshToken(Claims claims);

    public ResponseEntity<GeneralResponse<Object>> loadUser();

    public ResponseEntity<GeneralResponse<Object>> signup(SignupRequestDto signupRequestDto);

}
