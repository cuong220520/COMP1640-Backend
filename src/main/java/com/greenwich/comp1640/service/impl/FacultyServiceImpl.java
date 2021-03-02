package com.greenwich.comp1640.service.impl;

import com.greenwich.comp1640.dao.FacultyDao;
import com.greenwich.comp1640.dao.UserDao;
import com.greenwich.comp1640.dto.mapper.FacultyMapper;
import com.greenwich.comp1640.dto.request.faculty.CreateFacultyRequestDto;
import com.greenwich.comp1640.dto.request.faculty.UpdateFacultyRequestDto;
import com.greenwich.comp1640.dto.response.FacultyResponseDto;
import com.greenwich.comp1640.model.Faculty;
import com.greenwich.comp1640.model.User;
import com.greenwich.comp1640.response.GeneralResponse;
import com.greenwich.comp1640.response.ResponseFactory;
import com.greenwich.comp1640.service.abstr.FacultyService;
import com.greenwich.comp1640.util.constant.ResponseStatusCodeConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    FacultyDao facultyDao;

    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<GeneralResponse<Object>> getFacultyList() {
        List<Faculty> facultyList = facultyDao.findAllFaculty();

        List<FacultyResponseDto> facultyResponseDtoList = FacultyMapper.toListDto(facultyList);

        return responseFactory.success(facultyResponseDtoList);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> createFaculty(CreateFacultyRequestDto createFacultyRequestDto) {
        Faculty faculty = facultyDao.findByCode(createFacultyRequestDto.getCode());

        if (faculty != null) {
            log.error(String.format("Duplicated faculty with code: %s", createFacultyRequestDto.getCode()));
            return responseFactory.fail(String.format("Duplicated faculty with code: %s", createFacultyRequestDto.getCode()),
                    ResponseStatusCodeConst.DUPLICATE_ERROR,
                    null);
        }

        User coordinatorManager = userDao.findByUsername(createFacultyRequestDto.getCoordinatorUsername());

        if (coordinatorManager == null) {
            log.error(String.format("Can not find user with username: %s", createFacultyRequestDto.getCoordinatorUsername()));
            return responseFactory.fail(String.format("Can not find user with username: %s", createFacultyRequestDto.getCoordinatorUsername()),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        Faculty newFaculty = FacultyMapper.createFromDto(createFacultyRequestDto, coordinatorManager);

        facultyDao.saveFaculty(newFaculty);

        return responseFactory.success(FacultyMapper.toDto(newFaculty));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> updateFaculty(String code, UpdateFacultyRequestDto updateFacultyRequestDto) {
        Faculty faculty = facultyDao.findByCode(code);

        if (faculty == null) {
            log.error(String.format("Can not find faculty with faculty code: %s", code));
            return responseFactory.fail(String.format("Can not find faculty with faculty code: %s", code),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        User coordinatorManager = userDao.findByUsername(updateFacultyRequestDto.getCoordinatorUsername());

        if (coordinatorManager == null) {
            log.error(String.format("Can not find user with username: %s", updateFacultyRequestDto.getCoordinatorUsername()));
            return responseFactory.fail(String.format("Can not find user with username: %s", updateFacultyRequestDto.getCoordinatorUsername()),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        Faculty updatedFaculty = FacultyMapper.updateFromDto(faculty, updateFacultyRequestDto, coordinatorManager);

        facultyDao.saveFaculty(updatedFaculty);

        return responseFactory.success(FacultyMapper.toDto(updatedFaculty));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> getFaculty(String code) {
        Faculty faculty = facultyDao.findByCode(code);

        if (faculty == null) {
            log.error(String.format("Can not find faculty with faculty code: %s", code));
            return responseFactory.fail(String.format("Can not find faculty with faculty code: %s", code),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        return responseFactory.success(FacultyMapper.toDto(faculty));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> getFacultyByCoordinatorUsername(String username) {
        Faculty faculty = facultyDao.findByCoordinatorUsername(username);

        if (faculty == null) {
            log.error(String.format("Can not find faculty with coordinator username: %s", username));
            return responseFactory.fail(String.format("Can not find faculty with coordinator username: %s", username),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        return responseFactory.success(FacultyMapper.toDto(faculty));
    }
}
