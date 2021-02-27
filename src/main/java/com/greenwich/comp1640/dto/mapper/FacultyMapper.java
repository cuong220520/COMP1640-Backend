package com.greenwich.comp1640.dto.mapper;

import com.greenwich.comp1640.dto.request.faculty.CreateFacultyRequestDto;
import com.greenwich.comp1640.dto.request.faculty.UpdateFacultyRequestDto;
import com.greenwich.comp1640.dto.response.FacultyResponseDto;
import com.greenwich.comp1640.model.Faculty;
import com.greenwich.comp1640.model.User;

import java.util.ArrayList;
import java.util.List;

public class FacultyMapper {

    private FacultyMapper() {

    }

    public static FacultyResponseDto toDto(Faculty faculty) {
        FacultyResponseDto facultyResponseDto = new FacultyResponseDto();
        facultyResponseDto.setCode(faculty.getCode());
        facultyResponseDto.setName(faculty.getName());
        facultyResponseDto.setDescription(faculty.getDescription());
        facultyResponseDto.setCoordinatorId(faculty.getCoordinatorManager().getId());

        return facultyResponseDto;
    }

    public static List<FacultyResponseDto> toListDto(List<Faculty> facultyList) {
        List<FacultyResponseDto> facultyResponseDtoList = new ArrayList<>();

        for (Faculty faculty : facultyList) {
            facultyResponseDtoList.add(toDto(faculty));
        }

        return facultyResponseDtoList;
    }

    public static Faculty createFromDto(CreateFacultyRequestDto createFacultyRequestDto, User coordinatorManager) {
        Faculty faculty = new Faculty();

        faculty.setCode(createFacultyRequestDto.getCode());
        faculty.setName(createFacultyRequestDto.getName());
        faculty.setDescription(createFacultyRequestDto.getDescription());
        faculty.setCoordinatorManager(coordinatorManager);

        return faculty;
    }

    public static Faculty updateFromDto(Faculty faculty, UpdateFacultyRequestDto updateFacultyRequestDto, User coordinatorManager) {
        if (updateFacultyRequestDto.getName() != null) {
            faculty.setName(updateFacultyRequestDto.getName());
        }

        if (updateFacultyRequestDto.getDescription() != null) {
            faculty.setDescription(updateFacultyRequestDto.getDescription());
        }

        if (coordinatorManager != null) {
            faculty.setCoordinatorManager(coordinatorManager);
        }

        return faculty;
    }

}
