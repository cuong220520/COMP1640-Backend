package com.greenwich.comp1640.dto.mapper;

import com.greenwich.comp1640.dto.response.UserDetailsResponseDto;
import com.greenwich.comp1640.model.User;

public class UserMapper {

    private UserMapper() {

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

}
