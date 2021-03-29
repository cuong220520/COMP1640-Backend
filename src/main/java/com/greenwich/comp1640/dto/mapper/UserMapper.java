package com.greenwich.comp1640.dto.mapper;

import com.greenwich.comp1640.dto.request.user.SignupRequestDto;
import com.greenwich.comp1640.dto.response.UserDetailsResponseDto;
import com.greenwich.comp1640.model.Faculty;
import com.greenwich.comp1640.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    private UserMapper() {

    }

    public static User createFromDto(SignupRequestDto signupRequestDto, Faculty faculty) {
        User user = new User();

        user.setUsername(signupRequestDto.getUsername());
        user.setFirstName(signupRequestDto.getFirstName());
        user.setLastName(signupRequestDto.getLastName());
        user.setDateOfBirth(signupRequestDto.getDateOfBirth());
        user.setFaculty(faculty);
        user.setPhoneNumber(signupRequestDto.getPhoneNumber());
        user.setEmail(signupRequestDto.getEmail());

        return user;
    }

    public static UserDetailsResponseDto toDto(User user) {
        UserDetailsResponseDto userDetailsResponseDto = new UserDetailsResponseDto();

        userDetailsResponseDto.setId(user.getId());
        userDetailsResponseDto.setFirstName(user.getFirstName());
        userDetailsResponseDto.setLastName(user.getLastName());
        userDetailsResponseDto.setDateOfBirth(user.getDateOfBirth().toInstant().getEpochSecond());
        userDetailsResponseDto.setPhoneNumber(user.getPhoneNumber());
        userDetailsResponseDto.setFacultyCode(user.getFaculty().getCode());
        userDetailsResponseDto.setEmail(user.getEmail());

        return userDetailsResponseDto;
    }

    public static List<UserDetailsResponseDto> toListDto(List<User> users) {
        List<UserDetailsResponseDto> userDetailsResponseDtoList = new ArrayList<>();
        for (User user : users) {
            userDetailsResponseDtoList.add(toDto(user));
        }

        return userDetailsResponseDtoList;
    }

}
